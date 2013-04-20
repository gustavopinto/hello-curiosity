package br.com.entropie.hellocuriosity.controller;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.entropie.hellocuriosity.news.News;
import br.com.entropie.hellocuriosity.news.Timeline;
import br.com.entropie.hellocuriosity.news.filters.CategoryFilter;
import br.com.entropie.hellocuriosity.rss.RssReader;
import br.com.entropie.hellocuriosity.utils.PlainJSONSerialization;

@Resource
public class IndexController {

	private final Result result;
	private final RssReader rssReader;

	public IndexController(Result result, RssReader rssReader) {
		this.result = result;
		this.rssReader = rssReader;
	}

	@Get("/")
	public void index() {
	}

	@Get("/timeline")
	public void timeline() {
		List<News> news = this.rssReader.defaultFeed().lastNews(new CategoryFilter());
		Timeline timeline = new Timeline(news);
		this.result.use(PlainJSONSerialization.class).from(timeline).recursive().serialize();
	}
}
