package br.com.entropie.hellocuriosity.news.readers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import br.com.caelum.vraptor.ioc.Component;
import br.com.entropie.hellocuriosity.news.Asset;
import br.com.entropie.hellocuriosity.news.News;

@Component
public class FakeNews {

	private int HEADLINE_COLUMN = 0;
	private int TEXT_COLUMN = 1;
	private int DATE_COLUMN = 2;
	private int ASSET_COLUMN = 3;
	private int CATEGORY_COLUMN = 4;
	private int URL_COLUMN = 5;
	private int EXPECTED_COLUMN_QTY = 6;

	public List<News> lastNews() {
		try {
			List<News> fakeNews = getFakeNews(FakeNews.class.getResourceAsStream("/fakeNews.xls"));
			return fakeNews;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<News>();
		}
	}

	public List<News> getFakeNews(InputStream input) throws BiffException,
			IOException {

		List<News> fakeNewsList = new ArrayList<News>();
		Workbook workbook = Workbook.getWorkbook(input);
		Sheet sheet = workbook.getSheet(0);

		for (int r = 1; r < sheet.getRows(); r++) {

			String headline = sheet.getCell(HEADLINE_COLUMN, r).getContents()
					.trim();
			String text = sheet.getCell(TEXT_COLUMN, r).getContents().trim();
			String date = sheet.getCell(DATE_COLUMN, r).getContents().trim();
			Asset asset = new Asset(sheet.getCell(ASSET_COLUMN, r)
					.getContents().trim());
			String category = sheet.getCell(CATEGORY_COLUMN, r).getContents()
					.trim();
			String url = sheet.getCell(URL_COLUMN, r).getContents().trim();
			List<String> categories = new ArrayList<String>();
			categories.add(category);

			News news = new News(headline, text, url, date, asset, categories);

			fakeNewsList.add(news);

		}
		return fakeNewsList;
	}
}
