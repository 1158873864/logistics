import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { GoodsSource } from 'app/shared/model/goods-source.model';
import { GoodsSourceService } from './goods-source.service';
import { GoodsSourceComponent } from './goods-source.component';
import { GoodsSourceDetailComponent } from './goods-source-detail.component';
import { GoodsSourceUpdateComponent } from './goods-source-update.component';
import { GoodsSourceDeletePopupComponent } from './goods-source-delete-dialog.component';
import { IGoodsSource } from 'app/shared/model/goods-source.model';

@Injectable({ providedIn: 'root' })
export class GoodsSourceResolve implements Resolve<IGoodsSource> {
    constructor(private service: GoodsSourceService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((goodsSource: HttpResponse<GoodsSource>) => goodsSource.body));
        }
        return of(new GoodsSource());
    }
}

export const goodsSourceRoute: Routes = [
    {
        path: 'goods-source',
        component: GoodsSourceComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'wlApp.goodsSource.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'goods-source/:id/view',
        component: GoodsSourceDetailComponent,
        resolve: {
            goodsSource: GoodsSourceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.goodsSource.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'goods-source/new',
        component: GoodsSourceUpdateComponent,
        resolve: {
            goodsSource: GoodsSourceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.goodsSource.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'goods-source/:id/edit',
        component: GoodsSourceUpdateComponent,
        resolve: {
            goodsSource: GoodsSourceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.goodsSource.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const goodsSourcePopupRoute: Routes = [
    {
        path: 'goods-source/:id/delete',
        component: GoodsSourceDeletePopupComponent,
        resolve: {
            goodsSource: GoodsSourceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.goodsSource.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
