import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { IntegralChangeRecord } from 'app/shared/model/integral-change-record.model';
import { IntegralChangeRecordService } from './integral-change-record.service';
import { IntegralChangeRecordComponent } from './integral-change-record.component';
import { IntegralChangeRecordDetailComponent } from './integral-change-record-detail.component';
import { IntegralChangeRecordUpdateComponent } from './integral-change-record-update.component';
import { IntegralChangeRecordDeletePopupComponent } from './integral-change-record-delete-dialog.component';
import { IIntegralChangeRecord } from 'app/shared/model/integral-change-record.model';

@Injectable({ providedIn: 'root' })
export class IntegralChangeRecordResolve implements Resolve<IIntegralChangeRecord> {
    constructor(private service: IntegralChangeRecordService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((integralChangeRecord: HttpResponse<IntegralChangeRecord>) => integralChangeRecord.body));
        }
        return of(new IntegralChangeRecord());
    }
}

export const integralChangeRecordRoute: Routes = [
    {
        path: 'integral-change-record',
        component: IntegralChangeRecordComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'wlApp.integralChangeRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'integral-change-record/:id/view',
        component: IntegralChangeRecordDetailComponent,
        resolve: {
            integralChangeRecord: IntegralChangeRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.integralChangeRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'integral-change-record/new',
        component: IntegralChangeRecordUpdateComponent,
        resolve: {
            integralChangeRecord: IntegralChangeRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.integralChangeRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'integral-change-record/:id/edit',
        component: IntegralChangeRecordUpdateComponent,
        resolve: {
            integralChangeRecord: IntegralChangeRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.integralChangeRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const integralChangeRecordPopupRoute: Routes = [
    {
        path: 'integral-change-record/:id/delete',
        component: IntegralChangeRecordDeletePopupComponent,
        resolve: {
            integralChangeRecord: IntegralChangeRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.integralChangeRecord.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
