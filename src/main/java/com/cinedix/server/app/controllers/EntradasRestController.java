package com.cinedix.server.app.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cinedix.server.app.constantes.CEstadoEntrada;
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

	protected final Log logger = LogFactory.getLog(this.getClass());

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
			entrada.put("horaPelicula",
					formatearFecha(e.getSitiosOcupados().get(0).getSesionPelicula().getHoraPelicula()));
			entrada.put("fechaCreacion", formatearFecha(e.getFechaCreacion()));
			entrada.put("cine", e.getSitiosOcupados().get(0).getSesionPelicula().getCine().getNombre());
			entrada.put("pelicula", e.getSitiosOcupados().get(0).getSesionPelicula().getPelicula().getNombre());

			respuesta[iterador] = entrada;
			iterador++;
		}

		return respuesta;
	}

	@PostMapping("/entradas/crear")
	@ResponseBody
	public ResponseEntity<?> crearEntrada(@RequestBody @Valid Map<String, Object> sesionPelicula, Authentication auth) {

		Map<String, String> respuesta = new HashMap<>();
		try {
			if (sesionPelicula.isEmpty()) {
				respuesta.put("message", "No se han detectado datos");
				logger.info("No se han recibido los datos necesarios");
				return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.EXPECTATION_FAILED);
			} else if (sesionPelicula.get("sesionPelicula") == null || sesionPelicula.get("sitiosOcupados") == null) {
				respuesta.put("message", "No se han recibido los datos necesarios");
				logger.info("No se han recibido los datos necesarios");
				return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.EXPECTATION_FAILED);
			}

			// Obtengo los parametros de la sesion de la pelicula y los sitios ocupados
			Long sesion = null;
			Integer[] sitiosOcupados = null;

			sesion = Long.parseLong(sesionPelicula.get("sesionPelicula").toString());
			String sitiosOcupadosString = sesionPelicula.get("sitiosOcupados").toString().replace("[", "")
					.replace("]", "").replace(" ", "");
			String[] sitiosString = sitiosOcupadosString.split(",");
			sitiosOcupados = new Integer[sitiosString.length];
			for (int i = 0; i < sitiosString.length; i++) {
				sitiosOcupados[i] = Integer.parseInt(sitiosString[i]);
			}

			SesionPelicula sesionBBDD = sesionPeliculaService.findOne(sesion);
			
			if (sesionBBDD.getSitiosOcupados().size() + sitiosOcupados.length > sesionBBDD.getSitiosTotales()) {
				respuesta.put("message", "Lo sentimos, no hay asientos suficientes");
				logger.info("No hay asientos suficientes");
				return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.EXPECTATION_FAILED);
			}

			Entrada entrada = new Entrada();

			// Creo los nuevos sitios ocupados
			List<SitioOcupado> sitiosOcupadosList = new ArrayList<>();
			for (int i = 0; i < sitiosOcupados.length; i++) {
				SitioOcupado aux = new SitioOcupado();
				aux.setSitioOcupado(sitiosOcupados[i]);
				aux.setEntrada(entrada);
				aux.setSesionPelicula(sesionBBDD);
				sitiosOcupadosList.add(aux);
			}

			// Creo la nueva entrada con todos los datos necesarios
			Usuario usuario = usuarioService.findByUsername(auth.getName());
			String codigo = UUID.randomUUID().toString() + usuario.getId();
			entrada.setUsuario(usuario);
			entrada.setCodigo(codigo);
			entrada.setEstado(CEstadoEntrada.PROCESANDO);
			entrada.setFechaCreacion(new Date());
			entrada.setSitiosOcupados(sitiosOcupadosList);

			entradaService.save(entrada);
			
		} catch (NumberFormatException ex) {
			respuesta.put("message", "Lo sentimos, ha ocurrido un error en el servidor al parsear");
			logger.info("Error al parsear la cadena:" + ex.getMessage());
			return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.EXPECTATION_FAILED);
			
		} catch (DataIntegrityViolationException ex) {
			respuesta.put("message",
					"Lo sentimos, los asientos que intentas introducir ya han sido escogidos");
			logger.info("Campos en la BBDD ya existentes, probablemente los sitios ya han sido escogidos: "
					+ ex.getMessage());
			return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		} catch (DataAccessException ex) {
			respuesta.put("message", "La sesion a la que te intentas unir no existe en la BBDD");
			logger.info("Intento de unirse a una sesion inexistente: " + ex.getMessage());
			return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.NOT_FOUND);
			
		} catch (Exception ex) {
			respuesta.put("message",
					"Lo sentimos, ha ocurrido un error inesperado, vuelve a intentarlo por favor");
			logger.info("Error desconocido al intentar almacenar la nueva entrada: " + ex.getMessage());
			return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("message", "ok");
		return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.CREATED);
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
