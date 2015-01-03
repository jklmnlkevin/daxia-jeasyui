package com.daxia.wy.web.response;

import java.util.List;
/**
 * 图文信息
 * @author zzc
 * @email zzc6055@gmail.com
 * @version 1.0
 * @since 2014-1-22
 */
public class NewsMessage extends BaseMessage {  
    // 图文消息个数，限制为10条以内  
    private int ArticleCount;  
    // 多条图文消息信息，默认第一个item为大图  
    private List<Article> Articles;  
  
    public int getArticleCount() {  
        return ArticleCount;  
    }  
  
    public void setArticleCount(int articleCount) {  
        ArticleCount = articleCount;  
    }  
  
    public List<Article> getArticles() {  
        return Articles;  
    }  
  
    public void setArticles(List<Article> articles) {  
        Articles = articles;  
    }  
}