import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'proposal',
        loadChildren: () => import('./proposal/proposal.module').then(m => m.TaskManagementSoftwareServerProposalModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class TaskManagementSoftwareServerEntityModule {}
