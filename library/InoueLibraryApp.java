package library;
/*------------------//
//図書館システム	//
//製作者：井上由太	//
//------------------*/
public class InoueLibraryApp {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ		
				
		// オリジナルスキャナークラス
		MyScanner scan = new MyScanner();
		// PCクラス
		PCsystem pc = new PCsystem();
		
		// 初期の本を読み込む
		pc.initBook();
		
		int choice = -1;
		while (choice != 0) {

			scan.outputSentence("-----------------------------");
			scan.outputSentence("Input 0 : End");
			scan.outputSentence("Input 1 : Register");
			scan.outputSentence("Input 2 : Read");
			scan.outputSentence("Input 3 : Edit");
			scan.outputSentence("Input 4 : Remove");

			choice = scan.inputInteger();

			switch (choice) {
			case 0:
				scan.outputSentence(" Bye");
				break;
			case 1:
				pc.Register();
				break;
			case 2:
				pc.Read();
				break;
			case 3:
				pc.Edit();
				break;
			case 4:
				pc.Remove();
				break;
			default:
				scan.outputSentence("Only Input 0~4");
				break;
			}
		}

		// 終了時にcsvファイルに本を書き込む
		pc.csvWriteIn();
	}

}