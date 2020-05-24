import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TaskManagementSoftwareServerSharedModule } from 'app/shared/shared.module';
import { ProposalComponent } from './proposal.component';
import { ProposalDetailComponent } from './proposal-detail.component';
import { ProposalUpdateComponent } from './proposal-update.component';
import { ProposalDeleteDialogComponent } from './proposal-delete-dialog.component';
import { proposalRoute } from './proposal.route';

@NgModule({
  imports: [TaskManagementSoftwareServerSharedModule, RouterModule.forChild(proposalRoute)],
  declarations: [ProposalComponent, ProposalDetailComponent, ProposalUpdateComponent, ProposalDeleteDialogComponent],
  entryComponents: [ProposalDeleteDialogComponent],
})
export class TaskManagementSoftwareServerProposalModule {}
