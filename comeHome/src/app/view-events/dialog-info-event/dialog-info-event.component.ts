import { Component, Inject, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/view-login/auth.service';

@Component({
  selector: 'app-dialog-info-event',
  templateUrl: './dialog-info-event.component.html',
  styleUrls: ['./dialog-info-event.component.scss']
})
export class DialogInfoEventComponent implements OnInit {
  public formGroup: FormGroup;
  public adminAnser: string;
  public eventStatus: boolean;
  public userPermission: boolean = false;
  userEmail: string;
  constructor(
    public formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<DialogInfoEventComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private authService: AuthService,
    private router: Router, ) {}
  
    createForm(): FormGroup {
      return this.formBuilder.group({
        eventStatus: '',
      });
    }
  ngOnInit(): void {
    this.formGroup = this.createForm();
    this.userEmail = this.authService.getEmail();
    if (this.userEmail != '' && this.userEmail != null && this.userEmail != undefined) {
      this.userPermission = true;
    }
  }

  onAprovedClick() {
    this.formGroup.patchValue({
      eventStatus: 'Inscrito',
    });
    return this.formGroup.value
  }
  doLogin() {
    this.formGroup.patchValue({
      eventStatus: 'Login',
    });
    this.dialogRef.close();
    this.router.navigate(['/login']);
  }
}
