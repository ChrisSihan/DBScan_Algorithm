import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DBScan {
	private static List<Point3D> points;
	private double eps, minPts;
	private int C;
	
	public DBScan(List<Point3D> points) {
		this.points = points;
	}
	
	public void setEps(double eps) {
		this.eps = eps;
	}
	
	public void setMinPts(double minPts) {
		this.minPts = minPts;
	}
	public List<Point3D> getPoints(){
		return points;
	}
	
	
	public void findClusters() {
		C = 0;
		NearestNeighborsKD nearestNeighbors = new NearestNeighborsKD(points);
		for (Point3D point : points) {
			if (point.getLabel() != 0) {
				continue;
			}
			List<Point3D> N = nearestNeighbors.rangeQuery(point, eps);
			if (N.size() < minPts) {
				point.setLabel(-1);
				continue;
			}
			C ++;
			point.setLabel(C);
			Stack<Point3D> stack = new Stack<>();
			for (Point3D p : N) {
				stack.push(p);
			}
			while (!stack.isEmpty()) {
				Point3D Q = stack.pop();
				if (Q.getLabel() == -1) {
					Q.setLabel(C);
				}
				if (Q.getLabel()!= 0) {
					continue;
				}
				Q.setLabel(C);
				List<Point3D> QN = nearestNeighbors.rangeQuery(Q, eps);
				if (QN.size() >= minPts) {
					for (Point3D p : QN) {
						stack.push(p);
					}
				}	
			}
		}
		for (Point3D point : points) {
			point.setR((double) point.getLabel() / C);
			point.setG((double) point.getLabel() / C);
			point.setB((double) point.getLabel() / C);
		}
		
	}
	public int getNumberOfClusters() {
		return C;
	}
	
	public static List<Point3D> read(String fileName){
		List<Point3D> points = new LinkedList<>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = null;
			int lineNumber = 0;
			while ((line = reader.readLine())!=null) {
				lineNumber ++;
				if (lineNumber <= 2) {
					continue;
				}
				String[] coordinates = line.split(",");
				double x = Double.parseDouble(coordinates[0]);
				double y = Double.parseDouble(coordinates[1]);
				double z = Double.parseDouble(coordinates[2]);
				Point3D point = new Point3D(x, y, z);
				points.add(point);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return points;
	}
	public void save(String filename) {
		try {
			FileWriter writer = new FileWriter(filename);
			for (Point3D point : points) {
				writer.write(point.toString());
				writer.write("\n");
			}
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[]args) {
		List<Point3D> points = read("Point_Cloud_1.csv");;
		NearestNeighbors nearestNeighbors = new NearestNeighbors(points);
		NearestNeighborsKD nearestNeighborsKD = new NearestNeighborsKD(points);
		List<Point3D> neighbors;
		
		Point3D p1 = new Point3D(-5.429850155, 0.807567048, -0.398216823);
		neighbors = nearestNeighbors.rangeQuery(p1, 0.05);
		System.out.println(String.format("P1 neighbors size: %d", neighbors.size()));
		System.out.println(neighbors);
		neighbors = nearestNeighborsKD.rangeQuery(p1, 0.05);
		System.out.println(String.format("P1 neighborsKD size: %d", neighbors.size()));
		System.out.println(neighbors);
	}
	
}

	


