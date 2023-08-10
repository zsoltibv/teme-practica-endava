import {
  Navigate,
  Route,
  BrowserRouter as Router,
  Routes,
} from "react-router-dom";
import "./styles/App.scss";
import Nav from "./components/common/Nav";
import UsersIndex from "./components/user/UsersIndex";
import User from "./components/user/User";
import AdminsIndex from "./components/admin/AdminsIndex";

function App() {
  return (
    <div className="appStyles">
      <Router>
        <Nav />
        <div className="container">
          <Routes>
            <Route path="/" element={<UsersIndex />} />
            <Route path=":id" element={<User />} />
            <Route path="*" element={<Navigate to="/" />} />

            <Route path="/admin" element={<AdminsIndex />} />
          </Routes>
        </div>
      </Router>
    </div>
  );
}

export default App;
