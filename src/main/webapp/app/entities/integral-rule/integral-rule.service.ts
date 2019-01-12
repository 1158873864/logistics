import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IIntegralRule } from 'app/shared/model/integral-rule.model';

type EntityResponseType = HttpResponse<IIntegralRule>;
type EntityArrayResponseType = HttpResponse<IIntegralRule[]>;

@Injectable({ providedIn: 'root' })
export class IntegralRuleService {
    private resourceUrl = SERVER_API_URL + 'api/integral-rules';

    constructor(private http: HttpClient) {}

    create(integralRule: IIntegralRule): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(integralRule);
        return this.http
            .post<IIntegralRule>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(integralRule: IIntegralRule): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(integralRule);
        return this.http
            .put<IIntegralRule>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IIntegralRule>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IIntegralRule[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(integralRule: IIntegralRule): IIntegralRule {
        const copy: IIntegralRule = Object.assign({}, integralRule, {
            createdDate: integralRule.createdDate != null && integralRule.createdDate.isValid() ? integralRule.createdDate.toJSON() : null,
            lastModifiedDate:
                integralRule.lastModifiedDate != null && integralRule.lastModifiedDate.isValid()
                    ? integralRule.lastModifiedDate.toJSON()
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
        res.body.forEach((integralRule: IIntegralRule) => {
            integralRule.createdDate = integralRule.createdDate != null ? moment(integralRule.createdDate) : null;
            integralRule.lastModifiedDate = integralRule.lastModifiedDate != null ? moment(integralRule.lastModifiedDate) : null;
        });
        return res;
    }
}
