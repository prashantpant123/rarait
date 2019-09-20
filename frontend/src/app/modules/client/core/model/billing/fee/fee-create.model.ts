export class FeeCreateModel {
  title: string;
  amount: number;
  taxable: boolean;
  tax_value: number;
  description: string;
  class_id: number;

  constructor(classId: number, title: string, amount: number, taxable: boolean, tax_value: number, description: string) {
    this.class_id = classId;
    this.title = title;
    this.amount = amount;
    this.taxable = taxable;
    this.tax_value = tax_value;
    this.description = description;
  }
}
