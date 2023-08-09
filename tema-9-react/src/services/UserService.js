const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

export const getUsers = async () => {
  const response = await fetch(`${API_BASE_URL}/users`);
  if (response.ok) {
    return await response.json();
  }
  throw new Error("something went wrong");
};

export const getUser = async (id) => {
  const response = await fetch(`${API_BASE_URL}/users/${id}`);
  if (response.ok) {
    return await response.json();
  }
  throw new Error("something went wrong");
};
