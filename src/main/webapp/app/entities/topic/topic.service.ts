import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITopic } from 'app/shared/model/topic.model';

type EntityResponseType = HttpResponse<ITopic>;
type EntityArrayResponseType = HttpResponse<ITopic[]>;

@Injectable({ providedIn: 'root' })
export class TopicService {
    private resourceUrl = SERVER_API_URL + 'api/topics';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/topics';

    constructor(private http: HttpClient) {}

    create(topic: ITopic): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(topic);
        return this.http
            .post<ITopic>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(topic: ITopic): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(topic);
        return this.http
            .put<ITopic>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITopic>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITopic[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITopic[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    private convertDateFromClient(topic: ITopic): ITopic {
        const copy: ITopic = Object.assign({}, topic, {
            published: topic.published != null && topic.published.isValid() ? topic.published.toJSON() : null,
            createdDate: topic.createdDate != null && topic.createdDate.isValid() ? topic.createdDate.toJSON() : null,
            lastModifiedDate: topic.lastModifiedDate != null && topic.lastModifiedDate.isValid() ? topic.lastModifiedDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.published = res.body.published != null ? moment(res.body.published) : null;
        res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
        res.body.lastModifiedDate = res.body.lastModifiedDate != null ? moment(res.body.lastModifiedDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((topic: ITopic) => {
            topic.published = topic.published != null ? moment(topic.published) : null;
            topic.createdDate = topic.createdDate != null ? moment(topic.createdDate) : null;
            topic.lastModifiedDate = topic.lastModifiedDate != null ? moment(topic.lastModifiedDate) : null;
        });
        return res;
    }
}
