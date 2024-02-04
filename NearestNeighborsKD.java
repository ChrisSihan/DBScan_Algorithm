import java.util.LinkedList;
import java.util.List;

public class NearestNeighborsKD {
    private List<Point3D> points;
    private KDTree kdtree;

    public NearestNeighborsKD(List<Point3D> points) {
        this.points = points;
        kdtree = new KDTree();
        for(Point3D p : points){
            kdtree.insert(p);
        }
    }
    public List<Point3D> rangeQuery(Point3D p, double eps) {
        List<Point3D> neighbours = new LinkedList<>();
        kdtree.rangeQuery(p,eps,neighbours,kdtree.getRoot());
        return neighbours;
    }

}
