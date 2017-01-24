package me.dakbutfly.springtest;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.SharedEntityManagerCreator;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by khk on 2017-01-16.
 */
public class TestService {
    protected static EntityManagerFactory emf;
    private static LocalContainerEntityManagerFactoryBean em;

    protected static DataSource dataSource() {
        final BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:jpashop");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }


    protected static EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties){
        return em.getObject();
    }

    protected static void readyLocalContainerEntityFactoryBean(DataSource dataSource, Properties hibernateProperties) {
        em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource( dataSource );
        setLogLevelInfo();
        em.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
        em.setJpaProperties( hibernateProperties );
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.setPackagesToScan("me.dakbutfly.domain");
        em.setPersistenceUnitName( "mytestdomain" );
        em.afterPropertiesSet();
    }

    private static void setLogLevelInfo() {
        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);
    }

    protected static Properties hibernateProperties(){
        final Properties properties = new Properties();

        properties.put( "hibernate.dialect", "org.hibernate.dialect.H2Dialect" );
        properties.put( "hibernate.hbm2ddl.auto", "update" );
        properties.put( "hibernate.show_sql", "false" );
        properties.put( "hibernate.format_sql", "false" );
        properties.put( "hibernate.use_sql_comments", "false" );

        return properties;
    }

    protected static EntityManager getEntityManager(DataSource dataSource, Properties hibernateProperties) {
        return SharedEntityManagerCreator.createSharedEntityManager(em.getObject(), hibernateProperties(), true);
    }

    public static void readyForJPATest(){
        DataSource dataSource = dataSource();
        Properties hibernateProperties = hibernateProperties();
        readyLocalContainerEntityFactoryBean(dataSource, hibernateProperties);
        emf = entityManagerFactory(dataSource, hibernateProperties);
    }
}
