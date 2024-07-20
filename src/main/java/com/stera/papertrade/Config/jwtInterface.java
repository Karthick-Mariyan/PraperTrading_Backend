package com.stera.papertrade.Config;

import com.stera.papertrade.model.User;

public interface jwtInterface {
    public String extractUsername(String name);
    public boolean isTokenValid(String token, User userDetails);
    public String generateToken(User userDetails);
}
