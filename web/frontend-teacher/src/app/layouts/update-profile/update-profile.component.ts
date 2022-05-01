import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/model.interface';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css'],
})
export class UpdateProfileComponent implements OnInit {
  user: User;

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
    alert("Uspesno azurirano!")

  }
}
