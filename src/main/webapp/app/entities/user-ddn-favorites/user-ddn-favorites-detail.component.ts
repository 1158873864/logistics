import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserDdnFavorites } from 'app/shared/model/user-ddn-favorites.model';

@Component({
    selector: 'jhi-user-ddn-favorites-detail',
    templateUrl: './user-ddn-favorites-detail.component.html'
})
export class UserDdnFavoritesDetailComponent implements OnInit {
    userDdnFavorites: IUserDdnFavorites;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ userDdnFavorites }) => {
            this.userDdnFavorites = userDdnFavorites;
        });
    }

    previousState() {
        window.history.back();
    }
}
