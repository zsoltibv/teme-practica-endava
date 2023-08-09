import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getUser } from "../../services/UserService";

const User = () => {
  const { id } = useParams();
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    (async () => {
      try {
        const user = await getUser(id);
        setUser(user);
        console.log(user);
      } catch (e) {
        console.warn(e);
        navigate("/", { replace: true, state: { id } });
      }
    })();
  }, [id]);

  if (user === null) {
    return <div>Loading...</div>;
  }

  const fullAddress = `${user.address.street}, ${user.address.suite}, ${user.address.city}, ${user.address.zipcode}`;
  const fullGeo = `${user.address.geo.lat}, ${user.address.geo.lng}`;
  const fullCompany = `${user.company.name}, ${user.company.catchPhrase}, ${user.company.bs}`;

  return (
    <div className="userPageStyles">
      <h1>Id: {user.id}</h1>
      <p>Name: {user.name}</p>
      <p>Username: {user.username}</p>
      <p>Email: {user.email}</p>
      <p>Adress: {fullAddress}</p>
      <p>Street: {user.address.street}</p>
      <p>Suite: {user.address.suite}</p>
      <p>City: {user.address.city}</p>
      <p>Zipcode: {user.address.zipcode}</p>
      <p>Geo: {fullGeo}</p>
      <p>Latitude: {user.address.geo.lat}</p>
      <p>Longitude: {user.address.geo.lng}</p>
      <p>Phone: {user.phone}</p>
      <p>Website: {user.website}</p>
      <p>Company: {fullCompany}</p>
      <p>Company Name: {user.company.name}</p>
      <p>Company Moto: {user.company.catchPhrase}</p>
      <p>Company Businsess: {user.company.bs}</p>
    </div>
  );
};

export default User;
