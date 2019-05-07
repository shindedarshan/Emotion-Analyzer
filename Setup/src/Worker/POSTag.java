package Worker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.process.Morphology;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class POSTag {
	public POSTag() {
		// TODO Auto-generated constructor stub
		// init();
	}

	// static StanfordCoreNLP pipeline;
	static Morphology m = new Morphology();
	static MaxentTagger mt = new MaxentTagger(
			"C:\\Users\\Administrator\\Downloads\\New Workspace\\POSTagDemo\\models\\english-left3words-distsim.tagger");
	static List<String> stopwordList;
	static String words[] = { "name", "a", "about", "above", "after", "again",
			"against", "all", "am", "an", "and", "any", "are", "aren't", "as",
			"at", "be", "because", "been", "before", "being", "below",
			"between", "both", "but", "by", "can't", "cannot", "could",
			"coldn't", "did", "didn't", "do", "does", "doesn't", "doing",
			"don't", "down", "during", "each", "few", "for", "from", "further",
			"had", "had,'t", "has", "hasn't", "have", "haven't", "having",
			"he", "he'd", "he'll", "he's", "her", "here", "here's", "hers",
			"herself", "him", "himself", "his", "how", "how's", "i", "i'd",
			"i'll", "i'm", "i've", "if", "in", "into", "is", "isn't", "it",
			"it's", "its", "itself", "let's", "me", "more", "most", "mustn't",
			"my", "myself", "nor", "not", "of", "off", "on", "once", "only",
			"or", "other", "tought", "our", "ours", "ourselves", "out", "over",
			"own", "same", "shan't", "she", "she'd", "she'll", "she's",
			"should", "shouldn't", "so", "some", "such", "than", "that",
			"that's", "the", "their,", "theirs", "them", "themselves", "then",
			"there", "there's", "these", "they", "they'd", "they'll",
			"they're", "they've", "this", "those", "through", "to", "too",
			"under", "until", "up", "very", "was", "wasn't", "we", "we'd",
			"we'll", "we're", "we've", "were", "weren't", "what", "what's",
			"when", "when's", "where", "where's", "which", "while", "who",
			"who's", "whom", "why", "why's", "ith", "won't", "would",
			"wouldn't", "you", "you'd", "you'll", "you're", "you've", "your",
			"yours", "yourself", "yourselves", "no" };
	static {
		stopwordList = Arrays.asList(words);
	}

	/*
	 * public static void init() { pipeline = new
	 * StanfordCoreNLP("MyPropFile.properties"); }
	 */

	public static String stemming(String word) {
		return m.stem(word);
	}

	public static String tag(String text) {
		return mt.tagString(text);
	}

	public List<String> paratosen(String para) {
		List<String> senList = new ArrayList<String>();
		Pattern re = Pattern
				.compile(
						"[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)",
						Pattern.MULTILINE | Pattern.COMMENTS);
		Matcher reMatcher = re.matcher(para);
		while (reMatcher.find()) {
			senList.add(reMatcher.group());
		}
		return senList;
	}

	public boolean isStopword(String w) {
		w = w.toLowerCase();
		if (stopwordList.contains(w)) {
			System.out.println("StopWord:" + w);
			return true;
		} else
			return false;
	}

	// add pos,lemma into MyPropFile.properties

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		POSTag pt = new POSTag();
		Morphology m = new Morphology();
		// System.out.println(m.stem("now"));
		System.out.println(mt.tagString("None of the project were good I will beat you I will destroy whole project The food was horribly good"));
		// System.out.println(POSTag.lemmatize("banking"));
		// System.out.println(POSTag.tag("none neither unable never not"));

		/*
		 * System.out.println(m.stem("angering"));
		 * System.out.println(m.stem("offensive"));
		 * 
		 * System.out.println(mt.tagString("The food was horribly good"));
		 * System.out.println(mt.tagString("It is horrible"));
		 * System.out.println(mt.tagString("you are panic"));
		 * System.out.println(
		 * mt.tagString("There is nothing innovative in this"));
		 * System.out.println(mt
		 * .tagString("Insted of doing this i would prefer to die"));
		 * System.out.println(mt.tagString("I will beat you"));
		 * System.out.println(mt.tagString("I will destroy whole project"));
		 * System.out.println(mt
		 * .tagString("I will warn you this will spoile your life"));
		 * System.out.println(mt.tagString("you are doing rubbish things"));
		 * System.out.println(mt.tagString("Something new"));
		 * System.out.println(mt.tagString("disgust"));
		 * 
		 * System.out.println("Sentiment:" +
		 * POSTag.findSentiment("The lecture was very nice"));
		 * System.out.println
		 * ("Sentiment:"+POSTag.findSentiment("It is not that much bad"));
		 * System.out.println("Sentiment:"+POSTag.findSentiment("It is worst"));
		 */

	}

}
