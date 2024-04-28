
import { LoginComponent } from './login/component/login/login.component';
import { PanierComponent } from './panier/component/panier/panier.component';
import { HomeComponent } from './home/component/home/home.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeaderComponent } from './shared/components/header/header.component';
import { PreferancesComponent } from './login/component/preferances/preferances.component';
import { ProfileComponent } from './login/component/profile/profile.component';
import { UpdateComponent } from './login/component/update/update.component';
import { ChatComponent } from './login/component/chat/chat.component';
import { EventComponent } from './eventt/event.component';
import { TicketComponent } from './eventt/ticket/ticket.component';
import { CalendrierComponent } from './eventt/calendrier/calendrier.component';
import { ScheduleComponent } from './certi/schedule/schedule.component';

import { CertificationComponent } from './certi/certification/certification.component';

const routes: Routes = [

  {path:"home" , component:HomeComponent},
  {path:"panier" , component:PanierComponent},
  {path:"profile" , component:ProfileComponent},
  {path:"update" , component:UpdateComponent},
  {path:"chat" , component:ChatComponent},
  {path:"" , component:ScheduleComponent},
  { path: 'event', component: EventComponent },
  {path:"preferances" , component:PreferancesComponent},
  {path :"ticket",  component:TicketComponent},
  {path :"calendrier" , component: CalendrierComponent},
  {path :"certification" , component: CertificationComponent},
  {path :"schedule" , component: ScheduleComponent},

  {path:"**",redirectTo:"home" ,pathMatch:"full"} 

  
  
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
