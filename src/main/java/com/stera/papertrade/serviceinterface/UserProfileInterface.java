package com.stera.papertrade.serviceinterface;

import java.util.Optional;

import com.stera.papertrade.model.Auth.AuthenticationRequest;
import com.stera.papertrade.model.Auth.AuthenticationResponse;
import org.springframework.http.HttpStatus;

import com.stera.papertrade.model.TradeProfile;
import com.stera.papertrade.model.User;

public interface UserProfileInterface {
    boolean checkUserProfile(String entityId);
    Optional<User> getUserByID(String entityId);
    AuthenticationResponse registerUser(User entity);
    HttpStatus updateUserWithNewTradeProfileId(TradeProfile entity);
    Optional<User> loadUserByUserEmail(String userEmail);
    Optional<User> loadUserByUserID(String userEmail);

}
