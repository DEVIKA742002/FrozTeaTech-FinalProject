import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Customer } from 'src/app/class/customer';
import { ResturantmanagemntService } from 'src/app/service/resturantmanagemnt.service';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {
  username: any
  customer: Customer;
  isEditable: boolean;
  ngOnInit(): void {
    this.activeRoute.paramMap.subscribe(() => this.customer = JSON.parse(sessionStorage.getItem("customer")))
    this.checkSessionAndNavigate();
  }
  constructor(private resturantManageService: ResturantmanagemntService, public router: Router, private activeRoute: ActivatedRoute) { }

  getCustomerById() {
    const username = this.customer.username;

    console.log(username);
    if (username != null) {
      this.isEditable = true;
      this.resturantManageService.getCustomerByUsername("username").subscribe(data => {
        this.customer = data;
        console.log(this.customer)
      });
    }
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