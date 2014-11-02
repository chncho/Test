/**
 * class : BaseDao
 * open the connection to database and close the resource used
 * @author chenchao
 */
package dbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbtest.DBProperties;

public class BaseDao {

	private static  DBProperties dbproperties = DBProperties.getInstance();
	private static Connection con = null;

	
	/**
	 * method getConnection
	 * @return con
	 */
	public static Connection getConnection(){
		
		try {
			Class.forName(dbproperties.getProperty("driver"));
			con = DriverManager.getConnection(dbproperties.getProperty("url"),
					dbproperties.getProperty("username"),
					dbproperties.getProperty("userpassword"));
		} catch (SQLException e) {
			System.out.println("通过DriverManager得到连接对象con时发生异常");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("通过反射注册数据库驱动发生异常");
			e.printStackTrace();
		}
		
		return con;
		
	}
	
	
	/**
	 * method closeAllResouses
	 */
	public static void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con){
		
		if( rs != null ){
			try {
				rs.close();
				if( pstmt != null ){
					pstmt.close();
				}
				if( con != null ){
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("在关闭数据库资源时发生异常");
				e.printStackTrace();
			}
		}
		
	}
	
//	public static void main(String[] args) {
//		System.out.println(dbproperties.getProperty("url")+
//				dbproperties.getProperty("username")+
//				dbproperties.getProperty("userpassword"));
//	}
	
	
}
