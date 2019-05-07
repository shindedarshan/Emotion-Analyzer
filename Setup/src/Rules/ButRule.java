package Rules;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Controller.Controller;

public class ButRule {
	
	
	//for but word in middle of the sentence
	public static void calculateBut(String word, List<String> butList) {
		if (word.equals("but")) {
			for (String l : butList) {
				String lsp[] = l.split(";");
				Set<Double> scoreSet = Controller.rMap.get(lsp[1]);
				Set<Double> newScoreSet = new HashSet<Double>();
				for (Double d : scoreSet) {
					newScoreSet.add(d / 2.0);
				}
				Controller.rMap.put(lsp[1], newScoreSet);
			}
		}
	}

	//for sentences starting with but word
	public static void calculateBut1(int i) {
		Set<Double> tSet = Controller.rMap.get(Controller.emotion);

		if (i < Controller.senList.size() - 1
				&& (Controller.senList.get(i + 1).startsWith("But")
						|| Controller.senList.get(i + 1).startsWith("but") || Controller.senList
						.get(i + 1).startsWith("BUT")))
			tSet.add(Controller.score / 2.0);
		else
			tSet.add(Controller.score);
		Controller.rMap.put(Controller.emotion, tSet);
	}

}
