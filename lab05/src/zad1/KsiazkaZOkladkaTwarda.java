package zad1;

public class KsiazkaZOkladkaTwarda extends Decorator{
	String okladka = "okladka twarda";
	
	KsiazkaZOkladkaTwarda(Publikacja publikacja) {
        super(publikacja);
    }
     @Override
    public String toString() {
        return super.toString() + '|' + okladka;
    }
     

}
