import {Component, OnInit} from '@angular/core';
import {AdvertismentServiceService} from '../service/advertisment-service.service';
import {TokenStorageService} from '../service/token-storage.service';
import {ClientsService} from '../service/clients.service';
import {User} from '../model/user';
import {PageCompare} from '../model/PageCompare';
import {SizesService} from '../service/sizes.service';
import {Sizes} from '../model/sizes';

@Component({
  selector: 'app-car-compare',
  templateUrl: './car-compare.component.html',
  styleUrls: ['./car-compare.component.css']
})
export class CarCompareComponent implements OnInit {

  comparePage: PageCompare;
  user: User;
  page = 0;
  size = 3;
  selectedPage = 0;
  err = '';
  loading = false;
  login = false;
  cursize: Sizes;

  constructor(private advertSev: AdvertismentServiceService, private tokenStorage: TokenStorageService, private clentServ: ClientsService,
              private sizeSev: SizesService) {
  }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.user = this.tokenStorage.getUser();
      this.login = true;
       this.getPage(0);
    }
  }

  getPage(page: number): void {
    this.loading = true;
    this.getClientsCompare(page);
  }

  onSelect(page: number): void {
    this.selectedPage = page;
    this.getPage(page);

  }

  getClientsCompare(page: number) {
    this.clentServ.getClientComparepage(this.user.id, page, this.size).subscribe(data => {
      this.comparePage = data;
      console.log(data);
    });
  }

  delete(idCompar: bigint) {
    this.clentServ.deleteClientComp(idCompar).subscribe(data => {
      window.location.reload();
    });
  }

  getNumber(price: number): string {
    const rez = Math.round(price);
    return (rez + '').replace(/(\d)(?=(\d\d\d)+([^\d]|$))/g, '$1 ');
  }

}
