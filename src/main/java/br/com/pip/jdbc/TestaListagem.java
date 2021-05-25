package br.com.pip.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaListagem {
	
	public static void main(String[] args) throws SQLException {
		Connection conn = new ConnectionFactory().recuperarConexao();
		
		PreparedStatement stm = conn.prepareStatement("SELECT * FROM produto");
		stm.execute();
		
		ResultSet rst = stm.getResultSet();
		
		while (rst.next()) {
			System.out.print(rst.getInt("id") + " - ");
			System.out.print(rst.getString("nome") + " - ");
			System.out.print(rst.getString("descricao"));
			System.out.println();
		}
		
		rst.close();
		stm.close();
		conn.close();
	}

}
