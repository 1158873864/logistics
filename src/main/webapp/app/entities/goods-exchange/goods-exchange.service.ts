import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGoodsExchange } from 'app/shared/model/goods-exchange.model';

type EntityResponseType = HttpResponse<IGoodsExchange>;
type EntityArrayResponseType = HttpResponse<IGoodsExchange[]>;

@Injectable({ providedIn: 'root' })
export class GoodsExchangeService {
    private resourceUrl = SERVER_API_URL + 'api/goods-exchanges';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/goods-exchanges';

    constructor(private http: HttpClient) {}

    create(goodsExchange: IGoodsExchange): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(goodsExchange);
        return this.http
            .post<IGoodsExchange>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(goodsExchange: IGoodsExchange): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(goodsExchange);
        return this.http
            .put<IGoodsExchange>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IGoodsExchange>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IGoodsExchange[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IGoodsExchange[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    private convertDateFromClient(goodsExchange: IGoodsExchange): IGoodsExchange {
        const copy: IGoodsExchange = Object.assign({}, goodsExchange, {
            createdDate:
                goodsExchange.createdDate != null && goodsExchange.createdDate.isValid() ? goodsExchange.createdDate.toJSON() : null,
            lastModifiedDate:
                goodsExchange.lastModifiedDate != null && goodsExchange.lastModifiedDate.isValid()
                    ? goodsExchange.lastModifiedDate.toJSON()
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
        res.body.forEach((goodsExchange: IGoodsExchange) => {
            goodsExchange.createdDate = goodsExchange.createdDate != null ? moment(goodsExchange.createdDate) : null;
            goodsExchange.lastModifiedDate = goodsExchange.lastModifiedDate != null ? moment(goodsExchange.lastModifiedDate) : null;
        });
        return res;
    }
}
