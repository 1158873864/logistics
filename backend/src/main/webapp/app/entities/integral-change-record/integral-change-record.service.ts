import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IIntegralChangeRecord } from 'app/shared/model/integral-change-record.model';

type EntityResponseType = HttpResponse<IIntegralChangeRecord>;
type EntityArrayResponseType = HttpResponse<IIntegralChangeRecord[]>;

@Injectable({ providedIn: 'root' })
export class IntegralChangeRecordService {
    private resourceUrl = SERVER_API_URL + 'api/integral-change-records';

    constructor(private http: HttpClient) {}

    create(integralChangeRecord: IIntegralChangeRecord): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(integralChangeRecord);
        return this.http
            .post<IIntegralChangeRecord>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(integralChangeRecord: IIntegralChangeRecord): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(integralChangeRecord);
        return this.http
            .put<IIntegralChangeRecord>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IIntegralChangeRecord>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IIntegralChangeRecord[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(integralChangeRecord: IIntegralChangeRecord): IIntegralChangeRecord {
        const copy: IIntegralChangeRecord = Object.assign({}, integralChangeRecord, {
            createdDate:
                integralChangeRecord.createdDate != null && integralChangeRecord.createdDate.isValid()
                    ? integralChangeRecord.createdDate.toJSON()
                    : null,
            lastModifiedDate:
                integralChangeRecord.lastModifiedDate != null && integralChangeRecord.lastModifiedDate.isValid()
                    ? integralChangeRecord.lastModifiedDate.toJSON()
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
        res.body.forEach((integralChangeRecord: IIntegralChangeRecord) => {
            integralChangeRecord.createdDate = integralChangeRecord.createdDate != null ? moment(integralChangeRecord.createdDate) : null;
            integralChangeRecord.lastModifiedDate =
                integralChangeRecord.lastModifiedDate != null ? moment(integralChangeRecord.lastModifiedDate) : null;
        });
        return res;
    }
}
