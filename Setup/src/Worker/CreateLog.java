package Worker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CreateLog {
	public CreateLog(String line, List<String> senList) {
		FileWriter pr;
		try {
			pr = new FileWriter("sentences.txt", true);
			pr.write(senList.toString());
			pr.write(line);
			pr.write("\n");
			pr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
