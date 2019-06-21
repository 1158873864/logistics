import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPreferentialActivities } from 'app/shared/model/preferential-activities.model';
import { PreferentialActivitiesService } from './preferential-activities.service';

@Component({
    selector: 'jhi-preferential-activities-delete-dialog',
    templateUrl: './preferential-activities-delete-dialog.component.html'
})
export class PreferentialActivitiesDeleteDialogComponent {
    preferentialActivities: IPreferentialActivities;

    constructor(
        private preferentialActivitiesService: PreferentialActivitiesService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.preferentialActivitiesService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'preferentialActivitiesListModification',
                content: 'Deleted an preferentialActivities'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-preferential-activities-delete-popup',
    template: ''
})
export class PreferentialActivitiesDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ preferentialActivities }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PreferentialActivitiesDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.preferentialActivities = preferentialActivities;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
