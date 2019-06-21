import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { UserDdnFavorites } from 'app/shared/model/user-ddn-favorites.model';
import { UserDdnFavoritesService } from './user-ddn-favorites.service';
import { UserDdnFavoritesComponent } from './user-ddn-favorites.component';
import { UserDdnFavoritesDetailComponent } from './user-ddn-favorites-detail.component';
import { UserDdnFavoritesUpdateComponent } from './user-ddn-favorites-update.component';
import { UserDdnFavoritesDeletePopupComponent } from './user-ddn-favorites-delete-dialog.component';
import { IUserDdnFavorites } from 'app/shared/model/user-ddn-favorites.model';

@Injectable({ providedIn: 'root' })
export class UserDdnFavoritesResolve implements Resolve<IUserDdnFavorites> {
    constructor(private service: UserDdnFavoritesService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((userDdnFavorites: HttpResponse<UserDdnFavorites>) => userDdnFavorites.body));
        }
        return of(new UserDdnFavorites());
    }
}

export const userDdnFavoritesRoute: Routes = [
    {
        path: 'user-ddn-favorites',
        component: UserDdnFavoritesComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'wlApp.userDdnFavorites.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-ddn-favorites/:id/view',
        component: UserDdnFavoritesDetailComponent,
        resolve: {
            userDdnFavorites: UserDdnFavoritesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.userDdnFavorites.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-ddn-favorites/new',
        component: UserDdnFavoritesUpdateComponent,
        resolve: {
            userDdnFavorites: UserDdnFavoritesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.userDdnFavorites.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-ddn-favorites/:id/edit',
        component: UserDdnFavoritesUpdateComponent,
        resolve: {
            userDdnFavorites: UserDdnFavoritesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.userDdnFavorites.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const userDdnFavoritesPopupRoute: Routes = [
    {
        path: 'user-ddn-favorites/:id/delete',
        component: UserDdnFavoritesDeletePopupComponent,
        resolve: {
            userDdnFavorites: UserDdnFavoritesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.userDdnFavorites.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
