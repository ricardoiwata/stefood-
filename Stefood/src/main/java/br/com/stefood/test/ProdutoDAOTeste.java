package br.com.stefood.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.stefood.dao.ProdutoDAO;
import br.com.stefood.domain.Produto;
import br.com.stefood.domain.Restaurante;

public class ProdutoDAOTeste {
	@Test
	@Ignore
	public void salvar() throws SQLException {
		Produto p = new Produto();
		p.setDescricao("Big Mac");
		p.setPreco(23.50D);
		p.setQuantidade(13L);

		Restaurante r = new Restaurante();
		r.setCodigo(25L);

		p.setRestaurante(r);

		ProdutoDAO dao = new ProdutoDAO();
		dao.salvar(p);
	}

	@Test
	@Ignore
	public void listar() throws SQLException {
		ProdutoDAO dao = new ProdutoDAO();
		ArrayList<Produto> lista = dao.listar();
		
		for(Produto p : lista) {
			System.out.println("Código do produto: " + p.getCodigo());
			System.out.println("Descrição do produto: " + p.getDescricao());
			System.out.println("Preço: " + p.getPreco());
			System.out.println("Quantidade: " + p.getQuantidade());
			System.out.println("Descrição do Restaurante: " + p.getRestaurante().getDescricao());
			System.out.println();
			
		}
	}
	@Test
	public void excluir() throws SQLException {
		Produto p = new Produto();
		p.setCodigo(35L);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.excluir(p);
	}
	
	@Test
	@Ignore 
	public void editar() throws SQLException{
		Produto p = new Produto();
		p.setCodigo(10L);
		p.setDescricao("Whopper");
		p.setPreco(29.90);
		p.setQuantidade(1L);
		
		Restaurante r = new Restaurante();
		r.setCodigo(29L);
		
		p.setRestaurante(r);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.editar(p);
}
}
