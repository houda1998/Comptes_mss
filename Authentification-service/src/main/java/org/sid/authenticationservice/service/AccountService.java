package org.sid.authenticationservice.service;

import org.sid.authenticationservice.entities.AppRole;
import org.sid.authenticationservice.entities.AppUser;

import java.util.Collection;
import java.util.List;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String userName, String roleName);
    AppUser loadUserByUserName(String username);
    List<AppUser> listUsers();
}
