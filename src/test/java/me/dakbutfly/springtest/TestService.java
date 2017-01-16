package me.dakbutfly.springtest;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.junit.BeforeClass;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by khk on 2017-01-16.
 */
public class TestService {
    protected static EntityManagerFactory emf;

    protected static DataSource dataSource() {
        final BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:jpashop");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
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

    protected static EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties){
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource( dataSource );
        em.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
        em.setJpaProperties( hibernateProperties );
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.setPackagesToScan("me.dakbutfly.domain");
        em.setPersistenceUnitName( "mytestdomain" );
        em.afterPropertiesSet();

        return em.getObject();
    }

    @BeforeClass
    public static void start() {
        emf = entityManagerFactory(dataSource(), hibernateProperties());
    }
}
