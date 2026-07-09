package dev.gustavvocruzz.walletX.service;

import dev.gustavvocruzz.walletX.dtos.request.TransactionRequest;
import dev.gustavvocruzz.walletX.entity.Transaction;
import dev.gustavvocruzz.walletX.entity.TransactionStatus;
import dev.gustavvocruzz.walletX.entity.Wallet;
import dev.gustavvocruzz.walletX.entity.WalletStatus;
import dev.gustavvocruzz.walletX.exceptions.InsufficientBalanceException;
import dev.gustavvocruzz.walletX.exceptions.SameWalletTransferException;
import dev.gustavvocruzz.walletX.exceptions.WalletBlockedException;
import dev.gustavvocruzz.walletX.exceptions.WalletNotFoundException;
import dev.gustavvocruzz.walletX.mapper.TransactionMapper;
import dev.gustavvocruzz.walletX.repositories.TransactionRepository;
import dev.gustavvocruzz.walletX.repositories.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository repository;
    private final TransactionMapper mapper;
    private final WalletRepository walletRepository;
    private final TransactionAuditService auditService;


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
       } catch (RuntimeException ex) {
           transaction.setStatus(TransactionStatus.FAILED);
           auditService.persistFailedTransaction(transaction); //transação separada por conta do ROLLBACK
           throw ex;
       }
        return repository.save(transaction);

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

}
