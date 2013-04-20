package br.com.entropie.hellocuriosity.rss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.entropie.hellocuriosity.News;

public class CategoryFilter implements NewsFilter {

	//Press Releases    Feature Stories    Spotlights    Status Reports  
	private final static ArrayList<String> categories = new ArrayList<String>(Arrays.asList("status reports","sporlights", "feature stories"));
	
	public List<News> filterFrom(List<News> newsList) {
		List<News> filtredNews = new ArrayList<News>();
		
		for (News news : newsList){
			if (containsCategory(news))
					filtredNews.add(news);
		}
		
		return null;
	}
	private boolean containsCategory(News news) {
		
		for (String category : news.getCategories()){
			if (categories.contains(category)){
				return true;
			}
		}
		return false;
	}

}
