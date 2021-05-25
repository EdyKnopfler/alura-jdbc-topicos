package br.com.pip.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	private DataSource dataSource;
	
	public ConnectionFactory() {
		// https://www.mchange.com/projects/c3p0/
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setJdbcUrl("jdbc:postgresql://localhost/alura_jdbc");
		cpds.setUser("alura");
		cpds.setPassword("123456");
		
		cpds.setMaxPoolSize(15);
		
		this.dataSource = cpds;
	}
	
	public Connection recuperarConexao() throws SQLException {
		return dataSource.getConnection();
	}

}
