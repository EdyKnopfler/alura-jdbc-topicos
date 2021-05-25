package br.com.pip.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.pip.jdbc.modelo.Produto;

public class ProdutoDao {
	
	private Connection connection;

	public ProdutoDao(Connection connection) {
		this.connection = connection;
	}
	
	public void salvar(Produto produto) throws SQLException {
		String sql = "INSERT INTO PRODUTO (nome, descricao) " +
                     "VALUES (?, ?)";
		
		try (PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stm.setString(1, produto.getNome());
			stm.setString(2, produto.getDescricao());
			
			stm.execute();
			
			try (ResultSet rs = stm.getGeneratedKeys()) {
				while (rs.next()) {
					produto.setId(rs.getInt(1));
				}
			}
		}
	}
	
	public List<Produto> listar() throws SQLException {
		List<Produto> produtos = new ArrayList<>();
		String sql = "SELECT * FROM produto";
		
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.execute();
			
			try (ResultSet rs = stm.getResultSet()) {
				while (rs.next()) {
					Produto p = new Produto(rs.getInt("id"),
							rs.getString("nome"), rs.getString("descricao"));
					produtos.add(p);
				}
			}
		}
		
		return produtos;
	}

}
