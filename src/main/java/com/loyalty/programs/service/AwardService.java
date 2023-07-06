package com.loyalty.programs.service;

import com.loyalty.programs.model.*;
import com.loyalty.programs.repository.AwardRepository;
import com.loyalty.programs.repository.TransactionRepository;
import com.loyalty.programs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AwardService {

    @Autowired
    AwardRepository awardRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public Response addAward(AddAwardRequest request) {
        Award award = new Award();
        award.setId(String.valueOf(UUID.randomUUID()));
        award.setName(request.getName());
        award.setPoint(request.getPoint());
        award.setDescription(request.getDescription());
        award.setUserId(request.getUserId());
        Transaction transaction = new Transaction();
        transaction.setPoint(request.getPoint());
        transaction.setType("credit");
        transaction.setId(String.valueOf(UUID.randomUUID()));
        try {
            Optional<User> user = userRepository.findById(request.getUserId());
            if (user.isPresent()) {
                user.get().setTotal_points(user.get().getTotal_points() + request.getPoint());
                transaction.setUserId(user.get().getId());
                transactionRepository.save(transaction);
                awardRepository.save(award);
                userRepository.save(user.get());
            } else {
                return Response.builder().status(HttpStatus.BAD_REQUEST).description("user not found").response(null).build();
            }
            return Response.builder().status(HttpStatus.OK).description("award saved success").response(award).build();
        } catch (Exception e) {
            return Response.builder().status(HttpStatus.SERVICE_UNAVAILABLE).description("award saved failure").response(null).build();
        }
    }

    public Response getAllAwards(String userId) {
        try {
            List<Award> awardList = awardRepository.findAllByUserId(userId);
            if (awardList.size() == 0) {
                return Response.builder().status(HttpStatus.OK).description("no award found for the user").response(null).build();
            } else {
                return Response.builder().status(HttpStatus.OK).description("awards list").response(awardList).build();
            }
        } catch (Exception e) {
            return Response.builder().status(HttpStatus.SERVICE_UNAVAILABLE).description("failed to get award for the user").response(null).build();
        }
    }
}
