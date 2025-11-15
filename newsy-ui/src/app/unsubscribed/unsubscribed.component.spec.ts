import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnsubscribedComponent } from './unsubscribed.component';

describe('UnsubscribedComponent', () => {
  let component: UnsubscribedComponent;
  let fixture: ComponentFixture<UnsubscribedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UnsubscribedComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UnsubscribedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
