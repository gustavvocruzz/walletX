package dev.gustavvocruzz.walletX.dtos.request;
import java.time.LocalDate;

public record UserRequest(
        String firstName,
        String lastName,
        String gender,
        String email,
        LocalDate birthday,
        String password,
        String phone,
        String document
) {}
