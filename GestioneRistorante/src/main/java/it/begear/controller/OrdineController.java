package it.begear.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.begear.dao.OrdineDAO;
import it.begear.pojo.Ordine;
import it.begear.pojo.Prodotto;

@Controller
public class OrdineController {

	@Autowired
	private OrdineDAO ordineDAO;

	@RequestMapping(value = "/delOrdine", method = RequestMethod.GET)
	public void delOrdine(@RequestParam("codOrdine") int id) {
		ordineDAO.deleteOrdine(id);
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView getOrdini() {

		ModelAndView model = new ModelAndView("index");
		List<Ordine> lista = ordineDAO.listaOrdini();
		List<Prodotto> listaProdotti = lista.get(0).getProdotti();
		model.addObject("lista", lista);

		return model;
	}

}