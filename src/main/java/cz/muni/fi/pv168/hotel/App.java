package cz.muni.fi.pv168.hotel;

import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
import javax.sql.DataSource;

/**
 * Hello world!
 *
 */
public class App {
    
    private static ResourceBundle resource;
    
    public static void main( String[] args ) {
        
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
            bds.setUrl(resource.getString("url"));
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
