package com.starry.triones.mvc;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('USER')")
public class UserController {

	@RequestMapping({ "/user", "/me" })
	public Map<String, Object> user(Principal principal) {
		Map<String, Object> map = new LinkedHashMap<>();

		map.put("userInfo", principal);

		return map;
	}

}
