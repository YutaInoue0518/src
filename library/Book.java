package library;
/*------------------//
//本の情報			//
//製作者：井上由太	//
//------------------*/
import java.util.Arrays;

// 本クラス
public class Book {

    private int[] isbnCode;        // ISBNコード　
    private String title;        // 題名
    private String author;        // 作者
    private String publisher;    // 出版社
    private int year;            // 出版年
    private boolean lending;    // 貸し出し中かどうか（true=貸し出し中）
    
    // オリジナルスキャナークラス
    private MyScanner scan = new MyScanner();;

    // コンストラクタ
    public Book(int[] isbnCode,String title,String author,String publisher,int year,boolean lending) {
        //isbnCode = new int[13];
        this.isbnCode = isbnCode;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.lending = lending;
    }

    // 本の情報を表示する
    public void info() {        
        scan.outputSentence("----------------------");
        scan.outputSentence("ISBN:" + Arrays.toString(isbnCode));
        scan.outputSentence("Title:" + title);
        scan.outputSentence("Author:" + author);
        scan.outputSentence("Publisher:" + publisher);
        scan.outputSentence("Year:" + year);
        
        if(lending) {
            scan.outputSentence("On loan");
        }else {
            scan.outputSentence("Not on loan");
        }
    }

    
    // セッター（本の編集）
    public void setBook(Book book) {
        this.isbnCode = book.getisbnCode();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.year = book.getYear();
    }

 // ISBNコード取得
    public int[] getisbnCode() {
        return isbnCode;
    }

    // 題名取得
    public String getTitle() {
        return title;
    }

    // 作者取得
    public String getAuthor() {
        return author;
    }

    // 出版社取得
    public String getPublisher() {
        return publisher;
    }

    // 出版年所得
    public int getYear() {
        return year;
    }


    // 貸し出し中かどうか取得
    public boolean getLending() {
        return lending;
    }
    
    // 本の貸し出し変更（セッター）
    public void setLending() {
    	this.lending = !lending;
    }

}