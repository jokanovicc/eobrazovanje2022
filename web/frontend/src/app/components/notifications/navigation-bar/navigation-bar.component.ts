

import { ChangeDetectorRef, Component, OnInit } from '@angular/core';    
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
    
@Component({    
    selector:'app-navigation-bar',    
    templateUrl:'./navigation-bar.component.html',    
    styleUrls:['./navigation-bar.component.css']    
    }) 
export class NavigationBarComponent implements OnInit{

    token:string|null;

    constructor(private authService: AuthService, private changeDetector: ChangeDetectorRef,private router: Router){
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    }

    ngOnInit(): void {
        this.token = this.authService.getToken();
        this.changeDetector.detectChanges();

        //ili je string ili je null
        
    }

    logoutButton(){
        this.authService.removeToken();
        this.token = null;
        this.changeDetector.detectChanges();
    }

}