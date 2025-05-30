import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


	abstract class Shape {
    
    int x1,y1, x2,y2;
    Color color;
    boolean solid;
    //boolean normal; //1-remove normal and let solid filled
    boolean dotted;
        
    public abstract void draw(Graphics g);
    


    public void setX1(int x1) {
        this.x1 = x1;
    }
	
	    public int getX1() {
        return x1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY1() {
        return y1;
    }
	
	public void setX2(int x2) {
    this.x2 = x2;
    }
	
    public int getX2() {
        return x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getY2() {
        return y2;
    }

    public void setColor(Color color) {
        this.color = color;
    }
	
	public Color getColor() {
    return color;
    }
	
    public void setSolid(boolean solid) {
        this.solid = solid;
    }
	
    public boolean getSolid() {
        return solid;
    }


    public void setProperties(int x1, int y1, int x2, int y2, Color c, boolean solid, boolean dotted) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.color = c;
		this.solid = solid;
		this.dotted = dotted;
	}
    
    
}