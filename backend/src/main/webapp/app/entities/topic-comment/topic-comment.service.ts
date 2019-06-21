import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITopicComment } from 'app/shared/model/topic-comment.model';

type EntityResponseType = HttpResponse<ITopicComment>;
type EntityArrayResponseType = HttpResponse<ITopicComment[]>;

@Injectable({ providedIn: 'root' })
export class TopicCommentService {
    private resourceUrl = SERVER_API_URL + 'api/topic-comments';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/topic-comments';

    constructor(private http: HttpClient) {}

    create(topicComment: ITopicComment): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(topicComment);
        return this.http
            .post<ITopicComment>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(topicComment: ITopicComment): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(topicComment);
        return this.http
            .put<ITopicComment>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITopicComment>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITopicComment[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITopicComment[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    private convertDateFromClient(topicComment: ITopicComment): ITopicComment {
        const copy: ITopicComment = Object.assign({}, topicComment, {
            oDateTime: topicComment.oDateTime != null && topicComment.oDateTime.isValid() ? topicComment.oDateTime.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.oDateTime = res.body.oDateTime != null ? moment(res.body.oDateTime) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((topicComment: ITopicComment) => {
            topicComment.oDateTime = topicComment.oDateTime != null ? moment(topicComment.oDateTime) : null;
        });
        return res;
    }
}
