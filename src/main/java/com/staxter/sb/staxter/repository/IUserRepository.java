package com.staxter.sb.staxter.repository;

import com.staxter.sb.staxter.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, Number> {

    public Boolean existsUserByUserName(String userName);
}
