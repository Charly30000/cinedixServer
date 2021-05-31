package com.cinedix.server.app.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinedix.server.app.constantes.CRoles;
import com.cinedix.server.app.models.entity.Role;
import com.cinedix.server.app.models.entity.Usuario;
import com.cinedix.server.app.models.entity.response.ModificarUsuario;
import com.cinedix.server.app.models.service.IUsuarioService;

@RestController
@RequestMapping("/api/clientes")
public class UsuarioRestController {

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Secured("ROLE_USER")
	@GetMapping("/usuario")
	public Usuario obtenerUsuario(Authentication authentication) {
		
		String username = authentication.getName();
		Usuario usuario = usuarioService.findByUsername(username);
		usuario.setPassword(null);
		
		return usuario;
	}
	
	@PostMapping("/usuario/crear")
	public ResponseEntity<?> crear(@RequestBody @Valid Usuario usuario, BindingResult result) {
		Map<String, Object> respuesta = new HashMap<>();
		
		if (result.hasErrors()) {
			respuesta.put("message", "Los parametros no siguen los estandares correctos");
			respuesta.put("error", result.getAllErrors());
			
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}
		
		try {
			List<Role> roles = new ArrayList<>();
			Role role = new Role();
			role.setAuthority(CRoles.ROLE_USER);
			roles.add(role);
			
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			usuario.setRoles(roles);
			usuario.setEnabled(true);
			
			usuarioService.save(usuario);
			
		} catch (DataAccessException ex) {
			respuesta.put("message", "El usuario que intentas crear ya existe");
			logger.error("Error en la BBDD: " + ex.getMessage());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(Exception ex) {
			respuesta.put("message", "Ha ocurrido un error en el servidor");
			logger.error("Error inesperado: " + ex.getMessage());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		respuesta.put("message", "ok");
		
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	@Secured("ROLE_USER")
	@PutMapping("/usuario/modificar")
	public ResponseEntity<?> modificar(@RequestBody @Valid ModificarUsuario usuario, BindingResult result, Authentication authentication) {
		Map<String, Object> respuesta = new HashMap<>();
		
		if (result.hasErrors()) {
			respuesta.put("message", "Los parametros no siguen los estandares correctos");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}
		
		String nombreUsuario = authentication.getName();
		
		try {
			Usuario usuarioActualizar = usuarioService.findByUsername(nombreUsuario);
			
			boolean claveCorrecta = passwordEncoder.matches(usuario.getOldPassword(), usuarioActualizar.getPassword());
			if (!claveCorrecta) {
				respuesta.put("message", "Los parametros no coinciden con los del servidor, revisa tus datos");
				System.out.println("no se modifica");
				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
			}
			
			usuarioActualizar.setPassword(passwordEncoder.encode(usuario.getNewPassword()));
			usuarioActualizar.setEmail(usuario.getEmail());
			
			usuarioService.save(usuarioActualizar);
		} catch(DataAccessException ex) {
			respuesta.put("message", "Ha ocurrido un error en el servidor");
			logger.info("Error en la BBDD: " + ex.getMessage());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(Exception ex) {
			respuesta.put("message", "Ha ocurrido un error en el servidor");
			logger.info("Error inesperado: " + ex.getMessage());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		respuesta.put("message", "ok");
		
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}
}
