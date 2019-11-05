package zad1;

public class Decorator implements Publikacja  {

	 	public Publikacja publikacja;
	 	
		public Decorator(Publikacja publikacja2) {
			publikacja = publikacja2;
		}
		
		public String toString()  {
			return getAutor() + getName() + getPages();
			
		     
		}

		@Override
		public String getAutor() {
			// TODO Auto-generated method stub
			return publikacja.getAutor();
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return publikacja.getName();
		}

		@Override
		public int getPages() {
			// TODO Auto-generated method stub
			return publikacja.getPages();
		}

		@Override
		public boolean getObwoluta() {
			// TODO Auto-generated method stub
			return publikacja.getObwoluta();
		}

		@Override
		public void setOkladka() {
			// TODO Auto-generated method stub
			publikacja.setOkladka();
		}

		@Override
		public void setObwoluta() {
			// TODO Auto-generated method stub
			publikacja.setObwoluta();
		}

		@Override
		public boolean getOkladka() {
			// TODO Auto-generated method stub
			return publikacja.getOkladka();
		}

		@Override
		public void setAutograf() {
			publikacja.setAutograf();
			
		}

		@Override
		public boolean getAutograf() {
			// TODO Auto-generated method stub
			return publikacja.getAutograf();
		}
	 	
		



		

}
