package cz.muni.fi.pv168.hotel;

import java.util.ResourceBundle;
import javax.sql.DataSource;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	
    private static ResourceBundle resource;
    
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest( String testName ) {
		super( testName );
	}

	/**
	* @return the suite of tests being tested
	*/
	public static Test suite() {
		return new TestSuite( AppTest.class );
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue( true );
	}
    
    @Configuration
    @EnableTransactionManagement
    public static class SpringConfig {
 
        @Bean
        public DataSource dataSource() {
            //Apachce DBCP connection pooling DataSource
            resource = ResourceBundle.getBundle("login");
            BasicDataSource bds = new BasicDataSource();
            bds.setDriverClassName(resource.getString("name"));
            bds.setUrl(resource.getString("testurl"));
            bds.setUsername(resource.getString("user"));
            bds.setPassword(resource.getString("pass"));
            return bds;
        }
 
        @Bean
        public PlatformTransactionManager transactionManager() {
            return new DataSourceTransactionManager(dataSource());
        }
 
        @Bean
        public GuestManager guestManager() {
            return new GuestManagerImpl(dataSource());
        }
 
        @Bean
        public RoomManager roomManager() {
            return new RoomManagerImpl(dataSource());
        }
 
        @Bean
        public HotelManager hotelManager() {
            return new HotelManagerImpl(dataSource());
        }
        
    }
	
}
