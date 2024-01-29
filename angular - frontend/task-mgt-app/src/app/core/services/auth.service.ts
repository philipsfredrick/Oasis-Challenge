import { Injectable } from '@angular/core';
import { TokenService } from './token.service';
import {
  ILogin,
  ILoginResponse,
  IRegister,
  IRegisterResponse,
  IUser,
} from '../models/auth.model';
import { apiEndpoint } from '../constants/constants';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { Router } from '@angular/router';
import { IResponse } from '../models/todo.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(
    private http: HttpClient,
    private tokenService: TokenService,
    private router: Router
  ) {}

  onLogin(data: ILogin) {
    return this.http
      .post<ILoginResponse>(`${apiEndpoint.AuthEndpoint.login}`, data)
      .pipe(
        map((response) => {
          if (response) {
            this.tokenService.setToken(response.access_token);
          }
          return response;
        })
      );
  }

  onRegister(data: IRegister) {
    return this.http
      .post<IRegisterResponse>(`${apiEndpoint.AuthEndpoint.register}`, data)
      .pipe(
        map((response) => {
          if (response) {
            this.router.navigate(['']);
          }
          return response;
        })
      );
  }

  onLogout() {
    this.http.post(`${apiEndpoint.AuthEndpoint.logout}`, '').subscribe({
      next: (response) => {
        this.tokenService.removeToken();
      },
    });
  }

  getUser(): Observable<IResponse<IUser>> {
    return this.http.get<IResponse<IUser>>(
      `${apiEndpoint.AuthEndpoint.loggedUser}`
    );
  }

  updateUser(data: IUser): Observable<IResponse<IUser>> {
    return this.http.patch<IResponse<IUser>>(
      `${apiEndpoint.AuthEndpoint.loggedUser}`,
      data
    );
  }

  deleteUser(id: number): Observable<IResponse<IUser>> {
    return this.http.delete<IResponse<IUser>>(
      `${apiEndpoint.AuthEndpoint.loggedUser}/${id}`
    );
  }
}
