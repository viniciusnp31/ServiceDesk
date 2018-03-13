package br.usjt.arqsw.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import br.usjt.arqsw.dao.ChamadoDAO;
import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;

/**
 * 
 * @author Vinícius Neves Pereira - RA:201511080 SIN3AN-MCA1
 */


public class ChamadoService {
	private ChamadoDAO dao;
	
	public ChamadoService(){
		this.dao = new ChamadoDAO();
	}
	
	public int criarChamado(Chamado chamado) throws IOException{
		chamado.setStatus(Chamado.ABERTO);
		chamado.setDataAbertura(new Date());
		chamado.setDataFechamento(null);
		
		return dao.criarChamado(chamado);
	}
	
	public ArrayList<Chamado> listarChamados(Fila fila) throws IOException{
		return dao.listarChamados(fila);	
	}
}
