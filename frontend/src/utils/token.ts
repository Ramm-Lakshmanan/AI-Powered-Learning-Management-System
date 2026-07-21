const TOKEN = "token";
const ROLE = "role";
const NAME = "name";

export const saveAuth = (
  token: string,
  role: string,
  name: string
) => {
  localStorage.setItem(TOKEN, token);
  localStorage.setItem(ROLE, role);
  localStorage.setItem(NAME, name);
};

export const clearAuth = () => {
  localStorage.removeItem(TOKEN);
  localStorage.removeItem(ROLE);
  localStorage.removeItem(NAME);
};

export const getToken = () =>
  localStorage.getItem(TOKEN);

export const getRole = () =>
  localStorage.getItem(ROLE);

export const getName = () =>
  localStorage.getItem(NAME);

export const isLoggedIn = () =>
  !!localStorage.getItem(TOKEN);