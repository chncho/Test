package dbtest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class MysqlTest {
	/**
	 * the attribute which is needed to communicate with db
	 */
	static Connection con = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;

	public static void main(String[] args) throws SQLException {
		int count = 0;

		con = BaseDao.getConnection();

//		String sql = "select * from ttt";
		String sql = "select * from Persons";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();

		ResultSetMetaData rsMetaData = rs.getMetaData();

		System.out.println("rsMetaData---------------------");
		System.out.println(rsMetaData);
		System.out.println("rsMetaData---------------------");
		System.out.println("rsMetaData.getCatalogName(2) ... "+rsMetaData.getCatalogName(2));
		System.out.println("rsMetaData.getColumnType(2) ... "+rsMetaData.getColumnType(2));
		System.out.println("rsMetaData.getColumnTypeName(2) ... "+rsMetaData.getColumnTypeName(2));
		System.out.println("rsMetaData.getColumnLabel(2) ... "+rsMetaData.getColumnLabel(2));
		System.out.println("rsMetaData.getScale(2) ... "+rsMetaData.getScale(2));
		System.out.println("rsMetaData.getSchemaName(1) ... "+rsMetaData.getSchemaName(1));
		System.out.println("rsMetaData.getSchemaName(2) ... "+rsMetaData.getSchemaName(2));
		
		BaseDao.closeAll(rs, pstmt, con);

	}
}
