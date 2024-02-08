import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule} from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule , } from '@angular/material/sidenav';
import { AdminhomeComponent } from './components/admin/adminhome/adminhome.component';
import { AdminloginComponent } from './components/admin/admin-login/admin-login.component';
import { CustomerloginComponent } from './components/customer/customer-login/customer-login.component';
import { CustomersignupComponent } from './components/customer/customer-sign-up/customer-sign-up.component';
import { CustomerhomeComponent } from './components/customer/customerhome/customerhome.component';
import { AdminViewmenuComponent } from './components/admin/admin-viewmenu/admin-viewmenu.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { CustomerViewComponent } from './components/admin/customer-view/customer-view.component';
import { OrderViewComponent } from './components/admin/order-view/order-view.component';
import { PaymentViewComponent } from './components/admin/payment-view/payment-view.component';
import { AddMenuComponent } from './components/admin/add-menu/add-menu.component';
import { AddOrderComponent } from './components/customer/add-order/add-order.component';
import { ViewmenuComponent } from './components/customer/viewmenu/viewmenu.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgxPaginationModule } from 'ngx-pagination';
import { AddPaymentComponent } from './components/customer/add-payment/add-payment.component';
import { MyProfileComponent } from './components/customer/my-profile/my-profile.component';
import { MyOrderComponent } from './components/customer/my-order/my-order.component';
import { MyPaymentComponent } from './components/customer/my-payment/my-payment.component';
import { PaymentOptionsComponent } from './components/customer/payment-options/payment-options.component';



@NgModule({
  declarations: [
    AppComponent,
    
    WelcomeComponent,
    AdminhomeComponent,
    AdminloginComponent,
    AdminViewmenuComponent,
    CustomerloginComponent,
    CustomersignupComponent,
    CustomerhomeComponent,
    CustomerViewComponent,
    OrderViewComponent,
    PaymentViewComponent,
    AddMenuComponent,
    AddOrderComponent,
    ViewmenuComponent,
    AddPaymentComponent,
    MyProfileComponent,
    MyOrderComponent,
    MyPaymentComponent,
    PaymentOptionsComponent,


    
 
  
 
    
    
    
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatSidenavModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    BrowserAnimationsModule,
    NgxPaginationModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
