package dev.gustavvocruzz.walletX.dtos.response;

import dev.gustavvocruzz.walletX.entity.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record WalletResponse(
        UUID id,
        BigDecimal balance,
        BigDecimal blockedBalance,
        Currency currency,
        UUID userId,
        LocalDateTime createdAt
) {}
