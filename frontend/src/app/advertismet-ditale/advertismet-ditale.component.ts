import {Component, OnInit} from '@angular/core';
import {AdvertismentServiceService} from '../service/advertisment-service.service';
import {ActivatedRoute, Params} from '@angular/router';
import {Advertisment} from '../model/advertisment';


@Component({
  selector: 'app-advertismet-ditale',
  templateUrl: './advertismet-ditale.component.html',
  styleUrls: ['./advertismet-ditale.component.css']
})
export class AdvertismetDitaleComponent implements OnInit {

  advert: Advertisment;

  constructor(private adverdServ: AdvertismentServiceService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.adverdServ.getAdvertById(params.id).subscribe(data => {
        this.advert = data;
      });
    });
  }

  // getAdvertisment(id:)
}
