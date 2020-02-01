package com.cameraiq.technology.directory.Mapper;

import com.cameraiq.technology.directory.Annotations.MapperLink;
import com.cameraiq.technology.directory.Objects.Organizations;
import com.cameraiq.technology.directory.Objects.Users;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;

import java.util.List;

@MapperLink
@Repository("Mapper")
public interface Mapper {
    String isDataBaseActive();
    int doesUserExist(@Param("email") String email);
    void createUser(Users users);
    int doesOrgExist(@Param("name") String name);
    void createOrg(Organizations org);
    int isUserInOrg(@Param("org") String org,@Param("email") String email);
    void addUserToOrg(@Param("org") String org, @Param("email")String email);
    void removeUsersFromOrg(@Param("email") String email, @Param("org") String org);
    List<Organizations> printAllOrgsWithUser(String email);
    List<Users> printAllUsersInOrg(String org);

}
