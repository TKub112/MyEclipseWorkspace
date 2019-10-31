package abstractFab;

public class PowiescHistoryczna {

}
public class PowiescHistoryczna implements Animal {
	 
    @Override
    public String getAnimal() {
        return "Duck";
    }
 
    @Override
    public String makeSound() {
        return "Squeks";
    }
}