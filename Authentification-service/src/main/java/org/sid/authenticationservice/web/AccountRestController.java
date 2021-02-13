package org.sid.authenticationservice.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sid.authenticationservice.JwtUtil;
import org.sid.authenticationservice.entities.AppRole;
import org.sid.authenticationservice.entities.AppUser;
import org.sid.authenticationservice.service.AccountService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class AccountRestController {
    private final AccountService accountService;

    public AccountRestController(AccountService accountService) {

        this.accountService = accountService;
    }

    @GetMapping(path = "/users")
    @PostAuthorize("hasAnyAuthority('USER')")
    public List<AppUser> appUsers() {

        return accountService.listUsers();
    }
    @GetMapping(path = "/profile")
    public AppUser profile(Principal principal) {

        return  accountService.loadUserByUserName(principal.getName());
    }

    @PostMapping("/user")
    @PostAuthorize("hasAnyAuthority('ADMIN')")
    public AppUser saveUser(@RequestBody AppUser appUser) {

        return accountService.addNewUser(appUser);
    }

    @PostMapping("/role")
    @PostAuthorize("hasAnyAuthority('ADMIN')")
    public AppRole saveRole(@RequestBody AppRole appRole) {

        return accountService.addNewRole(appRole);
    }

    @PostMapping("/addRoleToUser")
    @PostAuthorize("hasAnyAuthority('ADMIN')")
    public void addRoleToUser(@RequestBody RoleUserForm roleToUser) {
        accountService.addRoleToUser(roleToUser.getUsername(), roleToUser.getRoleName());
    }

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationToken = request.getHeader(JwtUtil.AUTH_HEADER);
        if (authorizationToken != null && authorizationToken.startsWith(JwtUtil.PREFIX)) {
            try {
                String jwt = authorizationToken.substring(JwtUtil.PREFIX.length());
                Algorithm algorithm = Algorithm.HMAC256(JwtUtil.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                String userName = decodedJWT.getSubject();
                AppUser user = accountService.loadUserByUserName(userName);
                String jwtAccessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtUtil.EXPIRE_ACCESS_TOKEN))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getAppRoles().stream().map(r -> r.getRoleName()).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> idToken = new HashMap<>();
                idToken.put("access-token", jwtAccessToken);
                idToken.put("refresh-token", jwt);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);
            } catch (Exception e) {
                throw e;
            }
        } else {
            throw new RuntimeException("Refresh Token Required!");
        }
    }
}
