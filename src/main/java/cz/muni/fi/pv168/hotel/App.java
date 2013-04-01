package cz.muni.fi.pv168.hotel;

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
    
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
    }
    
    @Configuration
    @EnableTransactionManagement
    public static class SpringConfig {
 
        @Bean
        public DataSource dataSource() {
            //Apachce DBCP connection pooling DataSource
            BasicDataSource bds = new BasicDataSource();
            bds.setDriverClassName("com.mysql.jdbc.Driver");
            bds.setUrl("jdbc:mysql://localhost:80/hotel");
            bds.setUsername("hotel");
            bds.setPassword("hotel");
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
