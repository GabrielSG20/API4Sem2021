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
    /*this.appService.getOrgs().subscribe((values) => {
      this.data = values;
      this.eventPlot();
    });*/
    this.data = [
      {
        "idEvento": 9,
        "titulo": "Inauguração Oracle",
        "descricao": "Evento de inauguração da Casa Oracle, aberto ao público.",
        "dataInicio": "27/10/2021 08:00",
        "dataEncerramento": "27/10/2021 12:00",
        "tipoEvento": "Aberto",
        "status": null,
        "imagemDivulgacao": null,
        "org": {
          "email": "teste@gmail.com",
          "nomeCompleto": "Teste Name",
          "cpf": "56781",
          "telefone": "78961",
          "departamento": null,
          "nomeEmpresa": null,
          "idOracle": null,
          "comprovanteVacinacao": null,
          "tipoUsuario": "interno",
          "cargoUsuario": null,
          "senhaUsuario": "pass123"
        },
        "nomeEspaco": [
          {
            "idEspaco": 2,
            "nomeEspaco": "Open Space",
            "capEspaco": 50
          },
          {
            "idEspaco": 3,
            "nomeEspaco": "Lounge on Hall",
            "capEspaco": 20
          }
        ],
        "fornecedores": [],
        "convidados": [
          {
            "email": "mike@gmail.com",
            "nomeCompleto": "Teste Name",
            "cpf": "567815",
            "telefone": "789615",
            "departamento": null,
            "nomeEmpresa": null,
            "idOracle": null,
            "comprovanteVacinacao": null,
            "tipoUsuario": "interno",
            "cargoUsuario": null,
            "senhaUsuario": "pass1235"
          },
          {
            "email": "ferraz@gmail.com",
            "nomeCompleto": "Teste Name",
            "cpf": "567812",
            "telefone": "789612",
            "departamento": null,
            "nomeEmpresa": null,
            "idOracle": null,
            "comprovanteVacinacao": null,
            "tipoUsuario": "interno",
            "cargoUsuario": null,
            "senhaUsuario": "pass1232"
          }
        ]
      },
      {
        "idEvento": 9,
        "titulo": "Evento1",
        "descricao": "Desc 1",
        "dataInicio": "17/10/2021 08:00",
        "dataEncerramento": "17/10/2021 12:00",
        "tipoEvento": "fechado",
        "status": 'aprovado',
        "imagemDivulgacao": null,
        "org": {
          "email": "teste@gmail.com",
          "nomeCompleto": "Teste Name",
          "cpf": "56781",
          "telefone": "78961",
          "departamento": null,
          "nomeEmpresa": null,
          "idOracle": null,
          "comprovanteVacinacao": null,
          "tipoUsuario": "interno",
          "cargoUsuario": null,
          "senhaUsuario": "pass123"
        },
        "nomeEspaco": [
          {
            "idEspaco": 2,
            "nomeEspaco": "Open Space",
            "capEspaco": 50
          },
          {
            "idEspaco": 3,
            "nomeEspaco": "Lounge on Hall",
            "capEspaco": 20
          }
        ],
        "fornecedores": [],
        "convidados": [
          {
            "email": "mike@gmail.com",
            "nomeCompleto": "Teste Name",
            "cpf": "567815",
            "telefone": "789615",
            "departamento": null,
            "nomeEmpresa": null,
            "idOracle": null,
            "comprovanteVacinacao": null,
            "tipoUsuario": "interno",
            "cargoUsuario": null,
            "senhaUsuario": "pass1235"
          },
          {
            "email": "ferraz@gmail.com",
            "nomeCompleto": "Teste Name",
            "cpf": "567812",
            "telefone": "789612",
            "departamento": null,
            "nomeEmpresa": null,
            "idOracle": null,
            "comprovanteVacinacao": null,
            "tipoUsuario": "interno",
            "cargoUsuario": null,
            "senhaUsuario": "pass1232"
          }
        ]
      },
    ];
    this.eventPlot();
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
