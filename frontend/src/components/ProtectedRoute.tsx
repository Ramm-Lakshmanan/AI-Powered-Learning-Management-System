import type { ReactNode } from "react";

import {
  Navigate,
  useLocation,
} from "react-router-dom";

import useAuth from "../hooks/useAuth";

interface Props {
  children: ReactNode;
  roles?: string[];
}

export default function ProtectedRoute({
  children,
  roles,
}: Props) {
  const {
    loading,
    isAuthenticated,
    user,
  } = useAuth();

  const location = useLocation();

  if (loading) {
    return (
      <div className="flex h-screen items-center justify-center text-lg font-semibold">
        Loading...
      </div>
    );
  }

  if (!isAuthenticated) {
    return (
      <Navigate
        to="/login"
        replace
        state={{
          from: location,
        }}
      />
    );
  }

  if (
    roles &&
    user &&
    !roles.includes(user.role)
  ) {
    return (
      <Navigate
        to="/"
        replace
      />
    );
  }

  return <>{children}</>;
}