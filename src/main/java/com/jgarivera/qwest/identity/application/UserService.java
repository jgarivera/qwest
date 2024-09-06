package com.jgarivera.qwest.identity.application;

import com.jgarivera.qwest.identity.domain.model.EmailAddress;
import com.jgarivera.qwest.identity.domain.model.PersonalName;
import com.jgarivera.qwest.identity.domain.model.User;
import com.jgarivera.qwest.identity.domain.model.Username;

public interface UserService {

    User register(PersonalName name, EmailAddress email, Username username, String password);
}
