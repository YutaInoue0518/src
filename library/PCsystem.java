package library;
/*------------------//
//本棚を管理するPC	//
//製作者：井上由太	//
//------------------*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

//PCクラス
public class PCsystem {

    private Bookshelf bookshelf;
    private ArrayList<Book> lendinglist;

    private MyScanner scan = new MyScanner();
    
    // csvファイル名
    private String file = "src\\book.csv";
    private BufferedReader reader = null;
    private String line = "";


    // コンストラクタ
    public PCsystem() {
    	bookshelf = new Bookshelf();
    	lendinglist = new ArrayList<Book>();
    }
    
    // 初期の本をcsvで読み込んで登録する
    public void initBook() {
    	
    	// 初期値設定用の変数
    	int[] isbnCode = null;		// ISBNコード　
        String title = null;		// 題名
        String author = null;		// 作者
        String publisher = null;	// 出版社
        int year = 0;				// 出版年
        boolean lending = false;	// 貸し出し中かどうか（true=貸し出し中）

        int cnt = 0;		// 判定用
    	
    	try {
			reader = new BufferedReader(new FileReader(file));
			while((line = reader.readLine()) != null) {
				String[] row = line.split(",");
				
				for(String index : row) {
					switch(cnt) {
					case 0:
						Long num = Long.valueOf(index);
						isbnCode = toArray(num);
						
						cnt++;
						break;
					case 1:
						title = index;
						cnt++;
						break;
					case 2:
						author = index;
						cnt++;
						break;
					case 3:
						publisher = index;
						cnt++;
						break;
					case 4:
						year = Integer.valueOf(index);
						cnt++;
						break;
					case 5:
						lending = Boolean.valueOf(index);
						cnt = 0;
						break;
					default:
						scan.outputSentence("Init Books Error!");
						break;
					}
				}
				bookshelf.addBook(new Book(isbnCode, title, author, publisher, year, lending));
				//System.out.println();
			}
		}catch(Exception e){		
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
    }
    
    // 最後に本の情報をcsvファイルに書き込む
    public void csvWriteIn() {
    	
    	try {
            //出力先を作成する
            FileWriter fw = new FileWriter(file, false);	// falseモードで上書き
            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
    		
            //内容を更新する
            ArrayList<Book> data = bookshelf.listInfo();
            for(int i = 0;i < data.size();i++) {
            	for(int j = 0;j < 6;j++) {
            		switch(j) {
            		case 0:
            			pw.print(toLong(data.get(i).getisbnCode()));
            			break;
            		case 1:
            			pw.print(data.get(i).getTitle());
            			break;
            		case 2:
            			pw.print(data.get(i).getAuthor());
            			break;
            		case 3:
            			pw.print(data.get(i).getPublisher());
            			break;
            		case 4:
            			pw.print(data.get(i).getYear());
            			break;
            		case 5:
            			pw.print(data.get(i).getLending());
            			break;
            		}
            		pw.print(",");
            	}
            	pw.println();;
            }
            
            // ファイルに書き出す
            pw.close();
            
    	}catch(IOException e) {
    		//例外時処理
            e.printStackTrace();
    	}
    }
    
    // 登録
    public void Register() {
        
        int[] isbn = null;
        String title,author,publisher = null; 
        int year = 0;
        boolean lending = false;
        scan.outputSentence("Start registering a book!");
        isbn = toArray(scan.inputLong("Input ISBNcode(13 digits)"));
        scan.inputEnter(); // 勝手にエンターをされないように
        title = scan.inputString("Input Title");
        author = scan.inputString("Input Author");
        publisher = scan.inputString("Input Publisher");
        year = scan.inputInteger("Input Year");

        int choice = scan.inputInteger("OK? Yes=1 / No=2");
        if(choice == 1) {
            bookshelf.addBook(new Book(isbn, title, author, publisher, year, lending));
            scan.outputSentence("Complete Register");
        }else {
            scan.outputSentence("Cancelled");
        }
    }

    // 読み込み
    public void Read() {
        int num = scan.inputInteger("All show=1 / Designate book=2");
        if(num == 1) {
            ArrayList<Book> list = bookshelf.listInfo();
            if(list.size() > 0) {
            	for(int i = 0;i < list.size();i++) {
            		scan.outputSentence(String.valueOf(i), ":", list.get(i).getTitle());
            	}
            }
        }else{
            int check = scan.inputInteger("ISBNcode = 0 / Title = 1 / Author = 2 / Publisher = 3 / Year = 4 / Lending = 5");
            switch(check) {
            case 0:
            	bookshelf.Sisbncode(toArray(scan.inputLong("What is the ISBNcode?")));
            	break;
            case 1:
            	scan.inputEnter();
                bookshelf.Stitle(scan.inputString("What is the title?"));
                break;
            case 2:
            	scan.inputEnter();
                bookshelf.Sauthor(scan.inputString("Who is the author?"));
                break;
            case 3:
            	scan.inputEnter();
                bookshelf.Spublisher(scan.inputString("Where is the publisher?"));
                break;
            case 4:
                bookshelf.Syear(scan.inputInteger("When is the year?"));
                break;
            case 5:
                bookshelf.Slending();
                break;
            default:
                scan.outputSentence("Only Input 0~5");
                break;
            }
        }
    }

    // 編集(貸出と返却)
    public void Edit() {

    	int choice = scan.inputInteger("lend book = 1 / return book = 2");
    	if(choice == 1)
    	{
            // 貸し出し可能な本を表示
        	lendinglist = bookshelf.lendBookList(false);
        	// 貸し出し可能な本がある場合は選択画面
        	if(lendinglist.size() > 0) {
            	for(int i = 0;i < lendinglist.size();i++) {
            		scan.outputSentence(String.valueOf(i), ":", lendinglist.get(i).getTitle());
            	}
            	
            	int choiceBook = scan.inputInteger("Please enter the number you want to borrow");
            	// ISBNコードから変更する
            	bookshelf.SisbncodeChange(lendinglist.get(choiceBook).getisbnCode());
            	scan.outputSentence("lent out ",lendinglist.get(choiceBook).getTitle());
        	}else {
        		// 貸し出し可能な本がない場合は"ありません"表示
        		scan.outputSentence("Sorry...No books to borrow now");
        	}
        	
        	
    	}else if(choice == 2) {
    		// 返却可能な本を表示
        	lendinglist = bookshelf.lendBookList(true);
        	// 返却可能な本がある場合は選択画面
        	if(lendinglist.size() > 0) {
            	for(int i = 0;i < lendinglist.size();i++) {
            		scan.outputSentence(String.valueOf(i), ":", lendinglist.get(i).getTitle());
            	}
            	int choiceBook = scan.inputInteger("Please enter the number you want to return");
            	// ISBNコードから変更する
            	bookshelf.SisbncodeChange(lendinglist.get(choiceBook).getisbnCode());
            	scan.outputSentence("returned ",lendinglist.get(choiceBook).getTitle());
        	}else {
        		// 返却可能な本がない場合は"ありません"表示
        		scan.outputSentence("Oh...No books to return now");
        	}
    	}

    	
    }

    // 削除
    public void Remove() {
        ArrayList<Book> list = bookshelf.listInfo();
        if(list.size() > 0) {
        	for(int i = 0;i < list.size();i++) {
        		scan.outputSentence(String.valueOf(i), ":", list.get(i).getTitle());
        	}
        	int choiceBook = scan.inputInteger("Please enter the number you want to remove");
        	// ISBNコードから変更する
        	//bookshelf.delBook(choiceBook);
        	scan.outputSentence(bookshelf.delBook(choiceBook)," Removed from the bookshelf");

        }

    }

    
    // 数値を配列に一桁ずつ格納する関数
    private int[] toArray(Long num) {

    	// 桁数に合わせた配列を作成
    	int [] numArray = new int[Long.toString(num).length()];
    	
    	// 配列numArrayへ数値numを配置
    	for (int j = numArray.length - 1; j >= 0; j--) {
    	    Long d = num / 10;
    	    Long k = num - d * 10;
    	    num = d;
    	    numArray[j] = Math.toIntExact(k);
    	}
    	return numArray;
    }
    
    // 配列を数値に変換する関数
    private Long toLong(int[] array) {
    	Long code = 0L;
    	int digit = 1;
    	for(int i = array.length- 1;i >= 0;i--) {
    		code += array[i] * digit;
    		digit *= 10;
    	}
    	return code;
    }
}