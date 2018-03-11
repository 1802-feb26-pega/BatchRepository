import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Application {
	static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
	static final String USR = "chinook";
	static final String PWD = "p4ssw0rd";
	
	public static void main(String[] args) throws Exception {
		// Open a connection
		// Register JBDC driver
		Class.forName(DB_DRIVER);

		Connection conn = DriverManager.getConnection(DB_URL, USR, PWD);
		

		prepExample(conn, 5);
	}
	
	
	static void prepExample(Connection conn, int id) throws Exception {
		String sql = "SELECT * FROM artist WHERE artistid = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			int aid = rs.getInt(1);
			String aname = rs.getString(2);
			System.out.printf("id: %d, name: %s\n", aid, aname);
		}
	}
	
	
	static void statementExample(Connection conn) throws Exception {
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM artist";
		ResultSet rs = stmt.executeQuery(sql);
		
		// Extract data from result set
		while (rs.next()) {
			// Retrieve by column name
			int id = rs.getInt("artistid");
			String name = rs.getString("name");
			System.out.printf("id: %d, name: %s\n", id, name);
		}
	}
}
