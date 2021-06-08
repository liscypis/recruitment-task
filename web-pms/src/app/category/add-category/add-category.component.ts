import { SplitInterpolation } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Category } from 'src/app/models/Category';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {

  categoryForm = new FormGroup({
    categoryName: new FormControl('', [Validators.minLength(3), Validators.required]),
  });
  errorMessage: string = '';
  infoMessage: string = '';

  constructor(private api: ApiService) { }

  ngOnInit(): void {

  }

  addCategory():void {
    this.errorMessage = '';
    this.infoMessage = '';
    const category = new Category();
    category.name = this.categoryForm.value.categoryName;
    this.api.addCategory(category).subscribe(() =>{
      this.infoMessage = "Dodano kategorie";
    },
    err => {
      console.log(err);
      if(err.error.message == "The name is already in use.")
      this.errorMessage = "Nazwa jest zajęta";
     }
    );
  }

}
