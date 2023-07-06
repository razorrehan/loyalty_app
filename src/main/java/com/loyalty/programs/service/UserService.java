package com.loyalty.programs.service;

import com.loyalty.programs.model.CreateUserRequest;
import com.loyalty.programs.model.Response;
import com.loyalty.programs.model.User;
import com.loyalty.programs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Response saveUser(CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setContact(request.getContact());
        user.setId(String.valueOf(UUID.randomUUID()));
        user.setTotal_points(0L);
        try {
            userRepository.save(user);
            return Response.builder().status(HttpStatus.OK).description("user saved success").response(null).build();
        } catch (Exception e) {
            return Response.builder().status(HttpStatus.SERVICE_UNAVAILABLE).description("user saved failure").response(null).build();
        }
    }

    public Response getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            return Response.builder().status(HttpStatus.OK).description("user get success").response(users).build();
        } catch (Exception e) {
            return Response.builder().status(HttpStatus.SERVICE_UNAVAILABLE).description("failed to get users").response(null).build();
        }
    }

    public Response getUserById(String userId) {
        try {
            Optional<User> users = userRepository.findById(userId);
            if (users.isPresent()) {
                return Response.builder().status(HttpStatus.OK).description("user get success").response(users).build();
            }
            return Response.builder().status(HttpStatus.OK).description("user not found").response(null).build();
        } catch (Exception e) {
            return Response.builder().status(HttpStatus.SERVICE_UNAVAILABLE).description("failed to get users").response(null).build();
        }
    }


}
