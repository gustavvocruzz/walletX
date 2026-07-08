package dev.gustavvocruzz.walletX.service;

import dev.gustavvocruzz.walletX.entity.Currency;
import dev.gustavvocruzz.walletX.entity.User;
import dev.gustavvocruzz.walletX.entity.Wallet;
import dev.gustavvocruzz.walletX.entity.WalletStatus;
import dev.gustavvocruzz.walletX.exceptions.WalletNotFoundException;
import dev.gustavvocruzz.walletX.repositories.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository repository;


    public Wallet createWallet(User user ){
        var wallet = Wallet.builder()
                .user(user)
                .balance(BigDecimal.ZERO)
                .blockedBalance(BigDecimal.ZERO)
                .currency(Currency.BRL)
                .build();

        user.setWallet(wallet);

        return repository.save(wallet);
    }

    public List<Wallet> getAllWallets(){
        return repository.findAll();
    }

    public Wallet getWalletById(UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new WalletNotFoundException(id));
    }

    public Wallet getWalletByUserId(UUID userId){
        return repository.findByUserId(userId)
                .orElseThrow(()->new WalletNotFoundException(
                        "Wallet for user ID: " + userId +" was not found.")
                );
    }


}
