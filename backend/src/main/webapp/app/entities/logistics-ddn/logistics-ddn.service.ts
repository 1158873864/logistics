import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILogisticsDdn } from 'app/shared/model/logistics-ddn.model';

type EntityResponseType = HttpResponse<ILogisticsDdn>;
type EntityArrayResponseType = HttpResponse<ILogisticsDdn[]>;

@Injectable({ providedIn: 'root' })
export class LogisticsDdnService {
    private resourceUrl = SERVER_API_URL + 'api/logistics-ddns';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/logistics-ddns';

    constructor(private http: HttpClient) {}

    create(logisticsDdn: ILogisticsDdn): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(logisticsDdn);
        return this.http
            .post<ILogisticsDdn>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(logisticsDdn: ILogisticsDdn): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(logisticsDdn);
        return this.http
            .put<ILogisticsDdn>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ILogisticsDdn>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ILogisticsDdn[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ILogisticsDdn[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    private convertDateFromClient(logisticsDdn: ILogisticsDdn): ILogisticsDdn {
        const copy: ILogisticsDdn = Object.assign({}, logisticsDdn, {
            createdDate: logisticsDdn.createdDate != null && logisticsDdn.createdDate.isValid() ? logisticsDdn.createdDate.toJSON() : null,
            lastModifiedDate:
                logisticsDdn.lastModifiedDate != null && logisticsDdn.lastModifiedDate.isValid()
                    ? logisticsDdn.lastModifiedDate.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
        res.body.lastModifiedDate = res.body.lastModifiedDate != null ? moment(res.body.lastModifiedDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((logisticsDdn: ILogisticsDdn) => {
            logisticsDdn.createdDate = logisticsDdn.createdDate != null ? moment(logisticsDdn.createdDate) : null;
            logisticsDdn.lastModifiedDate = logisticsDdn.lastModifiedDate != null ? moment(logisticsDdn.lastModifiedDate) : null;
        });
        return res;
    }
}
