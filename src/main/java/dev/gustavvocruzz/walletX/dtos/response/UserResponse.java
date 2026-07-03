package dev.gustavvocruzz.walletX.dtos.response;

import dev.gustavvocruzz.walletX.entity.UserStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String document,
        UserStatus status,
        UUID walletId,
        LocalDateTime createdAt
) {}