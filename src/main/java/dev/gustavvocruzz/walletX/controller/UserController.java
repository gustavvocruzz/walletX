package dev.gustavvocruzz.walletX.controller;

import dev.gustavvocruzz.walletX.dtos.request.UserRequest;
import dev.gustavvocruzz.walletX.dtos.request.UserUpdateRequest;
import dev.gustavvocruzz.walletX.dtos.response.UserResponse;
import dev.gustavvocruzz.walletX.entity.User;
import dev.gustavvocruzz.walletX.mapper.UserMapper;
import dev.gustavvocruzz.walletX.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "User management endpoints")
public class UserController {

    private final UserService userService;

    private final UserMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new user", description = "Creates a new user account in the system with provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    public UserResponse createUser(@RequestBody @Valid UserRequest userRequest){
        User user = mapper.toEntity(userRequest);
        User savedUser = userService.createUser(user);
        return mapper.toResponse(savedUser);
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieves a list of all users in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of users retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class)))
    })
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieves a specific user by their unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found and retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public UserResponse getUserById(
            @Parameter(description = "User unique identifier", required = true, example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable("id") UUID uuid){
        var user = userService.getUserById(uuid);
        return mapper.toResponse(user);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable UUID id, @RequestBody @Valid UserUpdateRequest userRequest){
        User userUpdate = userService.updateUser(id,userRequest);
        return mapper.toResponse(userUpdate);
    }

    @PatchMapping("/deactivate/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateUser(@PathVariable UUID id) {
        userService.deactivateUser(id);
    }
}
