import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITopicViewed } from 'app/shared/model/topic-viewed.model';

type EntityResponseType = HttpResponse<ITopicViewed>;
type EntityArrayResponseType = HttpResponse<ITopicViewed[]>;

@Injectable({ providedIn: 'root' })
export class TopicViewedService {
    private resourceUrl = SERVER_API_URL + 'api/topic-vieweds';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/topic-vieweds';

    constructor(private http: HttpClient) {}

    create(topicViewed: ITopicViewed): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(topicViewed);
        return this.http
            .post<ITopicViewed>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(topicViewed: ITopicViewed): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(topicViewed);
        return this.http
            .put<ITopicViewed>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITopicViewed>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITopicViewed[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITopicViewed[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    private convertDateFromClient(topicViewed: ITopicViewed): ITopicViewed {
        const copy: ITopicViewed = Object.assign({}, topicViewed, {
            oDateTime: topicViewed.oDateTime != null && topicViewed.oDateTime.isValid() ? topicViewed.oDateTime.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.oDateTime = res.body.oDateTime != null ? moment(res.body.oDateTime) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((topicViewed: ITopicViewed) => {
            topicViewed.oDateTime = topicViewed.oDateTime != null ? moment(topicViewed.oDateTime) : null;
        });
        return res;
    }
}
