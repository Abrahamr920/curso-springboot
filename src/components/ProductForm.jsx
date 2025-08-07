import { useEffect, useState } from "react";

const initialDataForm = {
  id: 0,
  name: "",
  description: "",
  price: "",
};

export const ProductForm = ({ productSelected, handlerAdd: handlerSave }) => {
  const [form, setForm] = useState(initialDataForm);
  const { id, name, description, price } = form;
  useEffect(() => {
    setForm(productSelected);
  }, [productSelected]);

  return (
    <form
      action=""
      onSubmit={(event) => {
        event.preventDefault();
        if (!name || !description || !price) {
          alert("Complete los datos del Formulario.");
        }
        handlerSave(form);
        setForm(initialDataForm);
      }}
    >
      <div>
        <input
          type="number"
          readOnly
          className="form-control my-3 w-75"
          name="id"
          placeholder="Id"
          value={id}
          onChange={(event) =>
            setForm({
              ...form,
              id: event.target.value,
            })
          }
        />
      </div>
      <div>
        <input
          type="text"
          className="form-control my-3 w-75"
          name="name"
          placeholder="Name"
          value={name}
          onChange={(event) =>
            setForm({
              ...form,
              name: event.target.value,
            })
          }
        />
      </div>

      <div>
        <input
          type="text"
          className="form-control my-3 w-75"
          name="description"
          placeholder="Description"
          value={description}
          onChange={(event) =>
            setForm({
              ...form,
              description: event.target.value,
            })
          }
        />
      </div>
      <div>
        {" "}
        <input
          type="number"
          className="form-control my-3 w-75"
          name="price"
          placeholder="Price"
          value={price}
          onChange={(event) =>
            setForm({
              ...form,
              price: event.target.value,
            })
          }
        />
      </div>
      <button type="submit" className="btn btn-primary">
        {productSelected.id > 0 ? "Update" : "Save"}
      </button>
    </form>
  );
};
