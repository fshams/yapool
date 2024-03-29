package nl.fw.yapool.sql.datasource;

import java.util.Properties;

import static nl.fw.yapool.BeanConfig.*;

import nl.fw.yapool.BeanConfig;
import nl.fw.yapool.sql.SqlFactory;

/**
 * Builds a {@link SqlProxyPool} for a datasource.
 * @author Fred
 *
 */
public class HsqlPoolBuilder implements IPoolBuilder {

	/** jdbc:hsqldb:mem:test */ 
	public static final String JDBC_URL_TEST_IN_MEM = "jdbc:hsqldb:mem:test";
	/** org.hsqldb.jdbc.JDBCDriver */
	public static final String JDBC_DRIVER_CLASS = "org.hsqldb.jdbc.JDBCDriver";

	@Override
	public SqlProxyPool buildPool(Properties props) {

		SqlProxyPool pool = new SqlProxyPool();
		pool.setFactory(new SqlFactory());
		putIfNotExists(props, "poolName", "HsqlTestDb");
		putIfNotExists(props, "jdbcUrl", JDBC_URL_TEST_IN_MEM);
		putIfNotExists(props, "jdbcDriverClass", JDBC_DRIVER_CLASS);
		putIfNotExists(props, "connection.user", "SA");
		putIfNotExists(props, "connection.password", "");
		BeanConfig.configure(pool, props);
		BeanConfig.configure(pool.getFactory(), props);
		pool.open();
		return pool;
	}

}
