
public class Point3D {
	private double x, y, z;
	private double r, g, b;
	private int label;

	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getZ() {
		return z;
	}
	
	public void setZ(double z) {
		this.z = z;
	}

	
	public double getR() {
		return r;
	}
	
	public void setR(double r) {
		this.r = r;
	}
	
	public double getG() {
		return g;
	}
	
	public void setG(double g) {
		this.g = g;
	}
	
	public double getB() {
		return b;
	}
	
	public void setB(double b) {
		this.b = b;
	}

	
	
	public int getLabel() {
		return label;
	}
	
	public void setLabel(int label) {
		this.label = label;
	}
	
	public double get(int axis) {
		switch(axis) {
		case 0: return x;
		case 1: return y;
		case 2: return z;
		default: return 0.0;
		}		  
	}
	
	
	
	public double distance(Point3D pt) {
		return Math.sqrt(Math.pow(x - pt.x, 2) + Math.pow(y - pt.y, 2) + Math.pow(z - pt.z, 2));
	}
	
	public String toString() {
		return x + "," + y + "," + z + "," + label + "," + r + "," + g + "," + b;				
	}
}
