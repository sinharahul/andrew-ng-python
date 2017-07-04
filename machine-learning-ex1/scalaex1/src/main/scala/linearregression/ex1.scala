package linearregression

import breeze.linalg._
import breeze.numerics._
import breeze.plot._
import java.io.File

object Main extends App {
   def computeCost(X: DenseMatrix[Double], y: DenseVector[Double], 
       theta: DenseVector[Double]): (Double,DenseVector[Double]) = {
    val h = X * theta
    val cost = pow((h - y), 2).sum / (2 * m)
    (cost,theta)
  }
  def gradientDescent(m: Integer, X: DenseMatrix[Double], y: DenseVector[Double],
      theta: DenseVector[Double], alpha: Double, iterations: Integer): 
      (DenseVector[Double], DenseVector[Double]) 
  = {

    var result = DenseVector(theta.toArray)
    var costHistory = DenseVector.zeros[Double](iterations)
    for (i <- 0 to iterations - 1) {
      var h = X * result
      result = result - ((alpha / m) * (X.t * (h - y)))
      val (cost,theta) = computeCost(X, y, result)
      costHistory(i)=cost
    }
    (result, costHistory)
  }
    
  val file = new File(getClass.getResource("/ex1data1.txt").getFile)
  val data: DenseMatrix[Double] = csvread(file, ',')
  var X1 = data(::, 0)
  val y = data(::, 1)
  val m = y.length
  var theta = DenseVector.zeros[Double](2) //> theta  : breeze.linalg.DenseVector[Double] = DenseVector(0.0, 0.0)
  val iterations = 1500; //> iterations  : Int = 400
  val alpha = 0.01;
  val ones = DenseVector.ones[Double](m).asDenseMatrix
  //Append ones to X
  val X = DenseMatrix.create(m, 2, ones.toArray ++ X1.toArray)
  var (c,theta1)=computeCost(X, y, theta) 
  val (t1, t2) = gradientDescent(m, X, y, theta1, alpha, iterations)
  println(t1)
}