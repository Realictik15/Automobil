import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import {AppRoutingModule} from './app-routing.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {authInterceptorProviders} from './AuthInterceptor/AuthInterceptor';
import {LoginComponent} from './login/login.component';
import {AddAdvertComponent} from './add-advert/add-advert.component';
import {AdminboardComponent} from './adminboard/adminboard.component';
import {AdvertismetDitaleComponent} from './advertismet-ditale/advertismet-ditale.component';
import {AdvertismetsComponent} from './advertismets/advertismets.component';
import {CarCompareComponent} from './car-compare/car-compare.component';
import {HomepageComponent} from './homepage/homepage.component';
import {InfoAdvertComponent} from './info-advert/info-advert.component';
import {RegistrComponent} from './registr/registr.component';
import {RewisComponent} from './rewis/rewis.component';
import {RewisDitaleComponent} from './rewis-ditale/rewis-ditale.component';
import { ScrollingModule} from '@angular/cdk/scrolling';
import { ReportComponent } from './report/report.component';
import {ProfilComponent} from './profil/profil.component';
import { EditAdvertComponent } from './edit-advert/edit-advert.component';
import { CharacteristicsComponent } from './characteristics/characteristics.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    LoginComponent,
    AddAdvertComponent,
    AdminboardComponent,
    AdvertismetDitaleComponent,
    AdvertismetsComponent,
    CarCompareComponent,
    HomepageComponent,
    InfoAdvertComponent,
    RegistrComponent,
    RewisComponent,
    RewisDitaleComponent,
    ReportComponent,
    ProfilComponent,
    EditAdvertComponent,
    CharacteristicsComponent
  ],
  imports: [
    ScrollingModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
