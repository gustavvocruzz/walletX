package dev.gustavvocruzz.walletX.exceptions;

import java.util.UUID;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(UUID id) {
        super("Transaction with ID " + id + " was not found.");
    }
}
