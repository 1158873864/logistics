import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPreferentialActivities } from 'app/shared/model/preferential-activities.model';

type EntityResponseType = HttpResponse<IPreferentialActivities>;
type EntityArrayResponseType = HttpResponse<IPreferentialActivities[]>;

@Injectable({ providedIn: 'root' })
export class PreferentialActivitiesService {
    private resourceUrl = SERVER_API_URL + 'api/preferential-activities';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/preferential-activities';

    constructor(private http: HttpClient) {}

    create(preferentialActivities: IPreferentialActivities): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(preferentialActivities);
        return this.http
            .post<IPreferentialActivities>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(preferentialActivities: IPreferentialActivities): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(preferentialActivities);
        return this.http
            .put<IPreferentialActivities>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPreferentialActivities>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPreferentialActivities[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPreferentialActivities[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    private convertDateFromClient(preferentialActivities: IPreferentialActivities): IPreferentialActivities {
        const copy: IPreferentialActivities = Object.assign({}, preferentialActivities, {
            startDate:
                preferentialActivities.startDate != null && preferentialActivities.startDate.isValid()
                    ? preferentialActivities.startDate.toJSON()
                    : null,
            endDate:
                preferentialActivities.endDate != null && preferentialActivities.endDate.isValid()
                    ? preferentialActivities.endDate.toJSON()
                    : null,
            createdDate:
                preferentialActivities.createdDate != null && preferentialActivities.createdDate.isValid()
                    ? preferentialActivities.createdDate.toJSON()
                    : null,
            lastModifiedDate:
                preferentialActivities.lastModifiedDate != null && preferentialActivities.lastModifiedDate.isValid()
                    ? preferentialActivities.lastModifiedDate.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.startDate = res.body.startDate != null ? moment(res.body.startDate) : null;
        res.body.endDate = res.body.endDate != null ? moment(res.body.endDate) : null;
        res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
        res.body.lastModifiedDate = res.body.lastModifiedDate != null ? moment(res.body.lastModifiedDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((preferentialActivities: IPreferentialActivities) => {
            preferentialActivities.startDate = preferentialActivities.startDate != null ? moment(preferentialActivities.startDate) : null;
            preferentialActivities.endDate = preferentialActivities.endDate != null ? moment(preferentialActivities.endDate) : null;
            preferentialActivities.createdDate =
                preferentialActivities.createdDate != null ? moment(preferentialActivities.createdDate) : null;
            preferentialActivities.lastModifiedDate =
                preferentialActivities.lastModifiedDate != null ? moment(preferentialActivities.lastModifiedDate) : null;
        });
        return res;
    }
}
