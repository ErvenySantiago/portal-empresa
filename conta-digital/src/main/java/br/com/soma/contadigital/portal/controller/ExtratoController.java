package br.com.soma.contadigital.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.soma.contadigital.portal.model.Extrato;
import br.com.soma.contadigital.portal.repository.ExtratroRepository;
import br.com.soma.contadigital.portal.service.ExtratoService;

@Controller
@RequestMapping("/soma")
public class ExtratoController {

	private static final String CADASTRO_EXTRATO = "extrato/cadastro_extrato";

	@Autowired
	private ExtratroRepository repository; 
	
	@Autowired
	private ExtratoService service;
	
	@GetMapping
	public ModelAndView listarExtratos() {
		List<Extrato> extratos = repository.findAll();
		return new ModelAndView("extrato/index")
				.addObject("listaExtratos", extratos)
				.addObject("saldoTotal", service.calculoSaldo(extratos));
	}
	
	@RequestMapping("/extratos/novo")
	public ModelAndView novo() {
		return new ModelAndView(CADASTRO_EXTRATO).addObject(new Extrato());
	}
	
	@RequestMapping(value = "/extratos" ,method = RequestMethod.POST)
	public String salvar(@Validated Extrato extrato, Errors errors, RedirectAttributes attributes) {
		
		if (errors.hasErrors()) {
			return CADASTRO_EXTRATO;
		}
		repository.save(extrato);
		attributes.addFlashAttribute("mensagem", "Extrato salvo com sucesso!");
		return "redirect:/soma/extratos/novo";
	}
	
}
