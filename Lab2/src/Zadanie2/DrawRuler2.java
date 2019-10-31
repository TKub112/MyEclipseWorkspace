package Zadanie2;

/**
 * Narysuj w konsoli, z wykorzystaniem rekurencji, pionow¹ linijkê 
 *  draw(d³ugoœæ w danych jednostkach, liczba poziomów zagnie¿d¿eñ). 
 * @author Tobiasz
 *
 */

public class DrawRuler2 {

    public static void main(String[] args) {
        myMethod(2,5);
        //draw(2,4,4);
    }

    public static void draw(int n, int recursions, int prime_recursion){  //rysuje n-ty segment linijki (od numeru do numeru) o zadanym zagniezdzeniu
        int i;
        if(recursions == 1){
            for (i=0; i<prime_recursion-1;i++){
                System.out.print(" ");
            }
            System.out.print("--");
            if(prime_recursion==1){
                System.out.println(" 1");
            }else{
                System.out.println();
            }
        }
        else{
            draw(0,recursions-1,prime_recursion);
            for(i = 0; i<(prime_recursion-recursions);i++){
                System.out.print(" ");
            }
            for(i = 0; i<(recursions);i++){
                System.out.print("--");
            }

            if  (n!=0){
                System.out.println(" "+n);
                }
            else{
                System.out.println(" ");
                draw(0,recursions-1,prime_recursion);
            }

        }


    }

    public static void myMethod(int length, int recursion){
        int currentLength=1;
        for(int i=0; i<recursion;i++){
            System.out.print("--");
        }
        System.out.println(" 0");

        while(currentLength<=length){
            draw(currentLength,recursion,recursion);
            currentLength+=1;
        }

    } //rysuje length segmentowa linijke o zadanym zagniezdzeniu
}



// 4/1/2/1/3/1/2/1/4/1/2/1/3/1/2/1/4

