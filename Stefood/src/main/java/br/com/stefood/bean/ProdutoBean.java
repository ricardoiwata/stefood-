package br.com.stefood.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.stefood.dao.ProdutoDAO;
import br.com.stefood.dao.RestauranteDAO;
import br.com.stefood.domain.Produto;
import br.com.stefood.domain.Restaurante;
import br.com.stefood.util.JSFUtil;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {
	private ArrayList<Produto> itens;
	private ArrayList<Produto> itensFiltrados;

	private Produto produto;

	private ArrayList<Restaurante> comboRestaurantes;

	public ArrayList<Produto> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}

	public ArrayList<Produto> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Produto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ArrayList<Restaurante> getComboRestaurantes() {
		return comboRestaurantes;
	}

	public void setComboRestaurantes(ArrayList<Restaurante> comboRestaurantes) {
		this.comboRestaurantes = comboRestaurantes;
	}

	public void carregarListagem() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			itens = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void prepararNovo() {
		try {
			produto = new Produto();

			RestauranteDAO dao = new RestauranteDAO();

			comboRestaurantes = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());

		}
	}

	public void novo() {
		try { 
			ProdutoDAO dao = new ProdutoDAO();
		
		dao.salvar(produto);
		
		itens = dao.listar();
		
		JSFUtil.adicionarMensagemSucesso("Produto salvo com sucesso!");} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemSucesso(ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			
			dao.excluir(produto);
			
			JSFUtil.adicionarMensagemSucesso("Produto removido com sucesso.");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void preparaEditar() {
		try {
			RestauranteDAO dao = new RestauranteDAO();
			
			comboRestaurantes = dao.listar();
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void editar() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			
			dao.editar(produto);
			
			itens = dao.listar();
			
			JSFUtil.adicionarMensagemErro("Produto adicionado com sucesso!");
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}

