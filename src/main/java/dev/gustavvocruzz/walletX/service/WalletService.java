package dev.gustavvocruzz.walletX.service;

import dev.gustavvocruzz.walletX.entity.User;
import dev.gustavvocruzz.walletX.entity.Wallet;
import dev.gustavvocruzz.walletX.repositories.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository repository;


    public Wallet createWallet(User user ){
        var wallet = Wallet.builder()
                .user(user)
                .balance(BigDecimal.ZERO)
                .blockedBalance(BigDecimal.ZERO)
                .currency("BRL")
                .build();

        user.setWallet(wallet);

        return repository.save(wallet);
    }
}
