package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Rules.ButRule;
import Rules.NegationRule;
import Rules.QuantifierRule;
import Rules.WordClean;
import Worker.CreateLog;
import Worker.POSTag;
import Worker.OutputGenaration.OutputGenarator;
import Worker.OutputGenaration.OutputGeneratorImpl;

public class Controller {

	POSTag pt = new POSTag();
	public static List<String> senList = null;
	public static String emotion;
	public static double score;
	public static boolean isNeg = false;
	public static boolean riseFlag = false;
	public static Map<String, Set<Double>> rMap=null;

	public String showCategory(String sentences) throws Exception {

		OutputGenarator og = new OutputGeneratorImpl();
		rMap = og.createMap();

		senList = pt.paratosen(sentences);

		for (int i = 0; i < senList.size(); i++) {
			String sentence = senList.get(i);
			List<String> butList = new ArrayList<String>();
			sentence = sentence.replaceAll(",", " ");

			for (String word : sentence.split(" ")) {

				QuantifierRule.quantifierCheck(word);

				ButRule.calculateBut(word, butList);	

				if (NegationRule.negateCheck(word)) {
					isNeg = true;
					continue;
				}

				System.out.println(word);
				word = WordClean.removeSymbols(word);

				/*
				 * if (pt.isStopword(word)) continue;
				 */
				word = POSTag.stemming(word);
				System.out.println("Word is:" + word);
				
				butList=og.wordSearchCalculation(word, sentence, i, butList);
				
			}

		}

		rMap = og.calculateAvgScore(rMap);
		String line = og.generateOutputString(rMap, isNeg);
		new CreateLog(line, senList);
		return line;
	}

	public static void main(String[] args) throws Exception {
		
		String par = "it is not great,bad";
		System.out.println(new Controller().showCategory(par));
		
		/*
		 * String par = "it is not great,bad";//
		 * "I admit I had no idea what to expect before viewing this highly stylized piece. It could have been the cure for a zombie virus or the common cold for all I knew. It began with great visuals, little snippets to grab your attention and cause your imagination to run wild. As it continued I learned quickly through voice overs what was taking place. A nice little neo noir story that I felt was not a waist of a few minutes of my time. The little clues given to the audience through visuals at the beginning give them a sense of accomplishment as they piece together the plot. Along with a nice twist at the end its a cool package overall. The score, though not bad, gave the film almost a music video feel. It just felt a little dated, not adding anything to further the storyline. Some of the performances felt overly dramatic but fit perfectly with the feel of the overall piece. I walk away from this very satisfied. I was given a lot of information in a short period of time but through great editing and voice-over work it didn't feel rushed or pushed."
		 * ;
		 * 
		 * System.out.println(new Controller().showCategory(par));
		 * 
		 * 
		 * BufferedReader br=new BufferedReader(new FileReader("mdict.txt"));
		 * PrintWriter pr=new PrintWriter(new File("mdict1.txt")); String s="";
		 * while((s=br.readLine())!=null){ s=s.trim(); pr.println(s);
		 * pr.flush(); }
		 */
	}
}
