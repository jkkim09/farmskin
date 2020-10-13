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
1) Call by reference 와 Call by value의 차이점은? 
2) GC(Garbage Collecting) 의 역할은? 
3) override와 overload의 차이점은?
4) Stack memory와 Heap Memory는 어떤것인지?
5) DAO(Data Access Object) 와 DTO(Data Transfer Object)의 각각의 역할은 어떤것인지?