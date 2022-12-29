package Comparator_Comparable;

public class Comparable_ {
	/*
	 * public interface Comparable<T> {
	 * 		
	 * 		public int compareTo(T o);
	 * 
	 * }
	 * 
	 * Java에서 정의하는 Comparable 인터페이스이며, 메서드는 comparTo(T o); 하나만 있다.
	 * 
	 * Comparable 인터페이스를 사용하여면 compareTo 메서드를 반드시 구현해야한다. 
	 *
	 * Comparable<T> 에서 <T>는 하나의 객체 타입이 지정될 자리라고 생각하면 된다.
	 * 
	 * 즉, 구현을 하게 된다면
	 * puclic class className implements Comparable<type> {
	 * 
	 * @Override // 필수 구현부
	 * public int compareTo(Type o) {
	 *		 }
	 * }
	 * 가 될 것이며, 오버라이딩 된 부분의 파라미터 o는 인터페이스 Comparable<type>의 type 객체가 된다.
	 * 
	 * 여기서 필수 구현 부분인 compareTo()메서드가 바로 객체를 비교할 기준을 정의해주는 부분이다.
	 * 
	 * ClassName이라는 클래스에서 자기 자신은 ClassName으로 생성한 객체 자신이 되고,
	 * 매개변수 객체는 ClassName.compareTo(o)를 통해 들어온 파라미터 o가 비교할 객체가 된다.
	 *  
	 * 나머지는 아래에서 예시를 들겠다.
	 */
	public static void main(String[] args) {

		Student a = new Student(17, 1);
		//age = 17, classNumber = 1인 학생 객체 생성 및 초기화
		
		Student b = new Student(18, 2);
		//age = 18, classNumber = 2인 학생 객체 생성 및 초기화
		
		int isBig = a.compareTo(b);
		// a를 기준으로 b와 비교하여 결과 리턴.
		// Student에서 정의한 compareTo 메서드는 age를 기준으로 함.
		// comparable을 통하여 비교 기준을 age로 설정함.
	
		
	}

}

class Student implements Comparable<Student> {

	int age; // 학생의 나이
	int classNumber; // 학생의 반
	
	// 오버로딩
	Student(int age, int classNumber) {
		this.age = age;
		this.classNumber = classNumber;
	}
	
	@Override
	public int compareTo(Student o) {
		
		// 자기자신의 age가 o의 age보다 크다면 양수
		if(this.age > o.age) {
			return 1;
		}
		// 자기 자신의 age와 o의 age가 같다면 0
		else if(this.age == o.age) {
			return 0;
		}
		// 자기 자신의 age가 o의 age보다 작다면 음수
		else {
			return -1;
		}
	}
}
/*
 *  자기자신 (this) 를 통하여 compareTo의 매개변수로 들어온 Student의 객체와
 *  비교하게 된다.
 *  그리고 비교문을 통하여 어떤 값을 리턴할 지 결정하게 된다.
 *  
 *  비교문에 따라 리턴 값이 달라지는데, 리턴값은 this의 값이 양수면 1, 같으면 0, 다르면 -1을 준다.
 *  
 *  편하게 this.age - o.age로 표현해도 되지만, 오버플루우 또는 언더플로우가 나타날 수 있다.
 *  
 *  결론은 자기 자신을 기준으로 대소관계를 파악한다는 것이다.
 *  
 */ 
