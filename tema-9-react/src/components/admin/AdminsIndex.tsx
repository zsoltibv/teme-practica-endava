import { useEffect, useState } from "react";
import UserCard from "./UserCard";
import { deleteUser, getUsers } from "../../services/UserService";

const AdminsIndex = () => {
  const [users, setUsers] = useState(null);
  useEffect(() => {
    (async () => {
      const data = await getUsers();
      setUsers(data);
      console.log(data);
    })();
  }, []);

  if (users === null) {
    return <div>Loading ....</div>;
  }

  async function removeUser(id) {
    try {
      await deleteUser(id);
      setUsers((prevUsers) => prevUsers.filter((user) => user.id !== id));
    } catch (error) {
      console.error("Error deleting user:", error);
    }
  }

  return (
    <div className="usersGridStyles">
      {users.map((item) => (
        <UserCard key={item.id} user={item} removeUser={removeUser}/>
      ))}
    </div>
  );
};

export default AdminsIndex;
