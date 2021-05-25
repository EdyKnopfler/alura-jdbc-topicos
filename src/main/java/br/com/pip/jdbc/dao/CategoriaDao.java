package br.com.pip.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.pip.jdbc.modelo.Categoria;
import br.com.pip.jdbc.modelo.Produto;

public class CategoriaDao {

	private Connection connection;

	public CategoriaDao(Connection connection) {
		this.connection = connection;
	}

	public List<Categoria> listar() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		
		String sql = "SELECT * FROM categoria";
		
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.execute();
			
			try (ResultSet rs = stm.getResultSet()) {
				while (rs.next()) {
					Categoria c = new Categoria(rs.getInt(1), rs.getString(2));
					categorias.add(c);
				}
			}
		}
		
		return categorias;
	}

	public List<Categoria> listarComProdutos() throws SQLException {
		HashMap<Integer, Categoria> ids = new HashMap<>();
		List<Categoria> categorias = new ArrayList<>();
		
		String sql = "SELECT c.id, c.nome, p.id, p.nome, p.descricao " + 
		             "FROM categoria c " + 
		             "INNER JOIN produto p " + 
				     "   ON p.categoria_id = c.id";
		
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.execute();
			
			try (ResultSet rs = stm.getResultSet()) {
				while (rs.next()) {
					Categoria cat = ids.get(rs.getInt(1));
					if (cat == null) {
						cat = new Categoria(rs.getInt(1), rs.getString(2));
						ids.put(rs.getInt(1), cat);
						categorias.add(cat);
					}
					Produto p = new Produto(rs.getInt(3), rs.getString(4), rs.getString(5));
					cat.adicionar(p);
				}
			}
		}
		
		return categorias;
	}

}
