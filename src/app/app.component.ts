import { CommonModule } from '@angular/common'; // ✅ Importa CommonModule
import { Component } from '@angular/core';
import { ProductComponent } from './products/components/grid/product.component';

@Component({
  selector: 'app-root',
  imports: [CommonModule, ProductComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title: string = 'Hola mundo Angular';
  enabled: boolean = true;
  courses: string[] = ['Angular', 'React', 'Spring Boot'];

  setEnabled(): void {
    this.enabled = !this.enabled;
  }
}
