package com.teamursamajor.auto;

import java.awt.Graphics2D;

public class Curve {
	
	
	// public void quadBezier (double[] p0, double[] p1, double[] p2, double t){
	// 	double[] pFinal;
		
	// 	double xRes = Math.pow(1 - t, 2) * p0[0] +
	// 				  (1-t) * 2 * t * p1[0] +
	// 				  1 * t * p2[0];
		
	// 	double yRes = Math.pow(1 - t, 2) * p0[1] +
	// 			  (1-t) * 2 * t * p1[1] +
	// 			  1 * t * p2[1];
		
	// 	//return pFinal;
	// }
	
	public double[] cubicBezier (double[] p0, double[] p1, double[] p2, double[] p3, double t){
		double xRes = Math.pow(1-t, 3)*p0[0]+
					  Math.pow(1-t, 2)*3*t*p1[0]+
					  (1-t)*3*t*t*p2[0]+
					  t*t*t*p3[0];
		double yRes = Math.pow(1-t, 3)*p0[1]+
				  Math.pow(1-t, 2)*3*t*p1[1]+
				  (1-t)*3*t*t*p2[1]+
				  t*t*t*p3[1];
		double [] res = {xRes, yRes};
		return res;
	}
	
	public double[] cubicBezier (int[] p0, int[] p1, int[] p2, int[] p3, double t){
		double xRes = Math.pow(1-t, 3)*p0[0]+
					  Math.pow(1-t, 2)*3*t*p1[0]+
					  (1-t)*3*t*t*p2[0]+
					  t*t*t*p3[0];
		double yRes = Math.pow(1-t, 3)*p0[1]+
				  Math.pow(1-t, 2)*3*t*p1[1]+
				  (1-t)*3*t*t*p2[1]+
				  t*t*t*p3[1];
		double [] res = {xRes, yRes};
		return res;
	}
	
	public void recCurve (int[][] points, Graphics2D g){
		boolean ending = false;
		//int [] startPoint;
		int [] crntPoint = points[0];
		double time = 1.0;
		
		for (int p = 0; p < points.length-4; p+=3){
			for (double t = 0.0; t < time; t+=.1){
//				System.out.println(p );
				double [] res = cubicBezier(points[p],points[p+1],points[p+2],points[p+3],t);
				g.drawLine(crntPoint[0],crntPoint[1], (int)res[0],(int) res[1]);
				
				crntPoint[0] = (int) res[0];
				crntPoint[1] = (int) res[1];
				
				// if (ending && ( (points.length%4) <= p ) ){
				// 	break;
				// }
			
				
				
				//started = true;
				
				
            }
            if (points.length - p <= 0){
                break;
            }
		}
	}
	
}
