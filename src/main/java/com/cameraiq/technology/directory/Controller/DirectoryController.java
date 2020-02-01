package com.cameraiq.technology.directory.Controller;

import com.cameraiq.technology.directory.Objects.Organizations;
import com.cameraiq.technology.directory.Objects.Users;
import com.cameraiq.technology.directory.Services.DirectoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/Managment")
public class DirectoryController {
    Logger logger = LoggerFactory.getLogger(DirectoryController.class);

    private DirectoryService directoryService;

    public DirectoryController(@Autowired DirectoryService directoryService) {
        this.directoryService = directoryService;
    }



    /*
        http://localhost:8080/Directory/Managment/isDataBaseActive
    */

    // used to identify if the DB is currently active
    @RequestMapping(value = "/isDataBaseActive", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    Boolean isDataBaseActive() {

        try {
            if (directoryService.isDataBaseActive().equals("true")) {
                logger.info("DB is Active");
                return true;
            }
        } catch (Exception e) {
            logger.error("Error " + e);
        }
        return false;
    }

    /*
            http://localhost:8080/Directory/Managment/createUser?&firstname=ALCA&lastname=KAI&email=KAI1961@GMAIL.COM&address=7653%20N.%20Los%20Angeles%20St%20Los%20Angeles%20,%20CA%2090004&phone=8312314325
    */
    // Used to Create User
    @RequestMapping(value = "/createUser")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ResponseEntity createUser(@RequestParam("firstname") String firstName,
                              @RequestParam("lastname") String lastName,
                              @RequestParam("email") String email,
                              @RequestParam("address") String address,
                              @RequestParam("phone") String phone) {
        try {
            Users user = new Users();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setAddress(address);
            user.setPhone(phone);
            return directoryService.createUserSerivce(user);
        } catch (Exception e) {
            logger.error("Error " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /*
        http://localhost:8080/Directory/Managment/createOrg?&name=Postman&address=7653%20N.%20Los%20Angeles%20St%20Los%20Angeles%20,%20CA%2090004&phone=8312314325
     */
    // used to create org
    @RequestMapping(value = "/createOrg")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ResponseEntity createUser(@RequestParam("name") String name,
                              @RequestParam("address") String address,
                              @RequestParam("phone") String phone) {
        try {
            Organizations org = new Organizations();
            org.setName(name);
            org.setAddress(address);
            org.setPhone(phone);
            return directoryService.createOrganizationSerivce(org);
        } catch (Exception e) {
            logger.error("Error " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /*
            http://localhost:8080/Directory/Managment/addToOrg?&org=POSTMAN&email=ROD1961@GMAIL.COM
     */


    // add users to org
    @RequestMapping(value = "/addToOrg")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ResponseEntity addUser(@RequestParam("org") String org,
                           @RequestParam("email") String email) {
        try {
            return directoryService.addUserToOrg(org, email);
        } catch (Exception e) {
            logger.error("Error " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /*
        http://localhost:8080/Directory/Managment/removeUserRole?&org=cameraiq&email=REYES1961@GMAIL.COM
    */
    // remove a user from an org
    @RequestMapping(value = "/removeUserRole")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ResponseEntity removeUsersFromOrg(@RequestParam("org") String org,
                                      @RequestParam("email") String email) {
        try {
            return directoryService.removeUsersFromOrg(email, org);
        } catch (Exception e) {
            logger.error("Error " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /*
        http://localhost:8080/Directory/Managment/userOrgs?&email=ROD1961@GMAIL.COM
    */
    // print users from org
    @RequestMapping(value = "/userOrgs")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ResponseEntity printAllOrgsWithUser(@RequestParam("email") String email) {
        try {
            return directoryService.printAllOrgsWithUser(email);
        } catch (Exception e) {
            logger.error("Error " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /*
        http://localhost:8080/Directory/Managment/orgsUsers?&org=CameraIQ
    */
    // print orgs with user via email
    @RequestMapping(value = "/orgsUsers")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ResponseEntity printAllUsersInOrg(@RequestParam("org") String org) {
        try {
            return directoryService.printAllUsersInOrg(org);
        } catch (Exception e) {
            logger.error("Error " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
