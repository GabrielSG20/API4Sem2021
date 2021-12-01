import { Component, Inject, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import {MatDialogModule, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-aprove-event',
  templateUrl: './dialog-aprove-event.component.html',
  styleUrls: ['./dialog-aprove-event.component.scss'],
})
export class DialogAproveEventComponent implements OnInit{
  public formGroup: FormGroup;
  public adminAnser: string;
  public eventStatus: boolean;
  constructor(
    public formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<DialogAproveEventComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {}
  
    createForm(): FormGroup {
      return this.formBuilder.group({
        adminAnwser: this.adminAnser,
        eventStatus: '',
      });
    }
  ngOnInit(): void {
    this.formGroup = this.createForm();
  }

  onAprovedClick() {
    this.formGroup.patchValue({
      eventStatus: 'Aprovado',
    });
    return this.formGroup.value
  }
  onRefusedClick() {
    console.log('teste');
    this.formGroup.patchValue({
      eventStatus: 'Recusado',
    });
    return this.formGroup.value
  }
}
