import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IUserDdnFavorites } from 'app/shared/model/user-ddn-favorites.model';

type EntityResponseType = HttpResponse<IUserDdnFavorites>;
type EntityArrayResponseType = HttpResponse<IUserDdnFavorites[]>;

@Injectable({ providedIn: 'root' })
export class UserDdnFavoritesService {
    private resourceUrl = SERVER_API_URL + 'api/user-ddn-favorites';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/user-ddn-favorites';

    constructor(private http: HttpClient) {}

    create(userDdnFavorites: IUserDdnFavorites): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(userDdnFavorites);
        return this.http
            .post<IUserDdnFavorites>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(userDdnFavorites: IUserDdnFavorites): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(userDdnFavorites);
        return this.http
            .put<IUserDdnFavorites>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IUserDdnFavorites>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IUserDdnFavorites[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IUserDdnFavorites[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    private convertDateFromClient(userDdnFavorites: IUserDdnFavorites): IUserDdnFavorites {
        const copy: IUserDdnFavorites = Object.assign({}, userDdnFavorites, {
            createdDate:
                userDdnFavorites.createdDate != null && userDdnFavorites.createdDate.isValid()
                    ? userDdnFavorites.createdDate.toJSON()
                    : null,
            lastModifiedDate:
                userDdnFavorites.lastModifiedDate != null && userDdnFavorites.lastModifiedDate.isValid()
                    ? userDdnFavorites.lastModifiedDate.toJSON()
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
        res.body.forEach((userDdnFavorites: IUserDdnFavorites) => {
            userDdnFavorites.createdDate = userDdnFavorites.createdDate != null ? moment(userDdnFavorites.createdDate) : null;
            userDdnFavorites.lastModifiedDate =
                userDdnFavorites.lastModifiedDate != null ? moment(userDdnFavorites.lastModifiedDate) : null;
        });
        return res;
    }
}
