import {GearBox} from './gearBox';

export class Transmission {
  idTrans: bigint;
  gearBoxDto: GearBox;
  quantity: number;
  driveType: string;
  brakes: string;
  suspension: string;
}
