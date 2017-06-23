package it.begear.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

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
import it.begear.model.Cameriere;
import it.begear.model.Ordine;
import it.begear.model.Prodotto;

@Controller
public class OrdineController {
	
	static String msg = " ";
	
	@Autowired
	private OrdineDAO ordineDAO;

	private final static String COD_ORDINE = "codOrdine";

	@RequestMapping(value = "/delOrdine", method = RequestMethod.POST)
	public String delOrdine(@RequestParam(COD_ORDINE) int id) {
		ordineDAO.deleteOrdine(id);
		return "redirect:index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView getOrdini(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("index");
		String msg = (String) request.getParameter("msg");
		System.out.println(msg);
		List<Ordine> lista = ordineDAO.listaOrdini();
		model.addObject("lista", lista);
		model.addObject("msg", msg);
		return model;
	}

	@RequestMapping(value = "/newOrder", method = RequestMethod.POST)
	public ModelAndView inserisciOrdine(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("redirect:index");
		int codOrdine;
		if (!"".equals(request.getParameter(COD_ORDINE)))
			codOrdine = Integer.parseInt(request.getParameter(COD_ORDINE));
		else
			codOrdine = 0;
		int codCameriere = Integer.parseInt(request.getParameter("codCameriere"));
		int numTavolo = Integer.parseInt(request.getParameter("numTavolo"));
		int numCoperti = Integer.parseInt(request.getParameter("numCoperti"));
		int quantity;
		int acc = 0;
		double totale = 0;
		List<Prodotto> listaProdotti = new ProdottoDAOImpl().listaProdotti();
		List<Prodotto> prodotti = new ArrayList<>();
		for (Prodotto p : listaProdotti) {
			quantity = Integer.parseInt(request.getParameter("quantity" + p.getNome()));
			acc += quantity;
			for (int j = 0; j < quantity; j++) {
				prodotti.add(p);
				totale += p.getPrezzo();
			}
		}
		Cameriere cameriere = new CameriereDAOImpl().getCameriere(codCameriere);
		if (acc > 0) {
			ordineDAO.nuovoOrdine(cameriere, numTavolo, numCoperti, prodotti, totale, codOrdine);
			msg = "Ordine inserito";
		} else {
			msg = "Ordine vuoto";
		}

		model.addObject("msg", msg);
		return model;

	}

	@RequestMapping(value = "/nuovoOrdine")
	public ModelAndView menu() {
		ModelAndView model = new ModelAndView("pages/nuovoOrdine");
		List<Prodotto> listaProdotti = new ProdottoDAOImpl().listaProdotti();
		Map<Prodotto, Integer> mappaProdotti = new TreeMap<>();
		for (Prodotto p : listaProdotti) {
			mappaProdotti.put(p, 0);
		}
		model.addObject("menu", mappaProdotti);
		return model;
	}

	@RequestMapping(value = "/ordine/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ModelAndView orderDetail(@PathVariable("id") int id) {
		ModelAndView model = new ModelAndView("pages/dettaglioOrdine");
		List<Prodotto> prodotti = ordineDAO.getOrdine(id).getProdotti();
		Map<Prodotto, Integer> mappaProdotti = new TreeMap<>();
		for (Prodotto p : prodotti) {
			if (mappaProdotti.containsKey(p)) {
				Integer value = mappaProdotti.get(p);
				value++;
				mappaProdotti.put(p, value);
			} else {
				mappaProdotti.put(p, 1);
			}
		}
		model.addObject("ordine", ordineDAO.getOrdine(id));
		model.addObject("lista", mappaProdotti);
		return model;
	}

	@RequestMapping(value = "/updatePage", method = RequestMethod.POST)
	public ModelAndView modificaOrdine(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("pages/nuovoOrdine");
		int codOrdine = Integer.parseInt(request.getParameter("codOrdine"));
		Ordine ordine = ordineDAO.getOrdine(codOrdine);
		List<Prodotto> listaProdotti = new ProdottoDAOImpl().listaProdotti();
		List<Prodotto> prodotti = ordineDAO.getOrdine(codOrdine).getProdotti();
		Map<Prodotto, Integer> mappaProdotti = new TreeMap<>();
		for (Prodotto p : listaProdotti) {
			mappaProdotti.put(p, 0);
			for (int i = 0; i < prodotti.size(); i++) {
				if (prodotti.get(i).getNome().equals(p.getNome())) {
					prodotti.set(i, p);
				}
			}
		}
		for (Prodotto p : prodotti) {
			Integer value = mappaProdotti.get(p);
			value++;
			mappaProdotti.put(p, value);
		}
		model.addObject("menu", mappaProdotti);
		model.addObject("ordine", ordine);
		return model;
	}

}