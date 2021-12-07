import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class Test_Location {

    @Test
    public void Test_x(){
        Location l = new Location(35.9999,56.888,0.0);
        Assert.assertEquals(35.9999, l.x(),0);
    }

    @Test
    public void Test_y(){
        Location l = new Location(35.9999,56.888,0.0);
        Assert.assertEquals(56.888, l.y(),0);
    }

    @Test
    public void Test_z(){
        Location l = new Location(35.9999,56.888,0.0);
        Assert.assertEquals(0.0, l.z(),0);
    }

    @Test
    public void Test_distance(){
        Location l = new Location(35.9999,56.888,0.0);
        Location r = new Location(44,80.1,0.0);
        Assert.assertEquals(24.551956011894447,l.distance(r),0);
    }
}
