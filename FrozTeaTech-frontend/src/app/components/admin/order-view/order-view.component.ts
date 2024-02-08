import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Admin } from 'src/app/class/admin';
import { Order } from 'src/app/class/order';
import { ResturantmanagemntService } from 'src/app/service/resturantmanagemnt.service';

@Component({
  selector: 'app-order-view',
  templateUrl: './order-view.component.html',
  styleUrls: ['./order-view.component.css']
})
export class OrderViewComponent {
  order : any;
  hasSearchId: boolean;
  searchId: number;
  p: number = 1;
  count: number = 5;
  admin: Admin;
  constructor(private resturantManageService:ResturantmanagemntService,public router:Router, private activeRoute:ActivatedRoute) { }
  ngOnInit(): void 
      {
        this.activeRoute.paramMap.subscribe(()=>this.getAllOrders());
        this.activeRoute.paramMap.subscribe(()=>this.admin=JSON.parse(sessionStorage.getItem("admin")))
        this.checkSessionAndNavigate();
      }
      getAllOrders()
    {
      this.hasSearchId = this.activeRoute.snapshot.paramMap.has("orderId");
         if(this.hasSearchId)
         {this.searchId  = Number(this.activeRoute.snapshot.paramMap.get("orderId"));
          console.log(this.searchId)
          this.resturantManageService.getorderbyid(this.searchId).subscribe(data=>{
          console.log(data);
          this.order= data;
          })
        }
        else{
        this.resturantManageService.getAllOrder().subscribe(data=>{
          console.log(data);
          this.order=data;
        });
      }
    }
    deleteOrder(id:number):void{
      console.log(id);
      if(confirm("Do you want to delete ?")){
        this.resturantManageService.deleteOrder(id).subscribe(data=>{
          console.log(data);
          this.getAllOrders();
        })
      };
    }
    logout() {
      if (sessionStorage.getItem("admin")) {
        sessionStorage.clear()
        localStorage.clear()
        alert("Logout Successfully")
        this.router.navigateByUrl("/admin/login")
      }
      else {
        alert("No user loged in")
      }
    }
    checkSessionAndNavigate() {
      if (!this.admin) {
        this.router.navigateByUrl("/admin/login");
      }
    }
}
