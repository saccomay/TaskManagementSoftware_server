import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IProposal } from 'app/shared/model/proposal.model';

type EntityResponseType = HttpResponse<IProposal>;
type EntityArrayResponseType = HttpResponse<IProposal[]>;

@Injectable({ providedIn: 'root' })
export class ProposalService {
  public resourceUrl = SERVER_API_URL + 'api/proposals';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/proposals';

  constructor(protected http: HttpClient) {}

  create(proposal: IProposal): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proposal);
    return this.http
      .post<IProposal>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(proposal: IProposal): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proposal);
    return this.http
      .put<IProposal>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IProposal>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProposal[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProposal[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(proposal: IProposal): IProposal {
    const copy: IProposal = Object.assign({}, proposal, {
      startDate: proposal.startDate && proposal.startDate.isValid() ? proposal.startDate.toJSON() : undefined,
      endDate: proposal.endDate && proposal.endDate.isValid() ? proposal.endDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.startDate = res.body.startDate ? moment(res.body.startDate) : undefined;
      res.body.endDate = res.body.endDate ? moment(res.body.endDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((proposal: IProposal) => {
        proposal.startDate = proposal.startDate ? moment(proposal.startDate) : undefined;
        proposal.endDate = proposal.endDate ? moment(proposal.endDate) : undefined;
      });
    }
    return res;
  }
}
