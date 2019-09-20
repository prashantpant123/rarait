import {Injectable} from '@angular/core';
import {ReadOnlyModel} from './read-only.model';

@Injectable()
export class ReadOnlyService {

  title: string;
  private model: ReadOnlyModel[] = [];

  setTitle(text: string) {
    this.title = text;
  }

  add(model: ReadOnlyModel) {
    this.model.push(model);
  }

  getAll() {
    return this.model;
  }

  getTitle(): string {
    console.log('In model ' + this.title);
    return this.title;
  }
}
