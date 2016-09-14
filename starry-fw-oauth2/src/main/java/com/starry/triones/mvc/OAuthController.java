package com.starry.triones.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OAuthController {

	@RequestMapping(value = "/oauth_login", method = RequestMethod.GET)
	public String loginPage(ModelMap model, HttpSession session,
			@RequestParam(name="error", required=false) String error) {
		if (!StringUtils.isEmpty(error)) {
			//BadCredentialsException ex = (BadCredentialsException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
			model.addAttribute("error", "认证失败");
		}
		return "oauth_login";
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/oauth_login?logout";
	}

}
