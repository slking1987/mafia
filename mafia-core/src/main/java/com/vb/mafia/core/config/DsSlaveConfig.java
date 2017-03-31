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
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(basePackages = {ConfigConstant.REG_DS_SLAVE}, sqlSessionFactoryRef = ConfigConstant.NAME_DS_SSF_SLAVE)
public class DsSlaveConfig {

    private static Logger logger = LoggerFactory.getLogger(DsSlaveConfig.class);

    @Autowired
    private Environment env;

    @Bean(name = ConfigConstant.NAME_DS_SLAVE)
    @ConfigurationProperties(prefix = ConfigConstant.PREFIX_DS_SLAVE)
    public DataSource mafSlaveDataSource() {
        logger.info("----- MAFIA slave data source INIT -----");
        DruidDataSource ds = new DruidDataSource();
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

    @Bean(name = {ConfigConstant.NAME_DS_TM_SLAVE})
    public DataSourceTransactionManager mafSlaveTransactionManager() {
        logger.info("----- MAFIA slave data source transaction manager INIT -----");
        return new DataSourceTransactionManager(mafSlaveDataSource());
    }

    @Bean(name = ConfigConstant.NAME_DS_SSF_SLAVE)
    public SqlSessionFactory mafSlaveSqlSessionFactory(@Qualifier(ConfigConstant.NAME_DS_SLAVE) DataSource mafSlaveDataSource) throws Exception {
        logger.info("----- MAFIA slave data source sql session factory INIT -----");
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mafSlaveDataSource);
        sessionFactory.setConfigLocation(new ClassPathResource(ConfigConstant.NAME_MYBATIS_CONFIG));
        return sessionFactory.getObject();
    }
}
