import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DashboardResponse, DashboardService } from '../../services/dashboardService';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.scss'],
})
export class Dashboard implements OnInit {

  dashboardData?: DashboardResponse;
  loading = true;

  constructor(
    private dashboardService: DashboardService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const userId = Number(localStorage.getItem('userId'));
    console.log(userId);
    if (!userId) {
      this.router.navigate(['/login']);
      return;
    }

    this.dashboardService.getDashboard(userId).subscribe({
      next: (data) => {
        this.dashboardData = data;
        this.loading = false;
      },
      error: (err) => {
        console.error(err);
        this.loading = false;
      }
    });
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}