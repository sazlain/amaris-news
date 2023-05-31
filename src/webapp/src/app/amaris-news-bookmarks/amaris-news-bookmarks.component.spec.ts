import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AmarisNewsBookmarksComponent } from './amaris-news-bookmarks.component';

describe('AmarisNewsBookmarksComponent', () => {
  let component: AmarisNewsBookmarksComponent;
  let fixture: ComponentFixture<AmarisNewsBookmarksComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AmarisNewsBookmarksComponent]
    });
    fixture = TestBed.createComponent(AmarisNewsBookmarksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
