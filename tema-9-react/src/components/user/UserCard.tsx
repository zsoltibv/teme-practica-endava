import { Link } from "react-router-dom";
import "../../styles/User.scss";

const UserCard = ({ user }) => {
  return (
    <Link to={`/${user.id}`}>
      <div className="userCardStyles">
        <p>Id: {user.id}</p>
        <p>Name: {user.name}</p>
        <p>Email: {user.email}</p>
      </div>
    </Link>
  );
};

export default UserCard;
