package com.staxter.sb.staxter.to;

public class UserInformationWithPWD extends UserInformation {

    private String password;

    public UserInformationWithPWD(Integer id, String firstName, String lastName, String userName, String password) {
        super(id, firstName, lastName, userName);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}