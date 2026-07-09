package dev.gustavvocruzz.walletX.exceptions;

public class SameWalletTransferException extends RuntimeException {
    public SameWalletTransferException(String message) {
        super(message);
    }
}
