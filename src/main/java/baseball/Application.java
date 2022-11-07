package baseball;
import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {
	static int start = 1;
	static String startMessage = "숫자 야구 게임을 시작합니다.";
	static String inputMessage = "숫자를 입력해주세요 : ";
	static List<Integer> computer = new ArrayList<>();
	static String inputNumber = "";
	static boolean inputCheck = false;
	static boolean exceptionCheck = false;
	static int ball=0;
	static int strike=0;
    public static void main(String[] args) {

		gameStart();
		
    	while(start==1) {

    		computer = makeComputer();
    		
    		game();
    		strike=0;
    		ball=0;
    	}
    }
    
    public static void gameStart() {
    	System.out.println(startMessage);
    }
 
    public static List<Integer> makeComputer() {
    	
    	List<Integer> list = new ArrayList<>();
    	
    	while (list.size() < 3) {
    	    int randomNumber = Randoms.pickNumberInRange(1, 9);
    	    
    	    if (!list.contains(randomNumber)) {
    	    	list.add(randomNumber);
    	    }
    	}
    	
    	return list;
    }
    
    public static void game() {
    	
    	while(strike!=3) {
    		ball=0;
    		strike=0;
    		
    		inputNumber = inputNumber();
    		
    		exceptionCheck = checkException();
    		
    		if(exceptionCheck) {
    			compare(computer,inputNumber);
    			resultPrint(ball, strike);
    		}
    		else {
    			throw new IllegalArgumentException();
    		}
    	}
    }
    
    public static String inputNumber() {
    	
    	System.out.print(inputMessage);
    	
    	String s = Console.readLine();
    	
    	return s;
    }
    
    public static boolean checkException() {
    	if(inputNumber.length()==3&&!inputNumber.contains("0")&&checkType(inputNumber)&&checkDiff(inputNumber)) {
    		return true;
    	}else
    		return false;
    }
    
    public static boolean checkType(String inputNumber) {
    	
    	char[] arr = inputNumber.toCharArray();
    	boolean check = true;
    	
    	for(int i=0;i<arr.length;i++) {
    		if(!Character.isDigit(arr[i])) check = false;
    	}
    	
    	if(check)
    		return true;
    	else
    		return false;
    }

    public static boolean checkDiff(String inputNumber) {
    	char[] arr = inputNumber.toCharArray();
    	Set<Character> set = new HashSet<>();
    	
    	for(int i=0;i<arr.length;i++) {
    		set.add(arr[i]);
    	}
    	
    	if(set.size()<3)return false;
    	else return true; 
    }

    public static void compare(List<Integer> computer, String inputNumber) {
    	char[] arr = inputNumber.toCharArray();
    	for(int i=0;i<arr.length;i++) {
    		if(computer.indexOf(Character.getNumericValue(arr[i]))==i) {
    			strike+=1;
    		}else if(computer.contains(Character.getNumericValue(arr[i])))
    			ball+=1;
    		}
    }
    
    public static void resultPrint(int ball, int strike) {
    	
    	if(strike==0&&ball==0) {
    		System.out.println("낫싱");
    	}
    	else if(strike<3&&ball==0) {
    		System.out.println(strike + "스트라이크");
    	}
    	else if(strike==0&&ball!=0) {
    		System.out.println(ball + "볼");
    	}
    	else if(strike==3) {
    		start = successMessage();
    	}
    	else {
    		System.out.println(ball+"볼"+" "+strike + "스트라이크");
    	}
    }
    
    public static int successMessage() {
    	String coin = "";
    	System.out.println(strike+"스트라이크");
		System.out.println(strike+"개의 숫자를 모두 맞히셨습니다! 게임 종료");
		System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
		coin = Console.readLine();
		if(coin.equals("1")) {
			return 1;
		}else if(coin.equals("2")) {
			return 2;
		}else {
			throw new IllegalArgumentException();
		}
    }
    
}
