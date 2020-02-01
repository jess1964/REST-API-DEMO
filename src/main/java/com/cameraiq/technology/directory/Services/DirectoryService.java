package com.cameraiq.technology.directory.Services;

import com.cameraiq.technology.directory.Objects.Organizations;
import com.cameraiq.technology.directory.Objects.Users;
import org.springframework.http.ResponseEntity;


public interface DirectoryService {
    public String isDataBaseActive();

    public ResponseEntity createUserSerivce(Users user);

    public ResponseEntity createOrganizationSerivce(Organizations org);

    public ResponseEntity addUserToOrg(String org, String Email);

    public ResponseEntity removeUsersFromOrg(String email, String org);

    public ResponseEntity printAllOrgsWithUser(String email);

    public ResponseEntity printAllUsersInOrg(String org);
}
