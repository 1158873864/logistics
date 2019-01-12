import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { LogisticsDdnWWW } from 'app/shared/model/logistics-ddn-www.model';
import { LogisticsDdnWWWService } from './logistics-ddn-www.service';
import { LogisticsDdnWWWComponent } from './logistics-ddn-www.component';
import { LogisticsDdnWWWDetailComponent } from './logistics-ddn-www-detail.component';
import { LogisticsDdnWWWUpdateComponent } from './logistics-ddn-www-update.component';
import { LogisticsDdnWWWDeletePopupComponent } from './logistics-ddn-www-delete-dialog.component';
import { ILogisticsDdnWWW } from 'app/shared/model/logistics-ddn-www.model';

@Injectable({ providedIn: 'root' })
export class LogisticsDdnWWWResolve implements Resolve<ILogisticsDdnWWW> {
    constructor(private service: LogisticsDdnWWWService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((logisticsDdnWWW: HttpResponse<LogisticsDdnWWW>) => logisticsDdnWWW.body));
        }
        return of(new LogisticsDdnWWW());
    }
}

export const logisticsDdnWWWRoute: Routes = [
    {
        path: 'logistics-ddn-www',
        component: LogisticsDdnWWWComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'wlApp.logisticsDdnWWW.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'logistics-ddn-www/:id/view',
        component: LogisticsDdnWWWDetailComponent,
        resolve: {
            logisticsDdnWWW: LogisticsDdnWWWResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.logisticsDdnWWW.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'logistics-ddn-www/new',
        component: LogisticsDdnWWWUpdateComponent,
        resolve: {
            logisticsDdnWWW: LogisticsDdnWWWResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.logisticsDdnWWW.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'logistics-ddn-www/:id/edit',
        component: LogisticsDdnWWWUpdateComponent,
        resolve: {
            logisticsDdnWWW: LogisticsDdnWWWResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.logisticsDdnWWW.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const logisticsDdnWWWPopupRoute: Routes = [
    {
        path: 'logistics-ddn-www/:id/delete',
        component: LogisticsDdnWWWDeletePopupComponent,
        resolve: {
            logisticsDdnWWW: LogisticsDdnWWWResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.logisticsDdnWWW.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
