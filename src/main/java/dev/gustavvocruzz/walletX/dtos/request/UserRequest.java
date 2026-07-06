package dev.gustavvocruzz.walletX.dtos.request;
import dev.gustavvocruzz.walletX.entity.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "User creation request")
public record UserRequest(
        @NotBlank(message = "First name is required")
        @Size(max = 150, message = "First name must be at most 150 characters")
        @Schema(description = "User's first name", example = "John", maxLength = 150)
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(max = 150, message = "Last name must be at most 150 characters")
        @Schema(description = "User's last name", example = "Doe", maxLength = 150)
        String lastName,

        @NotNull(message = "Gender must contain only one character")
        @Schema(description = "User's gender", example = "M")
        Gender gender,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Schema(description = "User's email address", example = "john.doe@example.com")
        String email,

        @NotNull(message = "Birthday is required")
        @Schema(description = "User's date of birth", example = "1990-01-15")
        LocalDate birthday,

        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
        @Schema(description = "User's password (min 8 characters)", example = "SecurePassword123!")
        String password,

        @NotBlank(message = "Phone is required")
        @Size(max = 30, message = "Phone must be at most 30 characters")
        @Schema(description = "User's phone number", example = "+55 11 98765-4321", maxLength = 30)
        String phone,

        @NotBlank(message = "Document is required")
        @Size(max = 14, message = "Document must be at most 14 characters")
        @Schema(description = "User's document (CPF/CNPJ)", example = "12345678900", maxLength = 14)
        String document
) {}
