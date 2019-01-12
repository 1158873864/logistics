import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { LogisticsDdn } from 'app/shared/model/logistics-ddn.model';
import { LogisticsDdnService } from './logistics-ddn.service';
import { LogisticsDdnComponent } from './logistics-ddn.component';
import { LogisticsDdnDetailComponent } from './logistics-ddn-detail.component';
import { LogisticsDdnUpdateComponent } from './logistics-ddn-update.component';
import { LogisticsDdnDeletePopupComponent } from './logistics-ddn-delete-dialog.component';
import { ILogisticsDdn } from 'app/shared/model/logistics-ddn.model';

@Injectable({ providedIn: 'root' })
export class LogisticsDdnResolve implements Resolve<ILogisticsDdn> {
    constructor(private service: LogisticsDdnService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((logisticsDdn: HttpResponse<LogisticsDdn>) => logisticsDdn.body));
        }
        return of(new LogisticsDdn());
    }
}

export const logisticsDdnRoute: Routes = [
    {
        path: 'logistics-ddn',
        component: LogisticsDdnComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'wlApp.logisticsDdn.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'logistics-ddn/:id/view',
        component: LogisticsDdnDetailComponent,
        resolve: {
            logisticsDdn: LogisticsDdnResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.logisticsDdn.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'logistics-ddn/new',
        component: LogisticsDdnUpdateComponent,
        resolve: {
            logisticsDdn: LogisticsDdnResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.logisticsDdn.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'logistics-ddn/:id/edit',
        component: LogisticsDdnUpdateComponent,
        resolve: {
            logisticsDdn: LogisticsDdnResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.logisticsDdn.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const logisticsDdnPopupRoute: Routes = [
    {
        path: 'logistics-ddn/:id/delete',
        component: LogisticsDdnDeletePopupComponent,
        resolve: {
            logisticsDdn: LogisticsDdnResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.logisticsDdn.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
