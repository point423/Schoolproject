1.POST http://localhost:8080/api/courses
{
  "code": "CS1012",
  "title": "计算机科学导论",
  "instructor": {
    "id": "T001",
    "name": "张教授",
    "email": "zhang@example.edu.cn"
  },
  "schedule": {
    "dayOfWeek": "MONDAY",
    "startTime": "08:00",
    "endTime": "10:00",
    "expectedAttendance": 50
  },
  "capacity": 60
}

{
    "code": 201,
    "message": "Course created successfully",
    "data": {
        "id": "9bc81d12-81f8-40a8-8909-6f33d953cc7b",
        "code": "CS1012",
        "title": "计算机科学导论",
        "instructor": {
            "id": "T001",
            "name": "张教授",
            "email": "zhang@example.edu.cn"
        },
        "schedule": {
            "dayOfWeek": "MONDAY",
            "startTime": "08:00",
            "endTime": "10:00",
            "expectedAttendance": 50
        },
        "capacity": 60,
        "enrolled": 0
    }
}

POST http://localhost:8080/api/courses
{
  "code": "MA1011",
  "title": "高等数学",
  "instructor": {
    "id": "T002",
    "name": "李教授",
    "email": "li@example.edu.cn"
  },
  "schedule": {
    "dayOfWeek": "TUESDAY",
    "startTime": "10:30",
    "endTime": "12:30",
    "expectedAttendance": 40
  },
  "capacity": 50
}

{
    "code": 201,
    "message": "Course created successfully",
    "data": {
        "id": "c0699497-0da9-43da-a8ed-d2d2f8fd30f5",
        "code": "MA1011",
        "title": "高等数学",
        "instructor": {
            "id": "T002",
            "name": "李教授",
            "email": "li@example.edu.cn"
        },
        "schedule": {
            "dayOfWeek": "TUESDAY",
            "startTime": "10:30",
            "endTime": "12:30",
            "expectedAttendance": 40
        },
        "capacity": 50,
        "enrolled": 0
    }
}

POST http://localhost:8080/api/courses
{
  "code": "EN1013",
  "title": "大学英语",
  "instructor": {
    "id": "T003",
    "name": "王老师",
    "email": "wang@example.edu.cn"
  },
  "schedule": {
    "dayOfWeek": "WEDNESDAY",
    "startTime": "14:00",
    "endTime": "16:00",
    "expectedAttendance": 30
  },
  "capacity": 40
}

{
    "code": 201,
    "message": "Course created successfully",
    "data": {
        "id": "9465fe5e-d8f6-40d8-a04d-3176211175d6",
        "code": "EN1013",
        "title": "大学英语",
        "instructor": {
            "id": "T003",
            "name": "王老师",
            "email": "wang@example.edu.cn"
        },
        "schedule": {
            "dayOfWeek": "WEDNESDAY",
            "startTime": "14:00",
            "endTime": "16:00",
            "expectedAttendance": 30
        },
        "capacity": 40,
        "enrolled": 0
    }
}

GET http://localhost:8080/api/courses
{
    "code": 200,
    "message": "Success",
    "data": [
        {
            "id": "c0699497-0da9-43da-a8ed-d2d2f8fd30f5",
            "code": "MA1011",
            "title": "高等数学",
            "instructor": {
                "id": "T002",
                "name": "李教授",
                "email": "li@example.edu.cn"
            },
            "schedule": {
                "dayOfWeek": "TUESDAY",
                "startTime": "10:30",
                "endTime": "12:30",
                "expectedAttendance": 40
            },
            "capacity": 50,
            "enrolled": 0
        },
        {
            "id": "a6229001-0ba9-4f94-a889-16aab0985bfb",
            "code": "MA101",
            "title": "高等数学",
            "instructor": {
                "id": "T002",
                "name": "李教授",
                "email": "li@example.edu.cn"
            },
            "schedule": {
                "dayOfWeek": "TUESDAY",
                "startTime": "10:30",
                "endTime": "12:30",
                "expectedAttendance": 40
            },
            "capacity": 50,
            "enrolled": 0
        },
        {
            "id": "a8bcd85d-232d-40a4-a7a4-108726bdf5fd",
            "code": "CS101",
            "title": "计算机科学导论",
            "instructor": {
                "id": "T001",
                "name": "张教授",
                "email": "zhang@example.edu.cn"
            },
            "schedule": {
                "dayOfWeek": "MONDAY",
                "startTime": "08:00",
                "endTime": "10:00",
                "expectedAttendance": 50
            },
            "capacity": 60,
            "enrolled": 0
        },
        {
            "id": "9465fe5e-d8f6-40d8-a04d-3176211175d6",
            "code": "EN1013",
            "title": "大学英语",
            "instructor": {
                "id": "T003",
                "name": "王老师",
                "email": "wang@example.edu.cn"
            },
            "schedule": {
                "dayOfWeek": "WEDNESDAY",
                "startTime": "14:00",
                "endTime": "16:00",
                "expectedAttendance": 30
            },
            "capacity": 40,
            "enrolled": 0
        },
        {
            "id": "96c31e04-7c76-420f-8045-c0f6ae1b03d5",
            "code": "EN101",
            "title": "大学英语",
            "instructor": {
                "id": "T003",
                "name": "王老师",
                "email": "wang@example.edu.cn"
            },
            "schedule": {
                "dayOfWeek": "WEDNESDAY",
                "startTime": "14:00",
                "endTime": "16:00",
                "expectedAttendance": 30
            },
            "capacity": 40,
            "enrolled": 0
        }
    ]
}

