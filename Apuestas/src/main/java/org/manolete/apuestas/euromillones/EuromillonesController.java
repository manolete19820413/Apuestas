package org.manolete.apuestas.euromillones;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
// @RequestMapping("/euromillones")
public class EuromillonesController {
	
	private EuromillonesDao euromillonesDao;
	
	private static final Log log = LogFactory.getLog(EuromillonesController.class);
	
	@RequestMapping(value = "/hola")
	public ModelAndView diHola(HttpServletRequest req) {
		log.info("Devolviendo la vista hola.jsp");
		
		return new ModelAndView("hola");		
	}
	
	@RequestMapping(value = "/listado")
	public ModelAndView listado(HttpServletRequest req) {
		log.info("Devolviendo la lista de sorteos");
		
		ModelAndView mv = new ModelAndView("euromillones/lista");
		
		mv.getModel().put("sorteos", this.euromillonesDao.findAll());
		
		return mv;
	}
	
	@Autowired
	public void setEuromillonesDao(EuromillonesDao euromillonesDao) {
		this.euromillonesDao = euromillonesDao;
	}
}
