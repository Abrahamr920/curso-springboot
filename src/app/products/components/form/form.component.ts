import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Product } from '../../models/products';

@Component({
  selector: 'product-form',
  imports: [FormsModule, CommonModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css',
})
export class FormComponent {
  @Input() product: Product = new Product();

  @Output() newProductEvent = new EventEmitter();

  onSubmit(productForm: NgForm): void {
    if (productForm.valid) {
      this.newProductEvent.emit(this.product);
    }
    productForm.reset();
    productForm.resetForm();
  }
}
