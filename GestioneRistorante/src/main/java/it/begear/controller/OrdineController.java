package it.begear.controller;

import java.util.ArrayList;
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
	public String inserisciOrdine(HttpServletRequest request) {
		int codCameriere = Integer.parseInt(request.getParameter("codCameriere"));
		int numTavolo = Integer.parseInt(request.getParameter("numTavolo"));
		int numCoperti = Integer.parseInt(request.getParameter("numCoperti"));
		int quantity;
		List<Prodotto> listaProdotti = new ProdottoDAOImpl().listaProdotti();
		List<Prodotto> prodotti = new ArrayList<Prodotto>();
		int i = 0;
		double totale = 0;
		for (Prodotto p : listaProdotti) {
			quantity = Integer.parseInt(request.getParameter("quantity" + i));
			for (int j = 0; j < quantity; j++) {
				prodotti.add(p);
				totale += p.getPrezzo();
			}
			i++;
		}
		Cameriere cameriere = new CameriereDAOImpl().getCameriere(codCameriere);
		ordineDAO.nuovoOrdine(cameriere, numTavolo, numCoperti, prodotti, totale);
		return "redirect:index";
	}

	@RequestMapping(value = "/nuovoOrdine")
	public ModelAndView menu() {
		ModelAndView model = new ModelAndView("pages/nuovoOrdine");
		List<Prodotto> listaProdotti = new ProdottoDAOImpl().listaProdotti();
		model.addObject("menu", listaProdotti);
		return model;
	}

}