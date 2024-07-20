package com.stera.papertrade.service.Auth;

import com.stera.papertrade.Config.jwtInterface;
import com.stera.papertrade.model.Auth.AuthenticationResponse;
import com.stera.papertrade.model.User;
import com.stera.papertrade.service.TradeAssetService;
import com.stera.papertrade.serviceinterface.Auth.AuthenticationInterface;
import com.stera.papertrade.serviceinterface.UserProfileInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements AuthenticationInterface {
    private static final Logger LOG = LogManager.getLogger(TradeAssetService.class);
    @Autowired
    private jwtInterface jstService;
    @Autowired
    private UserProfileInterface userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse authenticateUser(User entity) {
        LOG.debug("AuthenticationService : User Entity : " + entity.toString());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        entity.getMail(),
                        entity.getPassword()
                )
                );
        Optional<User> userDetails = userService.loadUserByUserEmail(entity.getMail());
        if(!userDetails.isPresent()){
            LOG.error("AuthenticationService : loadUserByUserEmail : " + userDetails.toString());
            throw new UsernameNotFoundException("No User With Provided Email ID");
        }
        String jwtToken = jstService.generateToken(entity);
        return AuthenticationResponse.builder().token(jwtToken).status(200).build();
    }
}
