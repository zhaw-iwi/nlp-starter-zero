package ch.zhaw.iwi.nlp.corenlp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriteHelper {

	private static final String FILE_T2F = "Text2Facts";
	private static final String FILE_F2T = "Facts2Text";

	public static void writeToFile(String news, String facts) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(FileWriteHelper.FILE_T2F, true));
		writer.append("<item>");
		writer.append("<text>");
		writer.append(news);
		writer.append("</text>");
		writer.newLine();
		writer.append("<facts>");
		writer.append(facts);
		writer.append("</facts>");
		writer.newLine();
		writer.append("</item>");
		writer.newLine();
		writer.newLine();
		writer.close();

		writer = new BufferedWriter(new FileWriter(FileWriteHelper.FILE_F2T, true));
		writer.append("<item>");
		writer.append("<facts>");
		writer.append(facts);
		writer.append("</facts>");
		writer.newLine();
		writer.append("<text>");
		writer.append(news);
		writer.append("</text>");
		writer.newLine();
		writer.append("</item>");
		writer.newLine();
		writer.newLine();
		writer.close();

	}
}
