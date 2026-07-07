package dev.gustavvocruzz.walletX.mapper;

import dev.gustavvocruzz.walletX.dtos.request.UserRequest;
import dev.gustavvocruzz.walletX.dtos.request.UserUpdateRequest;
import dev.gustavvocruzz.walletX.dtos.response.UserResponse;
import dev.gustavvocruzz.walletX.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserMapper {

    public User toEntity(UserRequest request){

        var entity = new User();
        entity.setFirstName(request.firstName());
        entity.setLastName(request.lastName());
        entity.setGender(request.gender());
        entity.setEmail(request.email());
        entity.setBirthday(request.birthday());
        entity.setPassword(request.password());
        entity.setPhone(request.phone());
        entity.setDocument(request.document());


        return entity;
    }

    public UserResponse toResponse(User user){
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getDocument(),
                user.getStatus(),
                user.getWallet().getId(),
                user.getCreatedAt()
        );

    }

    public void updateEntity(User user, UserUpdateRequest request) {
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setGender(request.gender());
        user.setBirthday(request.birthday());
        user.setPhone(request.phone());
    }
}
