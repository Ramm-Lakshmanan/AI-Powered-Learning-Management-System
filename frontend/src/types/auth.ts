export type UserRole =
  | "ADMIN"
  | "FACULTY"
  | "STUDENT";

export interface LoginRequest {
  email: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  name: string;
  role: UserRole;
}

export interface AuthUser {
  token: string;
  name: string;
  role: UserRole;
}

export interface AuthContextType {
  user: AuthUser | null;

  loading: boolean;

  isAuthenticated: boolean;

  login: (
    data: LoginRequest
  ) => Promise<void>;

  logout: () => void;
}