package BO.Quartz;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.quartz.impl.jdbcjobstore.StdJDBCDelegate;

/**
 * 重写quartz的JDBC连接类，特别是getObjectFromBlob、getJobDetailFromBlob这两个方法。
 * 源代码中没有对null进行判断，经常会报出异常，这里进行了判断，以更安全的方式进行Blob类的获取。
 * @author lunwang
 *
 */
public class NewStdJDBCDelegate extends StdJDBCDelegate {

	public NewStdJDBCDelegate(Log logger, String tablePrefix, String instanceId) {
		super(logger, tablePrefix, instanceId);
		
	}
	public NewStdJDBCDelegate(Log logger, String tablePrefix,
			String instanceId, Boolean useProperties) {
		super(logger, tablePrefix, instanceId, useProperties);
		
	}
	protected Object getObjectFromBlob(ResultSet rs, String colName) 
	        throws ClassNotFoundException, IOException, SQLException { 
	        byte[] data = rs.getBytes(colName); 
	        if (data == null || data.length == 0) 
	        { 
	          return null; 
	        } 
	        ObjectInputStream in = new ObjectInputStream( 
	            new ByteArrayInputStream(data)); 
	        try { 
	            return in.readObject(); 
	        } finally { 
	            in.close(); 
	        } 
	} 
	public Object getJobDetailFromBlob(ResultSet rs, String colName) throws ClassNotFoundException, IOException, SQLException{
		 byte[] data = rs.getBytes(colName); 
	        
	        if (data == null || data.length == 0) 
	        { 
	          return null; 
	        } 
	        ObjectInputStream in = new ObjectInputStream( 
	            new ByteArrayInputStream(data)); 
	        try { 
	            return in.readObject(); 
	        } finally { 
	            in.close(); 
	        } 
	}
}
