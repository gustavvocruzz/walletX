package dev.gustavvocruzz.walletX.controller;

import dev.gustavvocruzz.walletX.dtos.request.TransactionRequest;
import dev.gustavvocruzz.walletX.dtos.response.TransactionResponse;
import dev.gustavvocruzz.walletX.mapper.TransactionMapper;
import dev.gustavvocruzz.walletX.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionMapper mapper;
    private final TransactionService transactionService;

    @GetMapping
    public Page<TransactionResponse> getAllTransactions(Pageable pageable){
        return transactionService.getAllTransactions(pageable)
                .map(mapper::toResponse);
    }

    @GetMapping("/{id}")
    public TransactionResponse getTransactionById(@PathVariable UUID id){
        var transaction = transactionService.getTransactionById(id);
        return mapper.toResponse(transaction);
    }

    @GetMapping("/wallet/{walletId}")
    public Page<TransactionResponse> getTransactionsByWalletId(Pageable pageable, @PathVariable UUID walletId){
        return transactionService.getTransactionsByWalletId(walletId, pageable)
                .map(mapper::toResponse);
    }

    @GetMapping("/user/{userId}")
    public Page<TransactionResponse> getTransactionsByUserId(Pageable pageable, @PathVariable UUID userId) {
        return transactionService.getTransactionsByUserId(userId, pageable)
                .map(mapper::toResponse);
    }

    @PostMapping
    public TransactionResponse transfer(@RequestBody @Valid TransactionRequest request) {
        var transaction = transactionService.transfer(request);
        return mapper.toResponse(transaction);
    }



}
