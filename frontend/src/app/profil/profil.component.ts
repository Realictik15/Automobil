import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from '../service/token-storage.service';
import {AuthService} from '../service/auth.service';
import {User} from '../model/user';
import {ClientsService} from '../service/clients.service';
import {Client} from '../model/client';
import {Advertisment} from '../model/advertisment';
import {AdvertismentServiceService} from '../service/advertisment-service.service';
import {nullSafeIsEquivalent} from '@angular/compiler/src/output/output_ast';


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

  update(advert: Advertisment, s: string) {
    advert.available = s;
    advert.carbodyTitle = null;
    advert.clientsDto = null;
    advert.marksTitle = null;
    advert.modelTitle = null;
    advert.generationsDto = null;
    advert.modificationsDto = null;
    advert.broken = null;
    advert.pts = null;
    advert.vin = null;
    console.log(advert);
    this.advertSev.patchAdvert(advert, advert.idAdvert).subscribe(data => {
      location.href = '/profile';
    });

  }

  Delete(idAdvert: bigint) {
    this.advertSev.deleteAdvert(idAdvert).subscribe();
    location.href = '/profile';
  }
}
