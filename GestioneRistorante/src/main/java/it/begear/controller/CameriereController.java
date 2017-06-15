package it.begear.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.begear.dao.CameriereDAO;
import it.begear.pojo.Cameriere;

@Controller
public class CameriereController {

	@Autowired
	private CameriereDAO cameriereService;

	@RequestMapping(value = "/delWaiter", method = RequestMethod.GET)
	public String delCameriere(@RequestParam("codCameriere") int id) {
		cameriereService.deleteCameriere(id);
		return "index";
	}

	@RequestMapping(value = "/insertWaiter", method = RequestMethod.GET)
	public boolean insertCameriere(@RequestParam("nome") String nome, @RequestParam("cognome") String cognome) {
		boolean success = cameriereService.creaCameriere(nome, cognome);
		return success;
	}

	@RequestMapping(value = "/viewAllWaiters", method = RequestMethod.GET)
	public ModelAndView getCamerieri() {
		ModelAndView model = new ModelAndView();
		List<Cameriere> listaCamerieri = cameriereService.getCamerieri();
		model.addObject("listaCamerieri", listaCamerieri);
		return model;
	}
}