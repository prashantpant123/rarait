export class Logout {

  private auth_token: string;

  constructor(auth_token: string) {
    this.auth_token = auth_token;
  }
}
