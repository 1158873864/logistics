import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TopicViewed } from 'app/shared/model/topic-viewed.model';
import { TopicViewedService } from './topic-viewed.service';
import { TopicViewedComponent } from './topic-viewed.component';
import { TopicViewedDetailComponent } from './topic-viewed-detail.component';
import { TopicViewedUpdateComponent } from './topic-viewed-update.component';
import { TopicViewedDeletePopupComponent } from './topic-viewed-delete-dialog.component';
import { ITopicViewed } from 'app/shared/model/topic-viewed.model';

@Injectable({ providedIn: 'root' })
export class TopicViewedResolve implements Resolve<ITopicViewed> {
    constructor(private service: TopicViewedService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((topicViewed: HttpResponse<TopicViewed>) => topicViewed.body));
        }
        return of(new TopicViewed());
    }
}

export const topicViewedRoute: Routes = [
    {
        path: 'topic-viewed',
        component: TopicViewedComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'wlApp.topicViewed.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'topic-viewed/:id/view',
        component: TopicViewedDetailComponent,
        resolve: {
            topicViewed: TopicViewedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.topicViewed.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'topic-viewed/new',
        component: TopicViewedUpdateComponent,
        resolve: {
            topicViewed: TopicViewedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.topicViewed.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'topic-viewed/:id/edit',
        component: TopicViewedUpdateComponent,
        resolve: {
            topicViewed: TopicViewedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.topicViewed.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const topicViewedPopupRoute: Routes = [
    {
        path: 'topic-viewed/:id/delete',
        component: TopicViewedDeletePopupComponent,
        resolve: {
            topicViewed: TopicViewedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.topicViewed.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
