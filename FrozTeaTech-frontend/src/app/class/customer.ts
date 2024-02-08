export class Customer {
        username: string ;
        customerName: string;
        customerPhone: string;
        userpassword: string;
        customerId:number;
        email : string;
        constructor(customerId:number, customerName:string, customerPhone:string, username:string, userpassword:string , email : string) {
                this.customerId = customerId;
                this.customerName = customerName;
                this.customerPhone = customerPhone;
                this.username = username;
                this.userpassword = userpassword;
                this.email= email;
        }
      
}
 