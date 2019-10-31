package abstractFab;


public class WydawnictwoFactory implements AbstractFactory<Ksiazka> {
	 
    @Override
    public Ksiazka create(String animalType) {
        if ("Dog".equalsIgnoreCase(animalType)) {
            return new PowiescHistoryczna();
        } else if ("Duck".equalsIgnoreCase(animalType)) {
            return new Thriller();
        }
 
        return null;
    }
 
}