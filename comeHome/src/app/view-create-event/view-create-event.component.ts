import { formatDate } from '@angular/common';
import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, ReactiveFormsModule, Validators } from '@angular/forms';

import { AppService } from '../app.service';
import AppMockedService from '../app.mocked.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-view-create-event',
  templateUrl: './view-create-event.component.html',
  styleUrls: ['./view-create-event.component.scss']
})
export class ViewCreateEventComponent implements OnInit, OnDestroy {
  public labelPicker: String;
  @ViewChild('formDirective') 
  private formDirective: NgForm;
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
  public HOS_EVENT_IMAGE: File;
  public HOS_DESCRIPTION: any;
  public emails: Object[];
  constructor(
    public formBuilder: FormBuilder,
    private appService: AppService,
    private http: HttpClient,
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
      '18:00',
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
  
  onFileSelected(event: any) {
    this.HOS_EVENT_IMAGE = event.target.files[0];
    console.log(this.HOS_EVENT_IMAGE);
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
      convidados: [this.HOS_GUESTS],
      descricao: [this.HOS_DESCRIPTION, Validators.required],
      imagemDivulgacao: this.HOS_EVENT_IMAGE,
      email: 'teste@gmail.com',
    });
  }
  checkLocate() {
    if(this.formGroup.value.HOS_OPEN_SPACE == true && this.formGroup.value.HOS_PRIVATE_ESPACE == true) {
      this.formGroup.patchValue({
        nomeEspaco: [{nomeEspaco: 'Open Space'},
                      {nomeEspaco: 'Lounge on Hall'}],
      });  
    } 
    else if (this.formGroup.value.HOS_OPEN_SPACE != true && this.formGroup.value.HOS_PRIVATE_ESPACE == true) {
      this.formGroup.patchValue({
        nomeEspaco: [{nomeEspaco: 'Lounge on Hall'}],
      }); 
    }
    else if (this.formGroup.value.HOS_OPEN_SPACE == true && this.formGroup.value.HOS_PRIVATE_ESPACE != true) {
      this.formGroup.patchValue({
        nomeEspaco: [{nomeEspaco: 'Open Space'}],
      }); 
    }
    else {
      this.formGroup.value.HOS_SPACE.se = null;
    }
  }

  getImage() {
    console.log(this.HOS_EVENT_IMAGE);
    this.formGroup.patchValue({
      nomeEspaco: this.HOS_EVENT_IMAGE,
    });
  }
  private getAllResults() {
    this.appService.getAllResults().subscribe((values) => {
      this.data = values;
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
    this.getImage();
    this.checkLocate();
    this.getAllEmails();
    this.dateFormat();
    if (this.formGroup.valid) {
        this.appService.insertResult(this.formGroup.value).subscribe(response => {
          },
          error => {
            console.log('chegou');
            this.showError = true;
            this.showSucss = false;
          }, 
          () => {
            this.showSucss = true;
            setTimeout(() =>{this.showSucss = false;}, 4000);
            this.showError = false;
          });
    } else {
        this.showError = true;
        this.showSucss = false;
    }
    this.formDirective.resetForm();
    this.formGroup.reset();
  }

  private getOrgs(){
    this.appService.getOrgs().subscribe((values) => {
      this.data = values;
    });
  }
  private getAllEmails() {
    this.HOS_GUESTS = this.formGroup.value.convidados.split(", ");
    this.emails = this.HOS_GUESTS.map((str: string) => ({email: str}));
    this.formGroup.patchValue({
      convidados: this.emails,
    });
  }
}
