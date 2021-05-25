package br.com.pip.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.pip.jdbc.dao.ProdutoDao;
import br.com.pip.jdbc.modelo.Produto;

public class TestaInsercaoModelo {

	public static void main(String[] args) throws SQLException {
		Produto comoda = new Produto("Cômoda", "Vertical");
		try (Connection connection = new ConnectionFactory().recuperarConexao()) {
			ProdutoDao persistencia = new ProdutoDao(connection);
			persistencia.salvar(comoda);
			List<Produto> lista = persistencia.listar();
			lista.stream().forEach((p) -> System.out.println(p));
		}
	}

}
