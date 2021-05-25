package ch.zhaw.iwi.nlp.corenlp;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderHeaderAwareBuilder;
import com.opencsv.exceptions.CsvValidationException;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class POSTaggingGerman {

	public static void main(String[] args) throws CsvValidationException, IOException {
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize,ssplit,pos");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
		CSVReader reader = new CSVReaderHeaderAwareBuilder(
				new FileReader("resources/jobsch_url-title-description-umlaute.csv")).withCSVParser(parser).build();
		String[] currentLine = reader.readNext();

		// TODO this n was introduced to save time and just look at the first couple of
		// lines. Remove this if you want to go through all of the historical figures.
		int n = 5;

		while (currentLine != null && n > 0) {

			System.out.println("> " + currentLine[1]);

			CoreDocument document = pipeline.processToCoreDocument(currentLine[2]);
			// display tokens
			for (CoreLabel tok : document.tokens()) {
				// if (tok.tag().startsWith("NN")) {
				System.out.println(String.format("%s\t%s", tok.word(), tok.tag()));
				// }
			}
			
			currentLine = reader.readNext();
			n--;

		}
	}

}
