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

@Component({
  selector: 'app-adminboard',
  templateUrl: './adminboard.component.html',
  styleUrls: ['./adminboard.component.css']
})
export class AdminboardComponent implements OnInit {
  adverts: Advertisment[];
  clients: Client[];
  user:User;
  mark:Mark;
  model:Model;
  gen:Generation;
  modif:Modification;
  loading=false;

  constructor(private markServ: MarkServiceService, private modelServ: ModelsService,
              private clientServ: ClientsService, private advertServ: AdvertismentServiceService,
              private genServ: GenerationsService, private modifServ: ModificationService,
              private tokenStroage: TokenStorageService,private sizeServ:SizesService,
              private engines:EnginesServiceService,private transServ:TransmissionService,
              private complServ: ComplSetsService) {
  }

  ngOnInit(): void {
    if (this.tokenStroage.getToken()) {
      this.user=this.tokenStroage.getUser();
      this.getAllClient();
    }
  }
  getAllClient(){
    this.clientServ.getAllClient().subscribe(data=>{
      this.clients=data;
      console.log(data)
    })
  }
   postMark(){
    this.loading=true;
    this.markServ.postMark(this.mark).subscribe(data=>{
      this.loading=false;
    })
   }
   postModel(){
     this.loading=true;
     this.modelServ.postModel(this.model).subscribe(data=>{
       this.loading=false;
     })
   }
  postGen(){
    this.loading=true;
    this.genServ.postGen(this.gen).subscribe(data=>{
      this.loading=false;
    })
  }
  postModif(){
    this.loading=true;
    this.modifServ.postModif(this.modif).subscribe(data=>{
      this.loading=false;
    })
  }

}
