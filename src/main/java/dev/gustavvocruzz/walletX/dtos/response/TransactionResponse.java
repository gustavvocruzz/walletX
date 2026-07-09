package dev.gustavvocruzz.walletX.dtos.response;

import dev.gustavvocruzz.walletX.entity.TransactionStatus;
import dev.gustavvocruzz.walletX.entity.Wallet;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionResponse(
        UUID id,
        UUID senderWalletId,
        UUID receiverWalletId,
        BigDecimal amount,
        TransactionStatus status,
        String description,
        LocalDateTime createdAt
) {
}
