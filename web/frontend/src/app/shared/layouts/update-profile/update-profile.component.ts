import { Component, OnInit } from '@angular/core';
import { UserInfo } from 'src/app/layouts/dashboard/dashboard.component';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {
  user:UserInfo;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.getUser();

  }

  updateData(value:any){
    let body = {
      name: value.name,
      lastname: value.lastname,
      address:value.address,
      username: value.username,
      email: value.username
    }
    console.log(body);
  }

  getUser(){
    this.authService.fetchCurrentUser().subscribe((Response)=> {
      this.user = Response;
      console.log(this.user);
    })
  }

}
