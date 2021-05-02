import {Client} from './client';
import {Generation} from './generation';
import {Modification} from './modification';
import {Mark} from './mark';
import {Model} from './model';

export class FormAdvert{
  carbodyTitle: string;
  clientId: bigint;
  mark: Mark;
  model: Model;
  generations: Generation;
  modifications: Modification;
  available: string;
  price: number;
  colour: string;
  mileage: number;
  broken: boolean;
  city: string;
  place: string;
  phone: bigint;
  swap: boolean;
  pts: boolean;
  owners: number;
  gosnumber: string;
  buyday: number;
  dayofwarranty: Date;
  vin: string;
  sts: bigint;
  commentns: string;
  files: File[]=[];

}
