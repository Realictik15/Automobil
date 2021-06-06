import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';
import {GenerationsService} from '../service/generations.service';
import {Modification} from '../model/modification';
import {Generation} from '../model/generation';
import {SizesService} from '../service/sizes.service';
import {CarbodyService} from '../service/carbody.service';
import {Carbody} from '../model/carbody';
import {Sizes} from '../model/sizes';
import {ComplSetsService} from '../service/compl-sets.service';
import {ModificationService} from '../service/modification-service';
import {CompleSet} from '../model/compleSet';

@Component({
  selector: 'app-characteristics',
  templateUrl: './characteristics.component.html',
  styleUrls: ['./characteristics.component.css']
})

export class CharacteristicsComponent implements OnInit {
  id: bigint;
  generation: Generation;
  modifications: Modification[];
  curentModif: Modification;
  complesets:CompleSet[];
  carbody: Carbody;
  size: Sizes;
  title: string;
  idcur: bigint;
  err = '';
  arrimg = ['assets/q50_tech_full.jpg', 'assets/qx50_tech_full.jpg', 'assets/b5_1.jpg', 'assets/xet.jpg'];
  img: string;
  listSusp: string[];
  listBrakes: string[];
  arrClass = [false, false, false, false];

  constructor(private modifServ:ModificationService ,private route: ActivatedRoute, private genSev: GenerationsService, private sizeSev: SizesService, private carBodySev: CarbodyService) {
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.id = params.id;
      this.title = params.title;
      this.idcur = params.idm;
      this.getGeneration();
      this.getModifArr();
      this.getBodyByTitle();
      this.setImg();
    });


  }

  setImg() {
    switch (this.title) {
      case 'Седан': {
        this.img = this.arrimg[0];
        this.arrClass[0] = true;
        break;
      }
      case 'Внедорожник': {
        this.img = this.arrimg[1];
        this.arrClass[1] = true;
        break;
      }
      case 'Универсал': {
        this.img = this.arrimg[2];
        this.arrClass[2] = true;
        break;
      }
      case 'Хэтчбек': {
        this.img = this.arrimg[3];
        this.arrClass[2] = true;
        break;
      }
      default:
        this.img = this.arrimg[0];
        this.arrClass[0] = true;
        break;
    }

  }


  getGeneration(): void {
    this.genSev.getGen(this.id).subscribe(data => {
      this.generation = data;
      console.log(data)
    }, error => {
      this.err = error.error.message;
      console.log(error);
    });
  }

  getModifArr(): void {
    console.log(this.idcur);
    this.genSev.getModifByGen(this.id).subscribe(data => {
      this.modifications = data;
      console.log(data)
      this.curentModif = this.modifications.filter(item => item.idModif == this.idcur)[0];
      this.psrsing();
    }, error => {
      this.err = error.error.message;
      console.log(error);
    });
  }

  getBodyByTitle(): void {
    console.log(this.idcur);
    this.carBodySev.getBodyByTitle(this.title).subscribe(data => {

      this.carbody = data;
      console.log(this.carbody);
      this.getSize();
    }, error => {
      this.err = error.error.message;
      console.log(error);
    });
  }

  getSize(): void {
    console.log(this.idcur);
    this.sizeSev.getSizeByidCarbodyAndGen(this.carbody.idCarBody, this.id).subscribe(data => {
      this.size = data;
      console.log(this.size);
    }, error => {
      this.err = error.error.message;
      console.log(error);
    });
  }

  psrsing(): void {
    this.listSusp = this.curentModif.transmissionsDto.suspension.split('/');
    this.listBrakes = this.curentModif.transmissionsDto.brakes.split('/');
  }
}
