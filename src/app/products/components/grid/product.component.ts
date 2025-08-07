import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/products';
import { ProductService } from '../../services/product.service';
import { FormComponent } from '../form/form.component';

@Component({
  selector: 'app-product',
  imports: [FormComponent],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css',
})
export class ProductComponent implements OnInit {
  products: Product[] = [];

  productSelected: Product = new Product();

  constructor(private service: ProductService) {}
  ngOnInit(): void {
    this.service.findAll().subscribe((products) => (this.products = products));
  }

  addProduct(product: Product) {
    if (product.id > 0) {
      this.service.update(product).subscribe((productUpdate) => {
        this.products = this.products.map((prod) => {
          if (prod.id == product.id) {
            return { ...productUpdate };
          }
          return prod;
        });
      });
    } else {
      this.service.create(product).subscribe((p) => {
        this.products = [...this.products, { ...p }];
      });
    }

    this.productSelected = new Product();
  }

  onUpdateProduct(product: Product) {
    this.productSelected = { ...product };
  }
  onDeleteProduct(id: number) {
    this.service.delete(id).subscribe((p) => {
      this.products = this.products.filter((product) => product.id !== p.id);
    });
  }
}
