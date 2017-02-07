package org.manolete.apuestas.euromillones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@Autowired
	public void setEuromillonesDao(EuromillonesDao euromillonesDao) {
		this.euromillonesDao = euromillonesDao;
	}
}
