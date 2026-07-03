package dev.gustavvocruzz.walletX.service;

import dev.gustavvocruzz.walletX.entity.User;
import dev.gustavvocruzz.walletX.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository repository;
    private final WalletService walletService;
    private final PasswordEncoder passwordEncoder;


    //TODO: CRIAR USER
    //TODO: ATUALIZAR USER
    //TODO: VALIDATIONS

    @Transactional
    public User createUser(User user){

        validateUser(user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = repository.save(user);

        walletService.createWallet(savedUser);

        return savedUser;
    }


    //Valida o usuario
    //TODO: Criar tratamento de excecoes

    private void validateUser(User user){
        if(repository.existsByDocument(user.getDocument())){
            throw new RuntimeException("Já existe um usuario cadastrado com esse documento.");
        }

        if(repository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Já existe um usuario cadastrado com esse e-mail.");
        }

        if(repository.existsByPhone(user.getPhone())){
            throw new RuntimeException("Já existe um usuario cadastrado com esse telefone.");
        }

    }
}
