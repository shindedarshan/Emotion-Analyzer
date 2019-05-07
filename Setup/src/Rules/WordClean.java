package Rules;

public class WordClean {
	public static String removeSymbols(String word){
		word = word.replace('.', ' ');
		word = word.replace('\'', ' ');
		word = word.replace('!', ' ');
		word = word.trim();
		return word;
	}
}
