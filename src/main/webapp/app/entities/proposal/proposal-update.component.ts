import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IProposal, Proposal } from 'app/shared/model/proposal.model';
import { ProposalService } from './proposal.service';

@Component({
  selector: 'jhi-proposal-update',
  templateUrl: './proposal-update.component.html',
})
export class ProposalUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    content: [],
    startDate: [],
    endDate: [],
    status: [],
  });

  constructor(protected proposalService: ProposalService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ proposal }) => {
      if (!proposal.id) {
        const today = moment().startOf('day');
        proposal.startDate = today;
        proposal.endDate = today;
      }

      this.updateForm(proposal);
    });
  }

  updateForm(proposal: IProposal): void {
    this.editForm.patchValue({
      id: proposal.id,
      content: proposal.content,
      startDate: proposal.startDate ? proposal.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: proposal.endDate ? proposal.endDate.format(DATE_TIME_FORMAT) : null,
      status: proposal.status,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const proposal = this.createFromForm();
    if (proposal.id !== undefined) {
      this.subscribeToSaveResponse(this.proposalService.update(proposal));
    } else {
      this.subscribeToSaveResponse(this.proposalService.create(proposal));
    }
  }

  private createFromForm(): IProposal {
    return {
      ...new Proposal(),
      id: this.editForm.get(['id'])!.value,
      content: this.editForm.get(['content'])!.value,
      startDate: this.editForm.get(['startDate'])!.value ? moment(this.editForm.get(['startDate'])!.value, DATE_TIME_FORMAT) : undefined,
      endDate: this.editForm.get(['endDate'])!.value ? moment(this.editForm.get(['endDate'])!.value, DATE_TIME_FORMAT) : undefined,
      status: this.editForm.get(['status'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProposal>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
