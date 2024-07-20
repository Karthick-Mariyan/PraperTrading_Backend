package com.stera.papertrade.controller.Auth;

import com.stera.papertrade.model.Auth.AuthenticationResponse;
import com.stera.papertrade.model.User;
import com.stera.papertrade.serviceinterface.Auth.AuthenticationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationInterface authService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody User entity){
        return ResponseEntity.ok(authService.authenticateUser(entity));
    }
}
