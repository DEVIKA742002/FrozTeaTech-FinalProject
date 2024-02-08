import { TestBed } from '@angular/core/testing';

import { ResturantmanagemntService } from './resturantmanagemnt.service';

describe('ResturantmanagemntService', () => {
  let service: ResturantmanagemntService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResturantmanagemntService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
