import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILogisticsDdnWWW } from 'app/shared/model/logistics-ddn-www.model';

type EntityResponseType = HttpResponse<ILogisticsDdnWWW>;
type EntityArrayResponseType = HttpResponse<ILogisticsDdnWWW[]>;

@Injectable({ providedIn: 'root' })
export class LogisticsDdnWWWService {
    private resourceUrl = SERVER_API_URL + 'api/logistics-ddn-wwws';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/logistics-ddn-wwws';

    constructor(private http: HttpClient) {}

    create(logisticsDdnWWW: ILogisticsDdnWWW): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(logisticsDdnWWW);
        return this.http
            .post<ILogisticsDdnWWW>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(logisticsDdnWWW: ILogisticsDdnWWW): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(logisticsDdnWWW);
        return this.http
            .put<ILogisticsDdnWWW>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ILogisticsDdnWWW>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ILogisticsDdnWWW[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ILogisticsDdnWWW[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    private convertDateFromClient(logisticsDdnWWW: ILogisticsDdnWWW): ILogisticsDdnWWW {
        const copy: ILogisticsDdnWWW = Object.assign({}, logisticsDdnWWW, {
            createdDate:
                logisticsDdnWWW.createdDate != null && logisticsDdnWWW.createdDate.isValid() ? logisticsDdnWWW.createdDate.toJSON() : null,
            lastModifiedDate:
                logisticsDdnWWW.lastModifiedDate != null && logisticsDdnWWW.lastModifiedDate.isValid()
                    ? logisticsDdnWWW.lastModifiedDate.toJSON()
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
        res.body.forEach((logisticsDdnWWW: ILogisticsDdnWWW) => {
            logisticsDdnWWW.createdDate = logisticsDdnWWW.createdDate != null ? moment(logisticsDdnWWW.createdDate) : null;
            logisticsDdnWWW.lastModifiedDate = logisticsDdnWWW.lastModifiedDate != null ? moment(logisticsDdnWWW.lastModifiedDate) : null;
        });
        return res;
    }
}
