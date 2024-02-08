import { Customer } from "./customer";

export class Payment {
    paymentId: number;
    totalPrice: number;
    orderId: number;
    nameOnCard: string;
    cardNumber: string;
    expYear: string;
    cvv: number;
    paidDate: Date;
    paidAmount: number;
    customer: Customer;

    constructor(
        paymentId: number,totalPrice: number,orderId: number,nameOnCard: string,cardNumber: string,expYear: string,cvv: number,paidDate: Date,paidAmount: number,customer: Customer) {};
}
