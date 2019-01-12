import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILogisticsDdnPic } from 'app/shared/model/logistics-ddn-pic.model';
import { LogisticsDdnPicService } from './logistics-ddn-pic.service';

@Component({
    selector: 'jhi-logistics-ddn-pic-delete-dialog',
    templateUrl: './logistics-ddn-pic-delete-dialog.component.html'
})
export class LogisticsDdnPicDeleteDialogComponent {
    logisticsDdnPic: ILogisticsDdnPic;

    constructor(
        private logisticsDdnPicService: LogisticsDdnPicService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.logisticsDdnPicService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'logisticsDdnPicListModification',
                content: 'Deleted an logisticsDdnPic'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-logistics-ddn-pic-delete-popup',
    template: ''
})
export class LogisticsDdnPicDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ logisticsDdnPic }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LogisticsDdnPicDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.logisticsDdnPic = logisticsDdnPic;
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
