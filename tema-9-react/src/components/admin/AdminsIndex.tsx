import { useEffect, useState } from "react";
import UserCard from "./UserCard";
import { deleteUser, getUsers } from "../../services/UserService";
import "../../styles/Admin.scss";
import { Link } from "react-router-dom";

const AdminsIndex = () => {
  const [users, setUsers] = useState(null);
  useEffect(() => {
    (async () => {
      const data = await getUsers();
      setUsers(data);
    })();
  }, []);

  if (users === null) {
    return <div>Loading ....</div>;
  }

  async function removeUser(id) {
    try {
      await deleteUser(id);
      alert(`Deleted user with id: ${id}`);
      setUsers((prevUsers) => prevUsers.filter((user) => user.id !== id));
    } catch (error) {
      console.error("Error deleting user:", error);
    }
  }

  return (
    <>
      <Link to={`/admin/new`} className="buttonsGroup createNewButton">
        <button>Create new</button>
      </Link>
      <div className="usersGridStyles">
        {users.map((item) => (
          <UserCard key={item.id} user={item} removeUser={removeUser} />
        ))}
      </div>
    </>
  );
};

export default AdminsIndex;
