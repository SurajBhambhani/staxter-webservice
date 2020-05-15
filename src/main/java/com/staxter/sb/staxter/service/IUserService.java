package com.staxter.sb.staxter.service;

import com.staxter.sb.staxter.exception.UserAlreadyExistsException;
import com.staxter.sb.staxter.to.UserInformation;
import com.staxter.sb.staxter.to.UserInformationWithPWD;

import java.util.List;

public interface IUserService {

    List<UserInformation> getAllUsers();

    // TODO Change to validator
    UserInformation createUser(UserInformationWithPWD userInformation) throws UserAlreadyExistsException;
}
