import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from "../../environments/environment";

export interface SubscribePayload {
  email: string;
  categories: string[];
}

@Injectable({ providedIn: 'root' })
export class SubscriptionService {

  private apiUrl = environment.apiUrl;
  private base =  this.apiUrl + '/api/subscriptions';

  constructor(private http: HttpClient) {}

  subscribe(payload: SubscribePayload): Observable<any> {
    return this.http.post<any>(`${this.base}/subscribe`, payload);
  }
}
