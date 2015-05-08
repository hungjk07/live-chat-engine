/*
 * Copyright 2015 Evgeny Dolganov (evgenij.dolganov@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package och.util.sql;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class SingleTxConnection implements Connection {
	
	Connection real;
	boolean txRollbacked;
	Throwable realRollbackException;

	public SingleTxConnection(Connection real) {
		this.real = real;
	}
	
	
	
	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		//NOTHING
	}

	@Override
	public boolean getAutoCommit() throws SQLException {
		return false;
	}

	@Override
	public void commit() throws SQLException {
		//NOTHING
	}
	
	@Override
	public void rollback() throws SQLException {
		txRollbacked = true;
		Throwable readEx = SingleTx.getSavedRealRoolbackException();
		if(readEx == null){
			throw new SingleTxConnectionRollbackException();
		} else {
			throw new SingleTxConnectionRollbackException(readEx);
		}
		
	}
	
	@Override
	public void close() throws SQLException {
		//NOTHING
	}

	@Override
	public boolean isClosed() throws SQLException {
		return false;
	}
	
	
	
	
	

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return real.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return real.isWrapperFor(iface);
	}

	@Override
	public Statement createStatement() throws SQLException {
		return real.createStatement();
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return real.prepareStatement(sql);
	}

	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		return real.prepareCall(sql);
	}

	@Override
	public String nativeSQL(String sql) throws SQLException {
		return real.nativeSQL(sql);
	}



	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		return real.getMetaData();
	}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
		real.setReadOnly(readOnly);
	}

	@Override
	public boolean isReadOnly() throws SQLException {
		return real.isReadOnly();
	}

	@Override
	public void setCatalog(String catalog) throws SQLException {
		real.setCatalog(catalog);
	}

	@Override
	public String getCatalog() throws SQLException {
		return real.getCatalog();
	}

	@Override
	public void setTransactionIsolation(int level) throws SQLException {
		real.setTransactionIsolation(level);
	}

	@Override
	public int getTransactionIsolation() throws SQLException {
		return real.getTransactionIsolation();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return real.getWarnings();
	}

	@Override
	public void clearWarnings() throws SQLException {
		real.clearWarnings();
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency)
			throws SQLException {
		return real.createStatement(resultSetType, resultSetConcurrency);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		return real.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		return real.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return real.getTypeMap();
	}

	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		real.setTypeMap(map);
	}

	@Override
	public void setHoldability(int holdability) throws SQLException {
		real.setHoldability(holdability);
	}

	@Override
	public int getHoldability() throws SQLException {
		return real.getHoldability();
	}

	@Override
	public Savepoint setSavepoint() throws SQLException {
		return real.setSavepoint();
	}

	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		return real.setSavepoint(name);
	}

	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
		real.rollback(savepoint);
	}

	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		real.releaseSavepoint(savepoint);
	}

	@Override
	public Statement createStatement(int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return real.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return real.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return real.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
			throws SQLException {
		return real.prepareStatement(sql, autoGeneratedKeys);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
			throws SQLException {
		return real.prepareStatement(sql, columnIndexes);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames)
			throws SQLException {
		return real.prepareStatement(sql, columnNames);
	}

	@Override
	public Clob createClob() throws SQLException {
		return real.createClob();
	}

	@Override
	public Blob createBlob() throws SQLException {
		return real.createBlob();
	}

	@Override
	public NClob createNClob() throws SQLException {
		return real.createNClob();
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		return real.createSQLXML();
	}

	@Override
	public boolean isValid(int timeout) throws SQLException {
		return real.isValid(timeout);
	}

	@Override
	public void setClientInfo(String name, String value)
			throws SQLClientInfoException {
		setClientInfo(name, value);
	}

	@Override
	public void setClientInfo(Properties properties)
			throws SQLClientInfoException {
		real.setClientInfo(properties);
	}

	@Override
	public String getClientInfo(String name) throws SQLException {
		return real.getClientInfo(name);
	}

	@Override
	public Properties getClientInfo() throws SQLException {
		return real.getClientInfo();
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements)
			throws SQLException {
		return real.createArrayOf(typeName, elements);
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes)
			throws SQLException {
		return real.createStruct(typeName, attributes);
	}

	@Override
	public void setSchema(String schema) throws SQLException {
		real.setSchema(schema);
	}

	@Override
	public String getSchema() throws SQLException {
		return real.getSchema();
	}

	@Override
	public void abort(Executor executor) throws SQLException {
		real.abort(executor);
	}

	@Override
	public void setNetworkTimeout(Executor executor, int milliseconds)
			throws SQLException {
		real.setNetworkTimeout(executor, milliseconds);
	}

	@Override
	public int getNetworkTimeout() throws SQLException {
		return real.getNetworkTimeout();
	}

}
