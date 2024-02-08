import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AdminhomeComponent } from './components/admin/adminhome/adminhome.component';
import { AdminloginComponent } from './components/admin/admin-login/admin-login.component';
import { CustomerhomeComponent } from './components/customer/customerhome/customerhome.component';
import { CustomerloginComponent } from './components/customer/customer-login/customer-login.component';
import { CustomersignupComponent } from './components/customer/customer-sign-up/customer-sign-up.component';
import { AdminViewmenuComponent } from './components/admin/admin-viewmenu/admin-viewmenu.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { CustomerViewComponent } from './components/admin/customer-view/customer-view.component';
import { OrderViewComponent } from './components/admin/order-view/order-view.component';
import { PaymentViewComponent } from './components/admin/payment-view/payment-view.component';
import { AddMenuComponent } from './components/admin/add-menu/add-menu.component';
import { AddOrderComponent } from './components/customer/add-order/add-order.component';
import { ViewmenuComponent } from './components/customer/viewmenu/viewmenu.component';
import { MyProfileComponent } from './components/customer/my-profile/my-profile.component';
import { MyOrderComponent } from './components/customer/my-order/my-order.component';
import { MyPaymentComponent } from './components/customer/my-payment/my-payment.component';
import { AddPaymentComponent } from './components/customer/add-payment/add-payment.component';
import { PaymentOptionsComponent } from './components/customer/payment-options/payment-options.component';


const routes: Routes = [
  {path:'',component:WelcomeComponent},
  {path: 'admin/home', component:AdminhomeComponent },
  {path:'admin/menu',component:AdminViewmenuComponent},
  {path:'admin/login',component:AdminloginComponent},
  {path:'admin/customer/veiwall',component:CustomerViewComponent},          
  {path:'admin/order/veiwall',component:OrderViewComponent},
  {path:'admin/payment/veiwall',component:PaymentViewComponent},
  {path:'admin/menu/add',component:AddMenuComponent},
  {path:'admin/updateMenu/:id',component:AddMenuComponent},
  {path: 'customer/home', component:CustomerhomeComponent },
  {path:'customer/signup',component:CustomersignupComponent},
  {path:'customer/login',component:CustomerloginComponent},
  {path:'customer/placeorder/:id',component:AddOrderComponent},
  {path:'customer/menu',component:ViewmenuComponent},
  {path:'customer/myprofile',component:MyProfileComponent},
  {path:'customer/myorders',component:MyOrderComponent},
  {path:'customer/mypayment',component:MyPaymentComponent},
  {path:'customer/addpayment',component:AddPaymentComponent},
  {path:'customer/paymentoptions',component:PaymentOptionsComponent}];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
