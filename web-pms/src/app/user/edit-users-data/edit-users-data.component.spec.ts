import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditUsersDataComponent } from './edit-users-data.component';

describe('EditUsersDataComponent', () => {
  let component: EditUsersDataComponent;
  let fixture: ComponentFixture<EditUsersDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditUsersDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditUsersDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
