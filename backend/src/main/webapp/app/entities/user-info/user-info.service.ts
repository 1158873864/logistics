import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IUserInfo } from 'app/shared/model/user-info.model';

type EntityResponseType = HttpResponse<IUserInfo>;
type EntityArrayResponseType = HttpResponse<IUserInfo[]>;

@Injectable({ providedIn: 'root' })
export class UserInfoService {
    private resourceUrl = SERVER_API_URL + 'api/user-infos';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/user-infos';

    constructor(private http: HttpClient) {}

    create(userInfo: IUserInfo): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(userInfo);
        return this.http
            .post<IUserInfo>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(userInfo: IUserInfo): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(userInfo);
        return this.http
            .put<IUserInfo>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IUserInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IUserInfo[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IUserInfo[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    private convertDateFromClient(userInfo: IUserInfo): IUserInfo {
        const copy: IUserInfo = Object.assign({}, userInfo, {
            registerDate: userInfo.registerDate != null && userInfo.registerDate.isValid() ? userInfo.registerDate.toJSON() : null,
            lastLoginedDate:
                userInfo.lastLoginedDate != null && userInfo.lastLoginedDate.isValid() ? userInfo.lastLoginedDate.toJSON() : null,
            createdDate: userInfo.createdDate != null && userInfo.createdDate.isValid() ? userInfo.createdDate.toJSON() : null,
            lastModifiedDate:
                userInfo.lastModifiedDate != null && userInfo.lastModifiedDate.isValid() ? userInfo.lastModifiedDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.registerDate = res.body.registerDate != null ? moment(res.body.registerDate) : null;
        res.body.lastLoginedDate = res.body.lastLoginedDate != null ? moment(res.body.lastLoginedDate) : null;
        res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
        res.body.lastModifiedDate = res.body.lastModifiedDate != null ? moment(res.body.lastModifiedDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((userInfo: IUserInfo) => {
            userInfo.registerDate = userInfo.registerDate != null ? moment(userInfo.registerDate) : null;
            userInfo.lastLoginedDate = userInfo.lastLoginedDate != null ? moment(userInfo.lastLoginedDate) : null;
            userInfo.createdDate = userInfo.createdDate != null ? moment(userInfo.createdDate) : null;
            userInfo.lastModifiedDate = userInfo.lastModifiedDate != null ? moment(userInfo.lastModifiedDate) : null;
        });
        return res;
    }
}
