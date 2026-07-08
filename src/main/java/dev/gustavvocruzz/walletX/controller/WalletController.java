package dev.gustavvocruzz.walletX.controller;

import dev.gustavvocruzz.walletX.dtos.response.WalletResponse;
import dev.gustavvocruzz.walletX.entity.Wallet;
import dev.gustavvocruzz.walletX.mapper.WalletMapper;
import dev.gustavvocruzz.walletX.repositories.WalletRepository;
import dev.gustavvocruzz.walletX.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;
    private final WalletMapper walletMapper;


    @GetMapping
    public List<WalletResponse> getAllWallets(){
        return walletService.getAllWallets()
                .stream()
                .map(walletMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public WalletResponse getWalletById(@PathVariable UUID id){
        var wallet = walletService.getWalletById(id);
        return walletMapper.toResponse(wallet);
    }

    @PatchMapping()
}
