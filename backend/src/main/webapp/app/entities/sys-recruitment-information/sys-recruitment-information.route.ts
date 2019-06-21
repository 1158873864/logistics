import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { SysRecruitmentInformation } from 'app/shared/model/sys-recruitment-information.model';
import { SysRecruitmentInformationService } from './sys-recruitment-information.service';
import { SysRecruitmentInformationComponent } from './sys-recruitment-information.component';
import { SysRecruitmentInformationDetailComponent } from './sys-recruitment-information-detail.component';
import { SysRecruitmentInformationUpdateComponent } from './sys-recruitment-information-update.component';
import { SysRecruitmentInformationDeletePopupComponent } from './sys-recruitment-information-delete-dialog.component';
import { ISysRecruitmentInformation } from 'app/shared/model/sys-recruitment-information.model';

@Injectable({ providedIn: 'root' })
export class SysRecruitmentInformationResolve implements Resolve<ISysRecruitmentInformation> {
    constructor(private service: SysRecruitmentInformationService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((sysRecruitmentInformation: HttpResponse<SysRecruitmentInformation>) => sysRecruitmentInformation.body));
        }
        return of(new SysRecruitmentInformation());
    }
}

export const sysRecruitmentInformationRoute: Routes = [
    {
        path: 'sys-recruitment-information',
        component: SysRecruitmentInformationComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'wlApp.sysRecruitmentInformation.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-recruitment-information/:id/view',
        component: SysRecruitmentInformationDetailComponent,
        resolve: {
            sysRecruitmentInformation: SysRecruitmentInformationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.sysRecruitmentInformation.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-recruitment-information/new',
        component: SysRecruitmentInformationUpdateComponent,
        resolve: {
            sysRecruitmentInformation: SysRecruitmentInformationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.sysRecruitmentInformation.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-recruitment-information/:id/edit',
        component: SysRecruitmentInformationUpdateComponent,
        resolve: {
            sysRecruitmentInformation: SysRecruitmentInformationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.sysRecruitmentInformation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysRecruitmentInformationPopupRoute: Routes = [
    {
        path: 'sys-recruitment-information/:id/delete',
        component: SysRecruitmentInformationDeletePopupComponent,
        resolve: {
            sysRecruitmentInformation: SysRecruitmentInformationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.sysRecruitmentInformation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
