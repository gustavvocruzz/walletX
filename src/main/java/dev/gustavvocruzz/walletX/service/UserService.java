package dev.gustavvocruzz.walletX.service;

import dev.gustavvocruzz.walletX.entity.User;
import dev.gustavvocruzz.walletX.exceptions.UserNotFoundException;
import dev.gustavvocruzz.walletX.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository repository;
    private final WalletService walletService;
    private final PasswordEncoder passwordEncoder;


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
            throw new RuntimeException("There is already a registered user with this document.");
        }

        if(repository.existsByEmail(user.getEmail())){
            throw new RuntimeException("There is already a registered user with this e-mail.");
        }

        if(repository.existsByPhone(user.getPhone())){
            throw new RuntimeException("There is already a registered user with this phone.");
        }
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User getUserById(UUID id){
        return repository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
    }


}
