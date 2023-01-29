import { Component, OnInit } from '@angular/core';
import { Image } from 'src/domain/image';
import { ApiService } from 'src/service/api/api.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'file-store-front';
  images: Image[] = [];

  constructor(
    private apiService: ApiService
  ) {}


  ngOnInit(): void {
    this.apiService.get<Image[]>("v1/images")
    .subscribe((images) =>{ 
      console.log(images.length);
      console.log("images.length");
      this.images = images});
  
    }
}
