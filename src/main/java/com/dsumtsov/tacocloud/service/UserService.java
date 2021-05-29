package com.dsumtsov.tacocloud.service;

import com.dsumtsov.tacocloud.domain.User;
import com.dsumtsov.tacocloud.security.RegistrationForm;
import lombok.NonNull;

public interface UserService {

    User save(@NonNull RegistrationForm form);
}
