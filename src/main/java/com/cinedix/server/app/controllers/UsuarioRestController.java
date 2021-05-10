package com.cinedix.server.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinedix.server.app.models.entity.Usuario;
import com.cinedix.server.app.models.service.IUsuarioService;

@RestController
@Secured("ROLE_USER")
@RequestMapping("/api/clientes")
public class UsuarioRestController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/usuario")
	public Usuario obtenerUsuario(Authentication authentication) {
		
		String username = authentication.getName();
		Usuario usuario = usuarioService.findByUsername(username);
		usuario.setPassword(null);
		
		return usuario;
	}
}
