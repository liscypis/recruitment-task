import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ProductDetails } from '../models/ProductDetails';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  displayedColumns: string[] = ['productName', 'netPrice', 'grossPrice', 'description'];
  dataSource!: MatTableDataSource<ProductDetails>;

  @ViewChild(MatSort) sort!: MatSort;


  constructor(private api: ApiService) { }

  ngOnInit(): void {
    this.api.getAvailableProducts().subscribe(data => {
      this.dataSource = new MatTableDataSource(data)
      console.log(this.dataSource);
      this.dataSource.sort = this.sort;
    },
      err => {
        console.log(err);
      }
    )
  }

}
