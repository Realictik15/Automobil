import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Advertisment} from '../model/advertisment';
import {PageAdvert} from '../model/PageAdvert';
import {AdvertismentServiceService} from '../service/advertisment-service.service';
import {TokenStorageService} from '../service/token-storage.service';
import {User} from '../model/user';
import {ClientsService} from '../service/clients.service';
import {MarkServiceService} from '../service/mark-service.service';
import {Mark} from '../model/mark';
import {ModelsService} from '../service/models.service';
import {Model} from '../model/model';
import {Carbody} from '../model/carbody';
import {GearBox} from '../model/gearBox';
import {Filter} from '../model/filter';
import {CarbodyService} from '../service/carbody.service';
import {GearboxService} from '../service/gearbox-service';
import * as moment from 'moment';

@Component({
  selector: 'app-advertismets',
  templateUrl: './advertismets.component.html',
  styleUrls: ['./advertismets.component.css']
})
export class AdvertismetsComponent implements OnInit {

  user: User;
  item: Advertisment;
  arrMarks: Mark[];
  arrModel: Model[];
  arrCarBody: Carbody[];
  arrGear: GearBox[];
  filter: Filter;
  tmp: Mark;
  date: number[] = [];
  pageAdvert: PageAdvert;
  size = 3;
  page = 1;
  isClass = true;
  selectedPage = 0;
  err = '';
  loading = false;
  isLogin = false;
  cout: number;
  c = 0;

  constructor(private adverdServ: AdvertismentServiceService, private tokenStorage: TokenStorageService,
              private clientServ: ClientsService, private mark: MarkServiceService, private carBody: CarbodyService,
              private gear: GearboxService) {
    this.filter = new Filter();
    this.tmp = new Mark();
  }

  ngOnInit(): void {
    this.getPage(0);
    this.getMarks();
    this.getCarBody();
    this.getGearBox();
    this.getDate();
    if (this.tokenStorage.getToken()) {
      this.isLogin = true;
      this.user = this.tokenStorage.getUser();
    }
  }

  getDate(): void {
    this.cout = new Date().getFullYear();
    this.date[0] = this.cout;
    for (let i = 1; i < this.cout - 1900; i++) {
      this.date[i] = this.cout - i;
    }
  }

  onSelect(page: number): void {
    this.selectedPage = page;
    if (this.c === 0) {
      this.getPage(page);
    } else {
      this.getPageFilter(page);
    }
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

  getPageFilter(page: number): void {
    this.loading = true;
    console.log(this.loading);
    this.adverdServ.postAdvertFilters(this.filter, page, this.size)
      .subscribe(data => {
          this.pageAdvert = data;
          this.loading = false;
        }, error => {
          this.err = error.error.message;
        }
      );
    this.err = '';
  }

  onSubmit(): void {
    this.c = 1;
    this.filter.mark = this.tmp?.title;
    this.getPageFilter(0);
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

  getMarks(): void {
    this.mark.getAllMarks().subscribe(data => {
      this.arrMarks = data;
    });
  }

  getModels(id: bigint): void {
    this.mark.getModelById(id).subscribe(data => {
      this.arrModel = data;
    }, error => {
      this.err = error.message;
    });
  }

  getCarBody(): void {
    this.carBody.getAllCarBodies().subscribe(data => {
      this.arrCarBody = data;
    }, error => {
      this.err = error.message;
    });
  }
  getGearBox(): void {
    this.gear.getGearBoxes().subscribe(data => {
      this.arrGear = data;
    }, error => {
      this.err = error.message;
    });
  }
  select(value: Mark): void {
    this.getModels(value.idMark);
  }
}

