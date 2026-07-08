package dev.gustavvocruzz.walletX.mapper;

import dev.gustavvocruzz.walletX.dtos.response.WalletResponse;
import dev.gustavvocruzz.walletX.entity.Wallet;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper {

    public WalletResponse toResponse(Wallet wallet){
        return new WalletResponse(
                wallet.getId(),
                wallet.getBalance(),
                wallet.getBlockedBalance(),
                wallet.getCurrency(),
                wallet.getUser().getId(),
                wallet.getCreatedAt());

    }
}