GET http://localhost:8080/api/courses/a8bcd85d-232d-40a4-a7a4-108726bdf5fd
{
    "code": 200,
    "message": "Success",
    "data": {
        "id": "a8bcd85d-232d-40a4-a7a4-108726bdf5fd",
        "code": "CS101",
        "title": "计算机科学导论",
        "instructor": {
            "id": "T001",
            "name": "张教授",
            "email": "zhang@example.edu.cn"
        },
        "schedule": {
            "dayOfWeek": "MONDAY",
            "startTime": "08:00",
            "endTime": "10:00",
            "expectedAttendance": 50
        },
        "capacity": 60,
        "enrolled": 0
    }
}

PUT http://localhost:8080/api/courses/a8bcd85d-232d-40a4-a7a4-108726bdf5fd
{
  "code": "CS101",  
  "title": "数学导论",  
  "instructor": { "id": "T001", "name": "罗教授", "email": "zhang@example.edu.cn" },  
  "schedule": { "dayOfWeek": "MONDAY", "startTime": "08:00", "endTime": "10:00", "expectedAttendance": 50 },  
  "capacity": 60,  
  "enrolled": 0  
}

{
    "code": 200,
    "message": "Success",
    "data": {
        "id": "a8bcd85d-232d-40a4-a7a4-108726bdf5fd",
        "code": "CS101",
        "title": "数学导论",
        "instructor": {
            "id": "T001",
            "name": "罗教授",
            "email": "zhang@example.edu.cn"
        },
        "schedule": {
            "dayOfWeek": "MONDAY",
            "startTime": "08:00",
            "endTime": "10:00",
            "expectedAttendance": 50
        },
        "capacity": 60,
        "enrolled": 0
    }
}

DELETE http://localhost:8080/api/courses/a8bcd85d-232d-40a4-a7a4-108726bdf5fd
204No Content无内容
此请求除请求头外无其他内容可返回

GET http://localhost:8080/api/courses/a8bcd85d-232d-40a4-a7a4-108726bdf5fd
{
    "code": 404,
    "message": "Course not found with id: a8bcd85d-232d-40a4-a7a4-108726bdf5fd",
    "data": null
}

2.POST http://localhost:8080/api/courses
{
  "code": "PH101",
  "title": "大学物理",
  "instructor": { "id": "T004", "name": "赵教授", "email": "zhao@example.edu.cn" },
  "schedule": { "dayOfWeek": "THURSDAY", "startTime": "09:00", "endTime": "11:00", "expectedAttendance": 2 },
  "capacity": 2  
}

{
    "code": 201,
    "message": "Course created successfully",
    "data": {
        "id": "789d18c1-7c5b-41fa-9154-1ec81257d7ec",
        "code": "PH101",
        "title": "大学物理",
        "instructor": {
            "id": "T004",
            "name": "赵教授",
            "email": "zhao@example.edu.cn"
        },
        "schedule": {
            "dayOfWeek": "THURSDAY",
            "startTime": "09:00",
            "endTime": "11:00",
            "expectedAttendance": 2
        },
        "capacity": 2,
        "enrolled": 0
    }
}

POST http://localhost:8080/api/students
{ "studentId": "2024001", "name": "学生A", "major": "计算机", "grade": 2024, "email": "a@example.edu.cn" }

