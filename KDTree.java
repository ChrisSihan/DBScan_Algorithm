import java.util.List;

public class KDTree {

    public class KDNode{
        public Point3D point;
        public int axis;
        public double value;
        public KDNode left;
        public KDNode right;
        public KDNode(Point3D pt, int axis){
            this.point = pt;
            this.axis = axis;
            this.value = pt.get(axis);
            left = right = null;
        }
    }
    private KDNode root;
    public KDTree(){
        root = null;
    }
    public  KDNode getRoot(){
        return root;
    }
    public void insert(Point3D p){
        if(root == null){
            root = insert(p,root,0);
        }else{
            insert(p,root,0);
        }
    }
    public KDNode insert(Point3D p, KDNode node,int axis){
        if(node == null){
            node = new KDNode(p,axis);
        }else if(p.get(axis) <= node.value){
            node.left = insert(p,node.left,(axis+1)%3);
        }else{
            node.right = insert(p,node.right,(axis+1)%3);
        }
        return node;
    }
    public void rangeQuery(Point3D p, double eps, List<Point3D> neighbours, KDNode node){
        if(node == null){
            return;
        }
        if (p.distance(node.point) < eps) {
            neighbours.add(node.point);
        }
        if(p.get(node.axis) - eps <= node.value){
            rangeQuery(p,eps,neighbours,node.left);
        }
        if(p.get(node.axis) + eps > node.value){
            rangeQuery(p,eps,neighbours,node.right);
        }
    }
}
