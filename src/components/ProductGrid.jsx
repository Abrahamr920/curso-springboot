import { ProductDetail } from "./ProductDetail";

export const ProductGrid = ({
  handlerProductSelected,
  handlerRemove,
  products = [],
}) => {
  return (
    <table className="table table-striped table-hover table-bordered">
      <thead className="table-dark">
        <tr>
          <th>Id</th>
          <th>Name</th>
          <th>Description</th>
          <th>Price</th>
          <th>Update</th>
          <th>Remove</th>
        </tr>
      </thead>
      <tbody>
        {products.map((product) => {
          return (
            <ProductDetail
              handlerRemove={handlerRemove}
              handlerProductSelected={handlerProductSelected}
              product={product}
              key={product.id}
            />
          );
        })}
      </tbody>
    </table>
  );
};
