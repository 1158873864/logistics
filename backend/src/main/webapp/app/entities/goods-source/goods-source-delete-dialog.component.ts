import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGoodsSource } from 'app/shared/model/goods-source.model';
import { GoodsSourceService } from './goods-source.service';

@Component({
    selector: 'jhi-goods-source-delete-dialog',
    templateUrl: './goods-source-delete-dialog.component.html'
})
export class GoodsSourceDeleteDialogComponent {
    goodsSource: IGoodsSource;

    constructor(
        private goodsSourceService: GoodsSourceService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.goodsSourceService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'goodsSourceListModification',
                content: 'Deleted an goodsSource'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-goods-source-delete-popup',
    template: ''
})
export class GoodsSourceDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ goodsSource }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(GoodsSourceDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.goodsSource = goodsSource;
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
