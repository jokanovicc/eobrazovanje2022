import { ExamStatus } from '../models/exam-status.enum';

export default class ExamStatusHelper {
  static getExamStatusName(examStatus: ExamStatus) {
    switch (examStatus) {
      case ExamStatus.REGISTERED:
        return 'Prijavljen';
      case ExamStatus.PASSED:
        return 'Položen';
      case ExamStatus.FAILED:
        return 'Nepoložen';
      case ExamStatus.LISTENING:
        return 'Pohađa';
      case ExamStatus.PRELIMINARY:
        return 'Preliminarno';
      case ExamStatus.FINALLY:
        return 'Ocenjeno';
      case ExamStatus.UNRATED:
        return 'Neocenjeno';
    }
  }
}
