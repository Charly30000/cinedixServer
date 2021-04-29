package com.cinedix.server.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinedix.server.app.models.entity.Usuario;

@RestController
@Secured("ROLE_USER")
@RequestMapping("/api/clientes")
public class ClienteRestController {

	@GetMapping("/usuarios")
	public /*@ResponseBody */List<Usuario> ejemploRest() {
		List<Usuario> usuarios = new ArrayList<>();
		Usuario u = new Usuario();
		u.setId(1L);
		u.setUsername("andres");
		u.setPassword("12345");
		u.setEnabled(false);
		usuarios.add(u);
		
		u = new Usuario();
		u.setId(2L);
		u.setUsername("admin");
		u.setPassword("123456789");
		u.setEnabled(true);
		usuarios.add(u);
		
		
		return usuarios;
	}
}
