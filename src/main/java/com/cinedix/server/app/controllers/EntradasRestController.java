package com.cinedix.server.app.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
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
import com.cinedix.server.app.models.entity.request.SesionPeliculaRequest;
import com.cinedix.server.app.models.entity.response.EntradaResponse;
import com.cinedix.server.app.models.entity.response.SitioOcupadoResponse;
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
	public List<EntradaResponse> obtenerEntradas(Authentication auth) {

		List<Entrada> entradas = usuarioService.findByUsername(auth.getName()).getEntradas();
		List<EntradaResponse> entradasResponse = new ArrayList<>();

		for (Entrada e : entradas) {
			List<SitioOcupadoResponse> sitiosOcupados = new ArrayList<>();
			for (SitioOcupado s : e.getSitiosOcupados()) {
				sitiosOcupados.add(new SitioOcupadoResponse(s.getId(), s.getSitioOcupado()));
			}

			entradasResponse.add(new EntradaResponse(e.getCodigo(), 
					e.getEstado(), 
					sitiosOcupados,
					formatearFecha(e.getSitiosOcupados().get(0).getSesionPelicula().getHoraPelicula()),
					e.getSitiosOcupados().get(0).getSesionPelicula().getCine().getNombre(),
					e.getSitiosOcupados().get(0).getSesionPelicula().getPelicula().getNombre(),
					formatearFecha(e.getFechaCreacion()), 
					e.getId()));
		}

		return entradasResponse;
	}

	@PostMapping("/entradas/crear")
	@ResponseBody
	public ResponseEntity<?> crearEntrada(@RequestBody @Valid SesionPeliculaRequest sesionPelicula,
			BindingResult result, Authentication auth) {

		Map<String, String> respuesta = new HashMap<>();
		try {

			if (result.hasErrors()) {
				respuesta.put("message", "Los parametros no siguen los estandares correctos");
				logger.info("Los parametros no siguen los estandares correctos");
				return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.BAD_REQUEST);
			}

			SesionPelicula sesionBBDD = sesionPeliculaService
					.findOne(Long.parseLong(sesionPelicula.getSesionPelicula().toString()));

			if (sesionBBDD.getSitiosOcupados().size() + sesionPelicula.getSitiosOcupados().size() > sesionBBDD
					.getSitiosTotales()) {
				respuesta.put("message", "Lo sentimos, no hay asientos suficientes");
				logger.info("No hay asientos suficientes");
				return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.EXPECTATION_FAILED);
			}

			Entrada entrada = new Entrada();

			// Creo los nuevos sitios ocupados
			List<SitioOcupado> sitiosOcupadosList = new ArrayList<>();
			for (Integer i : sesionPelicula.getSitiosOcupados()) {
				SitioOcupado aux = new SitioOcupado();
				aux.setSitioOcupado(i);
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
			respuesta.put("message", "Lo sentimos, los asientos que intentas introducir ya han sido escogidos");
			logger.info("Campos en la BBDD ya existentes, probablemente los sitios ya han sido escogidos: "
					+ ex.getMessage());
			return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (DataAccessException ex) {
			respuesta.put("message", "La sesion a la que te intentas unir no existe en la BBDD");
			logger.info("Intento de unirse a una sesion inexistente: " + ex.getMessage());
			return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.NOT_FOUND);

		} catch (Exception ex) {
			respuesta.put("message", "Lo sentimos, ha ocurrido un error inesperado, vuelve a intentarlo por favor");
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
