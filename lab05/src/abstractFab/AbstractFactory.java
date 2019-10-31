package abstractFab;

public interface AbstractFactory<T> {
    T create(String animalType) ;
}