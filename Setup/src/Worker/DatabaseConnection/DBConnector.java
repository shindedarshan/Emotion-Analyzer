package Worker.DatabaseConnection;

import java.sql.Connection;

import Worker.POSTag;
import rita.RiWordNet;

public interface DBConnector {
	public Connection getConnection();
	public void closeConnection();
	public void insert(String query) throws Exception;
	public String getType(String query) throws Exception;
	public void addWord(String sen,POSTag pt,RiWordNet rw)throws Exception;
}
