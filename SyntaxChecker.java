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
