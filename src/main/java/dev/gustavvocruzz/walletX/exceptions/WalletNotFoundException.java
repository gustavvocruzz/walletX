package dev.gustavvocruzz.walletX.exceptions;

import java.util.UUID;

public class WalletNotFoundException extends RuntimeException {
    public WalletNotFoundException(UUID id) {
        super("Wallet with ID " + id + " was not found.");
    }}
