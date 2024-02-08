import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomersignupComponent } from './customer-sign-up.component';

describe('CustomerSignUpComponent', () => {
  let component: CustomersignupComponent;
  let fixture: ComponentFixture<CustomersignupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CustomersignupComponent]
    });
    fixture = TestBed.createComponent(CustomersignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
