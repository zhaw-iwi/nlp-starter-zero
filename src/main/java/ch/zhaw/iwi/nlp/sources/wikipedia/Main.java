package ch.zhaw.iwi.nlp.sources.wikipedia;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;

import com.google.gson.Gson;
import com.opencsv.exceptions.CsvValidationException;

public class Main {

	public static void main(String[] args) throws IOException, CsvValidationException {
		System.out.println("> Hello :-)");

		List<HistoricalFigure> historicalFigures = PantheonHelper.readFromCSV("resources/pantheon/pantheon.tsv");
		// Main.completeUsingWikiQueries(historicalFigures);
		Main.completeUsingWikiPages(historicalFigures, 3);

		// TODO
		// process all figures in order to insert into DB, create training data, ...
	}

	/**
	 * Use of GSON to request data from WikiPedia and extract description from JSON
	 * response. We extract the first nOfSentences of sentences from each page.
	 * 
	 * @param figures
	 * @param nOfSentences How many of the first sentences per page?
	 */
	public static void completeUsingWikiPages(List<HistoricalFigure> figures, Integer nOfSentences) {
		System.out.println("> Reading from WikiPedia (First " + nOfSentences + " Sentences from Page): START");

		// TODO we take the first ten figures only for demo purposes (speed :-))
		List<HistoricalFigure> sample = figures.subList(0, 10);

		int n = 0;
		Long pageId;
		String extract;
		for (HistoricalFigure current : sample) {
			pageId = WikiPediaHelper.searchPageId(current.getName());
			if (pageId != null) {
				extract = WikiPediaHelper.getPage(pageId, nOfSentences);
				System.out.println(extract);
				current.setDescription(extract);
				n++;
			}
		}

		System.out.println("> Reading from WikiPedia (First " + nOfSentences + " Sentences from Page): DONE");
		System.out.println("> Found " + n + " descriptions");
	}
}
