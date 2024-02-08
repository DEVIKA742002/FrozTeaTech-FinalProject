import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from 'src/app/class/customer';
import { Menu } from 'src/app/class/menu';
import { Order } from 'src/app/class/order';
import { ResturantmanagemntService } from 'src/app/service/resturantmanagemnt.service';


@Component({
  selector: 'app-add-order',
  templateUrl: './add-order.component.html',
  styleUrls: ['./add-order.component.css']
})
export class AddOrderComponent implements OnInit {
  customerId: number;
  itemId: number;
  quantity: number;
  customer: Customer;
  order: Order;
  menu: Menu = new Menu(0, "", 0, "");
  quan: any = 0;
  isEditable: boolean;

  constructor(private activeRoute: ActivatedRoute, private http: HttpClient, public router: Router, private resturantmanagemntservice: ResturantmanagemntService) { }

  ngOnInit(): void {
    this.activeRoute.paramMap.subscribe(() => this.customer = JSON.parse(sessionStorage.getItem("customer")));
    this.getMenuById();
  }
  getMenuById() {
    const item_id = parseFloat(this.activeRoute.snapshot.paramMap.get("id"));

    console.log(item_id);
    if (item_id > 0) {
      this.isEditable = true;
      this.resturantmanagemntservice.getmenubyid(item_id).subscribe(data => {
        this.menu = data;
        console.log(this.menu)
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
  placeOrder() {
    const itemId = this.menu.item_id;
    const customerId = this.customer.customerId;

    const quan = { quantity: this.quantity };

    this.resturantmanagemntservice.orderMenu(itemId, customerId, quan).subscribe(

      (response) => {

        console.log('Order placed successfully', response);
        alert("Order Successfully Placed")

        localStorage.setItem("generatedOrderId", response.orderId);

        this.router.navigateByUrl("/customer/paymentoptions")
      },
      (error) => {

        console.error('Error placing order', error);
      }
    );
  }
  checkSessionAndNavigate() {
    if (!this.customer) {
      this.router.navigateByUrl("/customer/login");
    }
  }
}