package abstractFab;


public class PowiescHistoryczna extends Wydawnictwo {
	 
    PowiescHistoryczna(String autor) {
    	if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new RoundedRectangle();         
         }else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new RoundedSquare();
         }	 
	}
}