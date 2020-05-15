package com.staxter.sb.staxter.to;

import java.util.Objects;

public class UserInformation {
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;

    public UserInformation(Integer id, String firstName, String lastName, String userName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInformation userInformation = (UserInformation) o;
        return id.equals(userInformation.id) &&
                Objects.equals(firstName, userInformation.firstName) &&
                Objects.equals(lastName, userInformation.lastName) &&
                userName.equals(userInformation.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, userName);
    }
}