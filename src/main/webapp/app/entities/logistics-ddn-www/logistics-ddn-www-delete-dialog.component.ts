import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILogisticsDdnWWW } from 'app/shared/model/logistics-ddn-www.model';
import { LogisticsDdnWWWService } from './logistics-ddn-www.service';

@Component({
    selector: 'jhi-logistics-ddn-www-delete-dialog',
    templateUrl: './logistics-ddn-www-delete-dialog.component.html'
})
export class LogisticsDdnWWWDeleteDialogComponent {
    logisticsDdnWWW: ILogisticsDdnWWW;

    constructor(
        private logisticsDdnWWWService: LogisticsDdnWWWService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.logisticsDdnWWWService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'logisticsDdnWWWListModification',
                content: 'Deleted an logisticsDdnWWW'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-logistics-ddn-www-delete-popup',
    template: ''
})
export class LogisticsDdnWWWDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ logisticsDdnWWW }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LogisticsDdnWWWDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.logisticsDdnWWW = logisticsDdnWWW;
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
