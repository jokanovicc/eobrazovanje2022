import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-student-profile',
  templateUrl: './student-profile.component.html',
  styleUrls: ['./student-profile.component.css'],
})
export class StudentProfileComponent implements OnInit {
  id: number;
  constructor(private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
  }

  getDocuments() {
    this.router.navigate(['/cardboard/' + this.id]);
  }

  getPayments() {
    this.router.navigate(['/payment/' + this.id]);
  }
}
