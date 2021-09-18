import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-view-create-event',
  templateUrl: './view-create-event.component.html',
  styleUrls: ['./view-create-event.component.scss']
})
export class ViewCreateEventComponent implements OnInit {
  public date: FormControl;
  public labelPicker: String;
  public formGroup: FormGroup;
  public hours: string[];
  public HOS_EVENT: any;
  public HOS_DATE_EVENT: any;
  constructor(public formBuilder: FormBuilder) { }

  ngOnInit(): void {
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
    console.log(this.hours);
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
  ngSubmit() {}
}
