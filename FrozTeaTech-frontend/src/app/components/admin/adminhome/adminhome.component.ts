import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Admin } from 'src/app/class/admin';

@Component({
  selector: 'app-adminhome',
  templateUrl: './adminhome.component.html',
  styleUrls: ['./adminhome.component.css']
})
export class AdminhomeComponent implements OnInit {
  admin:Admin
  constructor(private activateroute:ActivatedRoute,private route:Router) { }
  ngOnInit(): void 
  {
    this.activateroute.paramMap.subscribe(()=>this.admin=JSON.parse(sessionStorage.getItem("admin")))
    this.checkSessionAndNavigate();

  }
  logout() {
    if (sessionStorage.getItem("admin")) {
      sessionStorage.clear()
      localStorage.clear()
      alert("Logout Successfully")
      this.route.navigateByUrl("/admin/login")
    }
    else {
      alert("No user loged in")
    }
  }
  checkSessionAndNavigate() {
    if (!this.admin) {
      this.route.navigateByUrl("/admin/login");
    }
  }
  
}


