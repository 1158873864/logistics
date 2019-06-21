import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TopicComment } from 'app/shared/model/topic-comment.model';
import { TopicCommentService } from './topic-comment.service';
import { TopicCommentComponent } from './topic-comment.component';
import { TopicCommentDetailComponent } from './topic-comment-detail.component';
import { TopicCommentUpdateComponent } from './topic-comment-update.component';
import { TopicCommentDeletePopupComponent } from './topic-comment-delete-dialog.component';
import { ITopicComment } from 'app/shared/model/topic-comment.model';

@Injectable({ providedIn: 'root' })
export class TopicCommentResolve implements Resolve<ITopicComment> {
    constructor(private service: TopicCommentService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((topicComment: HttpResponse<TopicComment>) => topicComment.body));
        }
        return of(new TopicComment());
    }
}

export const topicCommentRoute: Routes = [
    {
        path: 'topic-comment',
        component: TopicCommentComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'wlApp.topicComment.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'topic-comment/:id/view',
        component: TopicCommentDetailComponent,
        resolve: {
            topicComment: TopicCommentResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.topicComment.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'topic-comment/new',
        component: TopicCommentUpdateComponent,
        resolve: {
            topicComment: TopicCommentResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.topicComment.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'topic-comment/:id/edit',
        component: TopicCommentUpdateComponent,
        resolve: {
            topicComment: TopicCommentResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.topicComment.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const topicCommentPopupRoute: Routes = [
    {
        path: 'topic-comment/:id/delete',
        component: TopicCommentDeletePopupComponent,
        resolve: {
            topicComment: TopicCommentResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.topicComment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
