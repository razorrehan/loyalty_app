package com.loyalty.programs.service;

import com.loyalty.programs.model.*;
import com.loyalty.programs.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public Response getAllTransactions(String userId) {
        try {
            List<Transaction> transactions = transactionRepository.findAllByUserId(userId);
            if (transactions.size() == 0) {
                return Response.builder().status(HttpStatus.OK).description("no transaction found for the user").response(null).build();
            } else {
                return Response.builder().status(HttpStatus.OK).description("transaction list").response(transactions).build();
            }
        } catch (Exception e) {
            return Response.builder().status(HttpStatus.SERVICE_UNAVAILABLE).description("failed to get transaction for the user").response(null).build();
        }
    }

}
