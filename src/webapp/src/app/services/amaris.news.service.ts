import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class AmarisNewsService {
  private apiUrl = 'http://localhost:8080/amaris-news';

  constructor(private http: HttpClient) { }

  getNews(): Observable<any> {
    const url = `${this.apiUrl}/api/news`;
    return this.http.get(url);
  }

  getBookmarks(): Observable<any> {
    const url = `${this.apiUrl}/api/bookmarks`;
    return this.http.get(url);
  }

  removeBookmark(endpoint: string): Observable<any> {
    const url = `${this.apiUrl}/${endpoint}`;
    return this.http.delete(url);
  }

  addBookmark(endpoint: string): Observable<any> {
    const url = `${this.apiUrl}/${endpoint}`;
    return this.http.post(url, null);
  }

}