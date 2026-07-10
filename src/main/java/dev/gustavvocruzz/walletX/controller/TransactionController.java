package dev.gustavvocruzz.walletX.controller;

import dev.gustavvocruzz.walletX.dtos.request.TransactionRequest;
import dev.gustavvocruzz.walletX.dtos.response.TransactionResponse;
import dev.gustavvocruzz.walletX.mapper.TransactionMapper;
import dev.gustavvocruzz.walletX.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionMapper mapper;
    private final TransactionService transactionService;

    @GetMapping
    public List<TransactionResponse> getAllTransactions(){
        return transactionService.getAllTransactions()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public TransactionResponse getTransactionById(@PathVariable UUID id){
        var transaction = transactionService.getTransactionById(id);
        return mapper.toResponse(transaction);
    }

    @GetMapping("/wallet/{walletId}")
    public List<TransactionResponse> getTransactionsByWalletId(@PathVariable UUID walletId){
        return transactionService.getTransactionsByWalletId(walletId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }


    @GetMapping("/user/{userId}")
    public List<TransactionResponse> getTransactionsByUserId(@PathVariable UUID userId){
        return transactionService.getTransactionsByUserId(userId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @PostMapping
    public TransactionResponse transfer(@RequestBody @Valid TransactionRequest request) {
        var transaction = transactionService.transfer(request);
        return mapper.toResponse(transaction);
    }



}
