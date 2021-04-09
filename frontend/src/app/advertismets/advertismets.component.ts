import { Component, OnInit } from '@angular/core';
import {Advertisment} from '../model/advertisment';
import {PageAdvert} from '../model/PageAdvert';
import {AdvertismentServiceService} from '../service/advertisment-service.service';

@Component({
  selector: 'app-advertismets',
  templateUrl: './advertismets.component.html',
  styleUrls: ['./advertismets.component.css']
})
export class AdvertismetsComponent implements OnInit {

  items: Advertisment[];
  pageAdvert: PageAdvert;
  size = 2;
  page = 1;
  selectedPage = 0;

  constructor(private adverdServ: AdvertismentServiceService) {
  }

  ngOnInit(): void {
    this.getPage(0);
  }

  onSelect(page: number): void {
    this.selectedPage = page;
    this.getPage(page);
  }

  getPage(page: number): void {
    this.adverdServ.getAllAvalibleAdvertPage(page, this.size)
      .subscribe(data => {
        this.pageAdvert = data;
      });
  }

}
