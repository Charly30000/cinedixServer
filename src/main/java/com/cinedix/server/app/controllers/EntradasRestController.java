package com.cinedix.server.app.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cinedix.server.app.models.entity.Entrada;
import com.cinedix.server.app.models.entity.SesionPelicula;
import com.cinedix.server.app.models.entity.SitioOcupado;
import com.cinedix.server.app.models.entity.Usuario;
import com.cinedix.server.app.models.service.IEntradaService;
import com.cinedix.server.app.models.service.ISesionPeliculaService;
import com.cinedix.server.app.models.service.ISitioOcupadoService;
import com.cinedix.server.app.models.service.IUsuarioService;

@RestController
@Secured("ROLE_USER")
@RequestMapping("/api/clientes")
public class EntradasRestController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IEntradaService entradaService;
	
	@Autowired
	private ISitioOcupadoService sitioOcupadoService;
	
	@Autowired
	private ISesionPeliculaService sesionPeliculaService;
	
	@GetMapping("/entradas")
	public Object[] obtenerEntradas(Authentication auth) {
		
		List<Entrada> entradas = usuarioService.findByUsername(auth.getName()).getEntradas();
		Object[] respuesta = new Object[entradas.size()];
		
		int iterador = 0;
		for (Entrada e : entradas) {
			Map<String, Object> entrada = new HashMap<>();
			entrada.put("id", e.getId());
			entrada.put("codigo", e.getCodigo());
			entrada.put("estado", e.getEstado());
			entrada.put("sitiosOcupados", e.getSitiosOcupados());
			entrada.put("horaPelicula", formatearFecha(e.getSitiosOcupados().get(0).getSesionPelicula().getHoraPelicula()));
			entrada.put("fechaCreacion", formatearFecha(e.getFechaCreacion()));
			entrada.put("cine", e.getSitiosOcupados().get(0).getSesionPelicula().getCine().getNombre());
			entrada.put("pelicula", e.getSitiosOcupados().get(0).getSesionPelicula().getPelicula().getNombre());
			
			respuesta[iterador] = entrada;
			iterador++;
		}
		
		return respuesta;
	}
	
	@PostMapping("/entradas/crear")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Map<String, String> crearEntrada(@RequestBody @Valid Map<String, Object> sesionPelicula, 
			Authentication auth) {
		
		Map<String, String> respuesta = new HashMap<>();
		
		if (sesionPelicula.isEmpty()) {
			respuesta.put("mensaje", "No se han detectado datos");
			return respuesta;
		} else if (sesionPelicula.get("sesionPelicula") == null || sesionPelicula.get("sitiosOcupados") == null) {
			respuesta.put("mensaje", "No se han recibido los datos necesarios");
			return respuesta;
		}
		
		// Obtengo los parametros de la sesion de la pelicula y los sitios ocupados
		Long sesion = null;
		Integer[] sitiosOcupados = null;
		try {
			sesion = Long.parseLong(sesionPelicula.get("sesionPelicula").toString());
			String sitiosOcupadosString = sesionPelicula.get("sitiosOcupados").toString()
					.replace("[", "").replace("]", "").replace(" ", "");
			String[] sitiosString = sitiosOcupadosString.split(",");
			sitiosOcupados = new Integer[sitiosString.length];
			for (int i = 0; i < sitiosString.length; i++) {
				sitiosOcupados[i] = Integer.parseInt(sitiosString[i]);
			}
		} catch(NumberFormatException ex) {
			respuesta.put("mensaje", "Lo sentimos, ha ocurrido un error en el servidor al parsear: " + ex.getMessage());
			return respuesta;
		} catch (Exception ex) {
			respuesta.put("mensaje", "Lo sentimos, ha ocurrido un error en el servidor: " + ex.getMessage());
			return respuesta;
		}
		
		SesionPelicula sesionBBDD = sesionPeliculaService.findOne(sesion);
		if (sesionBBDD == null) {
			respuesta.put("mensaje", "La sesion a la que te intentas unir no existe en la BBDD");
			return respuesta;
		}
		
		Entrada entrada = new Entrada();
		
		List<SitioOcupado> sitiosOcupadosList = new ArrayList<>();
		for (int i = 0; i < sitiosOcupados.length; i++) {
			SitioOcupado aux = new SitioOcupado();
			aux.setSitioOcupado(sitiosOcupados[i]);
			aux.setEntrada(entrada);
			aux.setSesionPelicula(sesionBBDD);
			sitiosOcupadosList.add(aux);
		}
		
		Usuario usuario = usuarioService.findByUsername(auth.getName());
		String codigo = UUID.randomUUID().toString() + usuario.getId(); 
		entrada.setUsuario(usuario);
		entrada.setCodigo(codigo);
		entrada.setEstado("procesando");
		entrada.setFechaCreacion(new Date());
		entrada.setSitiosOcupados(sitiosOcupadosList);
		
		entradaService.save(entrada);
		
		respuesta.put("mensaje", "ok");
		return respuesta;
	}
	
	@GetMapping("/entradas/sitios-ocupados")
	public List<SitioOcupado> sitiosOcupados() {
		return sitioOcupadoService.findAll();
	}
	
	private String formatearFecha(Date date) {
		String salida = date.toString();
		salida = salida.substring(0, salida.lastIndexOf(":"));
		
		return salida;
	}
	
}
