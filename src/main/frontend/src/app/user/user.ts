export class User {
    constructor(public id: number, public title: string, public firstname: string, public surname: string, public dateOfBirth: Date, public active: boolean) { }

    public toString(): string {
        return this.id + " " + this.title + " " + this.firstname + " " + this.surname + " " + this.dateOfBirth + " " + this.active;
    }
}