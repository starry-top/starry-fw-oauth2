package com.starry.triones.mvc;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starry.triones.oauth.TrionesUserDetails;
import com.starry.triones.oauth.TrionesUserDetailsManager;

@RestController
@PreAuthorize("hasRole('USER')")
public class UserController {

	@Autowired
	private TrionesUserDetailsManager trionesUserDetailsManager;

	@RequestMapping({ "/user", "/me" })
	public Map<String, Object> user(Principal principal) {
		Map<String, Object> map = new LinkedHashMap<>();

		OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;

		TrionesUserDetails userDetails = (TrionesUserDetails) trionesUserDetailsManager.loadUserByUsername(oAuth2Authentication.getName());

		map.put("userInfo", userDetails.getUsers());

		return map;
	}



}
