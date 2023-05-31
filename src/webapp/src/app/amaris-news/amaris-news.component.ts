import { Component, Input, TemplateRef, ViewChild } from '@angular/core';
import { AmarisNewsService } from '../services/amaris.news.service';
import { Columns, Config, DefaultConfig } from 'ngx-easy-table';
import * as moment from 'moment';

@Component({
  selector: 'app-amaris-news',
  templateUrl: './amaris-news.component.html',
  styleUrls: ['./amaris-news.component.scss', "../../assets/toastr.css"],

})
export class AmarisNewsComponent {

  @ViewChild('dateColumn', { static: true }) dateColumn!: TemplateRef<any>;
  @ViewChild('actionsTpl', { static: true }) actionsTpl!: TemplateRef<any>;

  alertType: string = ''
  alertMessage: string = ''

  alerts: any[] = [];

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
      { key: '', title: '', cellTemplate: this.actionsTpl, searchEnabled: false }
    ];

    this.getNews(true);
  }

  private getNews = (showAlert: boolean) => {
    this.apiService.getNews().subscribe({
      next: (response) => {
        this.data = response.slice(0, 10).sort();
        if (showAlert) {
          this.showAlert('success', 'Se han obtenido con exito las ultimas noticias');
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

  addBookmark = (id: number) => {
    this.apiService.addBookmark("api/news/bookmark/" + id).subscribe({
      next: (response) => {
        this.getNews(false);
        this.showAlert('success', 'Se agregado una nueva noticia a tus favoritos');
      },
      error: (err) => {
        console.log("Error", err)
        this.showAlert('danger', 'Error al agregar la noticia a favoritos');
      }
    }
    )
  }

  removeBookmark = (id: number) => {
    this.apiService.removeBookmark("api/news/bookmark/" + id).subscribe({
      next: (response) => {
        this.getNews(false);
        this.showAlert('warning', 'Se quitado una noticia de tus favoritos');
      },
      error: (err) => {
        console.log("Error", err);
        this.showAlert('danger', 'Error al quitar la noticia de tus favoritos');
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