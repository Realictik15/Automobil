import {Component, OnInit} from '@angular/core';
import {MarkServiceService} from '../service/mark-service.service';
import {ModelsService} from '../service/models.service';
import {ClientsService} from '../service/clients.service';
import {AdvertismentServiceService} from '../service/advertisment-service.service';
import {GenerationsService} from '../service/generations.service';
import {ModificationService} from '../service/modification-service';
import {Advertisment} from '../model/advertisment';
import {Client} from '../model/client';
import {TokenStorageService} from '../service/token-storage.service';
import {User} from '../model/user';
import {Mark} from '../model/mark';
import {Model} from '../model/model';
import {Generation} from '../model/generation';
import {Modification} from '../model/modification';
import {SizesService} from '../service/sizes.service';
import {EnginesServiceService} from '../service/engines-service.service';
import {TransmissionService} from '../service/transmission.service';
import {ComplSetsService} from '../service/compl-sets.service';
import {CompleSet} from '../model/compleSet';
import {Transmission} from '../model/transmission';
import {Sizes} from '../model/sizes';

@Component({
  selector: 'app-adminboard',
  templateUrl: './adminboard.component.html',
  styleUrls: ['./adminboard.component.css']
})
export class AdminboardComponent implements OnInit {
  adverts: Advertisment[];
  clients: Client[];
  user: User;
  mark: Mark;
  model: Model;
  modelArr: Model[];
  generation: Generation;
  modif: Modification;
  compl: CompleSet;
  complArr: CompleSet[];
  transArr: Transmission[];
  size: Sizes;
  loading = false;
  err=''

  constructor(private markServ: MarkServiceService, private modelServ: ModelsService,
              private clientServ: ClientsService, private advertServ: AdvertismentServiceService,
              private genServ: GenerationsService, private modifServ: ModificationService,
              private tokenStroage: TokenStorageService, private sizeServ: SizesService,
              private engines: EnginesServiceService, private transServ: TransmissionService,
              private complServ: ComplSetsService) {
    this.generation = new Generation();
    this.mark = new Mark();
    this.modif = new Modification();
    this.model = new Model();
  }

  ngOnInit(): void {
    if (this.tokenStroage.getToken()) {
      this.user = this.tokenStroage.getUser();
      this.getAllClient();
      this.getAllModel();
    }
  }

  getAllClient() {
    this.clientServ.getAllClient().subscribe(data => {
      this.clients = data;
      console.log(data);
    });
  }

  deleteClient(id: bigint) {
    this.clientServ.deleteClient(id).subscribe(data => {
      window.location.reload();
    });
  }

  postMark(mark:Mark) {
    this.loading = true;
    this.markServ.postMark(mark).subscribe(data => {
      this.loading = false;
    },err=>{
      this.err=err.message
    });
  }

  postModel() {
    this.loading = true;
    this.modelServ.postModel(this.model).subscribe(data => {
      this.loading = false;
    });
  }

  getAllModel() {
    this.modelServ.getAllModel().subscribe(data => {
      this.modelArr = data;
      console.log(data);
    });
  }

  postGen() {
    this.loading = true;
    this.genServ.postGen(this.generation).subscribe(data => {
      this.loading = false;
    });
  }

  postModif() {
    this.loading = true;
    this.modifServ.postModif(this.modif).subscribe(data => {
      this.loading = false;
    });
  }

  postCompl() {
    this.complServ.postCompl(this.compl).subscribe();
  }

  getAllCompl() {
    this.complServ.getComplAll().subscribe(data => {
      this.complArr = data;
    });
  }

  getAllTrans() {
    this.transServ.getAllTransmission().subscribe(data => {
      this.transArr = data;
    });
  }

  setSizes() {
    this.sizeServ.postAdvert(this.size).subscribe();
  }

  onSubmitMark() {
    this.mark.image='/'
    console.log(this.mark);
    this.postMark(this.mark)
  }
}
