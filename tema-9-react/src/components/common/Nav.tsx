import "../../styles/Nav.scss";
import { NavLink } from "react-router-dom";

const Nav = () => {
  return (
    <nav className="navStyles">
      <div className="logoArea">
        <a href="/">
          <img src="src\assets\react.svg" alt="" />
        </a>
      </div>
      <div className="navItems">
        <NavLink to="/" end>
          Users
        </NavLink>
        <NavLink to="/admin">Admin</NavLink>
      </div>
    </nav>
  );
};

export default Nav;
