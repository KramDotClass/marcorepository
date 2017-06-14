package it.begear.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.begear.dao.ProdottoDAOImpl;

@Controller
public class ProdottoController {
	private ProdottoDAOImpl prodottoService;
	
	@RequestMapping(value = "/delProdotto", method = RequestMethod.GET)
	public void delOrdine(@RequestParam("nome") String nome) {
		prodottoService.deleteProdotto(nome);
	}
}