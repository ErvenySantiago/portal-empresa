package br.com.soma.contadigital.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.soma.contadigital.portal.model.Extrato;
import br.com.soma.contadigital.portal.repository.ExtratroRepository;

@Controller
@RequestMapping("/soma")
public class ExtratoController {

	private static final String CADASTRO_EXTRATO = "extrato/cadastro_extrato";
	@Autowired
	private ExtratroRepository repository; 
	
	@GetMapping
	public ModelAndView listarExtratos() {
		return new ModelAndView("extrato/index")
				.addObject("listaExtratos", repository.findAll());
	}
	
	@RequestMapping("/extratos/novo")
	public ModelAndView novo() {
		return new ModelAndView(CADASTRO_EXTRATO).addObject(new Extrato());
	}
	
}
