import {Generation} from './generation';
import {Engin} from './engin';
import {Transmission} from './transmission';

export class Modification {
  idModif: bigint;
  generationsDto: Generation;
  enginesDto: Engin;
  transmissionsDto: Transmission;
  title: string;
  sits: number;
  doors: number;
  idComplSet: number[];
}
