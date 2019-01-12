import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PreferentialActivities } from 'app/shared/model/preferential-activities.model';
import { PreferentialActivitiesService } from './preferential-activities.service';
import { PreferentialActivitiesComponent } from './preferential-activities.component';
import { PreferentialActivitiesDetailComponent } from './preferential-activities-detail.component';
import { PreferentialActivitiesUpdateComponent } from './preferential-activities-update.component';
import { PreferentialActivitiesDeletePopupComponent } from './preferential-activities-delete-dialog.component';
import { IPreferentialActivities } from 'app/shared/model/preferential-activities.model';

@Injectable({ providedIn: 'root' })
export class PreferentialActivitiesResolve implements Resolve<IPreferentialActivities> {
    constructor(private service: PreferentialActivitiesService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((preferentialActivities: HttpResponse<PreferentialActivities>) => preferentialActivities.body));
        }
        return of(new PreferentialActivities());
    }
}

export const preferentialActivitiesRoute: Routes = [
    {
        path: 'preferential-activities',
        component: PreferentialActivitiesComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'wlApp.preferentialActivities.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'preferential-activities/:id/view',
        component: PreferentialActivitiesDetailComponent,
        resolve: {
            preferentialActivities: PreferentialActivitiesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.preferentialActivities.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'preferential-activities/new',
        component: PreferentialActivitiesUpdateComponent,
        resolve: {
            preferentialActivities: PreferentialActivitiesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.preferentialActivities.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'preferential-activities/:id/edit',
        component: PreferentialActivitiesUpdateComponent,
        resolve: {
            preferentialActivities: PreferentialActivitiesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.preferentialActivities.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const preferentialActivitiesPopupRoute: Routes = [
    {
        path: 'preferential-activities/:id/delete',
        component: PreferentialActivitiesDeletePopupComponent,
        resolve: {
            preferentialActivities: PreferentialActivitiesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.preferentialActivities.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
