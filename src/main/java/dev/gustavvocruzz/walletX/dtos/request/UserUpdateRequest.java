package dev.gustavvocruzz.walletX.dtos.request;

import dev.gustavvocruzz.walletX.entity.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserUpdateRequest(
        @NotBlank(message = "First name is required")
        @Size(max = 150, message = "First name must be at most 150 characters")
        String firstName,
        @NotBlank(message = "Last name is required")
        @Size(max = 150, message = "Last name must be at most 150 characters")
        String lastName,
        @NotNull(message = "Gender is required")
        Gender gender,
        @NotNull(message = "Birthday is required")
        @Past(message = "Birthday must be in the past")
        LocalDate birthday,
        @NotBlank(message = "Phone is required")
        @Size(max = 30, message = "Phone must be at most 30 characters")
        String phone
) {}

//senha e email vao ter um endpoint próprio
