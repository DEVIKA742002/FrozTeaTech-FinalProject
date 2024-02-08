import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Admin } from 'src/app/class/admin';
import { Customer } from 'src/app/class/customer';
import { ResturantmanagemntService } from 'src/app/service/resturantmanagemnt.service';

@Component({
  selector: 'app-viewmenu',
  templateUrl: './viewmenu.component.html',
  styleUrls: ['./viewmenu.component.css']
})
export class ViewmenuComponent {
  menu:any;
  customer:Customer;
  hasSearchName: boolean;
    searchName: string;
    constructor(private resturantManageService:ResturantmanagemntService,public router:Router, private activeRoute:ActivatedRoute) { }
    ngOnInit(): void 
    {
      this.activeRoute.paramMap.subscribe(()=>this.getAllMenu());
      this.activeRoute.paramMap.subscribe(()=>this.customer=JSON.parse(sessionStorage.getItem("customer")));
      this.checkSessionAndNavigate();
    }
    getAllMenu()
    {
      this.hasSearchName = this.activeRoute.snapshot.paramMap.has("fname");
         if(this.hasSearchName)
         {
          this.searchName  = this.activeRoute.snapshot.paramMap.get("fname");
          console.log(this.searchName)
        this.resturantManageService.getMenuByFname(this.searchName).subscribe(data=>{
          console.log(data);
          this.menu= data;
        })
      }
      else{
      this.resturantManageService.getAllMenu().subscribe(data=>{
        console.log(data);
        this.menu=data;
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

   orderMenu(id:number)
  {
    this.router.navigateByUrl("customer/placeorder/"+id);
  
  }
  checkSessionAndNavigate() {
    if (!this.customer) {
      this.router.navigateByUrl("/customer/login");
    }

  }
}
