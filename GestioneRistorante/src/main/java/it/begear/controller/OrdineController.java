package it.begear.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.begear.dao.OrdineDAOImpl;
import it.begear.pojo.Ordine;


@Controller
public class OrdineController {
	
    @Autowired
    private OrdineDAOImpl ordineDAO;

	@RequestMapping(value = "/delOrdine", method = RequestMethod.GET)
	public void delOrdine(@RequestParam("codOrdine") int id) {
	    ordineDAO.deleteOrdine(id);
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView getOrdini() {
	   
	    ModelAndView model = new ModelAndView("index");
		List<Ordine> lista = ordineDAO.listaOrdini();
		model.addObject("lista",lista);
		
		return model;
	}
	
	
}