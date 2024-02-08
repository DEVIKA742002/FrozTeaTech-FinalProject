import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Customer } from 'src/app/class/customer';
import { ResturantmanagemntService } from 'src/app/service/resturantmanagemnt.service';
@Component({
  selector: 'app-customerhome',
  templateUrl: './customerhome.component.html',
  styleUrls: ['./customerhome.component.css']
})
export class CustomerhomeComponent implements OnInit {
  customer:Customer;
  constructor(private resturantManageService:ResturantmanagemntService,public router:Router, private activeRoute:ActivatedRoute) { }
    ngOnInit(): void 
    {
      this.activeRoute.paramMap.subscribe(()=>this.customer=JSON.parse(sessionStorage.getItem("customer")))
      this.checkSessionAndNavigate();
    }

    logout() {
      if (sessionStorage.getItem("customer")) {
        sessionStorage.clear()
        localStorage.clear()
        alert("Logout Successfully")
        this.router.navigateByUrl("/customer/login")
      }
      else {
        alert("No user loged in")
      }
     }
     checkSessionAndNavigate() {
      if (!this.customer) {
        this.router.navigateByUrl("/customer/login");
      }
    }
}
