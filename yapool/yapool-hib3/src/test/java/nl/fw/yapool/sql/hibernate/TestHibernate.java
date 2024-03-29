package nl.fw.yapool.sql.hibernate;

import static org.junit.Assert.*;

//import java.sql.Connection;

import nl.fw.yapool.PoolEvent;
import nl.fw.yapool.listener.PoolEventQueue;
import nl.fw.yapool.sql.SqlPool;
import nl.fw.yapool.sql.hibernate.Hib3ConnectionProvider;
import static nl.fw.yapool.sql.hibernate.SqlUtil.clearDbInMem;
import static nl.fw.yapool.sql.hibernate.SqlUtil.createTable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestHibernate {

	protected Logger log = LoggerFactory.getLogger(getClass());
	
	/** Use hibernate with a database pool to insert and update a record. 
	 * Tests the Hib4ConnectionProvider. 
	 * <br>To see all Hibernate info, set log-level for the "org" category to INFO instead of WARN.
	 */
	@Test
	public void testHibernateDbInMem() {
		
		/* 
		 * Setup Hibernate configuration manually (usually done via hibernate.cfg.xml).
		 * Note that Hibernate automatic configuration via hibernate.cfg.xml is still very much possible,
		 * the only thing that must be specified in the hibernate.cfg.xml-file
		 * is the Hibernate property "hibernate.connection.provider_class":
		 * props.put(Environment.CONNECTION_PROVIDER, Hib4ConnectionProvider.class.getName());
		*/
		Configuration configuration = new Configuration();
		// Set the dbpool connection provider.
		configuration.setProperty(Environment.CONNECTION_PROVIDER, HibernateTestCP.class.getName());
		configuration.addAnnotatedClass(T.class);
		
		SessionFactory sessionFactory = null;
		Session session = null;
		//Connection c = null;
		SqlPool pool = null;
		PoolEventQueue events = null;
		try {
			sessionFactory = configuration.buildSessionFactory();
			
			// Cannot get a JDBC connection directly (deprecated):
			// clearDbInMem(sessionFactory.getCurrentSession().connection());
			// So instead lookup pool and use connection from there.
			
			pool = Hib3ConnectionProvider.getProvidedPools().get(HibernateTestCP.poolId);
			pool.getEvents().addPoolListener(events = new PoolEventQueue());
			events.register = true;
			//events.logEvent= true;
			
			clearDbInMem(pool);
			
			assertEquals("Database connections must not be used", 1, pool.getIdleSize());
			session = sessionFactory.openSession();
			session.createSQLQuery(createTable).executeUpdate();
			session.getTransaction().begin();
			T t = new T();
			t.setName("Frederik");
			session.persist(t);
			session.getTransaction().commit();
			session.getTransaction().begin();
			t.setName("Frederik Wiers");
			session.getTransaction().commit();
			session.close();
			long id = t.getId();
			session = sessionFactory.openSession();
			t = (T)session.load(T.class, id);
			assertEquals("Name must match last saved value", "Frederik Wiers", t.getName());
			session.close();
			assertEquals("Database connection must be released", 1, pool.getIdleSize());
			assertTrue(events.getCount(PoolEvent.ACQUIRED) > 1);
			assertEquals(events.getCount(PoolEvent.ACQUIRED), events.getCount(PoolEvent.RELEASING));
			//log.debug(events.toString());
		} catch (Exception se) {
			se.printStackTrace();
			throw new AssertionError(se);
		} finally {
			Exception error = null;
			if (session != null) try { session.close(); } catch (Exception e) {
				if (!"Session was already closed".equals(e.getMessage())) {
					e.printStackTrace();
					error = e;
				}
			}
			if (sessionFactory != null) {
				try { 
					sessionFactory.close(); 
				} catch (Exception e) {
					e.printStackTrace();
					error = e;
				}
				assertTrue(pool.isClosed());
			}
			Hib3ConnectionProvider.getProvidedPools().removePool(HibernateTestCP.poolId);
			if (error != null) 
				throw new AssertionError("Hibernate DbInMem test could not properly cleanup: " + error);
		}
	}
}
