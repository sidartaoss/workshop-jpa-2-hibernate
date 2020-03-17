/**
 * 
 */
package com.algaworks.gerenciador.util.jpa;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.engine.config.spi.ConfigurationService;
import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;

/**
 * @author SEMPR
 *
 */
public class MultiTenantProvider implements MultiTenantConnectionProvider, ServiceRegistryAwareService {

	private static final long serialVersionUID = 1L;
	
	private DriverManagerConnectionProviderImpl connectionProvider;

	@SuppressWarnings("rawtypes")
	@Override
	public boolean isUnwrappableAs(Class unwrapType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> unwrapType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		return this.connectionProvider.getConnection();
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		try {
			connection.createStatement().execute("use imperio_rodas");
		} catch (SQLException e) {
			throw new HibernateException("Could not alter JDBC connection to specified schema [public] 	", e);
		}
		this.connectionProvider.closeConnection(connection);
	}

	@Override
	public Connection getConnection(String tenantIdentifier) throws SQLException {
		final Connection connection = this.getAnyConnection();
		try {
			connection.createStatement().execute("use " + tenantIdentifier);
		} catch (SQLException e) {
			throw new HibernateException("Could not alter JDBC connection to specified schema [" + tenantIdentifier + "]", e);
		}
		return connection;
	}

	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
		this.releaseAnyConnection(connection);
	}

	@Override
	public boolean supportsAggressiveRelease() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void injectServices(ServiceRegistryImplementor serviceRegistry) {
		Map lSettings = serviceRegistry.getService(ConfigurationService.class).getSettings();
		
		this.connectionProvider = new DriverManagerConnectionProviderImpl();
		this.connectionProvider.injectServices(serviceRegistry);
		this.connectionProvider.configure(lSettings);
	}

}
