package com.saber11.auth.domain.model.gateway;

public interface EncryptGateway {
    String encrypt(String password);
    Boolean matches(String rawPasswaord, String encodedPassword);
}
