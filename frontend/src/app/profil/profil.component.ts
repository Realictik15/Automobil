import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from '../service/token-storage.service';
import {AuthService} from '../service/auth.service';
import {User} from '../model/user';
import {ClientsService} from '../service/clients.service';
import {Client} from '../model/client';
import {Advertisment} from '../model/advertisment';
import {AdvertismentServiceService} from '../service/advertisment-service.service';


@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {
  err = '';
  mem = false;
  loading = false;
  isLogin = false;
  user: User;
  client: Client;
  name = '';
  flag = true;
  adverts: Advertisment[];

  constructor(private tokenStorage: TokenStorageService, private clientSev: ClientsService, private advertSev: AdvertismentServiceService) {
  }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLogin = true;
      this.user = this.tokenStorage.getUser();
      this.getClient();
      this.getUsrAdvert();
    }
    if (!(this.err === '')) {
      this.flag = false;
    }
  }

  getClient(): void {
    this.clientSev.getClient(this.tokenStorage.getUser().id).subscribe(data => {
      this.client = data;
    });
  }

  onSubmit(): void {
    console.log(this.client);
    this.clientSev.patchClient(this.client, this.tokenStorage.getUser().id).subscribe();
  }

  getUsrAdvert(): void {
    this.clientSev.getClientAdvert(this.tokenStorage.getUser().id).subscribe(data => {
      this.adverts = data;
    });
  }

  userDelete(idAdvert: bigint): void {
    this.advertSev.userDelete(idAdvert).subscribe();
    location.href = '/profile';
  }

  show(): void {
    this.mem = !this.mem;
  }
}
