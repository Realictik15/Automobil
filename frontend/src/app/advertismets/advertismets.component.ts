import {Component, OnInit} from '@angular/core';
import {Advertisment} from '../model/advertisment';
import {PageAdvert} from '../model/PageAdvert';
import {AdvertismentServiceService} from '../service/advertisment-service.service';
import {TokenStorageService} from '../service/token-storage.service';
import {User} from '../model/user';
import {ClientsService} from '../service/clients.service';

@Component({
  selector: 'app-advertismets',
  templateUrl: './advertismets.component.html',
  styleUrls: ['./advertismets.component.css']
})
export class AdvertismetsComponent implements OnInit {

  user: User;
  item: Advertisment;
  pageAdvert: PageAdvert;
  size = 3;
  page = 1;
  isClass = true;
  selectedPage = 0;
  err = '';
  loading = false;
  isLogin = false;

  constructor(private adverdServ: AdvertismentServiceService, private tokenStorage: TokenStorageService,
              private clientServ: ClientsService) {
  }

  ngOnInit(): void {
    this.getPage(0);
    if (this.tokenStorage.getToken()) {
      this.isLogin = true;
      this.user = this.tokenStorage.getUser();
    }
  }

  onSelect(page: number): void {
    this.selectedPage = page;
    this.getPage(page);
  }

  getPage(page: number): void {
    this.loading = true;
    console.log(this.loading);
    this.adverdServ.getAllAvalibleAdvertPage(page, this.size)
      .subscribe(data => {
          this.pageAdvert = data;
          this.loading = false;
        }, error => {
          this.err = error.error.message;
        }
      );
    this.err = '';
  }

  getNumber(price: number): string {
    const rez = Math.round(price);
    const outrez = (rez + '').replace(/(\d)(?=(\d\d\d)+([^\d]|$))/g, '$1 ');
    return outrez;
  }

  setClass(owners: number): boolean {
    return owners < 3;
  }

  addCompare(idAdvert: bigint, id: bigint): void {
    this.clientServ.postCompare(id, idAdvert).subscribe(data => {
        console.log('success');
      }, error => {
        this.err = error.error.message;
        console.log(error);
      }
    );
  }
}

