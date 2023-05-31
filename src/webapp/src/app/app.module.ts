import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbAlertModule, NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { HttpClientModule } from '@angular/common/http';
import { AmarisNewsComponent } from './amaris-news/amaris-news.component';
import { NgbdTableComplete } from './data-table/table-complete';
import { TableModule } from 'ngx-easy-table';
import { AmarisNewsBookmarksComponent } from './amaris-news-bookmarks/amaris-news-bookmarks.component';
import { ToastrModule } from 'ngx-toastr';
import { AlertViewComponent } from './commons/alert-view/alert-view.component';


@NgModule({
  declarations: [
    AppComponent,
    AmarisNewsComponent,
    AmarisNewsBookmarksComponent,
    AlertViewComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NgbModule,
    HttpClientModule,
    NgbdTableComplete,
    TableModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
