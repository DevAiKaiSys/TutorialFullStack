import { Injectable } from '@angular/core';

const TOKEN = 'token';
const USER = 'user';

@Injectable({
  providedIn: 'root',
})
export class UserStorageService {
  constructor() {}
  static saveToken(token: string): void {
    if (typeof window !== 'undefined') {
      window.localStorage.removeItem(TOKEN);
      window.localStorage.setItem(TOKEN, token);
    }
  }

  static saveUser(user: any): void {
    if (typeof window !== 'undefined') {
      window.localStorage.removeItem(USER);
      window.localStorage.setItem(USER, JSON.stringify(user));
    }
  }

  static getToken(): string {
    if (typeof window !== 'undefined') {
      return localStorage.getItem(TOKEN) ?? '';
    }
    return '';
  }

  static getUser(): any {
    if (typeof window !== 'undefined') {
      const userData = localStorage.getItem(USER);
      return userData ? JSON.parse(userData) : null;
    }
    return null;
  }

  static getUserId(): string {
    const user = this.getUser();
    if (user == null) return '';
    return user.id;
  }

  static getUserRole(): string {
    const user = this.getUser();
    if (user == null) return '';
    return user.role;
  }

  static isAdminLoggedIn(): boolean {
    if (typeof window !== 'undefined' && this.getToken() !== '') {
      const role = this.getUserRole();
      return role === 'ADMIN';
    }
    return false;
  }

  static isCustomerLoggedIn(): boolean {
    if (typeof window !== 'undefined' && this.getToken() !== '') {
      const role = this.getUserRole();
      return role === 'CUSTOMER';
    }
    return false;
  }

  static signOut(): void {
    if (typeof window !== 'undefined') {
      window.localStorage.removeItem(TOKEN);
      window.localStorage.removeItem(USER);
    }
  }
}
