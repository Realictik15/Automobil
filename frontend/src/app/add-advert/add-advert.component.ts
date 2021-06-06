import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {GenerationsService} from '../service/generations.service';
import {SizesService} from '../service/sizes.service';
import {CarbodyService} from '../service/carbody.service';
import {Generation} from '../model/generation';
import {Modification} from '../model/modification';
import {Carbody} from '../model/carbody';
import {Mark} from '../model/mark';
import {Model} from '../model/model';
import {MarkServiceService} from '../service/mark-service.service';
import {ModelsService} from '../service/models.service';
import {TokenStorageService} from '../service/token-storage.service';
import {FormAdvert} from '../model/formAdvert';
import {AdvertismentServiceService} from '../service/advertisment-service.service';
import {errorObject} from 'rxjs/internal-compatibility';

@Component({
  selector: 'app-add-advert',
  templateUrl: './add-advert.component.html',
  styleUrls: ['./add-advert.component.css']
})
export class AddAdvertComponent implements OnInit {

  formAdvert: FormAdvert;
  marks: Mark[];
  models: Model[];
  generations: Generation[];
  modifications: Modification[];
  carbody: Carbody[];
  gearboxS: Set<string>;
  transmType: Set<string>;
  gear: string;
  typeDrive: string;
  cout: number;
  date: number[] = [];
  err = '';
  isLogin = false;
  idUser: bigint;
  message = '';


  constructor(private route: ActivatedRoute, private genSev: GenerationsService, private sizeSev: SizesService, private carBodySev: CarbodyService,
              private masrkSev: MarkServiceService, private modelSev: ModelsService, private tokenStorage: TokenStorageService,
              private advertSev: AdvertismentServiceService) {
    this.formAdvert = new FormAdvert();
  }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLogin = true;
      this.idUser = this.tokenStorage.getUser().id;
      this.getMarks();
      this.getDate();


    }
  }

  getDate(): void {
    this.cout = new Date().getFullYear();
    this.date[0] = this.cout;
    for (let i = 1; i < this.cout - 1960; i++) {
      this.date[i] = this.cout - i;
    }
  }


  getMarks(): void {
    this.masrkSev.getAllMarks().subscribe(data => {
      this.marks = data;
      console.log(this.marks);
    });
  }

  getModels(id: bigint): void {
    this.masrkSev.getModelById(id).subscribe(data => {
      this.models = data;
    }, error => {
      this.err = error.message;
    });
  }

  getGenerations(id: bigint, date: number): void {
    this.modelSev.getGenByIdModel(id).subscribe(data => {
      this.generations = data;
      console.log(this.generations);
      for (let i = 0; i < this.generations.length; i++) {
        if (this.generations[i].yearEndDate == null) {
          this.generations[i].yearEndDate = new Date().getFullYear().toString();
        }
      }
      this.generations = this.generations.filter(item => (+item.yearStartDate.substr(0, 4) <= date && +item.yearEndDate.substr(0, 4) >= date));
    }, error => {
      this.err = error.error.message;
      console.log(error);
    });
  }

  getCarbody(id: bigint) {
    this.genSev.getCarBody(id).subscribe(data => {
      this.carbody = data;
    }, error => {
      this.err = error.error.message;
      console.log(error);
    });
  }

  getGearBox() {
    let tmp = [];
    for (let i = 0; i < this.modifications.length; i++) {
      tmp[i] = this.modifications[i].transmissionsDto.gearBoxDto.title;
    }
    this.gearboxS = new Set(tmp);
  }

  getTransType() {
    let tmp = [];
    for (let i = 0; i < this.modifications.length; i++) {
      tmp[i] = this.modifications[i].transmissionsDto.driveType;
    }
    this.transmType = new Set(tmp);
  }

  getModifArr(id: bigint): void {
    this.genSev.getModifByGen(id).subscribe(data => {
      this.modifications = data;
      console.log(this.modifications);
      this.getGearBox();
      this.getTransType();
    }, error => {
      this.err = error.error.message;
      console.log(error);
    });
  }

  selectModel(mark: Mark): void {
    this.getModels(mark.idMark);
  }

  selectGeneration(model: Model, date: number): void {
    this.getGenerations(model.idModel, date);
  }

  selectCarBodyModif(gen: Generation): void {
    this.getCarbody(gen.idGen);
    this.getModifArr(gen.idGen);

  }

  setModif() {
    this.modifications = this.modifications.filter(item => (item.transmissionsDto.gearBoxDto.title === this.gear) && (item.transmissionsDto.driveType === this.typeDrive));
  }

  onSubmit() {
    let resformData = this.setFormData(this.formAdvert);
    console.log(resformData.get('carbodyTitle'));
    resformData.forEach(element => console.log(element));
    this.advertSev.postAdvert(resformData).subscribe(res => {
        window.location.reload()
      }, error => {
        this.err = error.message;
      }
    )
    ;

  }

  setFormData(form: FormAdvert): FormData {
    const formData = new FormData();
    for (let i = 0; i < form.files.length; i++) {
      console.log(this.formAdvert.files[i]);
      formData.append('files', form.files[i]);
    }
    formData.append('carbodyTitle', form.carbodyTitle);
    formData.append('clientsId', this.idUser.toString());
    formData.append('marksTitle', form.mark.title);
    formData.append('modelTitle', form.model.title);
    formData.append('generationsId', String(form.generations.idGen));
    formData.append('modificationsId', String(form.modifications.idModif));
    formData.append('available', 'yes');
    formData.append('price', String(form.price));
    formData.append('colour', form.colour);
    formData.append('mileage', String(form.mileage));
    if (form.broken) {
      formData.append('broken', String(1));
    } else {
      formData.append('broken', String(0));
    }

    formData.append('city', form.city);
    formData.append('place', form.place);
    formData.append('phone', String(form.phone));
    if (form.swap) {
      formData.append('swap', String(1));
    } else {
      formData.append('swap', String(0));
    }

    if (form.pts) {
      formData.append('pts', String(1));
    } else {
      formData.append('pts', String(0));
    }

    formData.append('owners', String(form.owners));
    formData.append('buydays', String(form.buyday + '-01-01'));
    if (form.dayofwarranty) {
      formData.append('dayofwarrantys', String(form.dayofwarranty));
    }
    formData.append('vin', form.vin);
    formData.append('gosnumber', form.gosnumber);
    formData.append('sts', String(form.sts));
    formData.append('commentns', form.commentns);
    return formData;

  }

  addPhoto(event) {
    if (event.target.files.length > 6) {
      alert('Вы выбрали более 6 фото, все остальные фото не будут сохранены ');
    } else {
      for (let i = 0; i < event.target.files.length && i < 6; i++) {
        this.formAdvert.files[i] = event.target.files[i];
      }
      this.message = '';
    }
  }
}
