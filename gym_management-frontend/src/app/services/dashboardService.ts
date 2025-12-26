import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface DashboardResponse {
  username: string;
  membershipName: string;
  joinDate: string;
  nextDueDate: string;
  status: string;
  lastPaymentAmount: number;
  dietDescription: string;
}

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private baseUrl = 'http://localhost:8080/api/dashboard';

  constructor(private http: HttpClient) {}

  getDashboard(userId: number): Observable<DashboardResponse> {
    return this.http.get<DashboardResponse>(`${this.baseUrl}/${userId}`);
  }
}
