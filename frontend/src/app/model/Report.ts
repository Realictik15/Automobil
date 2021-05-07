import {Accidents} from './Accidents';
import {Restriction} from './Restriction';
import {Advertisment} from './advertisment';

export class Report {
  engineNumber: string;
  buyYear: number;
  countOwners: number;
  lastOwnersDate: string;
  countAccidents: number;
  accidents: Accidents[];
  mileage: number;
  lastDiagnostic: string;
  restrictoins: Restriction[];
  advertismentDto: Advertisment[];

}

