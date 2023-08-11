import { useEffect, useState } from "react";
import UserCard from "./UserCard";
import { getUsers } from "../../services/UserService";

const UsersIndex = () => {
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

  return (
    <div className="usersGridStyles">
      {users.map((item) => (
        <UserCard key={item.id} user={item} />
      ))}
    </div>
  );
};

export default UsersIndex;
