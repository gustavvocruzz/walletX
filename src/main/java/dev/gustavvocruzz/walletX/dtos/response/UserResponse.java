package dev.gustavvocruzz.walletX.dtos.response;

import dev.gustavvocruzz.walletX.entity.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "User response data")
public record UserResponse(
        @Schema(description = "User's unique identifier", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,
        
        @Schema(description = "User's first name", example = "John")
        String firstName,
        
        @Schema(description = "User's last name", example = "Doe")
        String lastName,
        
        @Schema(description = "User's email address", example = "john.doe@example.com")
        String email,
        
        @Schema(description = "User's phone number", example = "+55 11 98765-4321")
        String phone,
        
        @Schema(description = "User's document identifier", example = "12345678900")
        String document,
        
        @Schema(description = "Current status of the user account", example = "ACTIVE")
        UserStatus status,
        
        @Schema(description = "Associated wallet identifier", example = "550e8400-e29b-41d4-a716-446655440001")
        UUID walletId,
        
        @Schema(description = "Account creation timestamp", example = "2024-01-15T10:30:00")
        LocalDateTime createdAt
) {}