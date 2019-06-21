import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGoodsExchange } from 'app/shared/model/goods-exchange.model';
import { GoodsExchangeService } from './goods-exchange.service';

@Component({
    selector: 'jhi-goods-exchange-delete-dialog',
    templateUrl: './goods-exchange-delete-dialog.component.html'
})
export class GoodsExchangeDeleteDialogComponent {
    goodsExchange: IGoodsExchange;

    constructor(
        private goodsExchangeService: GoodsExchangeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.goodsExchangeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'goodsExchangeListModification',
                content: 'Deleted an goodsExchange'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-goods-exchange-delete-popup',
    template: ''
})
export class GoodsExchangeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ goodsExchange }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(GoodsExchangeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.goodsExchange = goodsExchange;
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
