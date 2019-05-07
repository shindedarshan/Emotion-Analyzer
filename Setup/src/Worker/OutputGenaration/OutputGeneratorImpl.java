package Worker.OutputGenaration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import Controller.Controller;
import Rules.ButRule;
import Rules.NegationRule;
import Worker.DatabaseConnection.DBConnectorImpl;

public class OutputGeneratorImpl implements OutputGenarator {

	@Override
	public Map<String, Set<Double>> createMap() {
		Map<String, Set<Double>> rMap = new TreeMap<String, Set<Double>>();
		rMap.put("anger", new HashSet<Double>());
		rMap.put("joy", new HashSet<Double>());
		rMap.put("sad", new HashSet<Double>());
		rMap.put("fear", new HashSet<Double>());
		return rMap;
	}

	@Override
	public Map<String, Set<Double>> calculateAvgScore(
			Map<String, Set<Double>> rMap) {
		for (Map.Entry<String, Set<Double>> entry : rMap.entrySet()) {
			Set<Double> tempSet = entry.getValue();
			Double d1 = 0.0;
			for (Double d : tempSet) {
				System.out.println(entry.getKey() + "=>" + d);
				d1 += d;
			}
			Set<Double> newSet = new HashSet<Double>();
			newSet.add((d1 / tempSet.size()));
			rMap.put(entry.getKey(), newSet);
		}
		return rMap;
	}

	@Override
	public String generateOutputString(Map<String, Set<Double>> rMap,
			boolean isNeg) {
		String score = rMap.get("anger").iterator().next() + ";"
				+ rMap.get("joy").iterator().next() + ";"
				+ rMap.get("sad").iterator().next() + ";"
				+ rMap.get("fear").iterator().next();
		/*
		 * line=line.replace('[',' '); line=line.replace(']', ' ');
		 */
		score = score.replaceAll("NaN", "0.0");
		String lsplit[] = score.split(";");
		if ((lsplit[0].equals("0.0") && lsplit[1].equals("0.0")
				&& lsplit[2].equals("0.0") && lsplit[3].equals("0.0"))
				&& isNeg) {
			score = 0.0 + ";" + 0.0 + ";" + 0.41 + ";" + 0.0;
		}
		System.out.println(score);
		return score;
	}

	@Override
	public List<String> wordSearchCalculation(String word, String sentence,
			int i, List<String> butList) {
		DBConnectorImpl dci = new DBConnectorImpl();
		Connection con = dci.getConnection();
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select score,emotion from wordlist where word='"
							+ word + "'");
			while (rs.next()) {
				System.out.println("Word is:" + word);
				Controller.emotion = rs.getString(2).toLowerCase();
				Controller.score = rs.getDouble(1);
				if (Controller.riseFlag) {
					Controller.score *= 1.12;
					Controller.riseFlag = false;
				}

				if (Controller.isNeg) {
					NegationRule.negateCalculation();
				}

				System.out.println("Sen. is:" + sentence + "\nWord is:" + word
						+ " Emotion:" + Controller.emotion + "Score:"
						+ rs.getDouble(1));

				ButRule.calculateBut1(i);
				butList.add(Controller.score + ";" + Controller.emotion);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dci.closeConnection();
		return butList;
	}
}
