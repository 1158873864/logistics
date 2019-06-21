import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITopicForward } from 'app/shared/model/topic-forward.model';

type EntityResponseType = HttpResponse<ITopicForward>;
type EntityArrayResponseType = HttpResponse<ITopicForward[]>;

@Injectable({ providedIn: 'root' })
export class TopicForwardService {
    private resourceUrl = SERVER_API_URL + 'api/topic-forwards';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/topic-forwards';

    constructor(private http: HttpClient) {}

    create(topicForward: ITopicForward): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(topicForward);
        return this.http
            .post<ITopicForward>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(topicForward: ITopicForward): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(topicForward);
        return this.http
            .put<ITopicForward>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITopicForward>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITopicForward[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITopicForward[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    private convertDateFromClient(topicForward: ITopicForward): ITopicForward {
        const copy: ITopicForward = Object.assign({}, topicForward, {
            oDateTime: topicForward.oDateTime != null && topicForward.oDateTime.isValid() ? topicForward.oDateTime.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.oDateTime = res.body.oDateTime != null ? moment(res.body.oDateTime) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((topicForward: ITopicForward) => {
            topicForward.oDateTime = topicForward.oDateTime != null ? moment(topicForward.oDateTime) : null;
        });
        return res;
    }
}
