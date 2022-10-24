import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class SearchIn {
    private List<String> dictionary;
    private List<Integer> isCh;
    private Boolean isFull;

    public SearchIn() {
        dictionary = new ArrayList<>();
    }

    List<List<String>> find(){
        List<List<String>> toReturn = new ArrayList<>();
        int skipLine = 0;
        for(var w1: dictionary){
            List<String> wordBuild = new ArrayList<>();
            isCh = new ArrayList<>();
            isCh.add(skipLine);
            isFull = false;
            int skip = 0;
            for(var w2: dictionary){
                if(w1.contains(w2) && !(w2.equals(w1))){
                    String[] wordSplit = w1.split(w2);
                    for(int i = 0; i < wordSplit.length; i++){
                        isCh.add(skip);
                        List<String> tmp;
                        if(!wordSplit[i].equals("")) {
                            tmp = find(wordSplit[i]);
                            if (tmp.size() == 0)
                                break;
                            for (var t : tmp)
                                wordBuild.add(t);
                        }
                    }
                    skip++;
                    if(isFull) {
                        wordBuild.add(w2);
                        break;
                    }
                    isCh = new ArrayList<>();
                    isCh.add(skipLine);
                }
            }
            skipLine++;
            if(isFull){
                wordBuild.add(w1);
                toReturn.add(wordBuild);
            }
        }
        return toReturn;
    }

    List<String> find(String word){
        List<String> toReturn = new ArrayList<>();
        int toSkip = 0;
        for (var w: dictionary) {
            if(word.contains(w) && !isCh.contains(toSkip)){
                if(w.equals(word)){
                    toReturn.add(w);
                    isFull = true;
                    return toReturn;
                }
                else{
                    String[] wordSplit = word.split(w);
                    for(int i = 0; i < wordSplit.length; i++){
                        isCh.add(toSkip);
                        List<String> tmp = find(wordSplit[i]);
                        for(var t: tmp)
                            toReturn.add(t);
                        toReturn.add(w);
                        if(isFull)
                            return toReturn;
                    }
                }
            }
            toSkip++;
        }
        return toReturn;
    }

    public Boolean readFromFile(String path) throws IOException {
        FileReader file = new FileReader(path);
        Scanner scan = new Scanner(file);

        while (scan.hasNext()) {
            String[] words = scan.nextLine().split(" ");
            for(int i = 0; i < words.length; i++)
                dictionary.add(words[i]);
        }

        file.close();

        return true;
    }

}
