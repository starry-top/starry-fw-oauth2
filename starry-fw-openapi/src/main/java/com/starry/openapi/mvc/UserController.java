package com.starry.openapi.mvc;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('USER')")
public class UserController {

    @RequestMapping("/")
    public Map<String, Object> resource(Principal principal) {

    	Map<String, Object> map = new HashMap<>();

    	OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
    	String username = oAuth2Authentication.getName();

    	map.put("username", username);

        return map;
    }

}
