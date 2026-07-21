import { useNavigate, useLocation } from "react-router-dom";
import { useForm } from "react-hook-form";

import type { LoginRequest } from "../../types/auth";

import useAuth from "../../hooks/useAuth";

export default function Login() {
  const navigate = useNavigate();
  const location = useLocation();

  const { login, user } = useAuth();

  const {
    register,
    handleSubmit,
    formState: {
      errors,
      isSubmitting,
    },
  } = useForm<LoginRequest>();

  const from =
    (location.state as any)?.from?.pathname;

  const onSubmit = async (
    data: LoginRequest
  ) => {
    try {
      await login(data);

      // Since login updates context asynchronously,
      // use localStorage values immediately after login.
      const role = localStorage.getItem("role");

      if (from) {
        navigate(from, {
          replace: true,
        });
        return;
      }

      switch (role) {
        case "ADMIN":
          navigate("/admin");
          break;

        case "FACULTY":
          navigate("/faculty");
          break;

        case "STUDENT":
          navigate("/student");
          break;

        default:
          navigate("/");
      }
    } catch (err: any) {
      alert(
        err?.response?.data?.message ??
          "Invalid Credentials"
      );
    }
  };

  return (
    <div className="flex min-h-screen items-center justify-center bg-slate-100">

      <div className="w-full max-w-md rounded-xl bg-white p-8 shadow-lg">

        <h1 className="mb-8 text-center text-3xl font-bold">
          AI LMS
        </h1>

        <form
          onSubmit={handleSubmit(onSubmit)}
          className="space-y-5"
        >
          <div>
            <label>Email</label>

            <input
              type="email"
              {...register("email", {
                required: "Email is required",
              })}
              className="mt-2 w-full rounded-lg border p-3"
            />

            {errors.email && (
              <p className="mt-1 text-sm text-red-500">
                {errors.email.message}
              </p>
            )}
          </div>

          <div>
            <label>Password</label>

            <input
              type="password"
              {...register("password", {
                required: "Password is required",
              })}
              className="mt-2 w-full rounded-lg border p-3"
            />

            {errors.password && (
              <p className="mt-1 text-sm text-red-500">
                {errors.password.message}
              </p>
            )}
          </div>

          <button
            disabled={isSubmitting}
            className="w-full rounded-lg bg-blue-600 p-3 text-white transition hover:bg-blue-700 disabled:bg-gray-400"
          >
            {isSubmitting
              ? "Signing In..."
              : "Login"}
          </button>

        </form>

      </div>

    </div>
  );
}