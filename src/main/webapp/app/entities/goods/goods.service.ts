import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGoods } from 'app/shared/model/goods.model';

type EntityResponseType = HttpResponse<IGoods>;
type EntityArrayResponseType = HttpResponse<IGoods[]>;

@Injectable({ providedIn: 'root' })
export class GoodsService {
    private resourceUrl = SERVER_API_URL + 'api/goods';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/goods';

    constructor(private http: HttpClient) {}

    create(goods: IGoods): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(goods);
        return this.http
            .post<IGoods>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(goods: IGoods): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(goods);
        return this.http
            .put<IGoods>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IGoods>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IGoods[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IGoods[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    private convertDateFromClient(goods: IGoods): IGoods {
        const copy: IGoods = Object.assign({}, goods, {
            createdDate: goods.createdDate != null && goods.createdDate.isValid() ? goods.createdDate.toJSON() : null,
            lastModifiedDate: goods.lastModifiedDate != null && goods.lastModifiedDate.isValid() ? goods.lastModifiedDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
        res.body.lastModifiedDate = res.body.lastModifiedDate != null ? moment(res.body.lastModifiedDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((goods: IGoods) => {
            goods.createdDate = goods.createdDate != null ? moment(goods.createdDate) : null;
            goods.lastModifiedDate = goods.lastModifiedDate != null ? moment(goods.lastModifiedDate) : null;
        });
        return res;
    }
}
