const StudentList = ({
  students,
  loading,
  error,
  successMessage,
  onEditStudent,
  onDeleteStudent,
}) => {
  return (
    <section className="panel-card">
      <div className="section-heading">
        <p className="section-tag">Student Data</p>
        <h2>Student List</h2>
        <p>
          View all student records and use the action buttons to update or
          delete any row.
        </p>
      </div>

      {loading && <p className="status-message">Loading student records...</p>}
      {error && <p className="status-message error-message">{error}</p>}
      {!error && successMessage && (
        <p className="status-message success-message">{successMessage}</p>
      )}

      {!loading && (
        <div className="table-wrapper">
          <table className="student-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Course</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {students.length === 0 ? (
                <tr>
                  <td colSpan="5" className="empty-state">
                    No students found. Add your first record.
                  </td>
                </tr>
              ) : (
                students.map((student) => (
                  <tr key={student.id}>
                    <td>{student.id}</td>
                    <td>{student.name}</td>
                    <td>{student.email}</td>
                    <td>{student.course}</td>
                    <td>
                      <div className="action-row">
                        <button
                          type="button"
                          className="secondary-button"
                          onClick={() => onEditStudent(student)}
                        >
                          Update
                        </button>
                        <button
                          type="button"
                          className="danger-button"
                          onClick={() => onDeleteStudent(student.id)}
                        >
                          Delete
                        </button>
                      </div>
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      )}
    </section>
  );
};

export default StudentList;
