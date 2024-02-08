import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewmenuComponent } from './viewmenu.component';

describe('ViewmenuComponent', () => {
  let component: ViewmenuComponent;
  let fixture: ComponentFixture<ViewmenuComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ViewmenuComponent]
    });
    fixture = TestBed.createComponent(ViewmenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
