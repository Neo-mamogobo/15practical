import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> anagram = new HashMap<>();

        try{
            BufferedReader reader = new BufferedReader(new FileReader("joyce1922_ulysses.text"));
            String line;

            while((line = reader.readLine()) != null){
                line = line.toLowerCase();
                line = line.replaceAll("[^a-zA-z']", " ");
                String[] words = line.split("\\s+");

                for(String word : words){
                    if(word.isEmpty()) continue;

                    String sig = signature(word);
                    if(!anagram.containsKey(sig)){
                        anagram.put(sig, new ArrayList<>());
                    }
                    if(!anagram.get(sig).contains(word)){
                        anagram.get(sig).add(word);
                    }
                }
            }
            reader.close();
        }catch (IOException e){
            System.out.println("Error" + e);
        }
        for(String sig : anagram.keySet()){
            ArrayList<String> list = anagram.get(sig);
            if(list.size() > 1){
                System.out.println("The anagrams are " + sig + " : " + list);
            }
        }
        try{
            PrintWriter tex = new PrintWriter(new FileWriter("theanagrams.tex"));
            for(String sig : anagram.keySet()){
                ArrayList<String> list = anagram.get(sig);
                if(list.size() > 1){
                    tex.print("\\noin");

                    for(String w : list){
                        tex.print(w + " ");
                    }
                }
                tex.close();
            }
        }catch (IOException e){
            System.out.println("Error" + e);
        }

    }

    private static String signature(String word) {
        char[] arr = word.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}