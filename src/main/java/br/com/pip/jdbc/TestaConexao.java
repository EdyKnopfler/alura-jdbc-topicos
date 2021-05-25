package br.com.pip.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {
	
	public static void main(String[] args) throws SQLException {
		System.out.println("Conectando...");
		Connection connection = new ConnectionFactory().recuperarConexao();
		System.out.println("Fechando conexão...");
		connection.close();
		System.out.println("Pronto.");
	}

}
