import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IIntegralChangeRecord } from 'app/shared/model/integral-change-record.model';
import { IntegralChangeRecordService } from './integral-change-record.service';

@Component({
    selector: 'jhi-integral-change-record-delete-dialog',
    templateUrl: './integral-change-record-delete-dialog.component.html'
})
export class IntegralChangeRecordDeleteDialogComponent {
    integralChangeRecord: IIntegralChangeRecord;

    constructor(
        private integralChangeRecordService: IntegralChangeRecordService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.integralChangeRecordService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'integralChangeRecordListModification',
                content: 'Deleted an integralChangeRecord'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-integral-change-record-delete-popup',
    template: ''
})
export class IntegralChangeRecordDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ integralChangeRecord }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(IntegralChangeRecordDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.integralChangeRecord = integralChangeRecord;
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
