import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';

import { AppService } from '../app.service';

@Component({
  selector: 'app-view-register-org',
  templateUrl: './view-register-org.component.html',
  styleUrls: ['./view-register-org.component.scss']
})
export class ViewRegisterOrgComponent implements OnInit {
  @ViewChild('formDirective') 
  private formDirective: NgForm;
  public formGroup: FormGroup;
  public userFunction: any;
  public showSucss: boolean;
  constructor(
    public formBuilder: FormBuilder,
    private appService: AppService,
    ) { }

  ngOnInit(): void {
    this.formGroup = this.createForm();
  }

  createForm(): FormGroup {
    return this.formBuilder.group({
      cargoUsuario: [this.userFunction, Validators.required],
      email: 'gabisgoncalves20@gmail.com',
    });
  }

  ngSubmit() {
    this.appService.updateOrg(this.formGroup.value).subscribe((res) => {
      this.formDirective.resetForm();
      this.formGroup.reset();
      setTimeout(() =>{this.showSucss = false;}, 4000);
    });
  }
}
