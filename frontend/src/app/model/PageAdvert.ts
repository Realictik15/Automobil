import {Advertisment} from './advertisment';

export class PageAdvert {
  content: Advertisment[];
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number;
  first: boolean;
  sort: string;
  numberOfElements: number;
}
