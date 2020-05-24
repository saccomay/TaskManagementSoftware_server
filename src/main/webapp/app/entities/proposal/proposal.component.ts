import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProposal } from 'app/shared/model/proposal.model';
import { ProposalService } from './proposal.service';
import { ProposalDeleteDialogComponent } from './proposal-delete-dialog.component';

@Component({
  selector: 'jhi-proposal',
  templateUrl: './proposal.component.html',
})
export class ProposalComponent implements OnInit, OnDestroy {
  proposals?: IProposal[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected proposalService: ProposalService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected activatedRoute: ActivatedRoute
  ) {
    this.currentSearch =
      this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['search']
        ? this.activatedRoute.snapshot.queryParams['search']
        : '';
  }

  loadAll(): void {
    if (this.currentSearch) {
      this.proposalService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IProposal[]>) => (this.proposals = res.body || []));
      return;
    }

    this.proposalService.query().subscribe((res: HttpResponse<IProposal[]>) => (this.proposals = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProposals();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProposal): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProposals(): void {
    this.eventSubscriber = this.eventManager.subscribe('proposalListModification', () => this.loadAll());
  }

  delete(proposal: IProposal): void {
    const modalRef = this.modalService.open(ProposalDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.proposal = proposal;
  }
}
