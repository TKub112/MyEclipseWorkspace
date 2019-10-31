
public class LuhnAlgorithm
{
	public static void main(String[] args) {
		String ccNumber = "292723200000000021";
		 int sum = 0;
         boolean alternate = false;
         for (int i = ccNumber.length() - 1; i >= 0; i--)
         {
                 int n = Integer.parseInt(ccNumber.substring(i, i + 1));
                 if (alternate)
                 {
                         n *= 2;
                         if (n > 9)
                         {
                                 n = (n % 10) + 1;
                         }
                 }
                 sum += n;
                 alternate = !alternate;
         }
         
       System.out.println((sum % 10 == 0)); 
         
         return ;

	}
}
