import { Moment } from 'moment';

export interface IProposal {
  id?: number;
  content?: string;
  startDate?: Moment;
  endDate?: Moment;
  status?: boolean;
}

export class Proposal implements IProposal {
  constructor(public id?: number, public content?: string, public startDate?: Moment, public endDate?: Moment, public status?: boolean) {
    this.status = this.status || false;
  }
}
