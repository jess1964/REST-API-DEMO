package com.cameraiq.technology.directory.Dao;

import com.cameraiq.technology.directory.Mapper.Mapper;
import com.cameraiq.technology.directory.Objects.Organizations;
import com.cameraiq.technology.directory.Objects.Users;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("Dao")
public class Dao {

    @Autowired
    @Qualifier("Mapper")
    private Mapper mapper;


    public String isDataBaseActive() {
        return mapper.isDataBaseActive();
    }

    public int doesUserExist(String email) {
        return mapper.doesUserExist(email);
    }

    public void createUser(Users user) {
        mapper.createUser(user);
    }

    public int doesOrgExist(String org) {
        return mapper.doesOrgExist(org);
    }

    public void createOrg(Organizations org) {
        mapper.createOrg(org);
    }

    public int IsUserInOrg(String org, String email) {
        return mapper.isUserInOrg(org, email);
    }

    public void addUserToOrg(String org, String email) {
        mapper.addUserToOrg(org, email);
    }

    public void removeUsersFromOrg(String email, String org) {
        mapper.removeUsersFromOrg(email, org);
    }

    public List<Organizations> printAllOrgsWithUser(String email) {
        return mapper.printAllOrgsWithUser(email);
    }

    public List<Users> printAllUsersInOrg(String org) {
        return mapper.printAllUsersInOrg(org);
    }
}
