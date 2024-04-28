import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CertificationComponent } from './certification/certification.component';
import { FormsModule } from '@angular/forms'; 
import { HttpClientModule } from '@angular/common/http';
import { ScheduleComponent } from './schedule/schedule.component';
import { GoogleMapsModule } from '@angular/google-maps';
import { MatCard } from '@angular/material/card';
import { MatCardModule } from '@angular/material/card';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { SharedModule } from '../shared/shared.module';




@NgModule({
  declarations: [
    CertificationComponent,
    ScheduleComponent,
  ],
  imports: [
    CommonModule,
    FormsModule, // Add FormsModule here
    HttpClientModule,
    GoogleMapsModule,
    MatCardModule,
    MatDatepickerModule,
    SharedModule,
  ],
  exports:[
    CertificationComponent,
    ScheduleComponent,
  ]
})
export class CertiModule { }
