import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import {AppRoutingModule} from './app-routing.module';
import {FormsModule} from '@angular/forms';
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
    RewisDitaleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