{
    "code": 201,
    "message": "Student created successfully",
    "data": {
        "id": "f52c6efa-a27d-4b74-aa20-a932234b6c7e",
        "studentId": "2024001",
        "name": "学生A",
        "major": "计算机",
        "grade": 2024,
        "email": "a@example.edu.cn",
        "createdAt": "2025-10-18T14:00:41.7745922"
    }
}

POST http://localhost:8080/api/students
{ "studentId": "2024002", "name": "学生B", "major": "数学", "grade": 2024, "email": "b@example.edu.cn" }

{
    "code": 201,
    "message": "Student created successfully",
    "data": {
        "id": "d45be586-2f7d-4df6-8e12-3e1531a3d3db",
        "studentId": "2024002",
        "name": "学生B",
        "major": "数学",
        "grade": 2024,
        "email": "b@example.edu.cn",
        "createdAt": "2025-10-18T14:02:53.4324111"
    }
}

POST http://localhost:8080/api/students
{ "studentId": "2024003", "name": "学生C", "major": "英语", "grade": 2024, "email": "c@example.edu.cn" }

{
    "code": 201,
    "message": "Student created successfully",
    "data": {
        "id": "2e7719e6-dcad-4b4a-aa39-1eca47b0b67e",
        "studentId": "2024003",
        "name": "学生C",
        "major": "英语",
        "grade": 2024,
        "email": "c@example.edu.cn",
        "createdAt": "2025-10-18T14:03:58.0008664"
    }
}

POST http://localhost:8080/api/enrollments
{
  "courseId": "789d18c1-7c5b-41fa-9154-1ec81257d7ec",
  "studentId": "f52c6efa-a27d-4b74-aa20-a932234b6c7e"
}

{
    "code": 201,
    "message": "Enrolled successfully",
    "data": {
        "id": "b4b4a5e0-fdfd-42d0-b7ef-7e4977af6bce",
        "courseId": "789d18c1-7c5b-41fa-9154-1ec81257d7ec",
        "studentId": "f52c6efa-a27d-4b74-aa20-a932234b6c7e",
        "enrolledAt": "2025-10-18T14:05:42.9729803"
    }
}

POST http://localhost:8080/api/enrollments
{
  "courseId": "789d18c1-7c5b-41fa-9154-1ec81257d7ec",
  "studentId": "d45be586-2f7d-4df6-8e12-3e1531a3d3db"
}

{
    "code": 201,
    "message": "Enrolled successfully",
    "data": {
        "id": "94e2c42b-e09b-41de-9378-6e90d5d6a31f",
        "courseId": "789d18c1-7c5b-41fa-9154-1ec81257d7ec",
        "studentId": "d45be586-2f7d-4df6-8e12-3e1531a3d3db",
        "enrolledAt": "2025-10-18T14:08:53.4518974"
    }
}

POST http://localhost:8080/api/enrollments
{
  "courseId": "789d18c1-7c5b-41fa-9154-1ec81257d7ec",
  "studentId": "2e7719e6-dcad-4b4a-aa39-1eca47b0b67e"
}

{
    "code": 400,
    "message": "课程大学物理容量已满（当前：2/2）",
    "data": null
}

POST http://localhost:8080/api/enrollments
{
  "courseId": "789d18c1-7c5b-41fa-9154-1ec81257d7ec",
  "studentId": "f52c6efa-a27d-4b74-aa20-a932234b6c7e"
}

{
    "code": 400,
    "message": "学生2024001已选该课程：大学物理",
    "data": null
}

GET http://localhost:8080/api/courses/789d18c1-7c5b-41fa-9154-1ec81257d7ec
{
    "code": 200,
    "message": "Success",
    "data": {
        "id": "789d18c1-7c5b-41fa-9154-1ec81257d7ec",
        "code": "PH101",
        "title": "大学物理",
        "instructor": {
            "id": "T004",
            "name": "赵教授",
            "email": "zhao@example.edu.cn"
        },
        "schedule": {
            "dayOfWeek": "THURSDAY",
            "startTime": "09:00",
            "endTime": "11:00",
            "expectedAttendance": 2
        },
        "capacity": 2,
        "enrolled": 2
    }
}

