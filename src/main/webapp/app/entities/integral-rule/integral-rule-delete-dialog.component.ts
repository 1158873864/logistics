import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IIntegralRule } from 'app/shared/model/integral-rule.model';
import { IntegralRuleService } from './integral-rule.service';

@Component({
    selector: 'jhi-integral-rule-delete-dialog',
    templateUrl: './integral-rule-delete-dialog.component.html'
})
export class IntegralRuleDeleteDialogComponent {
    integralRule: IIntegralRule;

    constructor(
        private integralRuleService: IntegralRuleService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.integralRuleService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'integralRuleListModification',
                content: 'Deleted an integralRule'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-integral-rule-delete-popup',
    template: ''
})
export class IntegralRuleDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ integralRule }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(IntegralRuleDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.integralRule = integralRule;
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
