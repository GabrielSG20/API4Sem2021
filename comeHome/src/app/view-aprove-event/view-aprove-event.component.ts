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
import {MatDialog} from '@angular/material/dialog';
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
import { DialogAproveEventComponent } from './dialog-aprove-event/dialog-aprove-event.component';

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
  selector: 'app-view-aprove-event',
  templateUrl: './view-aprove-event.component.html',
  styleUrls: ['./view-aprove-event.component.scss']
})
export class ViewAproveEventComponent implements OnInit {
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

  activeDayIsOpen: boolean = false;

  constructor(
    private modal: NgbModal, 
    public formBuilder: FormBuilder, 
    private appService: AppService,
    public dialog: MatDialog) {}

  ngOnInit(): void {
    this.events = [];
    this.getAllResults();
  }

  colorEvent(element: any) {
    if (element.status === null) {
      return colors.yellow;
    }
    else {
      if(element.status === 'aprovado') {
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
    number += 1;
    element[2] = number.toString();
    element = element.join('-');
    return element;
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
  handleEvent(action: string, event: CalendarEvent): void {
    this.modalData = { event, action };
    this.event = this.modalData.event;
    this.openDialog();
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
    this.appService.getOrgs().subscribe((values) => {
      this.data = values;
      this.eventPlot();
    });
  }
  openDialog() {
    if (this.event.status === null) {
      const dialogRef = this.dialog.open(DialogAproveEventComponent, {
        data: this.event,
      });
  
      dialogRef.afterClosed().subscribe(result => {
        console.log(`Dialog result: ${result.adminAnwser}, ${result.eventStatus}`);
      });
    }
    else {
      alert('Esse evento já foi classificado!');
    }
  }
}
