package zad1;

public class KsiazkaZObwoluta extends Decorator{
	String obwoluta = "z obwoluta";
	Publikacja publikacja;
	KsiazkaZObwoluta(Publikacja publikacja) {
        super(publikacja);
        this.publikacja = publikacja;
    }
	@Override
    public String toString() {
		if(publikacja.getOkladka()==false)
		{
			System.out.println("ksiazka nie ma okladki aby zalozyc obwoute");
		}
		publikacja.setObwoluta();
        return super.toString() + '|' + obwoluta;
    }
}
