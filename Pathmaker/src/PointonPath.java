
public class PointonPath {
	protected double x;
	protected double y;
	protected double xft;//convert pixels to feet, look up conversion , field is 54/27ft.
	protected double yft;
	double xconv=27d/*ft*//450/*pixels*/;
	double yconv=54d/*ft*//900/*pixels*/;
	int precision =2;
	PointonPath(double x_,double y_){
		x=x_;
		y=y_;
		xft=Math.floor(x*xconv*Math.pow(10, precision))/Math.pow(10, precision);
		yft=Math.floor(y*yconv*Math.pow(10, precision))/Math.pow(10, precision);
	}
}
