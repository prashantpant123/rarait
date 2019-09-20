export class TransportCreateModel {

  name: string;
  number_plate: string;

  constructor(name: string, number_plate: string) {
    this.name = name;
    this.number_plate = number_plate;
  }
}
