package dev.gustavvocruzz.walletX.repositories;

import dev.gustavvocruzz.walletX.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository //Opcional essa annotation
public interface UserRepository extends JpaRepository<User, UUID> {

}
