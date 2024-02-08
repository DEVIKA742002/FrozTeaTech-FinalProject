import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Customer } from 'src/app/class/customer';
import { ResturantmanagemntService } from 'src/app/service/resturantmanagemnt.service';

@Component({
  selector: 'app-my-payment',
  templateUrl: './my-payment.component.html',
  styleUrls: ['./my-payment.component.css']
})
export class MyPaymentComponent implements OnInit {
  payment: any
  customer: Customer
  isEditable: boolean;
  p: number = 1;
  count: number = 5;

  constructor(private resturantmanagemntservice: ResturantmanagemntService, private router: Router, private activateRoute: ActivatedRoute) { }
  ngOnInit(): void {
    this.activateRoute.paramMap.subscribe(() => this.customer = JSON.parse(sessionStorage.getItem("customer")))
    this.activateRoute.paramMap.subscribe(() => this.getPaymentbyCustomerid())
    this.checkSessionAndNavigate();
  }
  getPaymentbyCustomerid() {
    const pay_id = this.customer.customerId;

    console.log(pay_id);
    if (pay_id > 0) {
      this.isEditable = true;
      this.resturantmanagemntservice.getpaymentcusyomerbyid(pay_id).subscribe(data => {
        this.payment = data;
        console.log(this.payment)
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
