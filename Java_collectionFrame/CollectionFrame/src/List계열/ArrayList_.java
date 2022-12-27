package List계열;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayList_ {
	/*
	 * AllayList는 컬렉션 프레임 워크에서 가장 많이 사용되는 컬렉션 클래스이다.
	 * 
	 * List인터페이스를 구형하기 때문에 데이터의 저장순서가 유지되고 중복을 허용한다는
	 * 특징이 있다.
	 * 
	 *  ArrayList는 기존의 Vector를 개선한 것으로 Vector의 구현원리와 기능적인 측면에서 동일하다고 할 수 있다.
	 *  
	 *  ArrayList는 Object배열을 이용해서 데이터를 순차적으로 저장한다.
	 *  데이터를 저장할 공간이 없으면 보다 큰 새로운 배열을 생성해서
	 *  기존의 배열에 저장된 내용을 새로운 배열에 복사한 다음, 저장한다.
	 *  
	 *  ArrayList의 요소를 삭제하는 경우, 삭제할 객체의 바로 아래에 있는 데이터를 한 칸씩
	 *  위로 복사해서 삭제할 객체를 덮어쓰는 방식으로 처리한다.
	 *  만일 삭제할 객체가 마지막 데이터라면, 복사할 필요없이 단순히 null로 변경해주면 된다.
	 *  
	 *  하지만, 그 경우가 아니라면 삭제할 객체부터 마지막 객체까지 데이터의 이동과 삭제, 저장의
	 *  과정이 발생하기 때문에, 다루는 데이터의 개수가 많을수록 작업 시간이 오래걸린다.
	 *  
	 *  데이터를 추가하는 과정에서도 마찬가지다. 
	 *
	 */

	public static void main(String[] args) {

		// ArrayList() : 크기가 0인 ArrayList를 생성.
		// ArrayList(Collection c) : 주어진 컬렉션이 저장된 ArrayList를 생성.
		ArrayList arr = new ArrayList();
		
		// ArrayList(int initialCapacity) : 지정된 초기용량을 갖는 ArrayList를 생성. 
		ArrayList arr2 = new ArrayList(2);
		
		
		// boolean add(Object O) : ArrayList의 마지막(인덱스)에 객체 추가를 성공하면 true.
		arr.add("일");
		arr.add("이");
		System.out.println("arr에 들어있는 객체 : "+arr);
		
		// void add(int index, Object element) : 지정된 위치에 객체를 저장.
		arr.add(2,"인덱스 지정하고 저장");
		arr.add(2,"같은 컬렉션 객체의 이미 존재하는 인덱스에 추가하면, 기존의 인덱스들은 한 칸씩 뒤로감");
		System.out.println("arr에 들어있는 객체 : "+arr);
		
		// boolean addAll(Collection c) : 주어진 컬렉션의 모든 객체를 저장.
		arr2.add(arr); // arr2의 사이즈는 2지만, 데이터가 사이즈보다 초과하여 자동으로 늘려주는 과정이 실행.
		System.out.println("arr2에 들어있는 객체 : "+arr2);
		
		// boolaean addAll(int index, Collection c) : 지정된 위치부터 주어진 컬렉션의 모든 객체를 저장한다.
		ArrayList arr3 = new ArrayList(2);
		arr3.add("1");
		arr3.add("2");
		arr3.add("3");
		// arr3에 인덱스 번호 0~2 까지 데이터가 저장되어 있음.
		System.out.println("arr3에 들어있는 객체 : "+arr3);

		arr3.addAll(2, arr);
		// arr3의 2번 인덱스부터 arr의 객체들을 저장시킴.
		System.out.println("arr3에 들어있는 객체 : "+arr3);
		
		// void clear() : AllayList를 완전히 비운다.
		arr3.clear();
		System.out.println("arr3에 들어있는 객체 : "+arr3);
		
		// Object clone() : ArrayList를 복제한다.
		Object obj = arr.clone();
		System.out.println("obj에 들어있는 객체 : "+obj);
		
		// boolean contains(Object o) : 지정된 객체(o)가 ArrayList에 포함되어있는지 확인.
		System.out.println("정수 3이 arr에 포함되어있는가? : " + arr.contains(3));
		
		// void ensureCapcity(int minCapacity) : ArrayList의 용량이 최소한 minCapacity가 되도록 한다.
		// 가용한 공간을 미리 만들어 두는 것임.
		arr.ensureCapacity(1000);
		
		// Object get(int index) : 지정된 위치에 저장된 객체를 반환한다.
		System.out.println("arr의 1번 인덱스의 객체 : " + arr.get(1));
		
		// boolean isEmpty() : ArrayList가 비어있는지 확인한다.
		System.out.println("arr이 비어있는가? : " + arr.isEmpty());
		arr3.clear();
		System.out.println("arr3가 비어있는가? : " + arr3.isEmpty());
		
		// Iterator iterator() : arrayList의 Iterator 객체를 반환.
		// ListIterator listIterator() : arrayList의 ListIterator를 반환.
		// ListIterator listIterator(int index) : arrayList의 지정된 위치부터 시작하는 ListIterator를 반환.
		// => 후반에 반복문을 통하여 설명하거나 따로 찾아봐서 정리할 예정.
		
		// int .indexOf(Obeject o) : 지정된 객체가 저장된 위치를 찾아 반환한다.
		// 없으면 -1 반환.
		System.out.println("arr객체의 '이'의 위치 : "+arr.indexOf("이"));
		
		// int lastIndexOf(Object o) : 객체 (o)가 저장된 위치를 끝부터 역방향으로 검색해서 반환.
		// 없으면 -1 반환.
		System.out.println("arr객체의 '삼'의 위치 : "+arr.lastIndexOf("삼"));
		
		// Object remove(int index) : 지정된 위치에 있는 객체를 제거한다.
		arr.remove(1);
		System.out.println("arr에 들어있는 객체 : "+arr);
		
		// boolean remove(Object o) : 지정한 객체를 제거한다. 성공하면 true, 실패하면 false.
		System.out.println("arr에서 '일' 제거 : " + arr.remove("일"));
		
		// boolean removeAll(Collection c) : 지정한 컬렉션 객체 중에서 주어진 컬렉션과 공통된 것들만 남기고 나머지는 삭제한다.
		ArrayList arr4 = new ArrayList(2);
		arr4.add(1);
		arr4.add(2);
		arr4.add(3);
		arr4.add(4);
		arr4.add(5);
		ArrayList arr5 = new ArrayList(2);
		arr5.add(3);
		arr5.add(4);
		arr5.add(5);
		arr5.add(6);
		
		System.out.println("arr4에 들어있는 객체 : " + arr4);
		System.out.println("arr4에서 arr5와 공통된 것 삭제(차집합) : " + arr4.removeAll(arr5));
		System.out.println("arr4에 들어있는 객체 : " + arr4);
		
		// boolean retainAll(Collection c) : ArrayList에 저장된 객체 중에서 주어진 컬렉션과 공통된 것들만 남기고
		// 나머지는 삭제한다.

		System.out.println("arr5에 들어있는 객체 : " + arr5);
		System.out.println("arr5에서 arr4와 공통되지 않는 객체 삭제(교집합) : " + arr5.retainAll(arr4));
		System.out.println("arr5에 들어있는 객체 : " + arr5);
		arr5.add(1);
		
		// Object set(int index, Object element) : 주어진 객체를 지정된 위치에 저장한다.
		// 지정된 인덱스에 값이 없다면 예외발생, => 저장이 아닌 덮어씌움.
		arr5.set(0, "인덱스 번호로 객체 덮어 씌움");
		System.out.println("arr5에 들어있는 객체 : " + arr5);
		
		// int size() : ArrayList에 저장된 객체의 개수를 반환한다.
		System.out.println("arr4에 속하는 객체의 수 : " + arr4.size());
		
		arr4.add(1);
		arr4.add(112);
		arr4.add(4556);
		arr4.add(36);
		arr4.add(74);
		arr4.add(1200);
		
		// void sort(Comparator C) : 지정된 정렬기준(c)로 arrayList를 정렬
		// Comparator은 추후 정리 예정. null 값은 컬렉션 클래스에서 이미 정의된 것을 활용?
		System.out.println("arr4에 들어있는 객체 : " + arr4);
		arr4.sort(null);		
		System.out.println("arr4에 들어있는 객체 : " + arr4);
		
		// List subList(int fromIndex, int toIndex) : fromIndex 부터 toIndex사이에 저장된 객체를 반환.
		System.out.println("arr4의 인덱스 번호 3~5에 들어있는 객체: " + arr4.subList(3, 5));
		
		// Object[] toArray() : ArrayList에 저장된 모든 객체들을 객체배열로 반환한다.
		Object[] i = arr4.toArray();
		System.out.println("i에 들어있는 객체 : " + Arrays.toString(i));
		
		// Object[] toArray(Object[] a) : ArrayList에 저장된 모든 객체들을 객체배열 a로 반환한다.
		Object[] a = new Object[10];
		arr4.toArray(a);
		System.out.println("a에 들어있는 객체 : " + Arrays.toString(a));
		
		// void trimToSize() : 용량을 크기에 맞게 줄인다(빈 공간을 없앤다)
		
	}

}
