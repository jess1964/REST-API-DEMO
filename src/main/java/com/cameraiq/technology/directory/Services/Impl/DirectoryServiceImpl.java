package com.cameraiq.technology.directory.Services.Impl;

import com.cameraiq.technology.directory.Dao.Dao;
import com.cameraiq.technology.directory.Objects.Organizations;
import com.cameraiq.technology.directory.Objects.Users;
import com.cameraiq.technology.directory.Services.DirectoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@Service("DirectoryService")
public class DirectoryServiceImpl implements DirectoryService {
    private Logger logger = LoggerFactory.getLogger(DirectoryServiceImpl.class);

    private Dao dao;

    public DirectoryServiceImpl(@Autowired Dao dao) {
        this.dao = dao;
    }

    @Override
    public String isDataBaseActive() {
        logger.info("Database Activity Measured");
        return dao.isDataBaseActive();
    }

    @Override
    public ResponseEntity createUserSerivce(Users user) {
        try {
            if (dao.doesUserExist(user.getEmail()) > 0) {
                logger.info("User: " + user.getEmail() + " is in DB Returning Bad Request");
                return new ResponseEntity("User In DB", HttpStatus.BAD_REQUEST);
            } else {
                logger.info("Adding User: " + user.getEmail() + " To DB");
                dao.createUser(user);
                return new ResponseEntity("Added TO DB", HttpStatus.ACCEPTED);
            }
        } catch (Exception e) {
            logger.error("Error " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity createOrganizationSerivce(Organizations org) {
        try {
            if (dao.doesOrgExist(org.getName()) > 0)// this is based only on the name not address since the project didnt specify
            {
                logger.info("Organization " + org.getName() + " is in DB Returning Bad Request");
                return new ResponseEntity("Organization In DB", HttpStatus.BAD_REQUEST);
            } else {
                logger.info("Adding Organization " + org.getName() + " To DB");
                dao.createOrg(org);
                return new ResponseEntity("Added " + org.getName() + " TO DB", HttpStatus.ACCEPTED);
            }
        } catch (Exception e) {
            logger.error("Error: " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity addUserToOrg(String org, String email) {
        try {
            if (dao.IsUserInOrg(org, email) > 0) {
                logger.info("User " + email + " Is Part Of Org " + org);
                return new ResponseEntity("User " + email + " Already Part Of " + org, HttpStatus.BAD_REQUEST);
            } else {
                if (dao.doesOrgExist(org) > 0 && dao.doesUserExist(email) > 0) {
                    logger.info("Adding User " + email + " to Organization: " + org);
                    dao.addUserToOrg(org, email);
                    return new ResponseEntity("Added " + email + " TO ORG " + org, HttpStatus.ACCEPTED);
                } else {
                    return new ResponseEntity("Missing Member", HttpStatus.BAD_REQUEST);

                }
            }
        } catch (Exception e) {
            logger.error("Error: " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity removeUsersFromOrg(String email, String org) {
        try {
            logger.info("Removing User : " + email + " From : " + org);
            dao.removeUsersFromOrg(email, org);
            return new ResponseEntity(email + " Removed From " + org, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            logger.error("Error: " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity printAllOrgsWithUser(String email) {
        try {
            logger.info("Getting All Organizations that Contain " + email);
            List<Organizations> orgs = dao.printAllOrgsWithUser(email);
            return new ResponseEntity(orgs, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            logger.error("Error: " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity printAllUsersInOrg(String org) {
        try {
            logger.info("Getting All User Part Of " + org);
            List<Users> users = dao.printAllUsersInOrg(org);
            return new ResponseEntity(users, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            logger.error("Error: " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


}
