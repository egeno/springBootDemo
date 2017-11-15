package com.qjkj.qjcsp.entity.weixin.res;

import java.util.List;

public class ResNewsMessage extends ResBaseMessage {

	private int ArticleCount;

	private List<ResArticle> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<ResArticle> getArticles() {
		return Articles;
	}

	public void setArticles(List<ResArticle> articles) {
		Articles = articles;
	}
	
}
