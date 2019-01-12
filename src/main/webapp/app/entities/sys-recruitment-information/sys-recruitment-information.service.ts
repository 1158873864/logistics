import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysRecruitmentInformation } from 'app/shared/model/sys-recruitment-information.model';

type EntityResponseType = HttpResponse<ISysRecruitmentInformation>;
type EntityArrayResponseType = HttpResponse<ISysRecruitmentInformation[]>;

@Injectable({ providedIn: 'root' })
export class SysRecruitmentInformationService {
    private resourceUrl = SERVER_API_URL + 'api/sys-recruitment-informations';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/sys-recruitment-informations';

    constructor(private http: HttpClient) {}

    create(sysRecruitmentInformation: ISysRecruitmentInformation): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysRecruitmentInformation);
        return this.http
            .post<ISysRecruitmentInformation>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysRecruitmentInformation: ISysRecruitmentInformation): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysRecruitmentInformation);
        return this.http
            .put<ISysRecruitmentInformation>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysRecruitmentInformation>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysRecruitmentInformation[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysRecruitmentInformation[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    private convertDateFromClient(sysRecruitmentInformation: ISysRecruitmentInformation): ISysRecruitmentInformation {
        const copy: ISysRecruitmentInformation = Object.assign({}, sysRecruitmentInformation, {
            createdDate:
                sysRecruitmentInformation.createdDate != null && sysRecruitmentInformation.createdDate.isValid()
                    ? sysRecruitmentInformation.createdDate.toJSON()
                    : null,
            lastModifiedDate:
                sysRecruitmentInformation.lastModifiedDate != null && sysRecruitmentInformation.lastModifiedDate.isValid()
                    ? sysRecruitmentInformation.lastModifiedDate.toJSON()
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
        res.body.forEach((sysRecruitmentInformation: ISysRecruitmentInformation) => {
            sysRecruitmentInformation.createdDate =
                sysRecruitmentInformation.createdDate != null ? moment(sysRecruitmentInformation.createdDate) : null;
            sysRecruitmentInformation.lastModifiedDate =
                sysRecruitmentInformation.lastModifiedDate != null ? moment(sysRecruitmentInformation.lastModifiedDate) : null;
        });
        return res;
    }
}
