import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TaskManagementSoftwareServerSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [TaskManagementSoftwareServerSharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent],
})
export class TaskManagementSoftwareServerHomeModule {}
