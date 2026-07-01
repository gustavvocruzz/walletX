package dev.gustavvocruzz.walletX.repositories;

import dev.gustavvocruzz.walletX.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
}
