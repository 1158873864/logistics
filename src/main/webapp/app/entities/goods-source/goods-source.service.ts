import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGoodsSource } from 'app/shared/model/goods-source.model';

type EntityResponseType = HttpResponse<IGoodsSource>;
type EntityArrayResponseType = HttpResponse<IGoodsSource[]>;

@Injectable({ providedIn: 'root' })
export class GoodsSourceService {
    private resourceUrl = SERVER_API_URL + 'api/goods-sources';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/goods-sources';

    constructor(private http: HttpClient) {}

    create(goodsSource: IGoodsSource): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(goodsSource);
        return this.http
            .post<IGoodsSource>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(goodsSource: IGoodsSource): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(goodsSource);
        return this.http
            .put<IGoodsSource>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IGoodsSource>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IGoodsSource[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IGoodsSource[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    private convertDateFromClient(goodsSource: IGoodsSource): IGoodsSource {
        const copy: IGoodsSource = Object.assign({}, goodsSource, {
            effectiveDate:
                goodsSource.effectiveDate != null && goodsSource.effectiveDate.isValid() ? goodsSource.effectiveDate.toJSON() : null,
            layTime: goodsSource.layTime != null && goodsSource.layTime.isValid() ? goodsSource.layTime.toJSON() : null,
            createdDate: goodsSource.createdDate != null && goodsSource.createdDate.isValid() ? goodsSource.createdDate.toJSON() : null,
            lastModifiedDate:
                goodsSource.lastModifiedDate != null && goodsSource.lastModifiedDate.isValid()
                    ? goodsSource.lastModifiedDate.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.effectiveDate = res.body.effectiveDate != null ? moment(res.body.effectiveDate) : null;
        res.body.layTime = res.body.layTime != null ? moment(res.body.layTime) : null;
        res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
        res.body.lastModifiedDate = res.body.lastModifiedDate != null ? moment(res.body.lastModifiedDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((goodsSource: IGoodsSource) => {
            goodsSource.effectiveDate = goodsSource.effectiveDate != null ? moment(goodsSource.effectiveDate) : null;
            goodsSource.layTime = goodsSource.layTime != null ? moment(goodsSource.layTime) : null;
            goodsSource.createdDate = goodsSource.createdDate != null ? moment(goodsSource.createdDate) : null;
            goodsSource.lastModifiedDate = goodsSource.lastModifiedDate != null ? moment(goodsSource.lastModifiedDate) : null;
        });
        return res;
    }
}
