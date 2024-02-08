import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminViewmenuComponent } from './admin-viewmenu.component';

describe('AdminViewmenuComponent', () => {
  let component: AdminViewmenuComponent;
  let fixture: ComponentFixture<AdminViewmenuComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminViewmenuComponent]
    });
    fixture = TestBed.createComponent(AdminViewmenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
