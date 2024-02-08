import { Customer } from "./customer";
import { Menu } from "./menu";
export class Order {
    totalPrice: number;
    quantity: number;
    customer: Customer;
    orderDate: Date;
    orderTime: string;
    menu: Menu;
    status: string;
     orderId: number; 

    constructor( orderId: number ,customer: Customer,orderDate: Date,orderTime: Date,menu: Menu,quantity: number,status: string){}
   
}
