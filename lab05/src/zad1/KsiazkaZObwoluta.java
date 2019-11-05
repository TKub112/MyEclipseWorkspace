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
		
		try {
			check();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		publikacja.setObwoluta();
        return publikacja.toString() + '|' + obwoluta;
    }
	
	
	private void check() throws Exception {
		if(publikacja.getOkladka()==false){
			throw new Exception("Ksiazka nie ma okladki exception");
		}
		
	}
	
	
}
