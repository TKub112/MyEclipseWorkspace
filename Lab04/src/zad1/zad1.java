package zad1;
import java.text.CollationKey;
import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

public class zad1 {
	public static void main(String[] args) {
	
		String[] imiona = {"Lukasz", "Lucyna", "Marek", "Malgosia","Jarek"};
        Collator collator = Collator.getInstance(new Locale("pl", "PL"));
        String[] imiona2=imiona;
        long start=System.nanoTime();
        for(int i=0;i<100000;i++){
            sortStrings(collator,imiona2);
            //imiona2=imiona;
        }
        long stop=System.nanoTime();
        System.out.println(stop-start);
        
        start=System.nanoTime();
        for(int i=0;i<100000;i++){
            
            //imiona2=imiona;
        }
        stop=System.nanoTime();
        System.out.println(stop-start);
	
	}
	

    public static void fastSortStrings(Collator collator, String[] words){
        Arrays.sort(words, new Comparator() {
            public int compare(Object o1, Object o2) {
                String stringOne = (String)o1;
                String stringTwo = (String)o2;
                return collator.compare(stringOne, stringTwo);
            }
        });
    }
    
    

    public static void fastSortStrings2(final Collator collator, String[] words) {
        Arrays.sort(words, (string, string1) -> collator.compare(string, string1));
    }

	
	
	public static void sortStrings(Collator collator, String[] words) {

		int i,j;
		String key;
		for (j = 1; j < words.length; j++) {
	        key = words[j];
	        i = j - 1;
	        while (i >= 0) {
	            if (collator.compare(words[j], key) > 0 && j>=0) {
	                break;
	            }
	            words[i + 1] = words[i];
	            i--;
	        }
	        words[i + 1] = key;
	        
	    }

	}
	

	

}
