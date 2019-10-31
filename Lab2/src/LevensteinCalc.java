
 
public class LevensteinCalc {
private static double levenshtein(String s, String t)
{
	int i, j, m, n;
	double cost;
	double d[][];
	KeyChecker keyCheck = new KeyChecker();
	keyCheck.init();
	
	m = s.length();
	n = t.length();
	 
	d = new double[m+1][n+1];
	 
	for (i=0; i<=m; i++)
	d[i][0] = i;
	
	for (j=1; j<=n; j++)
	d[0][j] = j;
	 
	for (i=1; i<=m; i++){
		for (j=1; j<=n; j++){
		if (s.charAt(i-1) == t.charAt(j-1)){
			cost = 0;
		}
		else
		{
			if(keyCheck.isNeighbor(s.charAt(i-1) , t.charAt(j-1))){
				cost=0.5;
			}else
				cost = 1;
		}
		 
		d[i][j] = Math.min(d[i-1][j] + 1,    // delete
		Math.min(d[i][j-1] + 1,      //insert 
		d[i-1][j-1] + cost));   // sub 
		}
	}
	 
	return d[m][n];
}
 

public double Calc(String s1, String s2) {

 
System.out.println("Odleglosc Levenshteina wynosi: " + levenshtein(s1, s2));
return levenshtein(s1, s2);
}
 
}