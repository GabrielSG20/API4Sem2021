import {
  Component,
  ChangeDetectionStrategy,
  ViewChild,
  TemplateRef,
  OnInit,
  EventEmitter,
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
import { DatePipe, formatDate } from '@angular/common';
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
  public events: any;
  eventConflit: boolean = false;

  activeDayIsOpen: boolean = false;

  constructor(
    private modal: NgbModal, 
    public formBuilder: FormBuilder, 
    private appService: AppService,
    public dialog: MatDialog,
    public datepipe: DatePipe) {}

  ngOnInit(): void {
    this.events = [];
    this.getAllResults();
  }

  colorEvent(element: any) {
    if (element.status === null) {
      return colors.yellow;
    }
    else {
      if(element.status === 1) {
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
    let eventCount = 0;
    for (var element of this.events) {
      console.log(this.event.space);
      if (element.space.length>1){
        if (this.datepipe.transform(this.event.start, 'yyyy-MM-dd') == this.datepipe.transform(element.start, 'yyyy-MM-dd')) {
          eventCount += 1;
        }
      } else {
        if (this.datepipe.transform(this.event.start, 'yyyy-MM-dd') == this.datepipe.transform(element.start, 'yyyy-MM-dd')
        && this.event.space[0].nomeEspaco == element.space[0].nomeEspaco) {
          eventCount += 1;
        }
      }
    }
    if (this.event.status === null) {
      if (eventCount > 1) {
        this.eventConflit = true;
        alert('Existe mais de um evento para o mesmo espaço no mesmo dia. Por favor recuse um evento, antes de realizar a aprovação.');
      }
      const dialogRef = this.dialog.open(DialogAproveEventComponent, {
        data: [this.event, this.eventConflit],
      });
  
      dialogRef.afterClosed().subscribe(result => {
        if(result.eventStatus === 'Aprovado') {
          this.appService.approveEvent(this.event.id).subscribe((values) => {
            this.events = [];
            alert('Esse evento aprovado com sucesso!');
            this.getAllResults();
          });
        } else {
          this.appService.deleteEvent(this.event.id, result.adminAnwser).subscribe((values) => {
            this.events = [];
            alert('Esse evento recusado com sucesso!');
            this.getAllResults();
          });
        }
        this.eventConflit = false;
      });
    }
    else {
      alert('Esse evento já foi classificado!');
    }
  }
}
