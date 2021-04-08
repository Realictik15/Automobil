import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from '../service/token-storage.service';
import {Client} from '../model/client';
import {Auth} from '../model/auth';
import {AuthService} from '../service/auth.service';

@Component({
  selector: 'app-registr',
  templateUrl: './registr.component.html',
  styleUrls: ['./registr.component.css']
})
export class RegistrComponent implements OnInit {
  client: Client;
  err = '';
  isLogin = false;
  loading = false;
  name = '';
  date;

  constructor(private tokenStorage: TokenStorageService, private authService: AuthService) {
    this.client = new Client();
    this.date = new Date();
  }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLogin = true;
      this.name = this.tokenStorage.getUser().name;
    }
    console.log(this.date);
  }

  onSubmit(): void {
    console.log(this.client);
    this.loading = true;
    this.authService.register(this.client).subscribe(
      data => {
        this.loading = false;
        location.href = '/profile';
      }, error => {
        this.err = error.error.message;
        console.log(error);
      }
    );
    this.err = '';
  }

}
