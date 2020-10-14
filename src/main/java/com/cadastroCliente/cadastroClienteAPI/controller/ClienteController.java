package com.cadastroCliente.cadastroClienteAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cadastroCliente.cadastroClienteAPI.model.Cliente;
import com.cadastroCliente.cadastroClienteAPI.repository.Clientes;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	private static final String CADASTRO_VIEW = "CadastroCliente";
	
	@Autowired
	private Clientes clientes;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Cliente());
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Cliente cliente, Errors errors, RedirectAttributes attributes){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		
		if(errors.hasErrors()) {
			return mv;
		}
		clientes.save(cliente);
		
		ModelAndView mv2 = new ModelAndView("redirect:/clientes/novo");
		attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!");
		
		return mv2;
	}
	
	@RequestMapping("/pesquisarClientes")
	public ModelAndView pesquisar() {
		List<Cliente> todosClientes = clientes.findAll();
		ModelAndView mv = new ModelAndView("PesquisaClientes");
		mv.addObject("clientes", todosClientes);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView inicio() {
		ModelAndView mv = new ModelAndView("Home");
		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Cliente cliente) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(cliente);
		System.out.println("Edicao");
		return mv;
	}
	
	@RequestMapping("/DELETE/{id}"/* , value="{codigo}", method = RequestMethod.DELETE */)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		clientes.deleteById(id);
		
		attributes.addFlashAttribute("mensagem", "Cliente excluído com sucesso!");
		return "redirect:/clientes";
	}
	
}
