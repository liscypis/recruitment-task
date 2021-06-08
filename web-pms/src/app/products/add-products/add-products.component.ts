import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Category } from 'src/app/models/Category';
import { ProductDetails } from 'src/app/models/ProductDetails';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-add-products',
  templateUrl: './add-products.component.html',
  styleUrls: ['./add-products.component.css']
})
export class AddProductsComponent implements OnInit {

  addProductForm = new FormGroup({
    name: new FormControl('', [Validators.minLength(4), Validators.required]),
    netPrice: new FormControl('', [Validators.pattern("^-?[0-9]\\d*(\\.\\d{1,2})?$"), Validators.minLength(1), Validators.maxLength(10), Validators.required]),
    vat: new FormControl('', [Validators.pattern("[0-9]+"), Validators.minLength(1), Validators.maxLength(2), Validators.required]),
    description: new FormControl('', [Validators.minLength(5), Validators.required]),
    cat: new FormControl('', Validators.required)
  });

  categories!: Array<Category>;
  selectedCategory!: any;
  errorMessage = '';
  infoMessage = '';

  constructor(private api: ApiService) { }

  ngOnInit(): void {
    this.getAllCategories();

  }

  addProduct(): void {
   this.clearMessages();
    const product = new ProductDetails();
    product.available = false;
    product.description = this.addProductForm.value.description;
    product.vat = this.addProductForm.value.vat;
    product.productName = this.addProductForm.value.name;
    product.netPrice = this.addProductForm.value.netPrice;
    console.log(product.description);
    console.log(this.addProductForm.value.description);
    this.api.addProduct(product, this.addProductForm.value.cat).subscribe(() => {
      this.infoMessage = "Dodano produkt";
    },
      err => {
        console.log(err);
        this.checkError(err.error.message);
      })
  }

  checkError(error: string): void {
    if (error == "Invalid categoryId")
      this.errorMessage = "Błędne id kategorii"
    if (error == "Product name is taken")
      this.errorMessage = "Nazwa prodktu jest zajęta";
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

  clearMessages(): void {
    this.infoMessage = '';
    this.errorMessage = '';
  }
}
