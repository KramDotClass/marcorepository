package it.begear.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.begear.dao.CameriereDAOImpl;
import it.begear.dao.OrdineDAO;
import it.begear.dao.ProdottoDAOImpl;
import it.begear.pojo.Cameriere;
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
		model.addObject("lista", lista);
		return model;
	}

	@RequestMapping(value = "/newOrder", method = RequestMethod.POST)
	public boolean inserisciOrdine(HttpServletRequest request) {
		int codCameriere = Integer.parseInt(request.getParameter("codCameriere"));
		for (int i = 0; i < 100; i++) {
			request.getParameter("text" + i);
		}
		Cameriere cameriere = new CameriereDAOImpl().getCameriere(codCameriere);
		// boolean success = ordineDAO.nuovoOrdine(cameriere, numTavolo, numCoperti, prodotti);
		return true;
	}

	@RequestMapping(value = "/nuovoOrdine")
	public ModelAndView menu() {
		ModelAndView model = new ModelAndView("pages/nuovoOrdine");
		List<Prodotto> listaProdotti = new ProdottoDAOImpl().listaProdotti();
		model.addObject("menu", listaProdotti);
		return model;
	}

}