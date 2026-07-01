package dev.gustavvocruzz.walletX.entity.dtos.response;

import dev.gustavvocruzz.walletX.entity.UserStatus;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String document,
        UserStatus status
) {}