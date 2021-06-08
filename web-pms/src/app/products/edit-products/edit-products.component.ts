import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Category } from 'src/app/models/Category';
import { ProductDetails } from 'src/app/models/ProductDetails';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-edit-products',
  templateUrl: './edit-products.component.html',
  styleUrls: ['./edit-products.component.css']
})
export class EditProductsComponent implements OnInit {

  displayedColumns: string[] = ['id', 'available', 'productName', 'netPrice', 'grossPrice', 'vat', 'category', 'description', 'edit'];
  dataSource!: MatTableDataSource<ProductDetails>;

  categories!: Array<Category>;
  selectedProductID!: string;
  selectedCategoryID!: string;
  selectedItem = false;

  selectedCategory!: string;
  selectedAvailable!: boolean;

  errorMessage!: string;


  updateProductForm = new FormGroup({
    name: new FormControl('', [Validators.minLength(4), Validators.required]),
    netPrice: new FormControl('', [Validators.pattern("^-?[0-9]\\d*(\\.\\d{1,2})?$"), Validators.minLength(1), Validators.maxLength(10), Validators.required]),
    vat: new FormControl('', [Validators.pattern("[0-9]+"), Validators.minLength(1), Validators.maxLength(2), Validators.required]),
    description: new FormControl('', [Validators.minLength(5), Validators.required]),
    cat: new FormControl('', Validators.required),
    ava: new FormControl('', Validators.required)
  });

  @ViewChild(MatSort) sort!: MatSort;

  constructor(private api: ApiService) { }

  ngOnInit(): void {
    this.getAllCategories();
    this.api.getProducts().subscribe(data => {
      this.dataSource = new MatTableDataSource(data)
      console.log(this.dataSource);
      this.dataSource.sort = this.sort;
    },
      err => {
        console.log(err);
      }
    )

  }

   getAllCategories(): void {
    this.api.getCategories().subscribe(data => {
      console.log(data);
      this.categories = data;
    },
      err => {
        console.log(err);
      })
  }

  changeCategory(id: string): void {
    this.selectedCategoryID = id;
  }

  changeAvailable(b: boolean): void {
    console.log(b);
  }

  onDeleteRowClicked(row: ProductDetails): void {
    const id = row.id;
    this.api.deleteProduct(id).subscribe(() => {
      window.location.reload();
    },
      err => {
        console.log(err);
      })
  }

  onEditRowClicked(row: ProductDetails): void {
    console.warn(row);
    this.selectedItem = true;
    this.selectedProductID = row.id;
    if (row.category != null)
      this.selectedCategoryID = row.category.id;

    this.updateProductForm.setValue({
      name: row.productName,
      vat: row.vat,
      description: row.description,
      netPrice: row.netPrice,
      cat: row.category == null ? "" : row.category.id,
      ava: row.available == true ? "1" : "0"
    })
  }

  updateProduct(): void {
    const product = new ProductDetails()
    product.available = this.updateProductForm.value.ava == "1" ? true : false;
    product.description = this.updateProductForm.value.description;
    product.vat = this.updateProductForm.value.vat;
    product.productName = this.updateProductForm.value.name;
    product.netPrice = this.updateProductForm.value.netPrice;

    this.api.updateProduct(product, this.selectedProductID, this.selectedCategoryID)
      .subscribe(() => {
        console.log("zaktualizowano");
        window.location.reload();
      },
        err => {
          console.log(err);
          if (err.error.message == "Product name is taken")
            this.errorMessage = "Nazwa produktu jest zajęta";
        })
  }

}
