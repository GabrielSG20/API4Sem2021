import { Component, OnInit } from '@angular/core';
import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'comeHome';
  public teste: any;
  constructor(private appService: AppService) { }
  ngOnInit(): void {
    this.getOrgs();
  }

  private getOrgs(){
    this.appService.getOrgs().subscribe((values) => {
      this.teste = values;
      console.log(this.teste);
    });
  }
}
