import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../class/customer';
import { Observable } from 'rxjs';
import { Admin } from '../class/admin';
import { Menu } from '../class/menu';
import { Order } from '../class/order';
import { Payment } from '../class/payment';

@Injectable({
  providedIn: 'root'
})
export class ResturantmanagemntService {
  //SignUP-Customer
  private newuserurl = "http://localhost:8080/customer/signup";
  private viewuserurl = "http://localhost:8080/customer/viewcustomer";
  private viewuserbyidurl = "http://localhost:8080/customer/id";
  private loginuserurl =  "http://localhost:8080/customer/login";
  private loginadminurl =  "http://localhost:8080/admin/login";
  private menuURL = "http://localhost:8080/restaurant/menu";
  private menuUpdateurl = "http://localhost:8080/restaurant/menu/update";
  private menusaveURl = "http://localhost:8080/restaurant/menu";
  private menudeleteURl = "http://localhost:8080/restaurant/menu";
  private viewuserbyusernameurl = "http://localhost:8080/customer/username";
  private customerdeleteURl = "http://localhost:8080/customer/delete";
  private viewallorderurl = "http://localhost:8080/restaurant/orders"; 
  private viewcustomerorderurl = "http://localhost:8080/restaurant/orders/customer"; 
  private deleteorderurl = "http://localhost:8080/restaurant/orders/delete"; 
  private viewpaybyoderidurl = "http://localhost:8080/payments/orderId";
  private viewpaybycustomeridurl = "http://localhost:8080/payments/customer";
  private viewallpayurl="http://localhost:8080/payments";
  private deletebypayidurl ="http://localhost:8080/payments/delete";
  private addorderurl ="http://localhost:8080/restaurant/orders/create";
  private addpaymenturl = "http://localhost:8080/payments";

  constructor(private http:HttpClient) { }


  //SignUP-Customer
  saveUser(customer: Customer): Observable<Customer> {
    const httpOptions = { 
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'auth-token',
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.post<Customer>(this.newuserurl, customer, httpOptions);
  }
  //Login-Customer
  getlogin(customer: Customer): Observable<Customer> {
    console.log(customer);
    return this.http.post<Customer>(`${this.loginuserurl}`, customer);
  }
  //GetUserById
  getuserbyid(uid: number):Observable<Customer>  {
    const uidUrl = this.viewuserbyidurl + "/" + uid;
    return this.http.get<Customer>(uidUrl);
  }

  updateUser(customer: Customer): Observable<Customer> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'auth-token',
        'Access-Control-Allow-Origin': '*'
      })
    };
    return this.http.put<Customer>(this.viewuserurl + `/${customer}`, customer, httpOptions);
  }

  //Login-Admin
  getadminlogin(admin: Admin): Observable<Admin> {
    console.log(admin);
    return this.http.post<Admin>(`${this.loginadminurl}`, admin);
  }
  //Update Menu
  updateMenu(menu:Menu):Observable<any>
  {
    const headers={'content-type':'application/json'};
    const body=JSON.stringify(menu);
    return this.http.put(this.menuUpdateurl,body,{'headers':headers});
  }
  //add menu
  SaveMenu(menu:Menu):Observable<any>
  {
    const httpOptions = {
      headers : new HttpHeaders({
          'Content-Type' : 'application/json',
          'Authorization' : 'auth-token',
          'Access-Control-Allow-Origin' : '*'
      })
    };
    return  this.http.post<Menu>(this.menusaveURl,menu,httpOptions);
  }
//GetMenuById
getmenubyid(uid: number):Observable<Menu>  {
  const uidUrl = this.menusaveURl + "/" + uid;
  return this.http.get<Menu>(uidUrl);
}
//get Menu by Fname
  getMenuByFname(fname:any):Observable<Menu>
  {
    const searchURL =   "http://localhost:8080/restaurant/menu/fname"+"/"+ fname;
    return  this.http.get<Menu>(searchURL);
  }
//getallmenu
  getAllMenu():Observable<any>
  {
   return this.http.get(this.menuURL);
  }
//delete Menu by ID
  deleteMenu(id: number) {
   
    const httpOptions = {
      headers : new HttpHeaders({
          'Content-Type' : 'application/json',
          'Authorization' : 'auth-token',
          'Access-Control-Allow-Origin' : '*'
      })
    };
    return  this.http.delete<Menu>(this.menudeleteURl+`/${id}`,httpOptions);
  }

//Get customer by username
getCustomerByUsername(username:String):Observable<Customer>  {
  const uidUrl = this.viewuserbyusernameurl + "/" + username;
  return this.http.get<Customer>(uidUrl);
}
//get all customers
getAllCustomer():Observable<any>
{
 return this.http.get(this.viewuserurl);
}


//delete Customer by id


deleteCustomer(id: number) {
   
  const httpOptions = {
    headers : new HttpHeaders({
        'Content-Type' : 'application/json',
        'Authorization' : 'auth-token',
        'Access-Control-Allow-Origin' : '*'
    })
  };
  return  this.http.delete<Customer>(this.customerdeleteURl+`/${id}`,httpOptions);
}
//GetOrderById
getorderbyid(uid: number):Observable<Order>  {
  const uidUrl = this.viewallorderurl + "/" + uid;
  return this.http.get<Order>(uidUrl);
}
//GetOrderByCustomerId
getorderbycustomerid(uid: number):Observable<Order>  {
  const uidUrl = this.viewcustomerorderurl + "/" + uid;
  return this.http.get<Order>(uidUrl);
}
//Get All Orders
getAllOrder():Observable<any>
{
 return this.http.get(this.viewallorderurl);
}

//delete Order by ID
deleteOrder(id: number): Observable<any> {
  const headers = new HttpHeaders({ 'Authorization': 'auth-token' }); 
  const url = `${this.deleteorderurl}/${id}`;
  return this.http.delete<Order>(url, { headers });
}
//GetpaymentById
getpaymentbyid(uid: number):Observable<Payment>  {
  const uidUrl = this.viewpaybyoderidurl + "/" + uid;
  return this.http.get<Payment>(uidUrl);
}

//Get All Payments
getAllPayments():Observable<any>
{
 return this.http.get(this.viewallpayurl);
}

//delete payment by ID
deletePayment(id: number) {
   
  const httpOptions = {
    headers : new HttpHeaders({
        'Content-Type' : 'application/json',
        'Authorization' : 'auth-token',
        'Access-Control-Allow-Origin' : '*'
    })
  };
  return  this.http.delete<Payment>(this.deletebypayidurl+`/${id}`,httpOptions);
}
//Order Menu
orderMenu(itemId:number,customerId:number,quan:any):Observable<any>
{
  const headers={'content-type':'application/json'};
  const body=JSON.stringify(quan);
  return this.http.post(this.addorderurl+"/"+itemId+"/"+customerId,body,{'headers':headers});
}
//GetpaymentBycustomerId
getpaymentcusyomerbyid(uid: number):Observable<Payment>  {
  const uidUrl = this.viewpaybycustomeridurl + "/" + uid;
  return this.http.get<Payment>(uidUrl);
}

//add payments code
addPayment(payment: any, orderId: number, customerId: number): Observable<any> {
  const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  const url = `${this.addpaymenturl}/${orderId}/${customerId}`;
  return this.http.post(url, payment, { headers });
}
}