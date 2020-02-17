package br.com.soma.contadigital.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.soma.contadigital.portal.model.Funcionario;
import br.com.soma.contadigital.portal.repository.FuncionarioRepository;

@Controller
@RequestMapping("/soma/funcionarios")
public class FuncionarioController {

	private static final String CADASTRO_FUNCIONARIO = "funcionario/cadastro_funcionario";
	
	@Autowired
	private FuncionarioRepository repository;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		return new ModelAndView(CADASTRO_FUNCIONARIO).addObject(new Funcionario());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Funcionario funcionario, Errors errors,RedirectAttributes attributes) {
		
		if (errors.hasErrors()) {
			return CADASTRO_FUNCIONARIO;
		}
		repository.save(funcionario);
		attributes.addFlashAttribute("mensagem", "Funcionário salvo com sucesso!");
		return "redirect:/soma/funcionarios/novo";
	}
	
	@RequestMapping
	public ModelAndView listarFuncionarios() {
		return new ModelAndView("funcionario/listar_funcionarios")
				.addObject("listaFuncionarios", repository.findAll());
	}
	
	@RequestMapping("{id}")
	public ModelAndView atualizarFuncionario(@PathVariable("id") Funcionario funcionario) {
		return new ModelAndView(CADASTRO_FUNCIONARIO).addObject(funcionario);
	}
	
}
