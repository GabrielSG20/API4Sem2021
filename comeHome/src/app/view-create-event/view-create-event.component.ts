import { formatDate } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

import { AppService } from '../app.service';
import AppMockedService from '../app.mocked.service';

@Component({
  selector: 'app-view-create-event',
  templateUrl: './view-create-event.component.html',
  styleUrls: ['./view-create-event.component.scss']
})
export class ViewCreateEventComponent implements OnInit, OnDestroy {
  public labelPicker: String;
  public data: any;
  public showSucss: boolean;
  public showError: boolean;
  public formGroup: FormGroup;
  public hours: string[];
  public HOS_EVENT: any;
  public HOS_DATE_EVENT: any;
  public HOS_OPEN_SPACE: any;
  public HOS_SPACE: any;
  public HOS_PRIVATE_ESPACE: any;
  public HOS_START: any;
  public HOS_END: any;
  public HOS_EVENT_TYPE: any;
  public HOS_GUESTS: any;
  public HOS_EVENT_IMAGE: any;
  public HOS_DESCRIPTION: any;
  constructor(
    public formBuilder: FormBuilder,
    private appService: AppService,
    private appMockedService: AppMockedService,
    ) { }

  ngOnInit(): void {
    this.HOS_OPEN_SPACE = new FormControl();
    this.HOS_PRIVATE_ESPACE = new FormControl();
    this.getOrgs();
    this.hours = [
      '08:00',
      '09:00',
      '10:00',
      '11:00',
      '12:00',
      '13:00',
      '14:00',
      '15:00',
      '16:00',
      '17:00',
    ]
    this.formGroup = this.createForm();
  }
  onClose() {
    this.labelPicker = (formatDate(this.formGroup.value.HOS_DATE_EVENT, 'fullDate', 'pt'));
  }
  dateFormat() {
    let eventDate = (formatDate(this.formGroup.value.HOS_DATE_EVENT, 'shortDate', 'pt'));
    const dateHourStart = eventDate.concat(' ',this.formGroup.value.dataInicio);
    const dateHoursEnd = eventDate.concat(' ', this.formGroup.value.dataEncerramento);
    this.formGroup.patchValue({
      dataInicio: dateHourStart,
      dataEncerramento: dateHoursEnd,
    });  

    
  }
  createForm(): FormGroup {
    return this.formBuilder.group({
      titulo: [this.HOS_EVENT, Validators.required],
      HOS_DATE_EVENT: [this.HOS_DATE_EVENT, Validators.required],
      HOS_OPEN_SPACE: this.HOS_OPEN_SPACE,
      HOS_PRIVATE_ESPACE: this.HOS_PRIVATE_ESPACE,
      nomeEspaco: [this.HOS_SPACE, Validators.required],
      dataEncerramento: [this.HOS_END, Validators.required],
      dataInicio: [this.HOS_START, Validators.required],
      tipoEvento: [this.HOS_EVENT_TYPE, Validators.required],
      HOS_GUESTS: [this.HOS_GUESTS],
      descricao: [this.HOS_DESCRIPTION, Validators.required],
      imagemDivulgacao: this.HOS_EVENT_IMAGE,
      email: 'ramon@oracle.com',
    });
  }
  checkLocate() {
    if(this.formGroup.value.HOS_OPEN_SPACE == true && this.formGroup.value.HOS_PRIVATE_ESPACE == true) {
      this.formGroup.patchValue({
        nomeEspaco: ['Open Space', 'Lounge on Hall'],
      });  
    } 
    else if (this.formGroup.value.HOS_OPEN_SPACE != true && this.formGroup.value.HOS_PRIVATE_ESPACE == true) {
      this.formGroup.patchValue({
        nomeEspaco: ['Lounge on Hall'],
      }); 
    }
    else if (this.formGroup.value.HOS_OPEN_SPACE == true && this.formGroup.value.HOS_PRIVATE_ESPACE != true) {
      this.formGroup.patchValue({
        nomeEspaco: ['Open Space'],
      }); 
    }
    else {
      this.formGroup.value.HOS_SPACE.se = null;
    }
  }
  private getAllResults() {
    this.appService.getAllResults().subscribe((values) => {
      this.data = values;
      console.log(this.data['results']);
    });
  }
  private closeConnection() {
    this.appService.closeMirage().subscribe((res) => {
      return res;
    });
  }
  public insertResult() {
    this.appService.insertResult(this.formGroup.value).subscribe((res) => {
      if(!res['_subscribe']){
        this.showSucss = true;
      } else{
        this.showError = true;
      }
      return res;
    });
    this.getAllResults();
    this.formGroup.reset();
  }
  ngOnDestroy(): void {
  }
  ngSubmit() {
    this.checkLocate();
    this.dateFormat();
    if (this.formGroup.valid) {
      console.log(this.appService.insertResult(this.formGroup.value));
        this.appService.insertResult(this.formGroup.value).subscribe((res) => {
          this.showSucss = true;
          this.showError = false;
          this.formGroup.reset();
          return res;
        });
      } else {
        this.showError = true;
        this.showSucss = false;
      }
  }

  private getOrgs(){
    this.appService.getOrgs().subscribe((values) => {
      this.data = values;
      console.log(this.data);
    });
  }
}
