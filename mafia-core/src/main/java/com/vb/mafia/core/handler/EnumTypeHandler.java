package com.vb.mafia.core.handler;

import com.google.api.client.util.Maps;
import com.vb.mafia.core.constant.MafEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class EnumTypeHandler<E extends MafEnum> extends BaseTypeHandler<E> {

    private final Map<Byte, E> enums = Maps.newHashMap();

    public EnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        E[] enumArr = type.getEnumConstants();
        if (enumArr == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }
        for (E e : enumArr) {
            enums.put(e.getId(), e);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setByte(i, parameter.getId());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.enums.get(rs.getByte(columnName));
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.enums.get(rs.getByte(columnIndex));
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.enums.get(cs.getByte(columnIndex));
    }
}
