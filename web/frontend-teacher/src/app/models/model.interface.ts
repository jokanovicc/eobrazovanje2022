export interface User {
    id: number;
    name: string;
    address: string;
    jmbg: string;
    lastname: string;
    gender: string;
    username: string;
    email: string;
    role: string;
  }


export interface PerformanceExam{
  id: number;
  date: any;
  name:string;
  classroom:string;
  examPeriod:string;
}

export interface ExamWithStudentInfoResponse{
    examId:number;
    preExamDutyPoints:number;
    finalExamPoints:number;
    grade:number;
    status:string;
    studentName:string;
    studentIndex:string;
}
  