3.GET http://localhost:8080/api/students
{
    "code": 200,
    "message": "Success",
    "data": [
        {
            "id": "f52c6efa-a27d-4b74-aa20-a932234b6c7e",
            "studentId": "2024001",
            "name": "学生A",
            "major": "计算机",
            "grade": 2024,
            "email": "a@example.edu.cn",
            "createdAt": "2025-10-18T14:00:41.7745922"
        },
        {
            "id": "2e7719e6-dcad-4b4a-aa39-1eca47b0b67e",
            "studentId": "2024003",
            "name": "学生C",
            "major": "英语",
            "grade": 2024,
            "email": "c@example.edu.cn",
            "createdAt": "2025-10-18T14:03:58.0008664"
        },
        {
            "id": "d45be586-2f7d-4df6-8e12-3e1531a3d3db",
            "studentId": "2024002",
            "name": "学生B",
            "major": "数学",
            "grade": 2024,
            "email": "b@example.edu.cn",
            "createdAt": "2025-10-18T14:02:53.4324111"
        }
    ]
}

GET http://localhost:8080/api/students/d45be586-2f7d-4df6-8e12-3e1531a3d3db
{
    "code": 200,
    "message": "Success",
    "data": {
        "id": "d45be586-2f7d-4df6-8e12-3e1531a3d3db",
        "studentId": "2024002",
        "name": "学生B",
        "major": "数学",
        "grade": 2024,
        "email": "b@example.edu.cn",
        "createdAt": "2025-10-18T14:02:53.4324111"
    }
}

PUT http://localhost:8080/api/students/d45be586-2f7d-4df6-8e12-3e1531a3d3db
{
  "studentId": "2024002",  
  "name": "学生B",  
  "major": "金融",  
  "grade": 2024,  
  "email": "b-updated@example.edu.cn"  
}

{
    "code": 200,
    "message": "Success",
    "data": {
        "id": "d45be586-2f7d-4df6-8e12-3e1531a3d3db",
        "studentId": "2024002",
        "name": "学生B",
        "major": "金融",
        "grade": 2024,
        "email": "b-updated@example.edu.cn",
        "createdAt": "2025-10-18T14:02:53.4324111"
    }
}

POST http://localhost:8080/api/enrollments
{
  "courseId": "789d18c1-7c5b-41fa-9154-1ec81257d7ec",
  "studentId": "d45be586-222d-1111-3332-3e1531a3d3db"
}

{
    "code": 404,
    "message": "Student not found with id: d45be586-222d-1111-3332-3e1531a3d3db",
    "data": null
}

DELETE http://localhost:8080/api/students/f52c6efa-a27d-4b74-aa20-a932234b6c7e
{
    "code": 400,
    "message": "无法删除：该学生存在选课记录",
    "data": null
}

DELETE http://localhost:8080/api/students/2e7719e6-dcad-4b4a-aa39-1eca47b0b67e
204No Content无内容
此请求除请求头外无其他内容可返回

4.GET http://localhost:8080/api/courses/1111111d-232d-40a4-a7a4-108726bdf5fd
{
    "code": 404,
    "message": "Course not found with id: 1111111d-232d-40a4-a7a4-108726bdf5fd",
    "data": null
}

POST http://localhost:8080/api/courses
{
  "code": "CS233",
  "title": "",
  "instructor": {
    "id": "T001",
    "name": "张教授",
    "email": "zhang@example.edu.cn"
  },
  "schedule": {
    "dayOfWeek": "MONDAY",
    "startTime": "08:00",
    "endTime": "10:00",
    "expectedAttendance": 50
  },
  "capacity": 60
}

{
    "code": 400,
    "message": "课程名称不能为空",
    "data": null
}

POST http://localhost:8080/api/enrollments
{
  "courseId": "789d18c1-1122-41fa-9154-1ec81257d7ec",
  "studentId": "f52c6efa-a27d-4b74-aa20-a932234b6c7e"
}

{
    "code": 404,
    "message": "Course not found with id: 789d18c1-1122-41fa-9154-1ec81257d7ec",
    "data": null
}

POST http://localhost:8080/api/students
{ "studentId": "2024004", "name": "学生D", "major": "英语", "grade": 2024, "email": "c@example.edu.cn" }

{
    "code": 400,
    "message": "学号已存在：2024004",
    "data": null
}

POST http://localhost:8080/api/students
{ "studentId": "2024005", "name": "学生F", "major": "英语", "grade": 2024, "email": "ample.edu.cn" }
{
    "code": 400,
    "message": "邮箱格式错误（需包含@和域名）",
    "data": null
}











