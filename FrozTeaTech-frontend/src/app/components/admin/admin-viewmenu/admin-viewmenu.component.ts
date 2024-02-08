import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Admin } from 'src/app/class/admin';
import { ResturantmanagemntService } from 'src/app/service/resturantmanagemnt.service';

@Component({
  selector: 'app-admin-viewmenu',
  templateUrl: './admin-viewmenu.component.html',
  styleUrls: ['./admin-viewmenu.component.css']
})

export class AdminViewmenuComponent {
  menu:any;
  hasSearchName: boolean;
    searchName: string;
  admin: Admin;
  p: number = 1;
count: number = 4;
    constructor(private resturantManageService:ResturantmanagemntService,public router:Router, private activeRoute:ActivatedRoute) { }
    ngOnInit(): void 
    {
      this.activeRoute.paramMap.subscribe(()=>this.getAllMenu());
      this.activeRoute.paramMap.subscribe(()=>this.admin=JSON.parse(sessionStorage.getItem("admin")))
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
  
    deleteMenu(id:number):void{
      console.log(id);
      if(confirm("Do you want to delete ?")){
        this.resturantManageService.deleteMenu(id).subscribe(data=>{
          console.log(data);
          this.getAllMenu();
        })
      };
    }
  
    updateMenu(id:number)
  {
    this.router.navigateByUrl("/admin/updateMenu/"+id);
  
  }
  addMenu():void
{
  this.router.navigateByUrl("/admin/menu/add");
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
  
