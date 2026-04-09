import { useEffect, useState } from "react";
import axios from "axios";
import AddStudent from "./components/AddStudent";
import StudentList from "./components/StudentList";
import "./App.css";

const API_URL = "http://localhost:8086/students";
const SWAGGER_URL = "http://localhost:8086/swagger-ui/index.html";

const App = () => {
  const [students, setStudents] = useState([]);
  const [editingStudent, setEditingStudent] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  const fetchStudents = async () => {
    try {
      setLoading(true);
      setError("");
      const response = await axios.get(API_URL);
      setStudents(response.data);
    } catch (err) {
      setError(
        err.response?.data?.message ||
          err.message ||
          "Unable to load students."
      );
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchStudents();
  }, []);

  const handleSubmitStudent = async (studentData) => {
    try {
      setError("");
      if (editingStudent) {
        await axios.put(`${API_URL}/${editingStudent.id}`, studentData);
        setSuccessMessage("Student updated successfully.");
      } else {
        await axios.post(API_URL, studentData);
        setSuccessMessage("Student added successfully.");
      }

      setEditingStudent(null);
      fetchStudents();
      return true;
    } catch (err) {
      setError(
        err.response?.data?.message ||
          err.message ||
          "Unable to save student."
      );
      return false;
    }
  };

  const handleDeleteStudent = async (id) => {
    try {
      setError("");
      await axios.delete(`${API_URL}/${id}`);
      setSuccessMessage("Student deleted successfully.");
      if (editingStudent?.id === id) {
        setEditingStudent(null);
      }
      fetchStudents();
    } catch (err) {
      setError(
        err.response?.data?.message ||
          err.message ||
          "Unable to delete student."
      );
    }
  };

  const handleEditStudent = (student) => {
    setEditingStudent(student);
    setSuccessMessage("");
  };

  const handleCancelEdit = () => {
    setEditingStudent(null);
    setError("");
    setSuccessMessage("");
  };

  return (
    <main className="app-shell">
      <section className="hero-card">
        <p className="eyebrow">Skill 16 - Swagger for Student CRUD</p>
        <h1>Student Management with OpenAPI Docs</h1>
        <p className="hero-copy">
          React frontend connected to a Spring Boot CRUD backend with
          Swagger/OpenAPI documentation for every student endpoint, schema, and
          validation response.
        </p>
        <div className="doc-strip">
          <span>Backend: `http://localhost:8086`</span>
          <a href={SWAGGER_URL} target="_blank" rel="noreferrer">
            Open Swagger UI
          </a>
        </div>
      </section>

      <section className="content-grid">
        <AddStudent
          editingStudent={editingStudent}
          onSubmitStudent={handleSubmitStudent}
          onCancelEdit={handleCancelEdit}
        />

        <StudentList
          students={students}
          loading={loading}
          error={error}
          successMessage={successMessage}
          onEditStudent={handleEditStudent}
          onDeleteStudent={handleDeleteStudent}
        />
      </section>
    </main>
  );
};

export default App;
