import {PageModel} from '../../../../../../shared/model/page.model';
import {GradeDetailModel} from './grade-detail.model';

export class GradePageModel extends PageModel {

  content: GradeDetailModel[] = [];
}
