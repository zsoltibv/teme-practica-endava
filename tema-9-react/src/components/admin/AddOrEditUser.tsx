import { useNavigate, useParams } from "react-router-dom";
import "../../styles/Admin.scss";
import { createUser, getUser, updateUser } from "../../services/UserService";
import { useEffect, useState } from "react";

const AddOrEditUser = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [form, setForm] = useState(null);

  useEffect(() => {
    if (id !== undefined) {
      (async () => {
        try {
          const product = await getUser(id);
          setForm(product);
        } catch (err) {
          console.log(err);
          navigate("/admin");
        }
      })();
    } else {
      setForm({
        id: 0,
        name: "",
        username: "",
        email: "",
        phone: "",
        website: "",
      });
    }
  }, [id]);

  const handleCreate = async () => {
    try {
      const { id } = await createUser(form);
      alert(`Created user with id: ${id}`);
      navigate(`/admin`);
    } catch (e) {
      console.warn(e);
    }
  };

  const handleUpdate = async () => {
    try {
      const { id } = await updateUser(form);
      alert(`Updated user with id: ${id}`);
      navigate(`/admin`);
    } catch (e) {
      console.warn(e);
    }
  };

  const updateField = ({ name, value }) => {
    setForm({
      ...form,
      [name]: value,
    });
  };

  if (form === null) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h1>Add a new user</h1>
      <div className="addOrEditUserForm">
        <form>
          <div className="formGroup">
            <label htmlFor="name">Id: </label>
            <input
              type="text"
              name="id"
              value={form.id}
              onChange={({ target }) => updateField(target)}
            />
          </div>
          <div className="formGroup">
            <label htmlFor="name">Name: </label>
            <input
              type="text"
              name="name"
              value={form.name}
              onChange={({ target }) => updateField(target)}
            />
          </div>
          <div className="formGroup">
            <label htmlFor="userName">Username: </label>
            <input
              type="text"
              name="username"
              value={form.username}
              onChange={({ target }) => updateField(target)}
            />
          </div>
          <div className="formGroup">
            <label htmlFor="email">Email: </label>
            <input
              type="text"
              name="email"
              value={form.email}
              onChange={({ target }) => updateField(target)}
            />
          </div>
          <div className="formGroup">
            <label htmlFor="phone">Phone: </label>
            <input
              type="text"
              name="phone"
              value={form.phone}
              onChange={({ target }) => updateField(target)}
            />
          </div>
          <div className="formGroup">
            <label htmlFor="website">Website: </label>
            <input
              type="text"
              name="website"
              value={form.website}
              onChange={({ target }) => updateField(target)}
            />
          </div>
        </form>
      </div>
      <div className="buttonsGroup addOrEditButtons">
        <button onClick={() => navigate(-1)}>Cancel</button>
        {id !== undefined ? (
          <button onClick={handleUpdate}>Update</button>
        ) : (
          <button onClick={handleCreate}>Save</button>
        )}
      </div>
    </div>
  );
};

export default AddOrEditUser;
