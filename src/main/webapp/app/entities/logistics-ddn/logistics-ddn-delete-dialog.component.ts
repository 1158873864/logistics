import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILogisticsDdn } from 'app/shared/model/logistics-ddn.model';
import { LogisticsDdnService } from './logistics-ddn.service';

@Component({
    selector: 'jhi-logistics-ddn-delete-dialog',
    templateUrl: './logistics-ddn-delete-dialog.component.html'
})
export class LogisticsDdnDeleteDialogComponent {
    logisticsDdn: ILogisticsDdn;

    constructor(
        private logisticsDdnService: LogisticsDdnService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.logisticsDdnService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'logisticsDdnListModification',
                content: 'Deleted an logisticsDdn'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-logistics-ddn-delete-popup',
    template: ''
})
export class LogisticsDdnDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ logisticsDdn }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LogisticsDdnDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.logisticsDdn = logisticsDdn;
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
