import api from "../api/axios";

import type {
  LoginRequest,
  LoginResponse,
} from "../types/auth";

class AuthService {
  async login(
    credentials: LoginRequest
  ): Promise<LoginResponse> {
    const { data } =
      await api.post<LoginResponse>(
        "/auth/login",
        credentials
      );

    return data;
  }
}

export default new AuthService();