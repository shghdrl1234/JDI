package Comparator_Comparable;

import java.util.Comparator;

public class Comparator_ {
	/*
	 * Comparable_.java를 먼저 보고오면 이해하기 수월함
	 * => 비슷한 부분이 많으며, 더 자세히 정리해놓음.
	 * 
	 * public interface Comparator<T> {
	 * 
	 * public int compare(Type o1, Type o2);
	 * 
	 * }
	 * 
	 * 자바에서 정의해놓은 Comparator 인터페이스이다.
	 * 
	 * Comparable 인터페이스와 유사하지만, 인터페이스 내에 선언된
	 * 메서드에는 두 개의 객체를 파라미터로 받아온다.
	 * 
	 * 구현을 하게 된다면
	 * puclic class className implements Comparator<type> {
	 * 
	 * @Override // 필수 구현부
	 * public int compare(Type o1, Type o2) {
	 *		 }
	 * }
	 * 
	 * 필수 구현부에서 객체를 비교할 기준을 정의해주는 부분이다.
	 * 
	 * comparable 인터페이스와 마찬가지고 비슷한 예를 통하여
	 * 설명을 계속 하겠음.
	 * 
	 */
	public static void main(String[] args) {

		Student2 a = new Student2(17, 1);
		//age = 17, classNumber = 1인 학생 객체 생성 및 초기화
		
		Student2 b = new Student2(18, 2);
		//age = 18, classNumber = 2인 학생 객체 생성 및 초기화
		
		int isBig = a.compare(a, b);
		
	}

}

class Student2 implements Comparator<Student2> {

	int age; // 학생의 나이
	int classNumber; // 학생의 반
	
	// 오버로딩
	Student2(int age, int classNumber) {
		this.age = age;
		this.classNumber = classNumber;
	}
	
	@Override
	public int compare(Student2 o1, Student2 o2) {
		
		// o1의 학급이 o2의 학급보다 크다면 양수
		if(o1.classNumber > o2.classNumber) {
			return 1;
		}
		// o1의 학급이 o2의 학급과 같다면 0
		else if(o1.classNumber == o2.classNumber) {
			return 0;
		}
		// o1의 학급이 o2의 학급보다 작다면 음수
		else {
			return -1;
		}
	}

}
/*
 * 두 객체를 비교하는 것이기 때문에 파라미터로 들어오는 o1과 o2를 비교해주는 것이다.
 * 
 * 이 말은 Student2 자기자신의 객체는 비교하는 연산에 영향이 없다는 것이다.
 *  
 *  그리고 익명함수를 활용하여 객체지향적인 코딩을 할 수 있다.
 *  이는 추후에 정리하도록 하겠다.
 */ 
