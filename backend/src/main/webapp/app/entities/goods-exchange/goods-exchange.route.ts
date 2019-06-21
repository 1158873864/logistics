import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { GoodsExchange } from 'app/shared/model/goods-exchange.model';
import { GoodsExchangeService } from './goods-exchange.service';
import { GoodsExchangeComponent } from './goods-exchange.component';
import { GoodsExchangeDetailComponent } from './goods-exchange-detail.component';
import { GoodsExchangeUpdateComponent } from './goods-exchange-update.component';
import { GoodsExchangeDeletePopupComponent } from './goods-exchange-delete-dialog.component';
import { IGoodsExchange } from 'app/shared/model/goods-exchange.model';

@Injectable({ providedIn: 'root' })
export class GoodsExchangeResolve implements Resolve<IGoodsExchange> {
    constructor(private service: GoodsExchangeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((goodsExchange: HttpResponse<GoodsExchange>) => goodsExchange.body));
        }
        return of(new GoodsExchange());
    }
}

export const goodsExchangeRoute: Routes = [
    {
        path: 'goods-exchange',
        component: GoodsExchangeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'wlApp.goodsExchange.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'goods-exchange/:id/view',
        component: GoodsExchangeDetailComponent,
        resolve: {
            goodsExchange: GoodsExchangeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.goodsExchange.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'goods-exchange/new',
        component: GoodsExchangeUpdateComponent,
        resolve: {
            goodsExchange: GoodsExchangeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.goodsExchange.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'goods-exchange/:id/edit',
        component: GoodsExchangeUpdateComponent,
        resolve: {
            goodsExchange: GoodsExchangeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.goodsExchange.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const goodsExchangePopupRoute: Routes = [
    {
        path: 'goods-exchange/:id/delete',
        component: GoodsExchangeDeletePopupComponent,
        resolve: {
            goodsExchange: GoodsExchangeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.goodsExchange.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
