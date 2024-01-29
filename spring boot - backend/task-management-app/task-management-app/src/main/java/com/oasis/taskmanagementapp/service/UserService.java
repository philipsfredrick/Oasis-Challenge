package com.oasis.taskmanagementapp.service;

import com.oasis.taskmanagementapp.converters.UserToResourceConverter;
import com.oasis.taskmanagementapp.dto.request.AuthenticationRequest;
import com.oasis.taskmanagementapp.dto.request.UserRequest;
import com.oasis.taskmanagementapp.dto.response.AuthenticationResponse;
import com.oasis.taskmanagementapp.dto.response.UserResource;
import com.oasis.taskmanagementapp.entities.Token;
import com.oasis.taskmanagementapp.entities.User;
import com.oasis.taskmanagementapp.exception.OasisException;
import com.oasis.taskmanagementapp.exception.UnAuthorizedException;
import com.oasis.taskmanagementapp.repository.TokenRepository;
import com.oasis.taskmanagementapp.repository.UserRepository;
import com.oasis.taskmanagementapp.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.oasis.taskmanagementapp.dto.ErrorCode.INVALID_CREDENTIALS;
import static com.oasis.taskmanagementapp.entities.enums.Role.USER;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserToResourceConverter userToResourceConverter;

    private final CredentialService credentialService;

    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Transactional
    public void registerUser(UserRequest request) {
        try {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(USER)
                .build();
        userRepository.save(user);
        } catch (Exception e) {
            log.error(format("An error occurred while creating user account, please contact support. " +
                    "Possible reasons: %s", e.getLocalizedMessage()));
            throw new OasisException("An error occurred while creating user account, please contact support",
                    INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public AuthenticationResponse loginUser(AuthenticationRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            var user = (User) authentication.getPrincipal();
            var token = jwtService.generateJwtToken(user);
            revokeAllUserTokens(user);
            persistUserToken(user, token);

            return new AuthenticationResponse(token);
        } catch (Exception e) {
            log.error(format("An error occurred while authenticating login request, please contact support. " +
                    "Possible reasons: %s", e.getLocalizedMessage()));
            throw new UnAuthorizedException("Invalid email and/or password", INVALID_CREDENTIALS);
        }
    }

    @Transactional
    public UserResource updateUserProfile(UserRequest request) {
        try {

            var user = userRepository.findById(credentialService.getUserAccount().getId())
                    .orElseThrow(()-> new UsernameNotFoundException("User not found"));
            user.setEmail(request.getEmail());
            user.setName(request.getName());
            userRepository.save(user);
            return userToResourceConverter.convert(user);
        } catch (Exception e) {
         log.error(format("An error occurred updating your profile, please contact support, " +
                 "Possible reasons: %s", e.getLocalizedMessage()));
         throw new OasisException("Error with profile update", INTERNAL_SERVER_ERROR);
        }
    }

    public UserResource viewUser() {
        var user = userRepository.findById(credentialService.getUserAccount().getId())
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return userToResourceConverter.convert(user);
    }

    public void deleteUser(Long userId) {
        var user = userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        userRepository.deleteById(user.getId());
    }


    private void persistUserToken(User user, String token) {
        tokenRepository.save(new Token(token, user));
    }

    private void revokeAllUserTokens(User user) {
        tokenRepository.invalidateAllUserTokens(user.getId());
    }
}
