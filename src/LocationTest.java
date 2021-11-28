import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static org.junit.jupiter.api.Assertions.*;



//import org.junit.Test;

class LocationTest {


    @Test
    void testDistance(){
        Location firstLoc = new Location(1.7,2.8,3.0);
        Location secondLoc = new Location(2.0,3.7,4.0);

        double distance = firstLoc.distance(secondLoc);
        NumberFormat nf = new DecimalFormat("#0.00000");
        distance = Double.parseDouble(nf.format(distance));
        assertEquals(distance, Double.parseDouble(nf.format(Math.sqrt(1.9))));

        secondLoc = new Location(1.7,2.8,3.0);

        distance = firstLoc.distance(secondLoc);;
        assertEquals(distance,0);
    }


}