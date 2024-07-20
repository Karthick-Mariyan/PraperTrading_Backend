package com.stera.papertrade.serviceinterface.Auth;

import com.stera.papertrade.model.Auth.AuthenticationRequest;
import com.stera.papertrade.model.Auth.AuthenticationResponse;
import com.stera.papertrade.model.User;

public interface AuthenticationInterface {

    AuthenticationResponse authenticateUser(User entity);
}
