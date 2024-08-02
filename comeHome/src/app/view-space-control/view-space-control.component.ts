import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AppService } from '../app.service';

@Component({
  selector: 'app-view-space-control',
  templateUrl: './view-space-control.component.html',
  styleUrls: ['./view-space-control.component.scss']
})
export class ViewSpaceControlComponent implements OnInit {
  public formGroup: FormGroup;
  public openspace: any;
  public lounge: any;
  public showSucss: boolean = false;
  public data: any;

  constructor(public formBuilder: FormBuilder, private appService: AppService,) { }

  ngOnInit(): void {
    this.getEspaco();
    this.formGroup = this.createForm();
  }

  createForm(): FormGroup {
    return this.formBuilder.group({
      openspace: [this.openspace],
      lounge: [this.lounge],
    });
  }

  ngSubmit() {
    if (this.formGroup.valid) {
        this.appService.updateSpace(this.formGroup.value).subscribe(response => {
          },
          error => {
          },
          () => {
            this.showSucss = true;
            setTimeout(() =>{this.showSucss = false;}, 4000);
          });
    }
  }

  getEspaco(){
    this.appService.getSpace().subscribe(response => {
      this.data = response;

      this.formGroup.patchValue({
        openspace: response[0],
        lounge: response[1],
      });
    })
  }
}
