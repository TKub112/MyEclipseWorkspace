package zad1;

public class KsiazkaZOkladkaTwarda extends Decorator{
	String okladka = "okladka twarda";
	public Publikacja publikacja;
	KsiazkaZOkladkaTwarda(Publikacja publikacja) {
        super(publikacja);
        
        this.publikacja = publikacja;
        publikacja.setOkladka();
    }
     @Override
    public String toString() {
        return publikacja.toString() + '|' + okladka;
    }
     

}
