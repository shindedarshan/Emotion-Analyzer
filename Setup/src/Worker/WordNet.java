package Worker;

import java.util.Arrays;

import rita.RiWordNet;
import Worker.DatabaseConnection.DBConnectorImpl;

public class WordNet {
	RiWordNet rw = new RiWordNet("C:\\Program Files (x86)\\WordNet\\2.1"); // Path where WordNet is installed on system 
	POSTag pt = new POSTag();

	public void addWord(String sen) throws Exception {
		System.out.println("in wordnet");
		DBConnectorImpl dci=new DBConnectorImpl();
		dci.addWord(sen,pt,rw);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RiWordNet rw = new RiWordNet("C:\\Program Files (x86)\\WordNet\\2.1");
		for (String p : rw.getPos("project")) {
			System.out.println(p + Arrays.asList(rw.getSynonyms("project", p)));
		}
	}

}
