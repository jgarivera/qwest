package com.jgarivera.qwest.identity.application;

public interface UserService {

    void register(String firstName, String middleName, String lastName, String email, String username, String password);
}
