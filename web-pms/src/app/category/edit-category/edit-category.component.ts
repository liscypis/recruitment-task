import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Category } from 'src/app/models/Category';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-edit-category',
  templateUrl: './edit-category.component.html',
  styleUrls: ['./edit-category.component.css']
})
export class EditCategoryComponent implements OnInit {

  updateCategoryForm = new FormGroup({
    name: new FormControl('', [Validators.minLength(3), Validators.required]),
  });

  displayedColumns: string[] = ['id', 'name', 'edit'];
  dataSource!: MatTableDataSource<Category>;

  nameError: string = '';
  infoMessage: string = '';
  selectedCategoryID!: string;

  @ViewChild(MatSort) sort!: MatSort;

  constructor(private api: ApiService) { }

  ngOnInit(): void {
    this.api.getCategories().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      console.log(this.dataSource);
      this.dataSource.sort = this.sort;
    },
      err => {
        console.log(err);
      })
  }


  updateCategory(): void {
    const category = new Category();
    category.name = this.updateCategoryForm.value.name;
    this.api.updateCategory(category, this.selectedCategoryID).subscribe(() => {
      window.location.reload();
    },
      err => {
        console.log(err);
        if (err.error.message == "The name is already in use.")
          this.nameError = "Nazwa jest zajęta";
          this.updateCategoryForm.get('name')!.setErrors({ valid: false });
      })
  }
  onDeleteRowClicked(row: Category): void {
    this.api.deleteCategory(row.id).subscribe(()=>{
      window.location.reload();
    },
    err=>{
      console.log(err);
    })
  }
  onEditRowClicked(row: Category): void {
    this.selectedCategoryID = row.id;
    console.log(this.selectedCategoryID);
    this.updateCategoryForm.setValue({
      name: row.name
    })
  }
}
