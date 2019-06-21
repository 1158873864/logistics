import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITopicComment } from 'app/shared/model/topic-comment.model';
import { TopicCommentService } from './topic-comment.service';

@Component({
    selector: 'jhi-topic-comment-delete-dialog',
    templateUrl: './topic-comment-delete-dialog.component.html'
})
export class TopicCommentDeleteDialogComponent {
    topicComment: ITopicComment;

    constructor(
        private topicCommentService: TopicCommentService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.topicCommentService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'topicCommentListModification',
                content: 'Deleted an topicComment'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-topic-comment-delete-popup',
    template: ''
})
export class TopicCommentDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ topicComment }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TopicCommentDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.topicComment = topicComment;
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
