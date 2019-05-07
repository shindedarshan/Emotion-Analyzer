package Worker.OutputGenaration;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface OutputGenarator {
	public Map<String, Set<Double>> createMap();
	public Map<String, Set<Double>> calculateAvgScore(Map<String, Set<Double>> rMap);
	public String generateOutputString(Map<String, Set<Double>> rMap, boolean isNeg);
	public List<String> wordSearchCalculation(String word, String sentence, int i, List<String> butList);
}
