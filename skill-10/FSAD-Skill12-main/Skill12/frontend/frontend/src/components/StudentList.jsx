import React,{useState,useEffect} from "react";
import axios from "axios";
import AddStudent from "./AddStudent";

function StudentList(){

const [students,setStudents]=useState([]);
const [editStudent,setEditStudent]=useState(null);

const fetchStudents=()=>{

axios.get("http://localhost:8081/students")
.then(res=>{
setStudents(res.data);
});

}

useEffect(()=>{
fetchStudents();
},[])

const deleteStudent=(id)=>{

axios.delete(`http://localhost:8081/students/${id}`)
.then(()=>{
fetchStudents();
})

}

return(

<div>

<AddStudent
fetchStudents={fetchStudents}
editStudent={editStudent}
setEditStudent={setEditStudent}
/>

<h2>Student List</h2>

<table border="1">

<thead>
<tr>
<th>Name</th>
<th>Email</th>
<th>Course</th>
<th>Actions</th>
</tr>
</thead>

<tbody>

{students.map(student=>(
<tr key={student.id}>

<td>{student.name}</td>
<td>{student.email}</td>
<td>{student.course}</td>

<td>

<button
onClick={()=>setEditStudent(student)}
>
Update
</button>

<button
onClick={()=>deleteStudent(student.id)}
>
Delete
</button>

</td>

</tr>
))}

</tbody>

</table>

</div>

)
}

export default StudentList