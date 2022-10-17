package studentGradeProgram;

public class Student implements Comparable<Student> {
	public static final int NUMBER_OF_SUBJECTS = 3;

	private String studentNumber;
	private String studentName;
	private boolean gender;
	private char charGender;
	private int korScore;
	private int engScore;
	private int mathScore;
	private int total;
	private double avg;
	private char grade;
	private int rank;

	public Student(String studentNumber, String studentName, boolean gender, int korScore, int engScore, int mathScore) {
		this.studentNumber = studentNumber;
		this.studentName = studentName;
		this.gender = gender;
		this.korScore = korScore;
		this.engScore = engScore;
		this.mathScore = mathScore;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}
	
	public char getCharGender() {
		return charGender;
	}

	public int getKorScore() {
		return korScore;
	}

	public void setKorScore(int korScore) {
		this.korScore = korScore;
	}

	public int getEngScore() {
		return engScore;
	}

	public void setEngScore(int engScore) {
		this.engScore = engScore;
	}

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getTotal() {
		return total;
	}

	public double getAvg() {
		return avg;
	}

	public char getGrade() {
		return grade;
	}

	public void calculateTotal() {
		this.total = this.korScore + this.engScore + this.mathScore;
	}

	public void calculateAvg() {
		String data = String.format("%.2f", (double) this.total / NUMBER_OF_SUBJECTS);
		this.avg = Double.parseDouble(data);
	}

	public void calculateGrade() {
		if (this.avg >= 90.0) {
			grade = 'A';
		} else if (this.avg >= 80.0) {
			grade = 'B';
		} else if (this.avg >= 70.0) {
			grade = 'C';
		} else if (this.avg >= 60.0) {
			grade = 'D';
		} else {
			grade = 'F';
		}
	}
	
	public void convertCharGender () {
		this.charGender = this.gender == false ? '남' : '여';
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Student))
			return false;
		Student student = (Student) obj;
		return this.studentNumber.equals(student.studentNumber);
	}

	@Override
	public int hashCode() {
		return this.studentNumber.hashCode();
	}

	@Override
	public int compareTo(Student o) {
		return o.total - this.total;
	}

	@Override
	public String toString() {
		return studentNumber + "\t" + studentName + "\t" + charGender + "\t" + korScore + "\t" + engScore + "\t"
				+ mathScore + "\t" + total + "\t" + avg + "\t" + grade + "\t" + rank;
	}
}