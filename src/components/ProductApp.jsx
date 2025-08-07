import { useEffect, useState } from "react";
import { create, findAll, remove, update } from "../services/ProductService";
import { ProductForm } from "./ProductForm";
import { ProductGrid } from "./ProductGrid";

export const ProductApp = ({ title }) => {
  const [products, setProducts] = useState([]);
  const [productSelected, setProductSelected] = useState({
    id: 0,
    name: "",
    description: "",
    price: "",
  });

  const getProducts = async () => {
    const result = await findAll();
    setProducts(result.data._embedded.products);
  };

  useEffect(() => {
    getProducts();
  }, []);

  const handlerSaveProduct = async (product) => {
    console.log(product);

    if (product.id > 0) {
      const response = await update(product);
      setProducts(
        products.map((prod) => {
          if (prod.id == response.data.id) {
            return { ...response.data };
          }
          return prod;
        })
      );
    } else {
      const response = await create(product);
      setProducts([...products, { ...response.data }]);
    }
  };

  const handlerRemoveProduct = async (id) => {
    await remove(id);
    setProducts(products.filter((product) => product.id !== id));
  };

  const handlerProductSelected = (product) => {
    setProductSelected({ ...product });
  };

  return (
    <div className="container my-4">
      <div className="row">
        <div className="col-md-8">
          <h2>Lista de {title}</h2>
          {products.length > 0 ? (
            <ProductGrid
              products={products}
              handlerRemove={handlerRemoveProduct}
              handlerProductSelected={handlerProductSelected}
            />
          ) : (
            <div className="alert alert-warning">
              No se encontraron registros.
            </div>
          )}
        </div>
        <div className="col-md-4">
          <h2>Form de {title}</h2>
          <ProductForm
            handlerAdd={handlerSaveProduct}
            productSelected={productSelected}
          />
        </div>
      </div>
    </div>
  );
};
