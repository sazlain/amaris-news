import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

export class AppComponent {
  title = 'amaris-news-app';

  constructor(private router: Router) { }

  goToRoute(route: string = ''): void {
    this.router.navigate([route]);
  }
}
