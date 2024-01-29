export interface IUser {
  id: number;
  name: string;
  email: string;
}

export interface ILogin {
  email: string;
  password: string;
}

export interface IRegister {
  name: string;
  email: string;
  password: string;
}

export interface IRegisterResponse {
  message: string;
  user: IUser;
}

export interface ILoginResponse {
  message: string;
  access_token: string;
  user: IUser;
}
