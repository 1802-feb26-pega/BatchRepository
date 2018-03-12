package BankInside;

	import java.io.FileReader;
	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.util.Properties;

	public class ConnectionFactory {
	    private static ConnectionFactory cf = null;

	    private ConnectionFactory(){
	    }

	    public static synchronized ConnectionFactory getInstance() {
	        if(cf == null)
	            cf = new ConnectionFactory();
	        return cf;
	    }

	    public Connection getConnection() {
	        Connection c = null;
	        Properties p = new Properties();

	        try {
	            p.load(new FileReader("C:\\Users\\ammon\\OneDrive\\Documents\\GitHub\\BatchRepository\\Ammon_Hallsted_Code\\Ammon_Hallsted_Code\\Week1\\BankAssign\\resources\\app.properties"));
	            Class.forName(p.getProperty("driver"));
	            c = DriverManager.getConnection(p.getProperty("url"),
	                                            p.getProperty("usr"),
	                                            p.getProperty("pwd"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return c;
	    }
	}

