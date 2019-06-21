import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserDdnFavorites } from 'app/shared/model/user-ddn-favorites.model';
import { UserDdnFavoritesService } from './user-ddn-favorites.service';

@Component({
    selector: 'jhi-user-ddn-favorites-delete-dialog',
    templateUrl: './user-ddn-favorites-delete-dialog.component.html'
})
export class UserDdnFavoritesDeleteDialogComponent {
    userDdnFavorites: IUserDdnFavorites;

    constructor(
        private userDdnFavoritesService: UserDdnFavoritesService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.userDdnFavoritesService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'userDdnFavoritesListModification',
                content: 'Deleted an userDdnFavorites'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-user-ddn-favorites-delete-popup',
    template: ''
})
export class UserDdnFavoritesDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ userDdnFavorites }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(UserDdnFavoritesDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.userDdnFavorites = userDdnFavorites;
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
