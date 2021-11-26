import {
  Component,
  ChangeDetectionStrategy,
  ViewChild,
  TemplateRef,
  OnInit,
} from '@angular/core';
import {
  startOfDay,
  endOfDay,
  subDays,
  addDays,
  endOfMonth,
  isSameDay,
  isSameMonth,
  addHours,
  setHours,
  setMinutes,
} from 'date-fns';
import { Subject } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent,
  CalendarView,
} from 'angular-calendar';
import { formatDate } from '@angular/common';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { AppService } from '../app.service';
import { MatDialog } from '@angular/material/dialog';
import { DialogInfoEventComponent } from './dialog-info-event/dialog-info-event.component';
import { AuthService } from '../view-login/auth.service';

const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3',
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF',
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA',
  },
};

@Component({
  selector: 'app-view-events',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './view-events.component.html',
  styleUrls: ['./view-events.component.scss']
})
export class ViewEventsComponent implements OnInit {
  @ViewChild('modalContent', { static: true }) modalContent: TemplateRef<any>;
  date = new FormControl(new Date());
  view: CalendarView = CalendarView.Month;
  CalendarView = CalendarView;
  viewDate: Date = new Date();
  modalData: {
    action: string;
    event: CalendarEvent;
  };
  public elemento: any;
  refresh: Subject<any> = new Subject();
  public data: any;
  public event: any;
  public events: CalendarEvent[];
  public userEmail: string;

  activeDayIsOpen: boolean = false;

  constructor(
    private modal: NgbModal, 
    public formBuilder: FormBuilder, 
    private appService: AppService, 
    public dialog: MatDialog,
    private authService: AuthService,) {}

  ngOnInit(): void {
    this.events = [];
    this.getAllResults();
    this.userEmail = this.authService.getEmail();
    console.log(this.userEmail != '' && this.userEmail != null && this.userEmail != undefined);
  }

  colorEvent(element: any) {
    let color;
    if (element.nomeEspaco.length == 2) {
      return colors.yellow;
    }
    else {
      if(element.nomeEspaco[0].nomeEspaco == "Open Space") {
        return colors.blue;
      }
      else {
        return colors.red;
      }
    }
  }
  eventDate(element: any) {
    element = element.split(" ", 1);
    element = element.join();
    element = element.split("/");
    element = element.reverse();
    let number = Number(element[2]);
    if (number < 10){
      element[2] = number.toString();
      element = element.join('-');
      return element;
    } else {
      number += 1;
      element[2] = number.toString();
      element = element.join('-');
      return element;
    }
  }
  eventHour(element: any) {
    element = element.split(" ");
    element = element[1];
    element = element.split(":");
    let number = Number(element[0]);
    return number
  }
  eventHourString(element: any) {
    element = element.split(" ");
    element = element[1];
    return element;
  }
  eventPlot() {
    for (var element of this.data) {
      this.elemento = element;
      var newEvent = {
        id: this.elemento.idEvento,
        space: this.elemento.nomeEspaco,
        desc: this.elemento.descricao,
        hourStart: this.eventHourString(this.elemento.dataInicio),
        hourEnd: this.eventHourString(this.elemento.dataEncerramento),
        eventType: this.elemento.tipoEvento,
        status: this.elemento.status,
        start: setHours(new Date(this.eventDate(this.elemento.dataInicio)), this.eventHour(this.elemento.dataInicio)),
        end: setHours(new Date(this.eventDate(this.elemento.dataEncerramento)), this.eventHour(this.elemento.dataEncerramento)),
        title: this.elemento.titulo,
        color: this.colorEvent(this.elemento),
      };
      this.events.push(newEvent);
      this.refresh.next();
    }
  }
  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
      this.viewDate = date;
      this.date = new FormControl(new Date(date));
      
    }
  }

  eventTimesChanged({
    event,
    newStart,
    newEnd,
  }: CalendarEventTimesChangedEvent): void {
    this.events = this.events.map((iEvent) => {
      if (iEvent === event) {
        return {
          ...event,
          start: newStart,
          end: newEnd,
        };
      }
      return iEvent;
    });
    this.handleEvent('Dropped or resized', event);
  }

  handleEvent(action: string, event: CalendarEvent): void {
    this.modalData = { event, action };
    this.modal.open(this.modalContent, { size: 'lg' });
  }

  setView(view: CalendarView) {
    this.view = view;
  }

  closeOpenMonthViewDay() {
    this.activeDayIsOpen = false;
  }
  onClose() {
    this.viewDate = this.date.value;
  }

  private getAllResults() {
    this.appService.getApprovedEvents().subscribe((values) => {
      this.data = values;
      this.eventPlot();
    });
  }
  openDialog() {
    const dialogRef = this.dialog.open(DialogInfoEventComponent, {
      data: this.event,
    });
  
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result.adminAnwser}, ${result.eventStatus}`);
      if(result.eventStatus === 'Inscrito') {
        //Trocar api aqui
        this.appService.participarEvento(this.event.id, this.userEmail).subscribe((values) => {
          this.events = [];
          alert('Você foi adicionado a lista de participantes com sucesso!');
          this.getAllResults();
        });
      }
    });
  }
}
