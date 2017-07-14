package org.manolete.apuestas.euromillones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/euromillones")
public class EuromillonesController {

	private EuromillonesDao euromillonesDao;

	private static final Log log = LogFactory.getLog(EuromillonesController.class);

	@RequestMapping(value = "/listado")
	public ModelAndView listado(@RequestParam(name = "fechaBusqueda", required = false) String fechaFormulario) {
		log.info("Devolviendo la lista de sorteos");

		Date fechaBusqueda;
		ModelAndView mv = new ModelAndView("euromillones/lista");

		if (fechaFormulario == null) {
			fechaBusqueda = new Date();
		} else {
			try {
				fechaBusqueda = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFormulario);
			} catch (ParseException e) {
				log.error(
						"Se ha producido un error al parsear la fecha del formulario de búsqueda. Se esperaba un formato 'dd/MM/yyyy' pero se ha recibido "
								+ fechaFormulario);
				fechaBusqueda = new Date();

				mv.getModel().put("error", "Se esperaba un formato 'dd/mm/aaaa'");
			}
		}

		mv.getModel().put("sorteos", this.euromillonesDao.find(fechaBusqueda, EuromillonesDao.DEFAULT_NUMBER));

		return mv;
	}

	@RequestMapping(value = "/prediccion")
	public ModelAndView predicciones(@RequestParam(name = "frecuencia", defaultValue = "true") String frecuencia) {
		ModelAndView mv = new ModelAndView("euromillones/prediccion");

		mv.getModel().put("prediccion", this.euromillonesDao.getPrediccion(new Date(), Boolean.parseBoolean(frecuencia)));

		return mv;
	}
	
	@RequestMapping(value = "/porcentajes")
	public ModelAndView porcentajes() {
		ModelAndView mv = new ModelAndView("euromillones/porcentajes");

		mv.getModel().put("numeros", this.euromillonesDao.getFrecuenciaNumeros(true));
		mv.getModel().put("estrellas", this.euromillonesDao.getFrecuenciaEstrellas(true));

		return mv;
	}
	
	@RequestMapping(value = "/insertar")
	public ModelAndView insertar(@RequestParam(name = "fecha_sorteo") String fechaSorteo, 
								@RequestParam(name = "nums") List<String> paramNumeros,
								@RequestParam(name = "stars") List<String> paramEstrellas) {
		
		try {
			int i = 0;
			boolean iguales = false;			
			List<Byte> numeros = new ArrayList<Byte>();
			
			while (i < paramNumeros.size() && !iguales) {
				byte byteNumber = Byte.parseByte(paramNumeros.get(i));
				
				if (byteNumber < 1 || byteNumber > 50) {
					throw new NumberFormatException("El numero '" + byteNumber + "' no es válido" );
				} else if (numeros.contains(byteNumber)) {
					iguales = true;
				} else {
					numeros.add(byteNumber);
					i++;
				}
			}
						
			if (!iguales) {
				i = 0;
				List<Byte> estrellas = new ArrayList<Byte>();
				
				while (i < paramEstrellas.size() && !iguales) {
					byte byteNumber = Byte.parseByte(paramEstrellas.get(i));
					
					if (byteNumber < 1 || byteNumber > 12) {
						throw new NumberFormatException("La estrella '" + byteNumber + "' no es válida" );
					} else if (estrellas.contains(byteNumber)) {
						iguales = true;
					} else {
						estrellas.add(byteNumber);
						i++;
					}
				}
				
				if (!iguales) {
					Sorteo nuevoSorteo = new Sorteo();
					
					nuevoSorteo.setFecha_sorteo(new Date(new SimpleDateFormat("dd/MM/yyyy").parse(fechaSorteo).getTime()));
					nuevoSorteo.setNum1(numeros.get(0));
					nuevoSorteo.setNum2(numeros.get(1));
					nuevoSorteo.setNum3(numeros.get(2));
					nuevoSorteo.setNum4(numeros.get(3));
					nuevoSorteo.setNum5(numeros.get(4));
					nuevoSorteo.setEstrella1(estrellas.get(0));
					nuevoSorteo.setEstrella2(estrellas.get(1));
					
					this.euromillonesDao.insertar(nuevoSorteo);
				}
			}
			
		} catch (ParseException e) {
			log.error("El formato de la fecha de sorteo no es legible", e);
		} catch (NumberFormatException e) {
			log.error("Al menos un número o estrella no es numérico", e);
		}
		
		return this.listado(null);
	}
	
	@RequestMapping(value = "/borrar", method = RequestMethod.GET)
	public ModelAndView borrar(@RequestParam(name = "fecha_sorteo") 
							   @DateTimeFormat(pattern = "dd - MM - yyyy") Date fechaSorteo) {
		
		Sorteo sorteoBorrar = new Sorteo();
		
		sorteoBorrar.setFecha_sorteo(fechaSorteo);
		this.euromillonesDao.borrar(sorteoBorrar);
		
		return this.listado(null);
	}
	
	@RequestMapping(value = "/prueba", method = RequestMethod.GET)
	public @ResponseBody ClaveValor pruebaJson() {
		ClaveValor salida = new ClaveValor();
		
		salida.setClave("clave");
		salida.setValor("valor");
		
		return salida;
	}
	
	@Autowired
	public void setEuromillonesDao(EuromillonesDao euromillonesDao) {
		this.euromillonesDao = euromillonesDao;
	}
}
