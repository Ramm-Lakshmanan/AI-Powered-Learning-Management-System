import { Routes, Route, Navigate } from "react-router-dom";

import Login from "./pages/auth/Login"

import ProtectedRoute from "./components/ProtectedRoute";

function AdminDashboard() {
  return <h1>Admin Dashboard</h1>;
}

function FacultyDashboard() {
  return <h1>Faculty Dashboard</h1>;
}

function StudentDashboard() {
  return <h1>Student Dashboard</h1>;
}

export default function App() {
  return (
    <Routes>

      <Route
        path="/"
        element={
          <Navigate
            to="/login"
            replace
          />
        }
      />

      <Route
        path="/login"
        element={<Login />}
      />

      <Route
        path="/admin"
        element={
          <ProtectedRoute
            roles={["ADMIN"]}
          >
            <AdminDashboard />
          </ProtectedRoute>
        }
      />

      <Route
        path="/faculty"
        element={
          <ProtectedRoute
            roles={["FACULTY"]}
          >
            <FacultyDashboard />
          </ProtectedRoute>
        }
      />

      <Route
        path="/student"
        element={
          <ProtectedRoute
            roles={["STUDENT"]}
          >
            <StudentDashboard />
          </ProtectedRoute>
        }
      />

      <Route
        path="*"
        element={
          <Navigate
            to="/login"
            replace
          />
        }
      />

    </Routes>
  );
}