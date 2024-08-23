package com.jgarivera.qwest.identity.application;

import com.jgarivera.qwest.identity.domain.EmailAddress;
import com.jgarivera.qwest.identity.domain.PersonalName;
import com.jgarivera.qwest.identity.domain.User;
import com.jgarivera.qwest.identity.domain.Username;

public interface UserService {

    User register(PersonalName name, EmailAddress email, Username username, String password);
}
