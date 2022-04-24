import { Component, OnInit } from '@angular/core';
import { UserInfo } from 'src/app/layouts/dashboard/dashboard.component';
import { AuthService } from 'src/app/service/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css'],
})
export class UpdateProfileComponent implements OnInit {
  user: UserInfo;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.getUser();
  }

  updateData() {
    this.authService
      .updateUserProfile(this.user.id, this.user)
      .subscribe((data: any) => {
        this.authService.user$.next(data);
      });
  }

  getUser() {
    this.authService.fetchCurrentUser().subscribe((Response) => {
      this.user = Response;
      console.log(this.user);
    });
  }

  onSubmit() {
    this.updateData();
    Swal.fire({
      icon: 'success',
      title: 'Profil uspešno ažuriran!',
    });
  }
}
