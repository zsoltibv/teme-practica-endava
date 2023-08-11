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

export const deleteUser = async (id) => {
  const response = await fetch(`${API_BASE_URL}/users/${id}`, {
    method: "DELETE",
  });
  if (response.ok) {
    return await response.json();
  }
  throw new Error("something went wrong");
};

export const createUser = async (user) => {
  const response = await fetch(`${API_BASE_URL}/users`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  });

  if (response.ok) {
    return await response.json();
  }
  throw new Error("something went wrong");
};

export const updateUser = async (user) => {
  const response = await fetch(`${API_BASE_URL}/users/${user.id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  });

  if (response.ok) {
    return await response.json();
  }
  throw new Error("something went wrong");
};