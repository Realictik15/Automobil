import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';
import {GenerationsService} from '../service/generations.service';
import {Modification} from '../model/modification';
import {Generation} from '../model/generation';
import {SizesService} from '../service/sizes.service';
import {CarbodyService} from '../service/carbody.service';
import {Carbody} from '../model/carbody';
import {Sizes} from '../model/sizes';

@Component({
  selector: 'app-characteristics',
  templateUrl: './characteristics.component.html',
  styleUrls: ['./characteristics.component.css']
})

export class CharacteristicsComponent implements OnInit {
  id: bigint;
  generation: Generation;
  modifications: Modification[];
  carbody: Carbody;
  size: Sizes;
  title: string;
  err = '';

  constructor(private route: ActivatedRoute, private genSev: GenerationsService, private sizeSev: SizesService, private carBodySev: CarbodyService) {
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.id = params.id;
      this.title = params.title;
    });
    this.getGeneration();
    this.getModifArr();
    this.getBodyByTitle();

  }

  getGeneration(): void {
    this.genSev.getGen(this.id).subscribe(data => {
      this.generation = data;
    }, error => {
      this.err = error.error.message;
      console.log(error);
    });
  }

  getModifArr(): void {
    this.genSev.getModifByGen(this.id).subscribe(data => {
      this.modifications = data;
      console.log(this.modifications);
    }, error => {
      this.err = error.error.message;
      console.log(error);
    });
  }

  getBodyByTitle():void{

    this.carBodySev.getBodyByTitle(this.title).subscribe(data => {

      this.carbody = data;
      console.log(this.carbody);
      this.getSize();
    }, error => {
      this.err = error.error.message;
      console.log(error);
    });
  }

  getSize():void{
    this.sizeSev.getSizeByidCarbodyAndGen(this.carbody.idCarBody, this.id).subscribe(data => {
      this.size = data;
      console.log( this.size);
    }, error => {
      this.err = error.error.message;
      console.log(error);
    });
  }
}
