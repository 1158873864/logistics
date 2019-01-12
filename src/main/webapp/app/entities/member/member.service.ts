import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMember } from 'app/shared/model/member.model';

type EntityResponseType = HttpResponse<IMember>;
type EntityArrayResponseType = HttpResponse<IMember[]>;

@Injectable({ providedIn: 'root' })
export class MemberService {
    private resourceUrl = SERVER_API_URL + 'api/members';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/members';

    constructor(private http: HttpClient) {}

    create(member: IMember): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(member);
        return this.http
            .post<IMember>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(member: IMember): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(member);
        return this.http
            .put<IMember>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMember>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMember[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMember[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    private convertDateFromClient(member: IMember): IMember {
        const copy: IMember = Object.assign({}, member, {
            startDate: member.startDate != null && member.startDate.isValid() ? member.startDate.toJSON() : null,
            endDate: member.endDate != null && member.endDate.isValid() ? member.endDate.toJSON() : null,
            createdDate: member.createdDate != null && member.createdDate.isValid() ? member.createdDate.toJSON() : null,
            lastModifiedDate: member.lastModifiedDate != null && member.lastModifiedDate.isValid() ? member.lastModifiedDate.toJSON() : null
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
        res.body.forEach((member: IMember) => {
            member.startDate = member.startDate != null ? moment(member.startDate) : null;
            member.endDate = member.endDate != null ? moment(member.endDate) : null;
            member.createdDate = member.createdDate != null ? moment(member.createdDate) : null;
            member.lastModifiedDate = member.lastModifiedDate != null ? moment(member.lastModifiedDate) : null;
        });
        return res;
    }
}
