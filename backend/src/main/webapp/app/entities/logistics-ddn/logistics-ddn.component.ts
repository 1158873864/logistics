import { Component, OnInit, OnDestroy } from "@angular/core";
import {
  HttpErrorResponse,
  HttpHeaders,
  HttpResponse
} from "@angular/common/http";
import { ActivatedRoute, Router } from "@angular/router";
import { Subscription } from "rxjs";
import { JhiEventManager, JhiParseLinks, JhiAlertService } from "ng-jhipster";

import { ILogisticsDdn } from "app/shared/model/logistics-ddn.model";
import { Principal } from "app/core";

import { ITEMS_PER_PAGE } from "app/shared";
import { LogisticsDdnService } from "./logistics-ddn.service";
import { LocalStorageService, SessionStorageService } from "ngx-webstorage";

@Component({
  selector: "jhi-logistics-ddn",
  templateUrl: "./logistics-ddn.component.html"
})
export class LogisticsDdnComponent implements OnInit, OnDestroy {
  currentAccount: any;
  logisticsDdns: ILogisticsDdn[];
  error: any;
  success: any;
  eventSubscriber: Subscription;
  currentSearch: string;
  routeData: any;
  links: any;
  totalItems: any;
  queryCount: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;

  constructor(
    private localStorage: LocalStorageService,
    private sessionStorage: SessionStorageService,
    private logisticsDdnService: LogisticsDdnService,
    private parseLinks: JhiParseLinks,
    private jhiAlertService: JhiAlertService,
    private principal: Principal,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private eventManager: JhiEventManager
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
    this.currentSearch =
      this.activatedRoute.snapshot &&
      this.activatedRoute.snapshot.params["search"]
        ? this.activatedRoute.snapshot.params["search"]
        : "";
  }

  loadAll() {
    if (this.currentSearch) {
      this.logisticsDdnService
        .search({
          page: this.page - 1,
          query: this.currentSearch,
          size: this.itemsPerPage,
          sort: this.sort()
        })
        .subscribe(
          (res: HttpResponse<ILogisticsDdn[]>) =>
            this.paginateLogisticsDdns(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
      return;
    }
    this.logisticsDdnService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<ILogisticsDdn[]>) =>
          this.paginateLogisticsDdns(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(["/logistics-ddn"], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        search: this.currentSearch,
        sort: this.predicate + "," + (this.reverse ? "asc" : "desc")
      }
    });
    this.loadAll();
  }

  clear() {
    this.page = 0;
    this.currentSearch = "";
    this.router.navigate([
      "/logistics-ddn",
      {
        page: this.page,
        sort: this.predicate + "," + (this.reverse ? "asc" : "desc")
      }
    ]);
    this.loadAll();
  }

  search(query) {
    if (!query) {
      return this.clear();
    }
    this.page = 0;
    this.currentSearch = query;
    this.router.navigate([
      "/logistics-ddn",
      {
        search: this.currentSearch,
        page: this.page,
        sort: this.predicate + "," + (this.reverse ? "asc" : "desc")
      }
    ]);
    this.loadAll();
  }

  importData(obj) {
    const uploader = window["WebUploader"].create({
      server: "/api/logistics-ddns/batch-import",
      pick: ".batch-upload",
      auto: true,
      resize: false,
      fileNumLimit: 1,
      accept: {
        title: "专线信息",
        extensions: "xls,xlsx"
      }
    });
    uploader.on("uploadBeforeSend", function(file, data, headers) {
      const token =
        obj.localStorage.retrieve("authenticationToken") ||
        obj.sessionStorage.retrieve("authenticationToken");
      headers["Authorization"] = "Bearer " + token;
    });
    uploader.on("uploadSuccess", function(file, ret) {
      // 上传成功 ...
      alert("导入成功");
      obj.loadAll();
      uploader.destroy();
      obj.importData(obj);
    });
    uploader.on("uploadError", function(file, ret) {
      alert("文件格式错误或文件过大！");
      uploader.destroy();
      obj.importData(obj);
    });
  }
  importPicsData(obj) {
    const uploader = window["WebUploader"].create({
      multiple: true,
      server: "/api/logistics-ddns/batch-import-pics",
      pick: ".batch-upload-pics",
      auto: true,
      resize: false,
      fileNumLimit: 30,
      accept: {
        title: "专线图片",
        extensions: "gif,jpg,jpeg,bmp,png"
      }
    });
    uploader.on("uploadBeforeSend", function(file, data, headers) {
      const token =
        obj.localStorage.retrieve("authenticationToken") ||
        obj.sessionStorage.retrieve("authenticationToken");
      headers["Authorization"] = "Bearer " + token;
    });
    uploader.on("uploadSuccess", function(file, ret) {
      // 上传成功 ...
      // alert('导入成功');
      //obj.loadAll();
      //uploader.destroy();
      //obj.importPicsData(obj);
    });
    uploader.on("uploadFinished", function(file, ret) {
      // 上传成功 ...
      alert("全部上传成功");
      obj.loadAll();
      uploader.destroy();
      obj.importPicsData(obj);
    });
    uploader.on("uploadError", function(file, ret) {
      alert("导入失败");
      uploader.destroy();
      obj.importPicsData(obj);
    });
  }
  ngOnInit() {
    this.loadAll();
    this.principal.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInLogisticsDdns();

    this.importData(this);
    this.importPicsData(this);
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ILogisticsDdn) {
    return item.id;
  }

  registerChangeInLogisticsDdns() {
    this.eventSubscriber = this.eventManager.subscribe(
      "logisticsDdnListModification",
      response => this.loadAll()
    );
  }

  sort() {
    const result = [this.predicate + "," + (this.reverse ? "asc" : "desc")];
    if (this.predicate !== "id") {
      result.push("id");
    }
    return result;
  }

  private paginateLogisticsDdns(data: ILogisticsDdn[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get("link"));
    this.totalItems = parseInt(headers.get("X-Total-Count"), 10);
    this.queryCount = this.totalItems;
    this.logisticsDdns = data;
  }

  private onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
