package dev.gustavvocruzz.walletX.mapper;

import dev.gustavvocruzz.walletX.dtos.request.TransactionRequest;
import dev.gustavvocruzz.walletX.dtos.response.TransactionResponse;
import dev.gustavvocruzz.walletX.entity.Transaction;
import dev.gustavvocruzz.walletX.entity.TransactionStatus;
import dev.gustavvocruzz.walletX.entity.Wallet;
import org.springframework.stereotype.Component;


@Component
public class TransactionMapper {

    public Transaction toEntity(
            TransactionRequest request,
            Wallet senderWallet,
            Wallet receiverWallet
    ) {

        var entity = new Transaction();

        entity.setSenderWallet(senderWallet);
        entity.setReceiverWallet(receiverWallet);
        entity.setAmount(request.amount());
        entity.setDescription(request.description());
        entity.setStatus(TransactionStatus.PENDING);

        return entity;
    }

    public TransactionResponse toResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getSenderWallet().getId(),
                transaction.getReceiverWallet().getId(),
                transaction.getAmount(),
                transaction.getStatus(),
                transaction.getDescription(),
                transaction.getCreatedAt()
        );
    }
}