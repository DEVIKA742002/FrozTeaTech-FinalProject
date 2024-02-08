import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Admin } from 'src/app/class/admin';
import { Menu } from 'src/app/class/menu';
import { ResturantmanagemntService } from 'src/app/service/resturantmanagemnt.service';

@Component({
  selector: 'app-add-menu',
  templateUrl: './add-menu.component.html',
  styleUrls: ['./add-menu.component.css']
})
export class AddMenuComponent {
  menu : Menu= new Menu(0,"",0,"");
  isEditable!: boolean;
  admin: Admin;
  constructor(private resturantmanagemntservice:ResturantmanagemntService,private router:Router,private activateRoute:ActivatedRoute) { }
  ngOnInit(): void 
  {
    this.activateRoute.paramMap.subscribe(()=>this.menu);
    this.activateRoute.paramMap.subscribe(()=>this.getMenuById());
    this.activateRoute.paramMap.subscribe(()=>this.admin=JSON.parse(sessionStorage.getItem("admin")))
    this.checkSessionAndNavigate();

  }
  onSubmit(){
    console.log(this.menu);
    if(this.isEditable){
      this.resturantmanagemntservice.updateMenu(this.menu).subscribe(data=>
        console.log(data))
        alert("The Menu Item is updated")
        this.router.navigateByUrl("/admin/menu");

    }
    else{
    this.resturantmanagemntservice.SaveMenu( this.menu ).subscribe(data =>
      console.log(data))
      alert("The Menu Item is Added")
      this.router.navigateByUrl("/admin/menu");
  }
}
getMenuById(){
  const item_id  = parseFloat(this.activateRoute.snapshot.paramMap.get("id"));
  
console.log(item_id);
if(item_id> 0)
{
  this.isEditable = true;
  this.resturantmanagemntservice.getmenubyid(item_id).subscribe(data=>{
    this.menu = data;
    console.log(this.menu)
  });
}

}
checkSessionAndNavigate() {
  if (!this.admin) {
    this.router.navigateByUrl("/admin/login");
  }
}

}
