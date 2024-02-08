import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Admin } from 'src/app/class/admin';
import { ResturantmanagemntService } from 'src/app/service/resturantmanagemnt.service';

@Component({
  selector: 'app-customer-view',
  templateUrl: './customer-view.component.html',
  styleUrls: ['./customer-view.component.css']
})
export class CustomerViewComponent implements OnInit{
customer : any;
hasSearchName: boolean;
searchName: string;
  admin: Admin;
  p: number = 1;
count: number = 5;
constructor(private resturantManageService:ResturantmanagemntService,public router:Router, private activeRoute:ActivatedRoute) { }
ngOnInit(): void 
    {
      this.activeRoute.paramMap.subscribe(()=>this.getAllCustomer());
      this.activeRoute.paramMap.subscribe(()=>this.admin=JSON.parse(sessionStorage.getItem("admin")))
      this.checkSessionAndNavigate();
    }

    getAllCustomer()
  {
    this.hasSearchName = this.activeRoute.snapshot.paramMap.has("username");
       if(this.hasSearchName)
       {this.searchName  = this.activeRoute.snapshot.paramMap.get("username");
        console.log(this.searchName)
        this.resturantManageService.getCustomerByUsername(this.searchName).subscribe(data=>{
        console.log(data);
        this.customer= data;
        })
      }
      else{
      this.resturantManageService.getAllCustomer().subscribe(data=>{
        console.log(data);
        this.customer=data;
      });
    }
    }

    deleteCustomer(id:number):void{
      console.log(id);
      if(confirm("Do you want to delete ?")){
        this.resturantManageService.deleteCustomer(id).subscribe(data=>{
          console.log(data);
          this.getAllCustomer();
        })
      };
    }
    updateCustomer(id:number)
  {
    this.router.navigateByUrl("/updateCustomer/"+id);
  
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
