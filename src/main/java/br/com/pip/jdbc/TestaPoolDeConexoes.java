package br.com.pip.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaPoolDeConexoes {
	
	public static void main(String[] args) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		
		for (int i = 1; i <= 20; i++) {
			System.out.println("Recuperando " + i);
			Connection connection = factory.recuperarConexao();
			
			// https://www.programmersought.com/article/52065486872/
			connection.close();
		}
	}

}
