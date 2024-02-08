import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminloginComponent } from './admin-login.component';

describe('AdminLoginComponent', () => {
  let component: AdminloginComponent;
  let fixture: ComponentFixture<AdminloginComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminloginComponent]
    });
    fixture = TestBed.createComponent(AdminloginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
