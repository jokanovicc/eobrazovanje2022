import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';



export interface UserInfo{
  name: string,
  address:string,
  jmbg:string,
  lastname:string,
  gender:string,
  username:string,
  email:string
}

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  user:UserInfo;
  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.getUser();
  }

  getUser(){
    this.authService.fetchCurrentUser().subscribe((Response)=> {
      this.user = Response;
      console.log(this.user);
    })
  }
}
