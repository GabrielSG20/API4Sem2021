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
  public labelPicker: String;
  public data: any;
  public formGroup: FormGroup;
  public hours: string[];
  public HOS_EVENT: any;
  public HOS_DATE_EVENT: any;
  public HOS_OPEN_SPACE: any;
  public HOS_PRIVATE_ESPACE: any;
  public HOS_LOCATE: any;
  public HOS_START: any;
  public HOS_END: any;
  public HOS_EVENT_TYPE: any;
  public HOS_GUESTS: any;
  public HOS_DESCRIPTION: any;
  constructor(
    public formBuilder: FormBuilder,
    private appService: AppService,
    private appMockedService: AppMockedService,
    ) { }

  ngOnInit(): void {
    this.HOS_OPEN_SPACE = new FormControl();
    this.HOS_PRIVATE_ESPACE = new FormControl();
    this.appMockedService.mirageJsServer();
    this.getAllResults();
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
  createForm(): FormGroup {
    return this.formBuilder.group({
      HOS_EVENT: [this.HOS_EVENT],
      HOS_DATE_EVENT: [this.HOS_DATE_EVENT],
      HOS_OPEN_SPACE: this.HOS_OPEN_SPACE,
      HOS_PRIVATE_ESPACE: this.HOS_PRIVATE_ESPACE,
      HOS_START: this.HOS_START,
      HOS_END: this.HOS_END,
      HOS_EVENT_TYPE: this.HOS_EVENT_TYPE,
      HOS_GUESTS: this.HOS_GUESTS,
      HOS_DESCRIPTION: this.HOS_DESCRIPTION,
    });
  }
  checkLocate() {
    if(this.formGroup.value.HOS_OPEN_SPACE == true && this.formGroup.value.HOS_PRIVATE_ESPACE == true) {
      return ['Open Space', 'Lounge on Hall'];
    } 
    else if (this.formGroup.value.HOS_OPEN_SPACE != true && this.formGroup.value.HOS_PRIVATE_ESPACE == true) {
      return ['Lounge on Hall'];
    }
    else if (this.formGroup.value.HOS_OPEN_SPACE == true && this.formGroup.value.HOS_PRIVATE_ESPACE != true) {
      return ['Open Space'];
    }
    else {
      console.log(this.HOS_OPEN_SPACE.value);
      return null;
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
      return res;
    });
    this.getAllResults();
    this.formGroup.reset();
  }
  ngOnDestroy(): void {
    this.closeConnection();
  }
  ngSubmit() {}
}
