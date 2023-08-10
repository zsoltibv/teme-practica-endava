import "../../styles/User.scss";
import "../../styles/Admin.scss";

const UserCard = ({ user, removeUser }) => {

  return (
    <div className="userCardStyles">
      <p>Id: {user.id}</p>
      <p>Name: {user.name}</p>
      <p>Email: {user.email}</p>
      <div className="buttonsGroup">
        <button>Edit</button>
        <button onClick={() => removeUser(user.id)}>Delete</button>
      </div>
    </div>
  );
};

export default UserCard;
