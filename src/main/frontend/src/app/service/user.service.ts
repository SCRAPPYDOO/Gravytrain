import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { User } from '../user/user';
import { UserListComponent } from "../user-list/user-list.component";

export const REST_URL: string = "http://localhost:8080";
export const REST_URL_USER: string = REST_URL + "/user";
export const REST_URL_USER_BY_SURNAME: string = REST_URL + "/userBySurname";

interface UserResponse {
  id, title, firstname, surname: string;
  dateOfBirth: Date; 
  active: boolean;
}

@Injectable()
export class UserService {

  constructor(private http: HttpClient) { }

  private options = {
    headers: new HttpHeaders({"Content-Type": "application/json"})

  }

  public getUsersBySurname(surname: string): User[] {
    let users: User[] = [];
    if(surname == null || surname == "") { 
      return this.getUsers();
    }
    this.http.get<UserResponse[]>(REST_URL_USER_BY_SURNAME + "/" + surname).subscribe(
      data => {
        for(let i=0; i<data.length; i++) {
          let user = data[i];
          console.log(user);
          users.push(new User(<number>user.id, <string>user.title, <string>user.firstname, <string>user.surname, <Date>user.dateOfBirth, <boolean>user.active));
      }
    });
    return users;
  }

  public getUsers(): User[] {
    let users: User[] = [];
    this.http.get<UserResponse[]>(REST_URL_USER).subscribe(
      data => {
        for(let i=0; i<data.length; i++) {
          let user = data[i];
          console.log(user);
          users.push(new User(<number>user.id, <string>user.title, <string>user.firstname, <string>user.surname, <Date>user.dateOfBirth, <boolean>user.active));
      }
    });
    return users;
  }

  public addUser(user: User): void {
    let body = JSON.stringify(user);

    this.http.post(REST_URL_USER, body, this.options).subscribe(
      res => {
        console.log(res);
      },
      err => {
        console.log("Error occured" + err);
      });
  }

  public delete(id: number) {
    return this.http.delete(REST_URL_USER + "/" + id, this.options).subscribe(
      res => {
        console.log(res);
      },
      err => {
        console.log("Error occured: " + err.message);
      });
  }

  public updateUser(user: User): void {
    let body = JSON.stringify(user);

    this.http.put(REST_URL_USER, body, this.options).subscribe(
      res => {
        console.log(res);
      },
      err => {
        console.log("Error occured" + err);
      });
  }
}
