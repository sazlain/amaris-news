import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AmarisNewsComponent } from './amaris-news/amaris-news.component';
import { AmarisNewsBookmarksComponent } from './amaris-news-bookmarks/amaris-news-bookmarks.component';

const routes: Routes = [
  {
    path: 'news', component: AmarisNewsComponent
  },
  {
    path: 'news/bookmarks', component: AmarisNewsBookmarksComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
