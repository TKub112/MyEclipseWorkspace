package zad1v2;

import zad1.KsiazkaZOkladkaTwarda;
import zad1.KsiazkaZOkladkaZwykla;

public class KsiazkaZObwoluta extends Decorator{
	String obwoluta = "z obwoluta";
	Publikacja publikacja;
	KsiazkaZObwoluta(Publikacja publikacja) {
        super(publikacja);
        this.publikacja = publikacja;
    }
	@Override
    public String toString() {

		if(publikacja.getOkladka())
		{	
			publikacja.setObwoluta();
	        return publikacja.toString() + '|' + obwoluta;
		}
		if(publikacja instanceof KsiazkaZOkladkaZwykla)
		{	
			
			publikacja.setObwoluta();
	        return ((KsiazkaZOkladkaZwykla) publikacja).publikacja.toString() + '|' + obwoluta;
		}
		else {
			System.out.println("ksiazka nie ma okladki aby zalozyc obwoute");
		}
		return null;
		    
    }
}
