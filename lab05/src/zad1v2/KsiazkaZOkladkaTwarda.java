package zad1v2;

public class KsiazkaZOkladkaTwarda extends Decorator{
	String okladka = "okladka twarda";
	Publikacja publikacja;
	KsiazkaZOkladkaTwarda(Publikacja publikacja) {
        super(publikacja);
        
        this.publikacja = publikacja;
        publikacja.setOkladka();
    }
     @Override
    public String toString() {
        return super.toString() + '|' + okladka;
    }
     

}
