import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { LogisticsDdnPic } from 'app/shared/model/logistics-ddn-pic.model';
import { LogisticsDdnPicService } from './logistics-ddn-pic.service';
import { LogisticsDdnPicComponent } from './logistics-ddn-pic.component';
import { LogisticsDdnPicDetailComponent } from './logistics-ddn-pic-detail.component';
import { LogisticsDdnPicUpdateComponent } from './logistics-ddn-pic-update.component';
import { LogisticsDdnPicDeletePopupComponent } from './logistics-ddn-pic-delete-dialog.component';
import { ILogisticsDdnPic } from 'app/shared/model/logistics-ddn-pic.model';

@Injectable({ providedIn: 'root' })
export class LogisticsDdnPicResolve implements Resolve<ILogisticsDdnPic> {
    constructor(private service: LogisticsDdnPicService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((logisticsDdnPic: HttpResponse<LogisticsDdnPic>) => logisticsDdnPic.body));
        }
        return of(new LogisticsDdnPic());
    }
}

export const logisticsDdnPicRoute: Routes = [
    {
        path: 'logistics-ddn-pic',
        component: LogisticsDdnPicComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'wlApp.logisticsDdnPic.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'logistics-ddn-pic/:id/view',
        component: LogisticsDdnPicDetailComponent,
        resolve: {
            logisticsDdnPic: LogisticsDdnPicResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.logisticsDdnPic.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'logistics-ddn-pic/new',
        component: LogisticsDdnPicUpdateComponent,
        resolve: {
            logisticsDdnPic: LogisticsDdnPicResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.logisticsDdnPic.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'logistics-ddn-pic/:id/edit',
        component: LogisticsDdnPicUpdateComponent,
        resolve: {
            logisticsDdnPic: LogisticsDdnPicResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.logisticsDdnPic.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const logisticsDdnPicPopupRoute: Routes = [
    {
        path: 'logistics-ddn-pic/:id/delete',
        component: LogisticsDdnPicDeletePopupComponent,
        resolve: {
            logisticsDdnPic: LogisticsDdnPicResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.logisticsDdnPic.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
