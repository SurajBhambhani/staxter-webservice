package com.staxter.sb.staxter.controller;

import com.staxter.sb.staxter.exception.UserAlreadyExistsException;
import com.staxter.sb.staxter.service.IUserService;
import com.staxter.sb.staxter.to.Error;
import com.staxter.sb.staxter.to.UserInformation;
import com.staxter.sb.staxter.to.UserInformationWithPWD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userservice")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/users")
    public @ResponseBody
    List<UserInformation> showUser() {
        return userService.getAllUsers();
    }

    // TODO Message needs to be moved to message file according to locale
    @PostMapping(value = "/register")
    public ResponseEntity<?> createUser(@RequestBody UserInformationWithPWD userInformation) throws UserAlreadyExistsException {

        try {
            return new ResponseEntity<>(userService.createUser(userInformation), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            return new ResponseEntity<>(new Error("USER_ALREADY_EXISTS", "A user with the given username already exists"), HttpStatus.valueOf(409));
        }

    }

}
