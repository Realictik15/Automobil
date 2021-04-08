import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from '../service/token-storage.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isLogin = false;

  constructor(private tokenStroage: TokenStorageService) {
  }

  ngOnInit(): void {
    if (this.tokenStroage.getToken()) {
      this.isLogin = true;
    }
  }

  logout(): void {
    this.tokenStroage.signOut();
    window.location.reload();
  }
}
