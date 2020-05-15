package com.staxter.sb.staxter.service;

import com.staxter.sb.staxter.entity.User;
import com.staxter.sb.staxter.exception.UserAlreadyExistsException;
import com.staxter.sb.staxter.repository.IUserRepository;
import com.staxter.sb.staxter.to.UserInformation;
import com.staxter.sb.staxter.to.UserInformationWithPWD;
import com.staxter.sb.staxter.util.HashingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<UserInformation> getAllUsers() {
        // TODO find a better way to map
        Iterator<User> userIterator = userRepository.findAll().iterator();
        List<UserInformation> userInformations = new ArrayList<>();

        while (userIterator.hasNext()) {
            User user = userIterator.next();
            userInformations.add(new UserInformation(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName()));
        }
        return userInformations;
    }

    // TODO Change to validator
    @Override
    public UserInformation createUser(UserInformationWithPWD userInformation) throws UserAlreadyExistsException {

        if (userRepository.existsUserByUserName(userInformation.getUserName().toLowerCase())) {
            throw new UserAlreadyExistsException();
        } else {
            User user = userRepository.save(
                    new User(userInformation.getFirstName(),
                            userInformation.getLastName(),
                            userInformation.getUserName(),
                            HashingUtil.hashPassword(userInformation.getPassword()).get()));
            return new UserInformation(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName().toLowerCase());
        }
    }

}



