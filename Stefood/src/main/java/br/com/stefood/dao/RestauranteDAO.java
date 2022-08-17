package br.com.stefood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.stefood.domain.Restaurante;
import br.com.stefood.factory.ConexaoFactory;

public class RestauranteDAO {
	public void salvar(Restaurante r) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO restaurante ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, r.getDescricao());

		comando.executeUpdate();

	}

	public void excluir(Restaurante r) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM restaurante ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, r.getCodigo());

		comando.executeUpdate();
	}

	public void editar(Restaurante r) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE restaurante ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, r.getDescricao());
		comando.setLong(2, r.getCodigo());

		comando.executeUpdate();

	}

	public Restaurante buscarPorCodigo(Restaurante r) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo descricao ");
		sql.append("FROM fabricante");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, r.getCodigo());

		ResultSet resultado = comando.executeQuery();

		Restaurante retorno = null;

		if (resultado.next()) {
			retorno = new Restaurante();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}

		return retorno;
	}

	public ArrayList<Restaurante> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM restaurante ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Restaurante> lista = new ArrayList<Restaurante>();

		while (resultado.next()) {
			Restaurante r = new Restaurante();
			r.setCodigo(resultado.getLong("codigo"));
			r.setDescricao(resultado.getString("descricao"));

			lista.add(r);
		}
		return lista;

	}

	public ArrayList<Restaurante> buscarPorDescricao(Restaurante r) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM restaurante ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, "%" + r.getDescricao() + "%");
		ResultSet resultado = comando.executeQuery();

		ArrayList<Restaurante> lista = new ArrayList<Restaurante>();

		while (resultado.next()) {
			Restaurante item = new Restaurante();
			item.setCodigo(resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("descricao"));

			lista.add(item);
		}
		return lista;

	}

	public static void main(String[] args) {
		
	}
}
