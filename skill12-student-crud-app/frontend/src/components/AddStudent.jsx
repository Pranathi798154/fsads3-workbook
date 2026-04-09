import { useEffect, useState } from "react";

const initialForm = {
  name: "",
  email: "",
  course: "",
};

const AddStudent = ({ editingStudent, onSubmitStudent, onCancelEdit }) => {
  const [formData, setFormData] = useState(initialForm);

  useEffect(() => {
    if (editingStudent) {
      setFormData({
        name: editingStudent.name,
        email: editingStudent.email,
        course: editingStudent.course,
      });
      return;
    }

    setFormData(initialForm);
  }, [editingStudent]);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData((current) => ({
      ...current,
      [name]: value,
    }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    const wasSaved = await onSubmitStudent(formData);
    if (wasSaved && !editingStudent) {
      setFormData(initialForm);
    }
  };

  return (
    <section className="panel-card">
      <div className="section-heading">
        <p className="section-tag">{editingStudent ? "Update" : "Add"}</p>
        <h2>{editingStudent ? "Update Student" : "Add Student"}</h2>
        <p>
          Enter student details here. The form supports both new records and
          editing existing ones.
        </p>
      </div>

      <form className="student-form" onSubmit={handleSubmit}>
        <label>
          <span>Name</span>
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            placeholder="Enter student name"
            required
          />
        </label>

        <label>
          <span>Email</span>
          <input
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            placeholder="Enter email address"
            required
          />
        </label>

        <label>
          <span>Course</span>
          <input
            type="text"
            name="course"
            value={formData.course}
            onChange={handleChange}
            placeholder="Enter course name"
            required
          />
        </label>

        <div className="button-row">
          <button type="submit" className="primary-button">
            {editingStudent ? "Update Student" : "Add Student"}
          </button>

          {editingStudent && (
            <button type="button" className="secondary-button" onClick={onCancelEdit}>
              Cancel
            </button>
          )}
        </div>
      </form>
    </section>
  );
};

export default AddStudent;
