import {
  createContext,
  useCallback,
  useEffect,
  useMemo,
  useState,
} from "react";

import type { ReactNode } from "react";

import authService from "../services/authService";

import {
  clearAuth,
  getName,
  getRole,
  getToken,
  saveAuth,
} from "../utils/token";

import type {
  AuthContextType,
  AuthUser,
  LoginRequest,
} from "../types/auth";

export const AuthContext =
  createContext<AuthContextType | null>(null);

interface Props {
  children: ReactNode;
}

export function AuthProvider({
  children,
}: Props) {
  const [user, setUser] =
    useState<AuthUser | null>(null);

  const [loading, setLoading] =
    useState(true);

  useEffect(() => {
    const token = getToken();

    if (token) {
      setUser({
        token,
        name: getName() ?? "",
        role: getRole() as AuthUser["role"],
      });
    }

    setLoading(false);
  }, []);

  const login = useCallback(
    async (credentials: LoginRequest) => {
      const response =
        await authService.login(credentials);

      saveAuth(
        response.token,
        response.role,
        response.name
      );

      setUser(response);
    },
    []
  );

  const logout = useCallback(() => {
    clearAuth();
    setUser(null);
  }, []);

  const value = useMemo(
    () => ({
      user,
      loading,
      login,
      logout,
      isAuthenticated: !!user,
    }),
    [user, loading, login, logout]
  );

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
}