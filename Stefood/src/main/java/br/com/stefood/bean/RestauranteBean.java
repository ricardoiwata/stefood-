package br.com.stefood.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.stefood.dao.RestauranteDAO;
import br.com.stefood.domain.Restaurante;
import br.com.stefood.util.JSFUtil;

@ManagedBean(name = "MBRestaurante")
@ViewScoped
public class RestauranteBean {
	private Restaurante restaurante;
	private ArrayList<Restaurante> itens;
	private ArrayList<Restaurante> itensFiltrados;

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public ArrayList<Restaurante> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Restaurante> itens) {
		this.itens = itens;
	}

	public ArrayList<Restaurante> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Restaurante> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa() {
		try {
			RestauranteDAO dao = new RestauranteDAO();
			itens = dao.listar();

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void prepararNovo() {
		restaurante = new Restaurante();
	}

	public void novo() {
		try {
			RestauranteDAO dao = new RestauranteDAO();
			dao.salvar(restaurante);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Restaurante adicionado com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}



	public void excluir() {
		try {
			RestauranteDAO dao = new RestauranteDAO();
			dao.excluir(restaurante);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Restaurante removido com sucesso");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}



	public void editar() {
		try {
			RestauranteDAO dao = new RestauranteDAO();
			dao.editar(restaurante);

			itens = dao.listar();
	

			JSFUtil.adicionarMensagemSucesso("Restaurante editado com sucesso.");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
