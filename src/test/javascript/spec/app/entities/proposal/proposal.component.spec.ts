import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TaskManagementSoftwareServerTestModule } from '../../../test.module';
import { ProposalComponent } from 'app/entities/proposal/proposal.component';
import { ProposalService } from 'app/entities/proposal/proposal.service';
import { Proposal } from 'app/shared/model/proposal.model';

describe('Component Tests', () => {
  describe('Proposal Management Component', () => {
    let comp: ProposalComponent;
    let fixture: ComponentFixture<ProposalComponent>;
    let service: ProposalService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaskManagementSoftwareServerTestModule],
        declarations: [ProposalComponent],
      })
        .overrideTemplate(ProposalComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProposalComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProposalService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Proposal(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.proposals && comp.proposals[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
