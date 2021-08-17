package com.dsumtsov.tacocloud.service;

import com.dsumtsov.tacocloud.model.User;
import com.dsumtsov.tacocloud.security.RegistrationForm;
import lombok.NonNull;

public interface UserService {

    User save(@NonNull RegistrationForm form);
}
