package Worker.DatabaseConnection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import Worker.POSTag;
import rita.RiWordNet;

public class DBConnectorImpl implements DBConnector {
	private Connection con;
	private Statement st;
	private ResultSet rs;

	@Override
	public Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/dictionary";
		String uname = "root";
		String pass = "root";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, uname, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	@Override
	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void insert(String query) throws Exception {
		// TODO Auto-generated method stub
		st = con.createStatement();
		st.execute(query);
	}

	@Override
	public String getType(String query) throws Exception {
		// TODO Auto-generated method stub
		st = con.createStatement();
		rs = st.executeQuery(query);
		if (rs.next())
			return rs.getString(1);
		else
			return null;
	}

	public void insert() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(
				"C:\\Users\\Administrator\\Desktop\\other.txt"));
		String s = "", query = "";
		st = con.createStatement();
		ResultSet rs;
		while ((s = br.readLine()) != null) {
			System.out.println(s);
			String sar[] = s.split(" ");
			rs = st.executeQuery("select * from wordlist where word='" + sar[0]
					+ "'");
			if (!rs.next()) {
				System.out.println(sar[0] + " " + sar[1] + " " + sar[2]);
				query = "insert into wordlist values('" + sar[0] + "',"
						+ sar[1] + ",'" + sar[2] + "')";
				System.out.println(query);
				st.execute(query);
			}
		}
	}

	@Override
	public void addWord(String sen, POSTag pt, RiWordNet rw) throws Exception {
		Connection con1, con2;
		con1 = getConnection();
		con2 = getConnection();
		Statement st = con1.createStatement();
		Statement st1 = con2.createStatement();
		ResultSet rs = null;
		System.out.println("Word is:" + sen);
		boolean added;
		for (String w : sen.split(" ")) {
			w = POSTag.stemming(w);
			added = false;
			if (pt.isStopword(w))
				continue;
			else {
				for (String p : rw.getPos(w)) {
					List<String> wordList = Arrays.asList(rw.getSynonyms(w, p));
					System.out.println("Synonyms :" + wordList);
					for (String word : wordList) {
						System.out.println(word);
						rs = st.executeQuery("select * from wordlist where word='"
								+ word + "'");
						if (rs.next()) {
							st1.executeUpdate("insert into wordlist values('"
									+ w + "'," + rs.getDouble(2) + ",'"
									+ rs.getString(3) + "')");
							System.out.println("New Word Added..");
							added = true;
							break;
						}
					}
					if (added)
						break;
				}
			}
		}
		closeConnection();
	}

}
