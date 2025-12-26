import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Auth {

  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, {
      username,
      password,
    });
  }

  logout() {
    localStorage.removeItem('loggedUser');
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('loggedUser');
  }
}
