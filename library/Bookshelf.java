package library;
/*------------------//
//本を格納する本棚	//
//製作者：井上由太	//
//------------------*/
import java.util.ArrayList;
import java.util.Arrays;

//本棚クラス
public class Bookshelf {
	
    // 本を収納するリスト
    private ArrayList<Book> booklist = new ArrayList<Book>();

    public Bookshelf() {
    }

    
    // 本棚の一覧を戻り値として返す
    public ArrayList<Book> listInfo() {
        return booklist;
    }

    
    // 本棚の貸し出し/返却可能な本を渡す（配列を渡す）
    public ArrayList<Book> lendBookList(boolean status){
    	ArrayList<Book> lendinglist = new ArrayList<Book>();
        for(int i = 0;i < booklist.size();i++) {
            if(booklist.get(i).getLending() == status) {
            	lendinglist.add(booklist.get(i));
            }
        }
		return lendinglist;
    }
    
    // 指定した本を追加する
    public void addBook(Book book) {
        booklist.add(book);
    }

    // 指定した本を探して削除する
    public String delBook(int index) {
    	String del = booklist.get(index).getTitle();
    	booklist.remove(index);
    	return del;

    }

    // 本を項目別で探す関数----------------------------------
    public void Stitle(String str) {
        for(int i = 0;i < booklist.size();i++) {
            if(booklist.get(i).getTitle().equals(str)) {
                booklist.get(i).info();
            }
        }
    }

    public void Sauthor(String str) {
        for(int i = 0;i < booklist.size();i++) {
            if(booklist.get(i).getAuthor().equals(str)) {
                booklist.get(i).info();
            }
        }
    }

    public void Spublisher(String str) {
        for(int i = 0;i < booklist.size();i++) {
            if(booklist.get(i).getPublisher().equals(str)) {
                booklist.get(i).info();
            }
        }
    }

    public void Syear(int num) {
        for(int i = 0;i < booklist.size();i++) {
            if(booklist.get(i).getYear() == num) {
                booklist.get(i).info();
            }
        }
    }

    
    public void Sisbncode(int[] num) {
        for(int i = 0;i < booklist.size();i++) {
            if(Arrays.equals(booklist.get(i).getisbnCode(), num)) {
                booklist.get(i).info();
            } 
        }
    }
    
    // 貸し出しを変更するために使う
    public void SisbncodeChange(int[] num) {
        for(int i = 0;i < booklist.size();i++) {
            if(Arrays.equals(booklist.get(i).getisbnCode(), num)) {
            	booklist.get(i).setLending();
            } 
        }
    }
    

    public void Slending() {
        for(int i = 0;i < booklist.size();i++) {
            if(booklist.get(i).getLending() == false) {
                booklist.get(i).info();
            }
        }

    }

    //---------------------------------------------------
    
    // 本棚リストを返す
    public ArrayList<Book> Getlist(){
    	return booklist;
    }

}