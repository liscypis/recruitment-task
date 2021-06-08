import { Category } from "./Category";

export class ProductDetails {
    id!: string;
    iproductNamed!: string;
    netPrice!: Number;
    grossPrice!: Number;
    description!: string;
    vat!: Number;
    category!: Category;
    available!: boolean;
    
}