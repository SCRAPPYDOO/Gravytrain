import { Component, OnInit } from '@angular/core';
import { User } from '../user/user';

import { UserService } from '../service/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  public userList: User[] = [];

  public searchBySurname: string = "";

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.onRefreshList();
  }

  public onRefreshList(): void {
    this.userList = this.userService.getUsers();
  }

  private remove(id: number) {
    for(let i=0; i<this.userList.length; i++) {
      if(this.userList[i].id == id && this.userList[i].active == false) {
        this.userList.splice(i);
      }
    }
  }

  public onDelete(id: number) {
    this.userService.delete(id);
    this.remove(id);
  }

  public onSearchBuSurname() {
    this.userList = this.userService.getUsersBySurname(this.searchBySurname);
  }

  public activeOptions: any = [true, false];
  
  public newUser: User = new User(null, "", "", "", null, false);

  public onSubmitNewUser() { 
    let user: User = this.newUser;
    this.userService.addUser(user);
    this.onRefreshList();
  }
}
