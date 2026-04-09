import React, {useState} from "react";
import axios from "axios";

function AddStudent({fetchStudents,editStudent,setEditStudent}){

const [name,setName]=useState("");
const [email,setEmail]=useState("");
const [course,setCourse]=useState("");

const handleSubmit=(e)=>{

e.preventDefault();

if(editStudent){

axios.put(`http://localhost:8081/students/${editStudent.id}`,
{name,email,course})
.then(()=>{
fetchStudents();
setEditStudent(null);
});

}
else{

axios.post("http://localhost:8081/students",
{name,email,course})
.then(()=>{
fetchStudents();
});

}

setName("");
setEmail("");
setCourse("");
}

return(

<div>

<h2>Add / Update Student</h2>

<form onSubmit={handleSubmit}>

<input
placeholder="Name"
value={name}
onChange={(e)=>setName(e.target.value)}
/>

<input
placeholder="Email"
value={email}
onChange={(e)=>setEmail(e.target.value)}
/>

<input
placeholder="Course"
value={course}
onChange={(e)=>setCourse(e.target.value)}
/>

<button type="submit">Submit</button>

</form>

</div>

)
}

export default AddStudent