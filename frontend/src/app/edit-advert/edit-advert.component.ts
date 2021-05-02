import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';
import {Advertisment} from '../model/advertisment';
import {AdvertismentServiceService} from '../service/advertisment-service.service';
import {TokenStorageService} from '../service/token-storage.service';

@Component({
  selector: 'app-edit-advert',
  templateUrl: './edit-advert.component.html',
  styleUrls: ['./edit-advert.component.css']
})
export class EditAdvertComponent implements OnInit {
  advert: Advertisment;
  editAdvert: Advertisment;
  slideIndex = 1;
  id: number;
  slides: HTMLCollectionOf<HTMLElement>;
  dots: HTMLCollectionOf<HTMLElement>;
  isLogin=false;
  constructor(private adverdServ: AdvertismentServiceService, private route: ActivatedRoute, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    if(this.tokenStorage.getToken()){
      this.isLogin=true;
      this.route.params.subscribe((params: Params) => {
        this.id = params.id;
      });
      this.adverdServ.getAdvertById(this.id).subscribe(data => {
        this.advert = data;
        this.editAdvert=data;
      });
    }
  }

  ngAfterViewChecked(): void {
    this.showSlides(1);
  }

  plusSlides(n): void {
    this.showSlides(this.slideIndex += n);
  }

  currentSlide(n): void {
    console.log(n);
    this.showSlides(this.slideIndex = n);
  }

  showSlides(n) {
    this.slides = document.getElementsByClassName('mySlides') as HTMLCollectionOf<HTMLElement>;
    const dots = document.getElementsByClassName('demo') as HTMLCollectionOf<HTMLElement>;
    if (n > this.slides.length) {
      this.slideIndex = 1;
    }
    if (n < 1) {
      this.slideIndex = this.slides.length;
    }
    for (let i = 0; i < this.slides.length; i++) {
      this.slides[i].style.display = 'none';
    }
    for (let i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(' active', '');
    }
    this.slides[this.slideIndex - 1].style.display = 'block';
    dots[this.slideIndex - 1].className += ' active';

  }

  getNumber(price: number): string {
    const rez = Math.round(price);
    const outrez = (rez + '').replace(/(\d)(?=(\d\d\d)+([^\d]|$))/g, '$1 ');
    return outrez;
  }
  update(advert: Advertisment) {
    advert.carbodyTitle = null;
    advert.clientsDto = null;
    advert.marksTitle = null;
    advert.modelTitle = null;
    advert.generationsDto = null;
    advert.modificationsDto = null;
    advert.broken = null;
    advert.pts = null;
    advert.vin = null;
    console.log(advert);
    this.adverdServ.patchAdvert(advert, advert.idAdvert).subscribe(data => {
      location.href = '/profile';
    });
  }

  onSubmit() {
 this.update(this.editAdvert);
  }
}
