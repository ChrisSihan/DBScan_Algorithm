import java.util.LinkedList;
import java.util.List;

public class NearestNeighbors {
	private List<Point3D> points;
	
	public NearestNeighbors(List<Point3D> points) {
		this.points = points;
	}
	
	public List<Point3D> rangeQuery(Point3D Q, double eps){
		List<Point3D> neighbors = new LinkedList<>();
		for (Point3D point : points) {
			if (Q.distance(point) <= eps) {
				neighbors.add(point);
			}
		}
		return neighbors;
	}

}
