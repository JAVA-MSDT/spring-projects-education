import { Component, OnInit } from '@angular/core';
import { Image } from 'src/domain/image';
import { environment } from 'src/environments/environment';
import { ApiService } from 'src/service/api/api.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  title = 'file-store-front';
  readonly IMAGE_BY_NAMEL = environment.IMAGE_URL() + 'name/';
  images: Image[] = [];
  image: String | undefined;

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.apiService.get<Image[]>('images').subscribe((images) => {
      images.forEach((image) => {
        this.setImageUrl(image);
      });
      this.images = images;
    });
  }

  setImageUrl(image: Image): void {
    image.url = this.IMAGE_BY_NAMEL + image.name;
  }
}
