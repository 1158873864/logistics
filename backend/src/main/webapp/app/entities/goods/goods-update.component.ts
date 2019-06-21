import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IGoods } from 'app/shared/model/goods.model';
import { GoodsService } from './goods.service';

@Component({
    selector: 'jhi-goods-update',
    templateUrl: './goods-update.component.html'
})
export class GoodsUpdateComponent implements OnInit {
    private _goods: IGoods;
    isSaving: boolean;
    createdDate: string;
    lastModifiedDate: string;

    editor: any;

    constructor(private goodsService: GoodsService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ goods }) => {
            this.goods = goods;
            this.uploadPic(this);
            this.initEditor(this);
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.goods.introduce = this.editor.txt.html();
        this.goods.createdDate = moment(this.createdDate, DATE_TIME_FORMAT);
        this.goods.lastModifiedDate = moment(this.lastModifiedDate, DATE_TIME_FORMAT);
        if (this.goods.id !== undefined) {
            this.subscribeToSaveResponse(this.goodsService.update(this.goods));
        } else {
            this.subscribeToSaveResponse(this.goodsService.create(this.goods));
        }
    }

    uploadPic(obj) {
        const uploader = window['WebUploader'].create({
            server: '/api/static/upload-goods-cover',
            pick: '#picker',
            auto: true,
            resize: false,
            fileNumLimit: 1,
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });
        uploader.on('uploadSuccess', function(file, ret) {
            obj._goods.cover = ret.path;
            uploader.destroy();
            obj.uploadPic(obj);
        });
        uploader.on('uploadError', function(file, ret) {
            uploader.destroy();
            obj.uploadPic(obj);
        });
    }

    initEditor(obj) {
        const E = window['wangEditor'];
        obj.editor = new E('#div-content');
        obj.editor.customConfig.uploadImgServer = '/api/static/upload-goods-pics';
        obj.editor.customConfig.uploadFileName = 'file';
        obj.editor.customConfig.uploadImgMaxSize = 1 * 1024 * 1024;
        obj.editor.create();
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGoods>>) {
        result.subscribe((res: HttpResponse<IGoods>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get goods() {
        return this._goods;
    }

    set goods(goods: IGoods) {
        this._goods = goods;
        this.createdDate = moment(goods.createdDate).format(DATE_TIME_FORMAT);
        this.lastModifiedDate = moment(goods.lastModifiedDate).format(DATE_TIME_FORMAT);
    }
}
