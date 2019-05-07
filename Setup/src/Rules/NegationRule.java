package Rules;

import Controller.Controller;

public class NegationRule {
	public static boolean negateCheck(String word) {
		if (word.equalsIgnoreCase("not")
				|| word.equalsIgnoreCase("never")
				|| word.endsWith("n't")
				|| word.equalsIgnoreCase("nowhere")
				|| word.equalsIgnoreCase("nothing")
				|| word.equalsIgnoreCase("none")
				|| word.equalsIgnoreCase("unable")
				|| word.equalsIgnoreCase("nobody")
				|| word.equalsIgnoreCase("neither")
				|| word.equalsIgnoreCase("no")) 
		
			return true;
		
		else return false;
	}
	
	public static void negateCalculation()
	{
		if (Controller.emotion.equalsIgnoreCase("joy")) {
			Controller.emotion = "sad";
		} else if (Controller.emotion.equalsIgnoreCase("sad")
				|| Controller.emotion.equalsIgnoreCase("anger")
				|| Controller.emotion.equalsIgnoreCase("fear")) {
			Controller.emotion = "joy";
		} else
			Controller.emotion = "sad";
		Controller.isNeg=false;
		if (!Controller.emotion.equals("sad"))
			Controller.score = Controller.score * 0.6;
	}
}
