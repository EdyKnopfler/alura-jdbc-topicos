package br.com.pip.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {
		try (Connection conn = new ConnectionFactory().recuperarConexao()) {
			conn.setAutoCommit(false);
			
			try (
				PreparedStatement pst = conn.prepareStatement("INSERT INTO produto (nome, descricao) " +
				            "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
			) {
				
				novoProduto("Mouse", "Mouse sem fio", pst);
				novoProduto("Smart TV", "Tevê intelijegue", pst);
				
				conn.commit();
				//pst.close();  // Autocloseable
			} catch (Exception e) {
				conn.rollback();
				System.out.println("Rollback executado");
				e.printStackTrace();
			}
		}
		//conn.close();
	}

	private static void novoProduto(String nome, String descricao, PreparedStatement pst) throws SQLException {
		/*
		if (nome.equals("Smart TV")) {
			throw new RuntimeException("Não estamos aceitando inserções de SmartTVs");
		}
		*/
		
		pst.setString(1, nome);
		pst.setString(2, descricao);
		
		boolean resultado = pst.execute();
		System.out.println(resultado);
		
		try (ResultSet rst = pst.getGeneratedKeys() ) {
			while (rst.next()) {
				System.out.println(rst.getInt(1));
			}
		}
	}

}
