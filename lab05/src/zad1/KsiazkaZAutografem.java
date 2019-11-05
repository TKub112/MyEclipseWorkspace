package zad1;

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
		if(publikacja instanceof KsiazkaZAutografem){
			System.out.println("ksiazka posiada autograf");
		}
		if(publikacja.getAutograf()==false){
			publikacja.setAutograf();
			return publikacja.toString() + '|' + autograf;
		}
		if(publikacja.getAutograf()==true){
			System.out.println("ksiazka posiada autograf");
		}
		return null;
    }
}
