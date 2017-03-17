package com.vb.mafia.core.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.vb.mafia.core.constant.ConfigConstant;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(basePackages = ConfigConstant.REG_DS_MASTER, sqlSessionFactoryRef = ConfigConstant.NAME_DS_SSF_MASTER)
public class DsMasterConfig {

    private static Logger logger = LoggerFactory.getLogger(DsMasterConfig.class);

    @Autowired
    private Environment env;

    @Bean(name = ConfigConstant.NAME_DS_MASTER)
    @Primary
    @ConfigurationProperties(prefix = ConfigConstant.PREFIX_DS_MASTER)
    public DataSource mafMasterDataSource() {
        logger.info("----- MAFIA master data source INIT -----");
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl(env.getProperty("ds.xms.master.url"));
        ds.setUsername(env.getProperty("ds.xms.master.username"));
        ds.setPassword(env.getProperty("ds.xms.master.password"));
        try {
            ds.setFilters(env.getProperty("ds.filters"));
        } catch (SQLException e) {
            logger.warn("Data source set filters ERROR:", e);
        }
        ds.setMaxActive(NumberUtils.toInt(env.getProperty("ds.maxActive"), 90));
        ds.setInitialSize(NumberUtils.toInt(env.getProperty("ds.initialSize"), 10));
        ds.setMaxWait(NumberUtils.toInt(env.getProperty("ds.maxWait"), 60000));
        ds.setMinIdle(NumberUtils.toInt(env.getProperty("ds.minIdle"), 1));
        ds.setTimeBetweenEvictionRunsMillis(NumberUtils.toInt(env.getProperty("ds.timeBetweenEvictionRunsMillis"), 60000));
        ds.setMinEvictableIdleTimeMillis(NumberUtils.toInt(env.getProperty("ds.minEvictableIdleTimeMillis"), 300000));
        ds.setValidationQuery(env.getProperty("ds.validationQuery"));
        ds.setTestWhileIdle(BooleanUtils.toBoolean(env.getProperty("ds.testWhileIdle")));
        ds.setTestOnBorrow(BooleanUtils.toBoolean(env.getProperty("ds.testOnBorrow")));
        ds.setTestOnReturn(BooleanUtils.toBoolean(env.getProperty("ds.testOnReturn")));
        ds.setPoolPreparedStatements(BooleanUtils.toBoolean(env.getProperty("ds.poolPreparedStatements")));
        ds.setMaxOpenPreparedStatements(NumberUtils.toInt(env.getProperty("ds.maxOpenPreparedStatements"), 20));
        return ds;
    }

    @Bean(name = ConfigConstant.NAME_DS_TM_MASTER)
    @Primary
    public DataSourceTransactionManager mafMasterTransactionManager() {
        logger.info("----- MAFIA master data source transaction manager INIT -----");
        return new DataSourceTransactionManager(mafMasterDataSource());
    }

    @Bean(name = ConfigConstant.NAME_DS_SSF_MASTER)
    @Primary
    public SqlSessionFactory mafMasterSqlSessionFactory(@Qualifier(ConfigConstant.NAME_DS_MASTER) DataSource mafMasterDataSource) throws Exception {
        logger.info("----- MAFIA master data source sql session factory INIT -----");
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mafMasterDataSource);
        sessionFactory.setConfigLocation(new ClassPathResource(ConfigConstant.NAME_MYBATIS_CONFIG));
        return sessionFactory.getObject();
    }
}
