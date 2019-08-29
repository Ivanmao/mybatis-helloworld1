/**
 * 
 */
package com.guoh.mybatis.helloworld1.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.guoh.mybatis.helloworld1.Enabled;

/**
 * @author mao_g
 *
 */
public class EnabledTypeHandler implements TypeHandler<Enabled> {
	Map<Integer, Enabled> enabledMap = new java.util.HashMap<>();
	
	public EnabledTypeHandler(){
		for (Enabled enabled : Enabled.values()) {
			enabledMap.put(enabled.getValue(), enabled);
		}
	}

	@Override
	public void setParameter(PreparedStatement ps, int i, Enabled parameter, JdbcType jdbcType) throws SQLException {
		// TODO Auto-generated method stub
		ps.setInt(i, parameter.getValue());
	}

	@Override
	public Enabled getResult(ResultSet rs, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		
		return enabledMap.get(rs.getInt(columnName));
	}

	@Override
	public Enabled getResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		
		return enabledMap.get(rs.getInt(columnIndex));
	}

	@Override
	public Enabled getResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return enabledMap.get(cs.getInt(columnIndex));
	}

}
