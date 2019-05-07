package Rules;

import Controller.Controller;

public class QuantifierRule {
	public static void quantifierCheck(String word) {
		if (word.equalsIgnoreCase("very") || word.equalsIgnoreCase("more")
				|| word.equalsIgnoreCase("most")
				|| word.equalsIgnoreCase("much")
				|| word.equalsIgnoreCase("extremely")
				|| word.equalsIgnoreCase("so") || word.equalsIgnoreCase("too"))
			Controller.riseFlag = true;

	}
}
