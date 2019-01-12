import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TopicFabulous } from 'app/shared/model/topic-fabulous.model';
import { TopicFabulousService } from './topic-fabulous.service';
import { TopicFabulousComponent } from './topic-fabulous.component';
import { TopicFabulousDetailComponent } from './topic-fabulous-detail.component';
import { TopicFabulousUpdateComponent } from './topic-fabulous-update.component';
import { TopicFabulousDeletePopupComponent } from './topic-fabulous-delete-dialog.component';
import { ITopicFabulous } from 'app/shared/model/topic-fabulous.model';

@Injectable({ providedIn: 'root' })
export class TopicFabulousResolve implements Resolve<ITopicFabulous> {
    constructor(private service: TopicFabulousService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((topicFabulous: HttpResponse<TopicFabulous>) => topicFabulous.body));
        }
        return of(new TopicFabulous());
    }
}

export const topicFabulousRoute: Routes = [
    {
        path: 'topic-fabulous',
        component: TopicFabulousComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'wlApp.topicFabulous.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'topic-fabulous/:id/view',
        component: TopicFabulousDetailComponent,
        resolve: {
            topicFabulous: TopicFabulousResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.topicFabulous.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'topic-fabulous/new',
        component: TopicFabulousUpdateComponent,
        resolve: {
            topicFabulous: TopicFabulousResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.topicFabulous.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'topic-fabulous/:id/edit',
        component: TopicFabulousUpdateComponent,
        resolve: {
            topicFabulous: TopicFabulousResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.topicFabulous.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const topicFabulousPopupRoute: Routes = [
    {
        path: 'topic-fabulous/:id/delete',
        component: TopicFabulousDeletePopupComponent,
        resolve: {
            topicFabulous: TopicFabulousResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.topicFabulous.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
