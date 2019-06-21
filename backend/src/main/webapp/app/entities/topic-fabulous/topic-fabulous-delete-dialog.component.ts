import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITopicFabulous } from 'app/shared/model/topic-fabulous.model';
import { TopicFabulousService } from './topic-fabulous.service';

@Component({
    selector: 'jhi-topic-fabulous-delete-dialog',
    templateUrl: './topic-fabulous-delete-dialog.component.html'
})
export class TopicFabulousDeleteDialogComponent {
    topicFabulous: ITopicFabulous;

    constructor(
        private topicFabulousService: TopicFabulousService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.topicFabulousService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'topicFabulousListModification',
                content: 'Deleted an topicFabulous'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-topic-fabulous-delete-popup',
    template: ''
})
export class TopicFabulousDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ topicFabulous }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TopicFabulousDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.topicFabulous = topicFabulous;
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
