export class TransportRouteCreateModel {

  transport_id: number;
  route_path: string;
  pickup_time: string;
  drop_time: string;

  constructor(transport_id: number,
              route_path: string,
              pickup_time: string,
              drop_time: string) {
    this.transport_id = transport_id;
    this.route_path = route_path;
    this.pickup_time = pickup_time;
    this.drop_time = drop_time;
  }
}
