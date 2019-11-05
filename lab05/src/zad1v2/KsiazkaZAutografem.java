package zad1v2;

public class KsiazkaZAutografem extends Decorator{
	String autograf = "";
	Publikacja publikacja;
	KsiazkaZAutografem(Publikacja publikacja,String autograf) {
        super(publikacja);
        this.publikacja = publikacja;
        this.autograf = autograf;
    }
	@Override
    public String toString() {
		if(publikacja.getAutograf()==false)
		{
			publikacja.setAutograf();
			return super.toString() + '|' + autograf;
		}
		else {
			System.out.println("ksiazka posiada autograf");
		}
		return null;
    }
}
