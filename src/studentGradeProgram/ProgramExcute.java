package studentGradeProgram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ProgramExcute {

	public static Scanner scanner = new Scanner(System.in);
	public static final int TEST_AUTOMATIC_INPUT = 5;

	public static void main(String[] args) {
		List<Student> list = new ArrayList<Student>();

		boolean loopFlag = false;
		while (!loopFlag) {
			int selectNumber = displayMenu();
			switch (selectNumber) {
			case 1:
				inputStudentData(list);
				break;
			case 2:
				updateStudentData(list);
				break;
			case 3:
				deleteStudentData(list);
				break;
			case 4:
				searchStudentData(list);
				break;
			case 5:
				outputStudentData(list);
				break;
			case 6:
				sortStudentData(list);
				break;
			case 7:
				statsStudentData(list);
				break;
			case 8:
				loopFlag = true;
				break;
			default:
				System.out.println("보기를 확인 후 다시 입력해주세요.");
				break;
			}
		}
		System.out.print("프로그램이 종료되었습니다.");
	}

	// Student data statistics (max / min / korean score / english score / math score)
	public static void statsStudentData(List<Student> list) {
		if (list.size() == 0) {
			System.out.println("통계를 낼 학생 데이터가 존재하지 않습니다. 먼저 데이터를 입력해주세요.");
			return;
		}
		
		scanner.nextLine();
		System.out.print("1. 최고총점  |  2. 최저총점  |  3. 국어통계  |  4. 영어통계  |  5. 수학통계\n입력 > ");
		int selectNumber = scanner.nextInt();
		switch (selectNumber) {
		case 1:
			int maxScore = Integer.MIN_VALUE;
			String data = null;
			for (Student student : list) {
				if (maxScore < student.getTotal()) {
					maxScore = student.getTotal();
					data = student.toString();
				}
			}
			System.out.println("학생번호\t이름\t성별\t국어점수\t영어점수\t수학점수\t총점\t평균\t등급\t등수");
			System.out.println(data);
			break;
		case 2:
			int minScore = Integer.MAX_VALUE;
			data = null;
			for (Student student : list) {
				if (minScore > student.getTotal()) {
					minScore = student.getTotal();
					data = student.toString();
				}
			}
			System.out.println("학생번호\t이름\t성별\t국어점수\t영어점수\t수학점수\t총점\t평균\t등급\t등수");
			System.out.println(data);
			break;
		case 3:
			maxScore = Integer.MIN_VALUE;
			minScore = Integer.MAX_VALUE;
			int total = 0;
			for (Student student : list) {
				total += student.getKorScore();
				if (maxScore < student.getKorScore()) {
					maxScore = student.getKorScore();
				}
				if (minScore > student.getKorScore()) {
					minScore = student.getKorScore();
				}
			}
			double avg = (double) total / list.size();
			System.out.println("국어 최고점 : " + maxScore + "점");
			System.out.println("국어 최저점 : " + minScore + "점");
			System.out.println("국어 평균점 : " + String.format("%.2f", avg) + "점");
			break;
		case 4:
			maxScore = Integer.MIN_VALUE;
			minScore = Integer.MAX_VALUE;
			total = 0;
			for (Student student : list) {
				total += student.getEngScore();
				if (maxScore < student.getEngScore()) {
					maxScore = student.getEngScore();
				}
				if (minScore > student.getEngScore()) {
					minScore = student.getEngScore();
				}
			}
			avg = (double) total / list.size();
			System.out.println("영어 최고점 : " + maxScore + "점");
			System.out.println("영어 최저점 : " + minScore + "점");
			System.out.println("영어 평균점 : " + String.format("%.2f", avg) + "점");
			break;
		case 5:
			maxScore = Integer.MIN_VALUE;
			minScore = Integer.MAX_VALUE;
			total = 0;
			for (Student student : list) {
				total += student.getMathScore();
				if (maxScore < student.getMathScore()) {
					maxScore = student.getMathScore();
				}
				if (minScore > student.getMathScore()) {
					minScore = student.getMathScore();
				}
			}
			avg = (double) total / list.size();
			System.out.println("수학 최고점 : " + maxScore + "점");
			System.out.println("수학 최저점 : " + minScore + "점");
			System.out.println("수학 평균점 : " + String.format("%.2f", avg) + "점");
			break;
		default:
			System.out.println("보기를 확인 후 다시 입력해주세요.");
			break;
		}
	}

	// Sort student data
	public static void sortStudentData(List<Student> list) {
		if (list.size() == 0) {
			System.out.println("정렬할 학생 데이터가 존재하지 않습니다. 먼저 데이터를 입력해주세요.");
			return;
		}
		scanner.nextLine();
		System.out.print("정렬 기준을 선택해주세요.\n1. 학생번호  |  2. 등수\n입력 > ");
		int criteria = scanner.nextInt();
		int successOrFailure = 0;
		switch (criteria) {
		case 1:
			successOrFailure = sortByNumber(list);
			break;
		case 2:
			successOrFailure = sortByRank(list);
			break;
		default:
			System.out.println("보기를 확인 후 다시 입력해주세요.");
			return;
		}
		if (successOrFailure == 1) {
			outputStudentData(list);
		} else {
			System.out.println("보기를 확인 후 올바른 메뉴를 선택해주세요.");
		}
	}

	// (1) Sort student data by total
	public static int sortByRank(List<Student> list) {
		System.out.print("정렬 방법을 선택해주세요.\n1. 오름차순  |  2. 내림차순\n입력 > ");
		int method = scanner.nextInt();
		switch (method) {
		case 1:
			list.sort(Comparator.naturalOrder());
			return 1;
		case 2:
			list.sort(Collections.reverseOrder());
			return 1;
		default:
			return 0;
		}
	}

	// (2) Sort student data by student number
	public static int sortByNumber(List<Student> list) {
		System.out.print("정렬 방법을 선택해주세요.\n1. 오름차순  |  2. 내림차순\n입력 > ");
		int method = scanner.nextInt();
		switch (method) {
		case 1:
			Collections.sort(list, new Comparator<Student>() {
				@Override
				public int compare(Student o1, Student o2) {
					return o1.getStudentNumber().compareToIgnoreCase(o2.getStudentNumber());
				}
			});
			return 1;
		case 2:
			Collections.sort(list, new Comparator<Student>() {
				@Override
				public int compare(Student o1, Student o2) {
					return o2.getStudentNumber().compareToIgnoreCase(o1.getStudentNumber());
				}
			});
			return 1;
		default:
			return 0;
		}
	}

	// Output student data
	public static void outputStudentData(List<Student> list) {
		if (list.size() == 0) {
			System.out.println("출력할 학생 데이터가 존재하지 않습니다. 먼저 데이터를 입력해주세요.");
			return;
		}
		
		System.out.println("학생번호\t이름\t성별\t국어점수\t영어점수\t수학점수\t총점\t평균\t등급\t등수");
		for (Student student : list) {
			System.out.println(student);
		}
	}
	
	// Search student data
	public static void searchStudentData(List<Student> list) {
		if (list.size() == 0) {
			System.out.println("검색할 학생 데이터가 존재하지 않습니다. 먼저 데이터를 입력해주세요.");
			return;
		}
		
		scanner.nextLine();
		System.out.print("검색할 학생 번호를 입력해주세요 : ");
		String studentNumber = scanner.nextLine();
		Student index = null;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getStudentNumber().equals(studentNumber)) {
				index = list.get(i);
				System.out.println("학생번호\t이름\t성별\t국어점수\t영어점수\t수학점수\t총점\t평균\t등급\t등수");
				System.out.println(index.toString());
				break;
			}
		}
		if (index == null) {
			System.out.println("해당되는 학생 데이터를 찾지 못했습니다.");
			return;
		}
	}

	// Delete student data
	public static void deleteStudentData(List<Student> list) {
		if (list.size() == 0) {
			System.out.println("삭제할 학생 데이터가 존재하지 않습니다. 먼저 데이터를 입력해주세요.");
			return;
		}
		
		scanner.nextLine();
		int index = -1;
		System.out.print("삭제할 학생 번호를 입력해주세요 : ");
		String studentNumber = scanner.nextLine();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getStudentNumber().equals(studentNumber)) {
				list.remove(i);
				calculateRank(list);
				index = i;
				System.out.println("데이터가 정상적으로 삭제되었습니다.");
				break;
			}
		}
		if (index == -1) {
			System.out.println("해당되는 학생 데이터를 찾지 못했습니다.");
			return;
		}
	}

	// Update student data
	public static void updateStudentData(List<Student> list) {
		if (list.size() == 0) {
			System.out.println("수정할 학생 데이터가 존재하지 않습니다. 먼저 데이터를 입력해주세요.");
			return;
		}
		
		scanner.nextLine();
		System.out.print("수정할 학생 번호를 입력해주세요 : ");
		String studentNumber = scanner.nextLine();
		Student student = null;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getStudentNumber().equals(studentNumber)) {
				student = list.get(i);
				System.out.println("현재 입력된 국어점수는 " + student.getKorScore() + "점입니다.");
				System.out.print("수정할 국어점수를 입력해주세요.\n입력 > ");
				int korScore = scanner.nextInt();
				if (korScore > 100 || korScore < 0) {
					System.out.println("국어점수를 정확하게 입력해주세요.");
					return;
				}
				student.setKorScore(korScore);
				System.out.println("현재 입력된 영어점수는 " + student.getEngScore() + "점입니다.");
				System.out.print("수정할 영어점수를 입력해주세요.\n입력 > ");
				int engScore = scanner.nextInt();
				if (engScore > 100 || engScore < 0) {
					System.out.println("영어점수를 정확하게 입력해주세요.");
					return;
				}
				student.setEngScore(engScore);
				System.out.println("현재 입력된 수학점수는 " + student.getMathScore() + "점입니다.");
				System.out.print("수정할 수학점수를 입력해주세요.\n입력 > ");
				int mathScore = scanner.nextInt();
				if (mathScore > 100 || mathScore < 0) {
					System.out.println("영어점수를 정확하게 입력해주세요.");
					return;
				}
				student.setMathScore(mathScore);
				student.calculateTotal();
				student.calculateAvg();
				student.calculateGrade();
				calculateRank(list);
				System.out.println("학생번호\t이름\t성별\t국어점수\t영어점수\t수학점수\t총점\t평균\t등급\t등수");
				System.out.println(student);
				System.out.println("데이터가 정상적으로 수정되었습니다.");
				break;
			}
		}
		if (student == null) {
			System.out.println("해당되는 학생 데이터를 찾지 못했습니다.");
			return;
		}
	}

	// Input student data
	public static void inputStudentData(List<Student> list) {
		String studentNumber = createStudentNumber();
		String studentName = createStudentName();
		boolean gender = (createNumber(0, 1) == 0) ? false : true;
		int korScore = createNumber(50, 100);
		int engScore = createNumber(50, 100);
		int mathScore = createNumber(50, 100);
		list.add(new Student(studentNumber, studentName, gender, korScore, engScore, mathScore));
		for (Student student : list) {
			student.calculateTotal();
			student.calculateAvg();
			student.calculateGrade();
			student.convertCharGender();
		}
		calculateRank(list);
		System.out.println("데이터가 정상적으로 등록되었습니다.");
	}

	// Create rank
	public static void calculateRank(List<Student> list) {
		list.sort(Comparator.naturalOrder());
		for (Student student : list) {
			student.setRank(list.indexOf(student) + 1);
		}
	}

	// Create student number
	public static String createStudentNumber() {
		String studentNumber = String.format("%02d", createNumber(1, 3)) + String.format("%02d", createNumber(1, 9)) + String.format("%02d", createNumber(1, 30));
		
		return studentNumber;
	}

	// Create student name
	public static String createStudentName() {
		List<String> lastName = new ArrayList<String>(Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임",
				"한", "오", "서", "신", "권", "황", "안", "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남",
				"심", "노", "정", "하", "곽", "성", "차", "주", "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원",
				"천", "방", "공", "강", "현", "함", "변", "염", "양", "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길",
				"주", "연", "방", "위", "표", "명", "기", "반", "왕", "금", "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여",
				"진", "어", "은", "편", "구", "용"));

		List<String> firstName = new ArrayList<String>(Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근",
				"기", "길", "나", "남", "노", "누", "다", "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마",
				"만", "명", "무", "문", "미", "민", "바", "박", "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선",
				"설", "섭", "성", "세", "소", "솔", "수", "숙", "순", "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영",
				"예", "오", "옥", "완", "요", "용", "우", "원", "월", "위", "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎",
				"자", "잔", "장", "재", "전", "정", "제", "조", "종", "주", "준", "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘",
				"충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현", "형", "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘",
				"희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린", "을", "비", "솜", "공", "면", "탁", "온", "디", "항", "후",
				"려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추", "걸", "삼", "열", "웅", "분", "변", "양", "출", "타",
				"흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직", "흠", "흔", "악", "람", "뜸", "권", "복",
				"심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련"));

		String name1 = lastName.get((int) (Math.random() * (lastName.size())));
		String name2 = firstName.get((int) (Math.random() * (lastName.size())));
		String name3 = firstName.get((int) (Math.random() * (lastName.size())));
		
		return name1 + name2 + name3;
	}

	// Create random number
	public static int createNumber(int min, int max) {
		return (int) (Math.random() * (max - min + 1) + min);
	}

	// Display program menu
	public static int displayMenu() {
		System.out.println("+---------------------------------------------------------------------------------------+");
		System.out.println("|  1. 입력  |  2. 수정  |  3. 삭제  |  4. 검색  |  5. 출력  |  6. 정렬  |  7. 통계  |  8. 종료      |");
		System.out.println("+---------------------------------------------------------------------------------------+");
		System.out.print("입력 > ");
		int selectNumber = scanner.nextInt();
		return selectNumber;
	}
}