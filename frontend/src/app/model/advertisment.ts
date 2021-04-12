import {Client} from './client';
import {Generation} from './generation';
import {Modification} from './modification';

export class Advertisment{
  idAdvert: bigint;
  carbodyTitle: string;
  clientsDto: Client;
  marksTitle: string;
  modelTitle: string;
  generationsDto: Generation;
  modificationsDto: Modification;
  available: string;
  price: number;
  colour: string;
  mileage: number;
  broken: number;
  city: string;
  place: string;
  phone: bigint;
  swap: number;
  pts: number;
  owners: number;
  gosnumber: string;
  buyday: Date;
  dayofwarranty: Date;
  vin: string;
  sts: bigint;
  commentns: string;
  imagesList: string[];
  nalog: number;
}
