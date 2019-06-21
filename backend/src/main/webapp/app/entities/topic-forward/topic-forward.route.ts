import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TopicForward } from 'app/shared/model/topic-forward.model';
import { TopicForwardService } from './topic-forward.service';
import { TopicForwardComponent } from './topic-forward.component';
import { TopicForwardDetailComponent } from './topic-forward-detail.component';
import { TopicForwardUpdateComponent } from './topic-forward-update.component';
import { TopicForwardDeletePopupComponent } from './topic-forward-delete-dialog.component';
import { ITopicForward } from 'app/shared/model/topic-forward.model';

@Injectable({ providedIn: 'root' })
export class TopicForwardResolve implements Resolve<ITopicForward> {
    constructor(private service: TopicForwardService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((topicForward: HttpResponse<TopicForward>) => topicForward.body));
        }
        return of(new TopicForward());
    }
}

export const topicForwardRoute: Routes = [
    {
        path: 'topic-forward',
        component: TopicForwardComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'wlApp.topicForward.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'topic-forward/:id/view',
        component: TopicForwardDetailComponent,
        resolve: {
            topicForward: TopicForwardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.topicForward.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'topic-forward/new',
        component: TopicForwardUpdateComponent,
        resolve: {
            topicForward: TopicForwardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.topicForward.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'topic-forward/:id/edit',
        component: TopicForwardUpdateComponent,
        resolve: {
            topicForward: TopicForwardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.topicForward.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const topicForwardPopupRoute: Routes = [
    {
        path: 'topic-forward/:id/delete',
        component: TopicForwardDeletePopupComponent,
        resolve: {
            topicForward: TopicForwardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.topicForward.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
