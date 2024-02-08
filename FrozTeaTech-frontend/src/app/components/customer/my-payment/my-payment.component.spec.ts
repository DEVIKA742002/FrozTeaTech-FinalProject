import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyPaymentComponent } from './my-payment.component';

describe('MyPaymentComponent', () => {
  let component: MyPaymentComponent;
  let fixture: ComponentFixture<MyPaymentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MyPaymentComponent]
    });
    fixture = TestBed.createComponent(MyPaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
