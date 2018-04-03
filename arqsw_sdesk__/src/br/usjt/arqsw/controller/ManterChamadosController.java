package br.usjt.arqsw.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.service.ChamadoService;
import br.usjt.arqsw.service.FilaService;

/**
 * 
 *  @author Vin�cius Neves Pereira - R.A.: 201511080 - Turma:SIN3AN-MCA
 */

@Controller
public class ManterChamadosController {
	private FilaService filaService;
	private ChamadoService chamadoService;
	
	@Autowired
	public ManterChamadosController(FilaService filaService, ChamadoService chamadoService) {
		this.filaService = filaService;
		this.chamadoService = chamadoService;
	}

	
	@RequestMapping("index")
	public String inicio() {
		return "index";
	}

	
	@RequestMapping("/tela_inicio")
	public String inicio2() {
		return "TelaPrincipal";
	}
	
	
	@Transactional
	private List<Fila> listarFilas() throws IOException{
			return filaService.listarFilas();
	}
	
	
	@Transactional
	private List<Chamado> listarChamados() throws IOException{
		return chamadoService.listarChamados();
	}
	
	
	@Transactional
	private void cadastrarChamado(String desc, Fila fila) throws IOException{
		Chamado chamado = new Chamado();
		Date d = new Date();
		chamado.setDescricao(desc);
		chamado.setFila(fila);
		chamado.setStatus("ABERTO");
		chamado.setDt_abertura(d);
		chamadoService.novoChamado(chamado);
	}
	
	
	@Transactional
	@RequestMapping("/listar_filas_exibir")
	public String listarFilasExibir(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "ChamadoListar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	
	@Transactional
	@RequestMapping("/cadastrar_chamado")
	public String cadastrarChamado(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "CadastrarChamado";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	 
	@Transactional
	@RequestMapping("/chamado_cadastrado")
	public String chamadoCadastrado(String desc, Fila fila) {
		try {
			cadastrarChamado(desc, fila);
			return "ChamadoCadastrado";		
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	
	@Transactional
	@RequestMapping("/listar_chamados_exibir")
	public String listarChamadosExibir(@Valid Fila fila, BindingResult result, Model model) {
		try {
			if (result.hasFieldErrors("id")) {
				model.addAttribute("filas", listarFilas());
				System.out.println("Deu erro " + result.toString());
				return "ChamadoListar";
				//return "redirect:listar_filas_exibir";
			}
			fila = filaService.carregar(fila.getId());
			model.addAttribute("chamados", listarChamados());
			
			return "ChamadoListarExibir";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@Transactional
	@RequestMapping("/fechar_chamado")
	public String fecharChamado(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "FecharChamado";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@Transactional
	@RequestMapping("/chamado_fechado")
	public String chamadoFechado(String desc, Fila fila) {
		try {
			cadastrarChamado(desc, fila);
			return "ChamadoFechado";		
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
}
