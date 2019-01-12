import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILogisticsDdnPic } from 'app/shared/model/logistics-ddn-pic.model';

type EntityResponseType = HttpResponse<ILogisticsDdnPic>;
type EntityArrayResponseType = HttpResponse<ILogisticsDdnPic[]>;

@Injectable({ providedIn: 'root' })
export class LogisticsDdnPicService {
    private resourceUrl = SERVER_API_URL + 'api/logistics-ddn-pics';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/logistics-ddn-pics';

    constructor(private http: HttpClient) {}

    create(logisticsDdnPic: ILogisticsDdnPic): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(logisticsDdnPic);
        return this.http
            .post<ILogisticsDdnPic>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(logisticsDdnPic: ILogisticsDdnPic): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(logisticsDdnPic);
        return this.http
            .put<ILogisticsDdnPic>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ILogisticsDdnPic>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ILogisticsDdnPic[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ILogisticsDdnPic[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    private convertDateFromClient(logisticsDdnPic: ILogisticsDdnPic): ILogisticsDdnPic {
        const copy: ILogisticsDdnPic = Object.assign({}, logisticsDdnPic, {
            createdDate:
                logisticsDdnPic.createdDate != null && logisticsDdnPic.createdDate.isValid() ? logisticsDdnPic.createdDate.toJSON() : null,
            lastModifiedDate:
                logisticsDdnPic.lastModifiedDate != null && logisticsDdnPic.lastModifiedDate.isValid()
                    ? logisticsDdnPic.lastModifiedDate.toJSON()
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
        res.body.forEach((logisticsDdnPic: ILogisticsDdnPic) => {
            logisticsDdnPic.createdDate = logisticsDdnPic.createdDate != null ? moment(logisticsDdnPic.createdDate) : null;
            logisticsDdnPic.lastModifiedDate = logisticsDdnPic.lastModifiedDate != null ? moment(logisticsDdnPic.lastModifiedDate) : null;
        });
        return res;
    }
}
