package linearregression
import breeze.linalg._
import breeze.numerics._
import breeze.plot._
import java.io.File
object ex1 {
  println("Running warmexercise")                 //> Running warmexercise
  def warmupExercise(n: Int) = DenseMatrix.eye[Double](n)
                                                  //> warmupExercise: (n: Int)breeze.linalg.DenseMatrix[Double]
  warmupExercise(5)                               //> res0: breeze.linalg.DenseMatrix[Double] = 1.0  0.0  0.0  0.0  0.0  
                                                  //| 0.0  1.0  0.0  0.0  0.0  
                                                  //| 0.0  0.0  1.0  0.0  0.0  
                                                  //| 0.0  0.0  0.0  1.0  0.0  
                                                  //| 0.0  0.0  0.0  0.0  1.0  
  val classLoader = Thread.currentThread().getContextClassLoader()
                                                  //> classLoader  : ClassLoader = sun.misc.Launcher$AppClassLoader@7b23ec81
  //in project settings add the resources folder to classpath
  val file = new File(getClass.getResource("/ex1data1.txt").getFile)
                                                  //> file  : java.io.File = /Users/rahulsinha/projects/python/andrew-ng-python/ma
                                                  //| chine-learning-ex1/scalaex1/bin/ex1data1.txt
  println(file)                                   //> /Users/rahulsinha/projects/python/andrew-ng-python/machine-learning-ex1/scal
                                                  //| aex1/bin/ex1data1.txt
  val data: DenseMatrix[Double] = csvread(file, ',')
                                                  //> data  : breeze.linalg.DenseMatrix[Double] = 6.1101  17.592    
                                                  //| 5.5277  9.1302    
                                                  //| 8.5186  13.662    
                                                  //| 7.0032  11.854    
                                                  //| 5.8598  6.8233    
                                                  //| 8.3829  11.886    
                                                  //| 7.4764  4.3483    
                                                  //| 8.5781  12.0      
                                                  //| 6.4862  6.5987    
                                                  //| 5.0546  3.8166    
                                                  //| 5.7107  3.2522    
                                                  //| 14.164  15.505    
                                                  //| 5.734   3.1551    
                                                  //| 8.4084  7.2258    
                                                  //| 5.6407  0.71618   
                                                  //| 5.3794  3.5129    
                                                  //| 6.3654  5.3048    
                                                  //| 5.1301  0.56077   
                                                  //| 6.4296  3.6518    
                                                  //| 7.0708  5.3893    
                                                  //| 6.1891  3.1386    
                                                  //| 20.27   21.767    
                                                  //| 5.4901  4.263     
                                                  //| 6.3261  5.1875    
                                                  //| 5.5649  3.0825    
                                                  //| 18.945  22.638    
                                                  //| 12.828  13.501    
                                                  //| 10.957  7.0467    
                                                  //| 13.176  14.692    
                                                  //| 22.203  24.147    
                                                  //| 5.2524  -1.22     
                                                  //| 6.5894  5.9966    
                                                  //| 9.2482  12.134    
                                                  //| 5.8918  1.8495    
                                                  //| 8.2111  6.5426    
                                                  //| 7.9334  4.5623    
                                                  //| 8.0959  4.1164    
                                                  //| 5.6063  3.3928    
                                                  //| 12.836  10.117    
                                                  //| 6.3534  5.4974    
                                                  //| 5.4069  0.55657   
                                                  //| 6.8825  3
                                                  //| Output exceeds cutoff limit.
  val X=data(::,0)                                //> X  : breeze.linalg.DenseVector[Double] = DenseVector(6.1101, 5.5277, 8.5186,
                                                  //|  7.0032, 5.8598, 8.3829, 7.4764, 8.5781, 6.4862, 5.0546, 5.7107, 14.164, 5.7
                                                  //| 34, 8.4084, 5.6407, 5.3794, 6.3654, 5.1301, 6.4296, 7.0708, 6.1891, 20.27, 5
                                                  //| .4901, 6.3261, 5.5649, 18.945, 12.828, 10.957, 13.176, 22.203, 5.2524, 6.589
                                                  //| 4, 9.2482, 5.8918, 8.2111, 7.9334, 8.0959, 5.6063, 12.836, 6.3534, 5.4069, 6
                                                  //| .8825, 11.708, 5.7737, 7.8247, 7.0931, 5.0702, 5.8014, 11.7, 5.5416, 7.5402,
                                                  //|  5.3077, 7.4239, 7.6031, 6.3328, 6.3589, 6.2742, 5.6397, 9.3102, 9.4536, 8.8
                                                  //| 254, 5.1793, 21.279, 14.908, 18.959, 7.2182, 8.2951, 10.236, 5.4994, 20.341,
                                                  //|  10.136, 7.3345, 6.0062, 7.2259, 5.0269, 6.5479, 7.5386, 5.0365, 10.274, 5.1
                                                  //| 077, 5.7292, 5.1884, 6.3557, 9.7687, 6.5159, 8.5172, 9.1802, 6.002, 5.5204, 
                                                  //| 5.0594, 5.7077, 7.6366, 5.8707, 5.3054, 8.2934, 13.394, 5.4369)
  val Y=data(::,1)                                //> Y  : breeze.linalg.DenseVector[Double] = DenseVector(17.592, 9.1302, 13.662,
                                                  //|  11.854, 6.8233, 11.886, 4.3483, 12.0, 6.5987, 3.8166, 3.2522, 15.505, 3.155
                                                  //| 1, 7.2258, 0.71618, 3.5129, 5.3048, 0.56077, 3.6518, 5.3893, 3.1386, 21.767,
                                                  //|  4.263, 5.1875, 3.0825, 22.638, 13.501, 7.0467, 14.692, 24.147, -1.22, 5.996
                                                  //| 6, 12.134, 1.8495, 6.5426, 4.5623, 4.1164, 3.3928, 10.117, 5.4974, 0.55657, 
                                                  //| 3.9115, 5.3854, 2.4406, 6.7318, 1.0463, 5.1337, 1.844, 8.0043, 1.0179, 6.750
                                                  //| 4, 1.8396, 4.2885, 4.9981, 1.4233, -1.4211, 2.4756, 4.6042, 3.9624, 5.4141, 
                                                  //| 5.1694, -0.74279, 17.929, 12.054, 17.054, 4.8852, 5.7442, 7.7754, 1.0173, 20
                                                  //| .992, 6.6799, 4.0259, 1.2784, 3.3411, -2.6807, 0.29678, 3.8845, 5.7014, 6.75
                                                  //| 26, 2.0576, 0.47953, 0.20421, 0.67861, 7.5435, 5.3436, 4.2415, 6.7981, 0.926
                                                  //| 95, 0.152, 2.8214, 1.8451, 4.2959, 7.2029, 1.9869, 0.14454, 9.0551, 0.61705)
                                                  //| 
  val m=Y.length                                  //> m  : Int = 97
  
  val fig = Figure("Population vs Profit")        //> fig  : breeze.plot.Figure = breeze.plot.Figure@15a34df2
  val plt = fig.subplot(0)                        //> plt  : breeze.plot.Plot = breeze.plot.Plot@5be1d0a4
  plt += plot( X,Y, '.',         colorcode="red") //> res1: breeze.plot.Plot = breeze.plot.Plot@5be1d0a4|
  plt.legend = true
  plt.xlabel = "Population"
  plt.ylabel = "Profit"
  fig.refresh()
}