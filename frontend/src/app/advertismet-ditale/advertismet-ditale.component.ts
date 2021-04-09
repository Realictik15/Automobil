import {Component, OnInit} from '@angular/core';
import {AdvertismentServiceService} from '../service/advertisment-service.service';


@Component({
  selector: 'app-advertismet-ditale',
  templateUrl: './advertismet-ditale.component.html',
  styleUrls: ['./advertismet-ditale.component.css']
})
export class AdvertismetDitaleComponent implements OnInit {


  constructor(private adverdServ: AdvertismentServiceService) {
  }

  ngOnInit(): void {

  }


}
