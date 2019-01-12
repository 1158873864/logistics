import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITopicForward } from 'app/shared/model/topic-forward.model';
import { TopicForwardService } from './topic-forward.service';

@Component({
    selector: 'jhi-topic-forward-delete-dialog',
    templateUrl: './topic-forward-delete-dialog.component.html'
})
export class TopicForwardDeleteDialogComponent {
    topicForward: ITopicForward;

    constructor(
        private topicForwardService: TopicForwardService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.topicForwardService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'topicForwardListModification',
                content: 'Deleted an topicForward'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-topic-forward-delete-popup',
    template: ''
})
export class TopicForwardDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ topicForward }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TopicForwardDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.topicForward = topicForward;
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
