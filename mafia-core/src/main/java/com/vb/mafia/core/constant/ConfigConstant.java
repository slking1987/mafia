package com.vb.mafia.core.constant;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public abstract class ConfigConstant {
    public static final String REG_BEAN_SCAN = "com.vb.mafia.*";

    public static final String REG_DS_MASTER = "com.vb.mafia.srv.**.master";
    public static final String REG_DS_SLAVE = "com.vb.mafia.srv.**.slave";

    public static final String PREFIX_DS_MASTER = "ds.mafia.master";
    public static final String PREFIX_DS_SLAVE = "ds.mafia.slave";

    public static final String NAME_DS_MASTER = "mafMasterDataSource";
    public static final String NAME_DS_TM_MASTER = "mafMasterTransactionManager";
    public static final String NAME_DS_SSF_MASTER = "mafMasterSqlSessionFactory";

    public static final String NAME_DS_SLAVE = "mafSlaveDataSource";
    public static final String NAME_DS_TM_SLAVE = "mafSlaveTransactionManager";
    public static final String NAME_DS_SSF_SLAVE = "mafSlaveSqlSessionFactory";

    public static final String NAME_MYBATIS_CONFIG = "mybatis-config.xml";

    public static final Locale DEFAULT_LOCALE = LocaleContextHolder.getLocale();
}
