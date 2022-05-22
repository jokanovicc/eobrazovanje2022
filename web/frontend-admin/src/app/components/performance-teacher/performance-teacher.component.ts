import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PerformanceTeacher, TeacherPerformance } from 'src/app/models/performance-teacher.interface';
import { PerformanceService } from 'src/app/services/performance.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-performance-teacher',
  templateUrl: './performance-teacher.component.html',
  styleUrls: ['./performance-teacher.component.css']
})
export class PerformanceTeacherComponent implements OnInit {

  public id: any;
  public performance: PerformanceTeacher;

  constructor(private performanceService: PerformanceService, private route:ActivatedRoute, private router:Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.fetchById();
  }

  fetchById(){
    this.performanceService.getById(this.id).subscribe((Response) => {
      this.performance = Response;
      console.log(Response);
    })

  }

  remove(teachId : any){
    alert("klik")
    this.performanceService.deleteTeacherFromPerformance(this.id,teachId);
    Swal.fire('Uspešno!','Nastavnik je uklonjen sa predmeta.','success').then(() => location.reload())

  
  }
  

  addNew(id:any){
    this.router.navigate(['/add-to-performance/' + this.id]);

  }
  



}
