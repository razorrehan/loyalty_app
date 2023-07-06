package com.loyalty.programs.service;

import com.loyalty.programs.model.*;
import com.loyalty.programs.repository.GiftCardRepository;
import com.loyalty.programs.repository.TransactionRepository;
import com.loyalty.programs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GiftCardService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    GiftCardRepository giftCardRepository;

    @Autowired
    UserRepository userRepository;

    public Response redeemReward(RedeemRewardRequest request) {
        try {
            Optional<User> optionalUser = userRepository.findById(request.getUserId());
            User user = optionalUser.orElse(null);

            if (user == null) {
                return Response.builder().status(HttpStatus.NOT_FOUND).description("User not found").response(null).build();
            }

            if (user.getTotal_points() < request.getPointsValue()) {
                return Response.builder().status(HttpStatus.NOT_FOUND).description("Not enough points").response(null).build();
            }

            // Reduce user points
            user.setTotal_points(user.getTotal_points() - request.getPointsValue());
            userRepository.save(user);

            // Create and save the giftcard
            Giftcard giftcard = new Giftcard();
            giftcard.setId(String.valueOf(UUID.randomUUID()));
            giftcard.setCompanyName(request.getCompanyName());
            giftcard.setCouponCode(String.valueOf(UUID.randomUUID()));
            giftcard.setPurchasedAt(new Date());
            giftcard.setPoshvineGiftCardId(request.getPoshvineGiftCardId());
            giftcard.setUserId(request.getUserId());

            LocalDateTime expirationDateTime = LocalDateTime.now().plusMonths(6);
            Date expirationDate = Date.from(expirationDateTime.atZone(ZoneId.systemDefault()).toInstant());
            giftcard.setExpiredAt(expirationDate);

            giftCardRepository.save(giftcard);

            Transaction transaction = new Transaction();
            transaction.setType("debit");
            transaction.setPoint(request.getPointsValue());
            transaction.setId(String.valueOf(UUID.randomUUID()));
            transaction.setUserId(request.getUserId());
            transaction.setGiftCardId(giftcard.getId());
            transactionRepository.save(transaction);

            return Response.builder().status(HttpStatus.OK).description("Redemption successful").response(giftcard).build();
        } catch (Exception e) {
            return Response.builder().status(HttpStatus.SERVICE_UNAVAILABLE).description("Failed to redeem points").response(null).build();
        }
    }

    public Response getAllGiftCards(String userId) {
        try {
            List<Giftcard> giftcards = giftCardRepository.findAllByUserId(userId);
            if (giftcards.size() == 0) {
                return Response.builder().status(HttpStatus.OK).description("no giftcard found for the user").response(null).build();
            } else {
                return Response.builder().status(HttpStatus.OK).description("giftcard list").response(giftcards).build();
            }
        } catch (Exception e) {
            return Response.builder().status(HttpStatus.SERVICE_UNAVAILABLE).description("failed to get giftcard for the user").response(null).build();
        }
    }


}
