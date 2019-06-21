import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITopicViewed } from 'app/shared/model/topic-viewed.model';
import { TopicViewedService } from './topic-viewed.service';

@Component({
    selector: 'jhi-topic-viewed-delete-dialog',
    templateUrl: './topic-viewed-delete-dialog.component.html'
})
export class TopicViewedDeleteDialogComponent {
    topicViewed: ITopicViewed;

    constructor(
        private topicViewedService: TopicViewedService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.topicViewedService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'topicViewedListModification',
                content: 'Deleted an topicViewed'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-topic-viewed-delete-popup',
    template: ''
})
export class TopicViewedDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ topicViewed }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TopicViewedDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.topicViewed = topicViewed;
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
