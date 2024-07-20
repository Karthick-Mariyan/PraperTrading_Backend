package com.stera.papertrade.service;

import com.stera.papertrade.Config.jwtInterface;
import com.stera.papertrade.model.Auth.AuthenticationRequest;
import com.stera.papertrade.model.Auth.AuthenticationResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import com.stera.papertrade.model.TradeProfile;
import com.stera.papertrade.model.User;
import com.stera.papertrade.repository.UserRepository;
import com.stera.papertrade.serviceinterface.UserProfileInterface;


@Service
public class UserProfileService implements UserProfileInterface {
    private static final Logger LOG = LogManager.getLogger(UserProfileService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private jwtInterface jwtService;

    @Override
    public boolean checkUserProfile(String entityId) {
        return userRepo.findById(entityId).isPresent();
    }

    @Override
    public Optional<User> getUserByID(String entityId) {
        return userRepo.findById(entityId);
    }

    @Override
    public AuthenticationResponse registerUser(User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        assert entity.getUserId() != null;
        if(userRepo.findById(entity.getUserId()).isPresent()){
            return AuthenticationResponse.builder()
                    .status(402)
                    .response("User with that username already exists")
                    .build();
        }
            userRepo.save(entity);
            String JwtToken  = jwtService.generateToken(entity);
            return AuthenticationResponse.builder()
                    .token(JwtToken)
                    .status(200)
                    .build();

    }

    @Override
    public HttpStatus updateUserWithNewTradeProfileId(TradeProfile oldTPEntity) {
        Optional<User> oldUserEntity = userRepo.findById(oldTPEntity.getUserId());
        if(oldUserEntity.isPresent()){
            if(oldUserEntity.get().getTradeId() != null && oldUserEntity.get().getTradeId().contains(oldTPEntity.getTradeProfileId())){

                    LOG.debug("UserProfileService : User Already Has The TradeId Registered! : " + oldUserEntity.get().getTradeId());

                return HttpStatus.OK;
            }else{
                ArrayList<Integer> tradeprofileList;
                if(oldUserEntity.get().getTradeId() != null){

                     tradeprofileList = oldUserEntity.get().getTradeId();
                }
                else{
                    tradeprofileList = new ArrayList<Integer>();
                }
                tradeprofileList.add(oldTPEntity.getTradeProfileId());
                oldUserEntity.get().setTradeId(tradeprofileList);
                userRepo.save(oldUserEntity.get());

                    LOG.debug("UserProfileService : User Is Registered With Trade Id! : " + oldTPEntity.getTradeProfileId() );

                return HttpStatus.OK;
            }
          
        }else{
            return HttpStatus.PRECONDITION_FAILED;
        }

    
    }

    @Override
    public Optional<User> loadUserByUserEmail(String userEmail) {
        Optional<User> userDetails = userRepo.findBymail(userEmail);
        if (userDetails.isPresent()){
            return  userDetails;
        }else {
            throw  new UsernameNotFoundException("User Not Found");
        }

    }

    @Override
    public Optional<User> loadUserByUserID(String userId) {
        Optional<User> userDetails = userRepo.findById(userId);
        if (userDetails.isPresent()){
            userDetails.get().setPassword("");
            return  userDetails;
        }else {
            throw  new UsernameNotFoundException("User Not Found");
        }
    }


}

