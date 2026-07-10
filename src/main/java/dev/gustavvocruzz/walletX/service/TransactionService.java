package dev.gustavvocruzz.walletX.service;

import dev.gustavvocruzz.walletX.dtos.request.TransactionRequest;
import dev.gustavvocruzz.walletX.entity.Transaction;
import dev.gustavvocruzz.walletX.entity.TransactionStatus;
import dev.gustavvocruzz.walletX.entity.Wallet;
import dev.gustavvocruzz.walletX.entity.WalletStatus;
import dev.gustavvocruzz.walletX.exceptions.*;
import dev.gustavvocruzz.walletX.mapper.TransactionMapper;
import dev.gustavvocruzz.walletX.repositories.TransactionRepository;
import dev.gustavvocruzz.walletX.repositories.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository repository;
    private final TransactionMapper mapper;
    private final WalletRepository walletRepository;
    private final TransactionAuditService auditService;
    private final WalletService walletService;


    @Transactional
    public Transaction transfer(TransactionRequest request){
        var sender = walletRepository.findById(request.senderWalletId())
                .orElseThrow(()-> new WalletNotFoundException(request.senderWalletId()));
        var receiver = walletRepository.findById(request.receiverWalletId())
                .orElseThrow(()-> new WalletNotFoundException(request.receiverWalletId()));


       var transaction = mapper.toEntity(request,sender,receiver);
       transaction.setStatus(TransactionStatus.PENDING);

       try {
           validateTransfer(sender,receiver,request.amount());
           sender.debit(transaction.getAmount());
           receiver.credit(transaction.getAmount());

           transaction.setStatus(TransactionStatus.COMPLETED);

           walletRepository.save(sender);
           walletRepository.save(receiver);
           return repository.save(transaction);
       } catch (RuntimeException ex) {
           transaction.setStatus(TransactionStatus.FAILED);
           auditService.persistFailedTransaction(transaction); //transação separada por conta do ROLLBACK
           throw ex;
       }

    }

    public void validateTransfer(Wallet sender, Wallet receiver, BigDecimal amount){

        if (sender.getId().equals(receiver.getId())) {
            throw new SameWalletTransferException("Cannot transfer funds to the same wallet.");
        }

        if(sender.getBalance().compareTo(amount) <0){
            throw new InsufficientBalanceException("Insufficient funds to complete this transfer.");
        }

        if(sender.getWalletStatus() == WalletStatus.BLOCKED){
            throw new WalletBlockedException("The sender wallet is blocked.");
        }
        if(receiver.getWalletStatus() == WalletStatus.BLOCKED){
            throw new WalletBlockedException("The receiver wallet is blocked.");
        }
    }

    //Get
    public List<Transaction> getAllTransactions(){
        return repository.findAll();
    }

    public Transaction getTransactionById(UUID uuid){
        return repository.findById(uuid)
                .orElseThrow(()-> new TransactionNotFoundException(uuid));
    }

    public List<Transaction> getTransactionsByWalletId(UUID id){
       walletService.getWalletById(id);
        return repository.findAllByWalletId(id);
    }

    public List<Transaction> getTransactionsByUserId(UUID id){
            var wallet = walletService.getWalletByUserId(id);
            return repository.findAllByWalletId(wallet.getId());
    }


}
