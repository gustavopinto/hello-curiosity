package br.com.entropie.hellocuriosity;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import br.com.entropie.hellocuriosity.utils.Dates;

import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndEntry;

public class News {

	private final String headline;
	private final String text;
	private final String startDate;
	private final List<String> categories;
	private final Asset asset;

	public News(String headline, String text, String startDate, Asset asset, List<String> categories) {
		this.headline = headline;
		this.text = text;
		this.startDate = startDate;
		this.categories = categories;
		this.asset = asset;
	}

	public String getHeadline() {
		return headline;
	}

	public String getText() {
		return text;
	}

	public String getStartDate() {
		return startDate;
	}

	public List<String> getCategories() {
		return categories;
	}

    
	public String toString() {
		return "News [headline=" + headline + ", text=" + text + ", startDate="
+ startDate + "]";
	}

	public static News buildWith(SyndEntry entry) {
		return new News(entry.getTitle(), extractEntryDescription(entry),
				Dates.format(entry.getPublishedDate()), extractEntryImage(entry), extractCategories(entry));

	}

	private static List<String> extractCategories(SyndEntry entry) {
		List<String> categories = new ArrayList<String>();
		for (Object category : entry.getCategories()) {
			String categoryName = ((SyndCategoryImpl) category).getName();
			categories.add(categoryName);
		}
		return categories;
		
	}

	private static String extractEntryDescription(SyndEntry entry) {
		String html = entry.getDescription().getValue();
		return html.replaceAll("<!--.*?-->", "").replaceAll("<[^>]+>", "").trim();
	}

	private static Asset extractEntryImage(SyndEntry entry) {
		String html = entry.getDescription().getValue();
		String value = Jsoup.parse(html, "UTF-8").select("img").attr("src");
		return new Asset(value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asset == null) ? 0 : asset.hashCode());
		result = prime * result
				+ ((categories == null) ? 0 : categories.hashCode());
		result = prime * result
				+ ((headline == null) ? 0 : headline.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		if (asset == null) {
			if (other.asset != null)
				return false;
		} else if (!asset.equals(other.asset))
			return false;
		if (categories == null) {
			if (other.categories != null)
				return false;
		} else if (!categories.equals(other.categories))
			return false;
		if (headline == null) {
			if (other.headline != null)
				return false;
		} else if (!headline.equals(other.headline))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	
}
