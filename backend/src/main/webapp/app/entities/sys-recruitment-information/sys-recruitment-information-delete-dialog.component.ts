import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysRecruitmentInformation } from 'app/shared/model/sys-recruitment-information.model';
import { SysRecruitmentInformationService } from './sys-recruitment-information.service';

@Component({
    selector: 'jhi-sys-recruitment-information-delete-dialog',
    templateUrl: './sys-recruitment-information-delete-dialog.component.html'
})
export class SysRecruitmentInformationDeleteDialogComponent {
    sysRecruitmentInformation: ISysRecruitmentInformation;

    constructor(
        private sysRecruitmentInformationService: SysRecruitmentInformationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysRecruitmentInformationService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sysRecruitmentInformationListModification',
                content: 'Deleted an sysRecruitmentInformation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-recruitment-information-delete-popup',
    template: ''
})
export class SysRecruitmentInformationDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysRecruitmentInformation }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SysRecruitmentInformationDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sysRecruitmentInformation = sysRecruitmentInformation;
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
