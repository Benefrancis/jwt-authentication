package br.com.benefrancis.jwtauthentication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.benefrancis.jwtauthentication.response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/test")
public class UserController {

	@GetMapping("/all")
	public MessageResponse allAccess() {
		return new MessageResponse("Server is up.....");
	}

	@GetMapping("/greeting")
	@PreAuthorize("isAuthenticated()")
	public MessageResponse userAccess() {

		return new MessageResponse("Congratulations! You are an authenticated user.");
	}

	@GetMapping("/user")
	@PreAuthorize("hasAuthority('ALTERAR')")
	public ResponseEntity<Authentication> getUser() {

		SecurityContext securityContext = SecurityContextHolder.getContext();
		return ResponseEntity.ok(securityContext.getAuthentication());

	}

}
