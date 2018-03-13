package br.usjt.arqsw.service;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.arqsw.dao.FilaDAO;
import br.usjt.arqsw.entity.Fila;

/**
 * 
 * @author Vinícius Neves Pereira - RA:201511080 SIN3AN-MCA1
 */

public class FilaService {
	private FilaDAO dao;
	
	public FilaService() {
		dao = new FilaDAO();
	}
	public ArrayList<Fila> listarFilas() throws IOException{
		return dao.listarFilas();
	}
	
	public Fila carregar(int id) throws IOException{
		return dao.carregar(id);
	}
}
