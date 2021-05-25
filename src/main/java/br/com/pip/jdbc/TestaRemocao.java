package br.com.pip.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {
		Connection conn = new ConnectionFactory().recuperarConexao();
		
		String nome = "Mouse";
		String descricao = "Mouse sem fio";
		
		PreparedStatement stm = conn.prepareStatement(
				"DELETE FROM produto WHERE nome = ? " +
	            "AND descricao = ?");
		stm.setString(1, nome);
		stm.setString(2, descricao);
		stm.execute();
		System.out.println(stm.getUpdateCount());
		
		stm.close();
		conn.close();
	}

}
