import {Component, ElementRef, OnInit, QueryList, ViewChildren, AfterViewChecked} from '@angular/core';
import {AdvertismentServiceService} from '../service/advertisment-service.service';
import {ActivatedRoute, Params} from '@angular/router';
import {Advertisment} from '../model/advertisment';


@Component({
  selector: 'app-advertismet-ditale',
  templateUrl: './advertismet-ditale.component.html',
  styleUrls: ['./advertismet-ditale.component.css']
})
export class AdvertismetDitaleComponent implements OnInit, AfterViewChecked {
  @ViewChildren('notification') private _notificationsElements: QueryList<ElementRef>;
  advert: Advertisment;
  slideIndex = 1;
  id: number;
  slides: HTMLCollectionOf<HTMLElement>;
  dots: HTMLCollectionOf<HTMLElement>;

  constructor(private adverdServ: AdvertismentServiceService, private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.id = params.id;
    });
    this.adverdServ.getAdvertById(this.id).subscribe(data => {
      this.advert = data;
    });

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
    debugger
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


}
