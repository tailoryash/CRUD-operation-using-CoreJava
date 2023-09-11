public class Employee {
    private Long empId;
    private String fullName;
    private String tech;

    public Employee(Long empId, String fullName, String tech) {
        this.empId = empId;
        this.fullName = fullName;
        this.tech = tech;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", fullName='" + fullName + '\'' +
                ", tech='" + tech + '\'' +
                '}';
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

}
