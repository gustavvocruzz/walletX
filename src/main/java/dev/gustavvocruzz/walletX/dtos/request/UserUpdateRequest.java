package dev.gustavvocruzz.walletX.dtos.request;

import dev.gustavvocruzz.walletX.entity.Gender;

import java.time.LocalDate;

public record UserUpdateRequest(
        String firstName,
        String lastName,
        Gender gender,
        LocalDate birthday,
        String phone
) {}

//senha e email vao ter um endpoint próprio
