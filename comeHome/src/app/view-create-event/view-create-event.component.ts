import { formatDate } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

import { AppService } from '../app.service';
import AppMockedService from '../app.mocked.service';

@Component({
  selector: 'app-view-create-event',
  templateUrl: './view-create-event.component.html',
  styleUrls: ['./view-create-event.component.scss']
})
export class ViewCreateEventComponent implements OnInit, OnDestroy {
  public date: FormControl;
  public labelPicker: String;
  public data: any;
  public formGroup: FormGroup;
  public hours: string[];
  public HOS_EVENT: any;
  public HOS_DATE_EVENT: any;
  constructor(
    public formBuilder: FormBuilder,
    private appService: AppService,
    private appMockedService: AppMockedService,
    ) { }

  ngOnInit(): void {
    this.appMockedService.mirageJsServer();
    console.log(this.getAllResults());
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
    this.date = new FormControl();
    this.formGroup = this.createForm();
  }
  onClose() {
    this.labelPicker = (formatDate(this.date.value, 'fullDate', 'pt'));
  }
  createForm(): FormGroup {
    return this.formBuilder.group({
      HOS_EVENT: [this.HOS_EVENT],
      HOS_DATE_EVENT: [this.date.value],
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
  ngOnDestroy(): void {
    this.closeConnection();
  }
  ngSubmit() {}
}
