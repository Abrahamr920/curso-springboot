import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { ProductApp } from "./components/productApp";
// import "./index.css";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <ProductApp title={"Productos"} />
  </StrictMode>
);
