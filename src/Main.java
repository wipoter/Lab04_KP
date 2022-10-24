import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("From 'ReadEng.txt':");
        //eng
        SearchIn searchIn = new SearchIn();
        searchIn.readFromFile("ReadEnd.txt");
        List<List<String>> eng;
        eng = searchIn.find();
        output(eng);
        System.out.println();System.out.println();


        System.out.println("From 'ReadUkr.txt':");
        //ukr
        searchIn = new SearchIn();
        searchIn.readFromFile("ReadUkr.txt");
        List<List<String>> ukr;
        ukr = searchIn.find();
        output(ukr);
    }

    static void output(List<List<String>> str){
        for(var item: str){
            for(var word: item){
                if(word != item.get(item.size()-1)){
                    System.out.print(word + " + ");
                }
                else{
                    System.out.print("-> " + word);
                }
            }
            System.out.println();
        }
    }
}