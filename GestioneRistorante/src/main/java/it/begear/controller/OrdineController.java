package it.begear.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import it.begear.dao.CameriereDAOImpl;
import it.begear.dao.OrdineDAO;
import it.begear.dao.OrdineDAOImpl;
import it.begear.dao.ProdottoDAOImpl;
import it.begear.pojo.Cameriere;
import it.begear.pojo.Ordine;
import it.begear.pojo.Prodotto;

@Controller
public class OrdineController {

	@Autowired
	private OrdineDAO ordineDAO;

	@RequestMapping(value = "/delOrdine", method = RequestMethod.GET)
	public String delOrdine(@RequestParam("codOrdine") int id) {
		ordineDAO.deleteOrdine(id);
		return "redirect:index";
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
		int i = 0;
		double totale = 0;
		List<Prodotto> listaProdotti = new ProdottoDAOImpl().listaProdotti();
		List<Prodotto> prodotti = new ArrayList<Prodotto>();
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

	@RequestMapping(value = "/ordine/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ModelAndView orderDetail(@PathVariable("id") int id) {
		ModelAndView model = new ModelAndView("pages/dettaglioOrdine");
		OrdineDAOImpl orDAO = new OrdineDAOImpl();
		List<Prodotto> prodotti = orDAO.getOrdine(id).getProdotti();
		Map<String, Integer> mappaProdotti = new HashMap<String, Integer>();
		for (Prodotto p : prodotti) {
			if (mappaProdotti.containsKey(p.getNome())) {
				Integer value = mappaProdotti.get(p.getNome());
				value++;
				mappaProdotti.put(p.getNome(), value);
			} else {
				mappaProdotti.put(p.getNome(), 1);
			}
		}
		model.addObject("lista", mappaProdotti);
		return model;
	}

}