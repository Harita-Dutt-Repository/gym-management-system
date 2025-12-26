import { Component } from '@angular/core';
import { ReactiveFormsModule, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { Auth } from '../../services/auth';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrls: ['./login.scss'],
})
export class Login {

  form;

  constructor(
    private fb: FormBuilder,
    private auth: Auth,
    private router: Router
  ) {
    this.form = this.fb.group({
      username: [''],
      password: [''],
    });
  }

  onLogin() {
    const { username, password } = this.form.value;

    if (!username || !password) {
      alert('Username and password are required');
      return;
    }
    this.auth.login(username, password).subscribe({
      next: (response) => {
        // example response: { userId: 8, username: 'john', role: 'MEMBER' }
    
        localStorage.setItem('userId', response.id);
        localStorage.setItem('username', response.username);
        localStorage.setItem('role', response.role);
        console.log(localStorage);
        this.router.navigate(['/dashboard']);
      }
      ,
      error: () => {
        alert('Invalid username or password');
      }
    });
  }
}
