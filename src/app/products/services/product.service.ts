import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Product } from '../models/products';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private products: Product[] = [
    {
      id: 1,
      name: 'Laptop',
      price: 1200,
      description: 'High performance laptop',
    },
    {
      id: 2,
      name: 'Smartphone',
      price: 800,
      description: 'Latest model smartphone',
    },
    {
      id: 3,
      name: 'Headphones',
      price: 150,
      description: 'Noise-cancelling headphones',
    },
  ];

  private url: string = 'http://localhost:8080/products';
  constructor(private http: HttpClient) {}

  findAll(): Observable<Product[]> {
    // return of(this.products);
    return this.http
      .get<Product[]>(this.url)
      .pipe(map((response: any) => response._embedded.products as Product[]));
  }
  create(product: Product): Observable<Product> {
    return this.http.post<Product>(this.url, product);
  }
  update(product: Product): Observable<Product> {
    return this.http.put<Product>(`${this.url}/${product.id}`, product);
  }
  delete(id: number): Observable<Product> {
    return this.http.delete<Product>(`${this.url}/${id}`);
  }
}

// export interface Product {
//   id?: number;
//   name: string;
//   price: number;
//   description: string;
// }

////////////////////////////////////////////////////////////////////////////////////////////////
// private apiUrl = 'http://localhost:8080/products';

//   constructor(private http: HttpClient) {}
// getProducts(): Observable<Product[]> {
//     return this.http.get<Product[]>(this.apiUrl);
//   }

//   getProduct(id: number): Observable<void> {
//     return this.http.get<void>(`${this.apiUrl}/${id}`);
//   }

//   addProduct(product: Product): Observable<Product> {
//     return this.http.post<Product>(this.apiUrl, product);
//   }

//   deleteProduct(id: number): Observable<void> {
//     return this.http.delete<void>(`${this.apiUrl}/${id}`);
//   }
