package dev.gustavvocruzz.walletX.service;

import dev.gustavvocruzz.walletX.entity.Transaction;
import dev.gustavvocruzz.walletX.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionAuditService {
    private final TransactionRepository repository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void persistFailedTransaction(Transaction transaction) {
        repository.save(transaction);
    }
}
