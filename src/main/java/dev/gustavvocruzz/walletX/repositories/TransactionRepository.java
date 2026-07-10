package dev.gustavvocruzz.walletX.repositories;

import dev.gustavvocruzz.walletX.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    @Query("""
    SELECT t
    FROM Transaction t
    WHERE t.senderWallet.id = :walletId
       OR t.receiverWallet.id = :walletId
    ORDER BY t.createdAt DESC
""")
    List<Transaction> findAllByWalletId(UUID walletId);
}
