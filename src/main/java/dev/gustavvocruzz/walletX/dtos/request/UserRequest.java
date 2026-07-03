package dev.gustavvocruzz.walletX.dtos.request;
import dev.gustavvocruzz.walletX.entity.Gender;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserRequest(
        @NotBlank(message = "First name is required")
        @Size(max = 150, message = "First name must be at most 150 characters")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(max = 150, message = "Last name must be at most 150 characters")
        String lastName,


        //o recomendado é usar um ENUM aqui no genero

        @Pattern(regexp = "M|F", message = "Gender must be M or F")
        @Size(max = 1, message = "Gender must contain only one character")
        Gender gender,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotNull(message = "Birthday is required")
        LocalDate birthday,

        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
        String password,


        @NotBlank(message = "Phone is required")
        @Size(max = 30, message = "Phone must be at most 30 characters")
        String phone,


        @NotBlank(message = "Document is required")
        @Size(max = 14, message = "Document must be at most 14 characters")
        String document
) {}
