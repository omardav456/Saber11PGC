package com.saber11.auth.infraestructure.security_encrypter;

import com.saber11.auth.domain.model.gateway.EncryptGateway;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncrypterGatewayImpl implements EncryptGateway {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(14);

    @Override
    public String encrypt(String password) {
        return encoder.encode(password);
    }

    @Override
    public Boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
