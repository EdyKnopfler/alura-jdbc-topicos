package br.com.pip.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.pip.jdbc.dao.CategoriaDao;
import br.com.pip.jdbc.modelo.Categoria;
import br.com.pip.jdbc.modelo.Produto;

public class TestaListagemCategorias {

	public static void main(String[] args) throws SQLException {
		try (Connection connection = new ConnectionFactory().recuperarConexao()) {
			List<Categoria> lista = new CategoriaDao(connection).listarComProdutos();
			for (Categoria c : lista) {
				System.out.println(c.getId() + " - " + c.getNome());
				for (Produto p : c.getProdutos()) {
					System.out.println("   " + p.getId() + " - " + p.getNome() + 
							           " - " + p.getDescricao());
				}
			}
		}
	}

}
