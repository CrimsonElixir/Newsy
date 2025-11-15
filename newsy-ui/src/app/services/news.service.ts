import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { NewsResponse, CategoryNewsResponse } from '../models/news.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NewsService {
  private apiUrl = environment.apiUrl;
  private base =  this.apiUrl + '/api/news';

  constructor(private http: HttpClient) { }

  getAllNews(): Observable<NewsResponse> {
    return this.http.get<NewsResponse>(this.base);
  }

  getNewsByCategory(category: string): Observable<CategoryNewsResponse> {
    return this.http.get<CategoryNewsResponse>(`${this.base}/${category}`);
  }

  fetchNews(): Observable<any> {
    return this.http.post(`${this.base}/fetch`, {});
  }
}
