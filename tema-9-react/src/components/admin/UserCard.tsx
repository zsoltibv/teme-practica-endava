import { Link } from "react-router-dom";
import "../../styles/Admin.scss";

const UserCard = ({ user, removeUser }) => {
  return (
    <div className="userCardStyles">
      <p>Id: {user.id}</p>
      <p>Name: {user.name}</p>
      <p>Email: {user.email}</p>
      <div className="buttonsGroup">
        <Link to={`/admin/${user.id}`}>
          <button>Edit</button>
        </Link>
        <button onClick={() => removeUser(user.id)}>Delete</button>
      </div>
    </div>
  );
};

export default UserCard;
