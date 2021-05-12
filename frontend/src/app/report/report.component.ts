import {Component, OnInit} from '@angular/core';
import {AdvertismentServiceService} from '../service/advertisment-service.service';
import {TokenStorageService} from '../service/token-storage.service';
import {User} from '../model/user';
import {ActivatedRoute, Params} from '@angular/router';
import {Report} from '../model/Report';
import {Advertisment} from '../model/advertisment';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {
  user: User;
  login = false;
  id: bigint;
  vin: string;
  report: Report;
  loading=false;

  constructor(private advertSev: AdvertismentServiceService, private tokenStorage: TokenStorageService, private route: ActivatedRoute) {

  }


  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.user = this.tokenStorage.getUser();
      this.login = true;
      this.route.params.subscribe((params: Params) => {
        this.id = params.id;
        this.vin = params.vin;
        this.getRepot();
      });

    }
  }

  getRepot() {
    this.loading=true;
    this.advertSev.getReport(this.id, this.vin).subscribe(data => {
        this.report = data;
        this.loading=false;
        console.log(this.report);
      }
    )
  }

}
