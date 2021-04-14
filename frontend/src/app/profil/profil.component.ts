import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from '../service/token-storage.service';
import {AuthService} from '../service/auth.service';
import {User} from '../model/user';


@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {
  err = '';
  loading = false;
  isLogin = false;
  user: User;
  name = '';
  flag = true;
  constructor(private tokenStorage: TokenStorageService, private authService: AuthService) {
  }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLogin = true;
      this.user = this.tokenStorage.getUser();
    }
    if (!(this.err === '')) {
      this.flag = false;
    }
  }

}
