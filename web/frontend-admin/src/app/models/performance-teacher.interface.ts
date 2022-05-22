
export interface PerformanceTeacher{
    id: number;
    courseName: string;
    courseSylabus: string;
    schoolYear: number;
    courseTeacher: TeacherPerformance[];

}


export interface TeacherPerformance{
    id: number;
    name:string;
    teacherRole: string;
    username: string;
    

}