package canvas; 

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;

import org.apache.log4j.Logger;

/**
 * <pre>
 * canvas 
 * point1.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 11. 1.
 * @author : ymg74
 * @version : v1.0
 */
public class Point1 extends Point2D implements java.io.Serializable {
	
    public int x;
    Color color = Color.RED;
    public int y;

    private static final long serialVersionUID = -5276940640259749850L;

    public Point1() {
        this(0, 0);
    }

    public Point1(Point1 p) {
        this(p.x, p.y);
    }

    public Point1(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }

    public Color getColor() {
    	return color;
    }
	@Override
	public void setLocation(double x, double y) {
		// TODO Auto-generated method stub
		
	}
}
