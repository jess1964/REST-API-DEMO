package com.cameraiq.technology.directory.Config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
@EnableTransactionManagement
public class DBConfig {

    @Value("${DirectoryDB.datasource.jndiname}")
    private String jndiname;


    @Bean(destroyMethod = "", name = "DirectoryDbSource")
    public DataSource jndiDataSource() throws IllegalArgumentException, NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName(jndiname);
        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(false);
        bean.afterPropertiesSet();
        return (DataSource) bean.getObject();
    }

    @Bean(name = "callDataTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("DirectoryDbSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "DirectorySqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("DirectoryDbSource") DataSource dataSource1) throws Exception {

        //comment out everything below and change the datasource1 above into datasource
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("JMAGX6WiGv");
            dataSource.setPassword("XLFoDCSr02");
            dataSource.setServerName("remotemysql.com");
            dataSource.setDatabaseName("JMAGX6WiGv");
        // comment out everything above
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.cameraiq.technology.directory.port");
        factoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return factoryBean.getObject();
    }
}
