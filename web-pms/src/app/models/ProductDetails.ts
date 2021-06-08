import { Category } from "./Category";

export class ProductDetails {
    id!: string;
    productName!: string;
    netPrice!: Number;
    grossPrice!: Number;
    description!: string;
    vat!: Number;
    category!: Category;
    available!: boolean;
    
}