package it.begear.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.begear.dao.CameriereDAOImpl;
import it.begear.pojo.Cameriere;

@Controller
public class CameriereController {
	private CameriereDAOImpl cameriereService;

	@RequestMapping(value = "/delCameriere", method = RequestMethod.GET)
	public String delCameriere(@RequestParam("codCameriere") int id) {
		cameriereService.deleteCameriere(id);
		return "index";
	}
}