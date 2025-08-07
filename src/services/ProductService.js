import axios from "axios";

const initProducts = [
  {
    id: 1,
    name: "Laptop Dell XPS 13",
    price: "999.99",
    description: "High-performance laptop with sleek design.",
  },
  {
    id: 2,
    name: "Sony WH-1000XM5 Headphones",
    price: "399.99",
    description: "Noise-canceling wireless headphones.",
  },
  {
    id: 3,
    name: "Apple Watch Series 8",
    price: "429.99",
    description: "Smartwatch with advanced health tracking.",
  },
  {
    id: 4,
    name: "Logitech MX Master 3 Mouse",
    price: "99.99",
    description: "Ergonomic wireless mouse with precision tracking.",
  },
  {
    id: 5,
    name: "Amazon Echo Dot (5th Gen)",
    price: "49.99",
    description: "Smart speaker with Alexa voice assistant.",
  },
  {
    id: 6,
    name: "Roku Streaming Stick 4K",
    price: "39.99",
    description: "4K streaming device with voice remote.",
  },
];
const baseUrl = "http://localhost:8080/products";
export const listProducts = () => {
  return initProducts;
};

export const findAll = async () => {
  try {
    const response = await axios.get(baseUrl);
    return response;
  } catch (error) {
    console.log(error);
  }
  return null;
};

export const create = async ({ name, description, price }) => {
  try {
    const response = await axios.post(baseUrl, { name, description, price });
    return response;
  } catch (error) {
    console.log(error);
  }
  return undefined;
};

export const update = async ({ id, name, description, price }) => {
  try {
    const response = axios.put(`${baseUrl}/${id}`, {
      name,
      description,
      price,
    });
    return response;
  } catch (error) {
    console.log(error);
  }
  return undefined;
};

export const remove = async (id) => {
  try {
    await axios.delete(`${baseUrl}/${id}`);
  } catch (error) {
    console.log(error);
  }
};
