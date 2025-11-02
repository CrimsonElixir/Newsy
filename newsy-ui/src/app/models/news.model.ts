export interface NewsArticle {
  id: string;
  title: string;
  author: string;
  source: string;
  url: string;
  publishedAt: string;
  category: string;
  description: string;
  imageUrl: string;
  sourceIcon: string;
}

export interface NewsResponse {
  totalCategories: number;
  totalArticles: number;
  news: {
    [category: string]: NewsArticle[];
  };
}

export interface CategoryNewsResponse {
  category: string;
  count: number;
  articles: NewsArticle[];
}

