package br.com.stefood.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	private static final String USUARIO = "root";
	private static final String SENHA = "ricardops22011";
	private static final String URL = "jdbc:mysql://localhost:3306/stefood";
	
	public static Connection conectar() throws SQLException {
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}
	
	public static void main(String[] args) {
		try {
			Connection conexao = ConexaoFactory.conectar();
		System.out.println("Conexão Realizada com sucesso!");
		} catch (SQLException ex) {
			System.out.println("Não foi possível realizar a conexão!");
			ex.printStackTrace();
		}
	}
}
