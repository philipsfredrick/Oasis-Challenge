package com.oasis.taskmanagementapp.service;

import com.oasis.taskmanagementapp.entities.User;
import com.oasis.taskmanagementapp.exception.OasisException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
@RequiredArgsConstructor
public class CredentialService {

    public User getUserAccount() {
        try {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
        } catch (Exception e) {
            throw new OasisException("Could not authenticate request", INTERNAL_SERVER_ERROR);
        }
    }
}
