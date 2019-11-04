package zad1;

public class Ksiazka implements Publikacja{
	private String autor;
	private String name;
	private int pages;
	boolean obwoluta = false;
	boolean okladka = false;
	boolean autograf = false;
	
	Ksiazka(String autor,String name,int pages)
	{
		this.autor = autor;
		this.name = name;
		this.pages = pages;
		
	}
	public boolean getOkladka() {
		return okladka;
	}
	
	@Override
	public String getAutor() {
		return autor;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public int getPages() {
		return pages;
	}
	@Override
	public String toString() {
		
		return autor + '|' + name + '|' + pages;
	}
	@Override
	public boolean getObwoluta() {
		return this.obwoluta;
	}
	@Override
	public void setOkladka() {
		this.okladka = true ;
	}
	@Override
	public void setObwoluta() {
		this.obwoluta = true;
		
	}
	@Override
	public void setAutograf() {
		this.autograf = true ;
	}
	@Override
	public boolean getAutograf() {
		
		return this.autograf;
	}

	
}
