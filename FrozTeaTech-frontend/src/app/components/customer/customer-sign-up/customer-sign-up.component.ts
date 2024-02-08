import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Customer } from 'src/app/class/customer';
import { ResturantmanagemntService } from 'src/app/service/resturantmanagemnt.service';

@Component({
  selector: 'app-customer-sign-up',
  templateUrl: './customer-sign-up.component.html',
  styleUrls: ['./customer-sign-up.component.css']
})
export class CustomersignupComponent implements OnInit {
  customer:Customer=new Customer(0,"","","","","")
  isEditable!: boolean;
  constructor(public resturantmanagmentservice:ResturantmanagemntService,public route:Router,public activateRoute: ActivatedRoute){}

  ngOnInit(): void {
    this.activateRoute.paramMap.subscribe(()=>this.getuserbyid())
  }
  getuserbyid(): void{
    const uid  = parseFloat(this.activateRoute.snapshot.paramMap.get("uid"));
    console.log(uid)
    if(uid>0)
    {
      this.isEditable=true;
      this.resturantmanagmentservice.getuserbyid(uid).subscribe(data=>{
        this.customer=data;
        console.log(this.customer);
      });
    }
  }
  SaveUsers(){
    if(this.isEditable){
      this.resturantmanagmentservice.updateUser(this.customer).subscribe(data=>{
        alert("Successfully updated "+this.customer.username)
        sessionStorage.clear()
        localStorage.clear()
        this.route.navigateByUrl("/customer/login")});
    }
    else{
      
    this.resturantmanagmentservice.saveUser(this.customer).subscribe(data =>{
      alert("Successfully Register ")
      this.route.navigateByUrl("/customer/login")
    },
    error => alert("enter the user data / change the user name")
      );
      
  }

}
onSubmit() {
  this.route.navigateByUrl("/welcomeuser");

}
}

