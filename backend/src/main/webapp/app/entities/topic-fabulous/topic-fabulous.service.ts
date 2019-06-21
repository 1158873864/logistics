import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITopicFabulous } from 'app/shared/model/topic-fabulous.model';

type EntityResponseType = HttpResponse<ITopicFabulous>;
type EntityArrayResponseType = HttpResponse<ITopicFabulous[]>;

@Injectable({ providedIn: 'root' })
export class TopicFabulousService {
    private resourceUrl = SERVER_API_URL + 'api/topic-fabulous';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/topic-fabulous';

    constructor(private http: HttpClient) {}

    create(topicFabulous: ITopicFabulous): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(topicFabulous);
        return this.http
            .post<ITopicFabulous>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(topicFabulous: ITopicFabulous): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(topicFabulous);
        return this.http
            .put<ITopicFabulous>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITopicFabulous>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITopicFabulous[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITopicFabulous[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    private convertDateFromClient(topicFabulous: ITopicFabulous): ITopicFabulous {
        const copy: ITopicFabulous = Object.assign({}, topicFabulous, {
            oDateTime: topicFabulous.oDateTime != null && topicFabulous.oDateTime.isValid() ? topicFabulous.oDateTime.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.oDateTime = res.body.oDateTime != null ? moment(res.body.oDateTime) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((topicFabulous: ITopicFabulous) => {
            topicFabulous.oDateTime = topicFabulous.oDateTime != null ? moment(topicFabulous.oDateTime) : null;
        });
        return res;
    }
}
