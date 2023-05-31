import { Component, Input, TemplateRef, ViewChild } from '@angular/core';
import * as moment from 'moment';
import { AmarisNewsService } from '../services/amaris.news.service';
import { Columns, Config, DefaultConfig } from 'ngx-easy-table';

@Component({
  selector: 'app-amaris-news-bookmarks',
  templateUrl: './amaris-news-bookmarks.component.html',
  styleUrls: ['./amaris-news-bookmarks.component.scss']
})
export class AmarisNewsBookmarksComponent {

  @ViewChild('dateColumn', { static: true }) dateColumn!: TemplateRef<any>;
  @ViewChild('actionsTpl', { static: true }) actionsTpl!: TemplateRef<any>;

  alertType: string = ''
  alertMessage: string = ''

  constructor(private apiService: AmarisNewsService) { }

  configuration!: Config;
  columns!: Columns[];

  @Input() data = [];

  ngOnInit(): void {
    this.configuration = { ...DefaultConfig };
    this.configuration.searchEnabled = true;
    this.configuration.rows = 5;

    this.columns = [
      { key: 'title', title: 'Titulo' },
      { key: 'summary', title: 'Resumen' },
      { key: 'publishedAt', title: 'fecha de publicacion', cellTemplate: this.dateColumn },
    ];
    this.getNews(true)
  }

  private getNews = (showAlert: boolean) => {
    this.apiService.getBookmarks().subscribe({
      next: (response) => {
        this.data = response.slice(0, 10).sort();
        if (showAlert) {
          if (this.data.length > 0) {
            this.showAlert('success', 'Se han obtenido con exito tus noticias favoritas');
          } else {
            this.showAlert('warning', 'No tienes noticias favoritas');
          }

        }

      },
      error: (err) => {
        console.log("Error", err);
        if (showAlert) {
          this.showAlert('danger', 'No se pudo obtener la informacion');
        }

      }
    })
  }

  newsDateFormatter = (dateStr: string) => {
    const date = new Date(dateStr);
    return moment(new Date(date)).format("DD-MM-YYYY")
  }

  private showAlert = (type: string, message: string) => {
    this.alertType = type
    this.alertMessage = message

    setTimeout(() => {
      this.alertType = ''
      this.alertMessage = ''
    }, 2000)
  }

}
