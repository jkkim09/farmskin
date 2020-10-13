# 팜스킨 백엔드 개발자 테스트

## Syntax Checker
-  괄호가 올바르게 쓰여졌는지를 검증하는 함수를 작성 해 주세요.
-  괄호는 대괄호 [], 중괄호 {}, 소괄호 () 가 있습니다. 
-  괄호의 크기는 상관이 없습니다. (다만, 괄호의 순서(열고 닫고)는 보장이 되어야 합니다.)
-  올바르게 사용 되었다면 true / 아니면 false 를 리턴

|   input  | output  |
|:--------:|:-------:|
|  [(){}]  |   true  |
|  [({})]  |   true  |
|  [(){{}] |  false  |
| [((){})] |   true  |
|    )(    |  false  |
|   {(})   |  false  |


### java code
````java
import java.util.Scanner;

public class SyntaxChecker {
	static char[] stack;
	static int position = 0;
	
	public static void push(char data) {
        stack[++position] = data;
    }
	
	public static char pop() {
        return stack[position--];
    }

    public static boolean isEmpty() {
        if(position == 0) {
            return true;
        }
        return false;
    }
	
	/**
	 * syntax string 값이 올바르게 사용 되었는지 확인한다.
	 * 
	 * @param item syntax string item
	 * @return syntax check value
	 */
	public static boolean checker (String item) {
		int i = 0;
		int itemLength = item.length();
		stack = new char[itemLength];
		
		for (i = 0; i < itemLength; i++) {
			char checkItem = item.charAt(i);
			// 처음부터 닫는 갈호인지를 확인한다. ex) }()
			boolean errorCheck = false;
			switch (checkItem) {
				case '[':
				case '{':
				case '(':
					push(checkItem);
					break;
				case ']':
					if (position == 0) {
						errorCheck = true;
					}
					
					if (stack[position] == '[') {
						pop();
					}
					break;
				case '}':
					if (position == 0) {
						errorCheck = true;
					}
					
					if (stack[position] == '{') {
						pop();				
					}
					break;
				case ')':
					if (position == 0) {
						errorCheck = true;
					}
					
					if (stack[position] == '(') {
						pop();
					}
					break;
			}

			if (errorCheck) {
				return false;
			}
		}
		return isEmpty();
	}
	
	
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
	     String item = sc.nextLine();
	     sc.close();
	     System.out.println(checker(item));
	}
}
````

## Reverse With Tags

-  HTML Tag가 포함된 String을 받아 단어 단위로 전체 문장을 뒤집어 주세요.
-  Tag들은 뒤집어지지 않고 그대로 두어야 합니다. 
-  Tag의 중첩은 없습니다. 
-  <> = ”” / 등 Tag와 관련된 특수문자 이외의 특수문자는 없습니다. 

````js
/**
 * Tag 사이값을 찾아 Reverse한다.
 * 
 * @param {string} text     Original 문장
 */
function reverse (text) {
    var itemList = text.split('')
    var tagStart = false
    var startIndex = 0
    var reverseList = []

    for (var i=0; i<itemList.length; i++) {
        var item = itemList[i]
        if (item === '<') {
            tagStart = true
        }

        if (item === '<' && itemList[i + 1] === '/') {
            tagStart = false
            reverseList.push({start: startIndex, end: i})
        } 

        if (item === '>' && tagStart) {
            startIndex = i + 1
        }
    }
    return textReverse(text, reverseList)
}
/**
 * Original 문장을 reverse 한다.
 * 
 * @param {string} text     Original 문장
 * @param {list} list       reverse 할 index list
 */
function textReverse(text, list) {
    var target = text

    for (var j=0; j<list.length; j++) {
        var check = list[j]
        var searchText = text.substring(check.start, check.end)
        var reverseText = searchText.split('').reverse().join('')
        target = target.substr(0, check.start) + reverseText + target.substr(check.end)
    }
    return target
}
````
front-end에서 사용될만한 기능 이므로 javascript(es5)로 작성하였습니다  

target 을 replace(searchText, reverseText) 하려고 하였으나 Tag와 동일한 문자가 Tag사이에 있을경우 오동작하여 앞뒤 문장을 붙이는 방식으로 작성 되었습니다.

## Java
1.  Call by reference 와 Call by value의 차이점은? 

````java
// call by value
public class CallByValue {
	public static void swap(int a, int b) {
		int temp = a;
		a = b;
		b = temp;
	}

	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		System.out.println("swap() 호출 전 : a = " + a + ", b = " + b);
		swap(a, b);
		System.out.println("swap() 호출 후 : a = " + a + ", b = " + b);
	}
}

// call by reference
public class CallByReference {
	int value;
	
	CallByReference(int value) {
		this.value = value;
	}

	public static void swap(CallByReference a, CallByReference b) {
		int temp = a.value;
		a.value = b.value;
		b.value = temp;
	}

	public static void main(String[] args) {
		CallByReference a = new CallByReference(22);
		CallByReference b = new CallByReference(33);
		System.out.println("swap() 호출 전 : a = " + a.value + ", b = " + b.value);
		swap(a, b);
		System.out.println("swap() 호출 전 : a = " + a.value + ", b = " + b.value);
	}
}
````
````
CallByValue 는 메소드를 호출할때 원래의 값을 복사하여 값을 전달하는 방법이므로 메소드에서 값이 변경되더라도 원래의 값에는 영향을 주지안는다. (CallByValue Class)  
CallByReference 는 매소드를 호출할때 원래의 값의 "주소"를 전달하기 때문에 메소드에서 값을 변경하면 원래의 값 주소의 값을 변경되기때문에 원래의 값에 영향을 준다. (CallByReference Class)
````
2. GC(Garbage Collecting) 의 역할은?  
````
GC는 Heap 영역의 오브젝가 stack과 연결이 끊기면 Heap영역 오브젝트들은 가비지 컬렉션의 정리 대상이 되어 메모리를 정리하는 역할을 한다.
````
3. override와 overload의 차이점은?
````
오버로딩 (Overloading) : 같은 이름의 함수를(또는 생성자) 여러 개 정의하고, 매개변수, 타입 개수를 다르게 하여 모두 호출 할 수 있는것을 말한다..

오버라이딩 (Overriding) : 상속 받은 Class의 메소드를 재정의 하여 사용하는 것을 말한다.
````
4. Stack memory와 Heap Memory는 어떤것인지?
````
Stack : 후입선출 LIFO(나중에 들어온 값이 가장 먼저나가는) 구조로 되어있으며, 함수가 호출될때 지역변수, 매겨변수가 할당되는 영역이며 함수가 완료되면 정리된다.

Heap : 객체들이 할당 되는 영역이다. Method Area 에서 참조 되어있는 객체를 생성하면 이곳에 할당된다.

// 직접 관리하는 블러그에 좀더 상세하게 정리되어있습니다. https://jktech.tistory.com/11?category=887327
````
5. DAO(Data Access Object) 와 DTO(Data Transfer Object)의 각각의 역할은 어떤것인지?
````
DAO : Database(DB)에 직접 접근하는 객체

DTO : 단어 그대로 데이터를 교환하는 객체이다 예를 들어 Controller, Service, View 이러한 구조(계층 에서) 데이터를 교환하는 객체
````