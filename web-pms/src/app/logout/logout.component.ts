import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  message = "WYLOGOWANO";
  info;

  constructor(private tokenStorageService: TokenStorageService,
    private router: Router) {
    if (this.router.getCurrentNavigation()?.extras.state !== null && this.router.getCurrentNavigation()?.extras.state !== undefined) {
      this.info = this.router.getCurrentNavigation()?.extras.state;
      console.log(this.info!.info);
      this.message = this.info!.info;
    }


  }

  ngOnInit(): void {
    if (this.tokenStorageService.getJWTToken()) {
      this.logout();
      if (this.info !== undefined) {
        setTimeout(() => {
          this.router.navigate(['login'])
            .then(() => {
              window.location.reload();
            });
        }, 2500);
      } else
        this.reloadPage();
    }
  }


  logout(): void {
    this.tokenStorageService.signOut();
  }
  reloadPage(): void {
    window.location.reload();
  }
}

