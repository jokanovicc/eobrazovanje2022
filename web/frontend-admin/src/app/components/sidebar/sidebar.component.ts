import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  status: boolean = false;

  constructor() {}

  ngOnInit(): void {}
  clickEvent() {
    console.log('hoho');
    this.status = !this.status;
  }

}
