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
  public data: Array<any> = [
    {
      "idEvento": 9,
      "titulo": "Evento1",
      "descricao": "Desc 1",
      "dataInicio": "17/09/2021 08:00",
      "dataEncerramento": "17/09/2021 12:00",
      "tipoEvento": "fechado",
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
      "idEvento": 10,
      "titulo": "Evento1",
      "descricao": "Desc 1",
      "dataInicio": "17/09/2021 08:00",
      "dataEncerramento": "17/09/2021 12:00",
      "tipoEvento": "fechado",
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
  ]  
  events: CalendarEvent[] = [
    {
      start: setHours(setMinutes(new Date('2021-10-04'), 45), 12),
      end: setHours(new Date('2021-10-04'), 18),
      title: 'Evento de teste',
      color: colors.red,
    },
    {
      start: setHours(setMinutes(new Date('2021-10-04'), 45), 12),
      end: setHours(new Date('2021-10-04'), 18),
      title: 'Evento de teste1',
      color: colors.blue,
    },
    {
      start: setHours(setMinutes(new Date('2021-10-06'), 45), 12),
      end: setHours(new Date('2021-10-06'), 18),
      title: 'Evento de teste2',
      color: colors.blue,
    },
    {
      start: setHours(setMinutes(new Date('2021-12-05'), 45), 12),
      end: setHours(new Date('2021-12-05'), 18),
      title: 'Evento de teste3',
      color: colors.red,
    },
  ];

  activeDayIsOpen: boolean = false;

  constructor(private modal: NgbModal, public formBuilder: FormBuilder,) {}

  ngOnInit(): void {
    this.eventPlot();
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
  eventPlot() {
    for (var element of this.data) {
      this.elemento = element;
      var newEvent = {
        start: setHours(new Date(this.eventDate(this.elemento.dataInicio)), this.eventHour(this.elemento.dataInicio)),
        end: setHours(new Date(this.eventDate(this.elemento.dataEncerramento)), this.eventHour(this.elemento.dataEncerramento)),
        title: this.elemento.titulo,
        color: this.colorEvent(this.elemento),
      };
      this.events.push(newEvent);
      console.log(this.elemento);
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
}
