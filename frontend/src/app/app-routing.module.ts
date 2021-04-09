import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {HomepageComponent} from './homepage/homepage.component';
import {AdvertismetsComponent} from './advertismets/advertismets.component';
import {AdvertismetDitaleComponent} from './advertismet-ditale/advertismet-ditale.component';
import {LoginComponent} from './login/login.component';
import {RegistrComponent} from './registr/registr.component';
import {ProfilComponent} from './profil/profil.component';
import {InfoAdvertComponent} from './info-advert/info-advert.component';
import {AdminboardComponent} from './adminboard/adminboard.component';
import {AddAdvertComponent} from './add-advert/add-advert.component';
import {CarCompareComponent} from './car-compare/car-compare.component';
import {RewisComponent} from './rewis/rewis.component';
import {RewisDitaleComponent} from './rewis-ditale/rewis-ditale.component';

const routes: Routes = [
  {path: '', component: HomepageComponent},
  {path: 'adverts/:class', component: AdvertismetsComponent},
  {
    path: 'advert/:id', component: AdvertismetDitaleComponent, children:
      [{path: 'info', component: InfoAdvertComponent}]
  },
  {path: 'login', component: LoginComponent},
  {path: 'reg', component: RegistrComponent},
  {path: 'profile', component: ProfilComponent},
  {path: 'admin', component: AdminboardComponent},
  {path: 'add/car', component: AddAdvertComponent},
  {path: 'compare', component: CarCompareComponent},
  {path: 'reviews', component: RewisComponent},
  {path: 'reviews/:id', component: RewisDitaleComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {

}
