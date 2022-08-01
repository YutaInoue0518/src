package library;
import java.util.InputMismatchException;
import java.util.Scanner;
/*------------------------------//
//オリジナルスキャナークラス	//
//製作者：井上由太				//
//------------------------------*/
public class MyScanner {

    Scanner sc = new Scanner(System.in);

    // 文字をアウトプットする関数----------------------
    public void outputSentence(String sentence) {
        System.out.println(sentence);
    }

    public void outputSentence(String sentence,String sentence2) {
        System.out.println(sentence + sentence2);
    }
    
    public void outputSentence(String sentence,String sentence2,String sentence3) {
        System.out.println(sentence + sentence2 + sentence3);
    }
    
    // Stringを返す関数-------------------------------
    public String inputString(String sentence) {
        String answer;
        System.out.println(sentence);
        answer = sc.nextLine();
        return answer;
    }


    // intを返す関数----------------------------------
    public int inputInteger(String sentence) {
        int answer = 0;
        System.out.println(sentence);
     // 数値以外が入力された場合
        try {
            answer = sc.nextInt();
        }catch(InputMismatchException e){
        	System.out.println("Please enter only numeric values!");
        	this.inputEnter();
        	answer = this.inputInteger(sentence);
        }
        return answer;
    }
    
    public int inputInteger(String sentence,String sentence2) {
        int answer = 0;
        System.out.println(sentence + sentence2);
        // 数値以外が入力された場合
        try {
            answer = sc.nextInt();
        }catch(InputMismatchException e){
        	System.out.println("Please enter only numeric values!");
        	this.inputEnter();
        	answer = this.inputInteger(sentence,sentence2);
        }

        return answer;
    }

    public int inputInteger() {
        int answer = 0;
        // 数値以外が入力された場合
        try {
            answer = sc.nextInt();
        }catch(InputMismatchException e){
        	System.out.println("Please enter only numeric values!");
        	this.inputEnter();
        	answer = this.inputInteger();
        }

        return answer;
    }
    
    // Longを返す関数(ISBN番号用)---------------------
    public Long inputLong(String sentence) {
        Long answer = 0L;
        System.out.println(sentence);
        // 数値以外が入力された場合
        try {
        	answer = sc.nextLong();
        }catch(InputMismatchException e){
        	System.out.println("Please enter only numeric values!");
        	this.inputEnter();
        	answer = this.inputLong(sentence);
        }

        return answer;
    }

    // 勝手に改行（エンター）しないようにするための関数
    public void inputEnter() {
    	sc.nextLine();
    }
}