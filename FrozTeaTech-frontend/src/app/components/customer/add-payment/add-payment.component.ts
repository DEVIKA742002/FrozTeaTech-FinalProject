import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from 'src/app/class/customer';
import { Order } from 'src/app/class/order';
import { ResturantmanagemntService } from 'src/app/service/resturantmanagemnt.service';

@Component({
  selector: 'app-add-payment',
  templateUrl: './add-payment.component.html',
  styleUrls: ['./add-payment.component.css']
})
export class AddPaymentComponent implements OnInit {
  customer:Customer
  order: Order;
  generatedOrderId: number;
  cust: number;
  payment: any = {
    nameOnCard: '',
    cardNumber: '',
    expYear: '',
    cvv: '',
    paidAmount: 0
  };
  constructor(private activeRoute: ActivatedRoute,private router: Router,private resturantmanagemntservice: ResturantmanagemntService) {}
  ngOnInit(): void {
    this.generatedOrderId = Number(localStorage.getItem("generatedOrderId"));
    this.getOrderByOrderId();
    this.activeRoute.paramMap.subscribe(()=>this.customer=JSON.parse(sessionStorage.getItem("customer")));
    this.cust=this.customer.customerId
    this.checkSessionAndNavigate();
  }
  
  onSubmit() {
    this.resturantmanagemntservice.addPayment(this.payment, this.generatedOrderId, this.cust).subscribe(
      () => {
        
        console.log(this.payment);
        console.log('Payment added successfully');
        alert("Payment added successfully")
        localStorage.clear()
        this.router.navigateByUrl("/customer/myorders")
      },
      (error) => {
        console.error('Error adding payment', error); 
      }
    );
  }

  getOrderByOrderId() {
    this.resturantmanagemntservice.getorderbyid(this.generatedOrderId).subscribe(
      (data) => { 
        console.log(data);
        this.order = data;
        this.payment.paidAmount = this.order.totalPrice; 
      },
      (error) => {
        console.error('Error fetching order', error);
        
      }
    );
  }
  checkSessionAndNavigate() {
    if (!this.customer) {
      this.router.navigateByUrl("/customer/login");
    }
  }
}
