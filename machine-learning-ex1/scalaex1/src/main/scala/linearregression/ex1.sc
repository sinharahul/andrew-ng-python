package linearregression
import breeze.linalg._
import breeze.numerics._
import breeze.plot._
import java.io.File
object ex1 {
  println(" Running warmexercise")                //>  Running warmexercise
  def warmupExercise(n: Int) = DenseMatrix.eye[Double](n)
                                                  //> warmupExercise: (n: Int)breeze.linalg.DenseMatrix[Double]
  def plotData(X: DenseVector[Double], Y: DenseVector[Double]) = {
    val fig = Figure("Population vs Profit")
    val plt = fig.subplot(0)
    plt += plot(X, Y, '.', colorcode = "red")
    plt.legend = true
    plt.xlabel = "Population"
    plt.ylabel = "Profit"
    fig.refresh()
  }                                               //> plotData: (X: breeze.linalg.DenseVector[Double], Y: breeze.linalg.DenseVecto
                                                  //| r[Double])Unit
  warmupExercise(5)                               //> res0: breeze.linalg.DenseMatrix[Double] = 1.0  0.0  0.0  0.0  0.0  
                                                  //| 0.0  1.0  0.0  0.0  0.0  
                                                  //| 0.0  0.0  1.0  0.0  0.0  
                                                  //| 0.0  0.0  0.0  1.0  0.0  
                                                  //| 0.0  0.0  0.0  0.0  1.0  
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
  var X1 = data(::, 0)                            //> X1  : breeze.linalg.DenseVector[Double] = DenseVector(6.1101, 5.5277, 8.5186
                                                  //| , 7.0032, 5.8598, 8.3829, 7.4764, 8.5781, 6.4862, 5.0546, 5.7107, 14.164, 5.
                                                  //| 734, 8.4084, 5.6407, 5.3794, 6.3654, 5.1301, 6.4296, 7.0708, 6.1891, 20.27, 
                                                  //| 5.4901, 6.3261, 5.5649, 18.945, 12.828, 10.957, 13.176, 22.203, 5.2524, 6.58
                                                  //| 94, 9.2482, 5.8918, 8.2111, 7.9334, 8.0959, 5.6063, 12.836, 6.3534, 5.4069, 
                                                  //| 6.8825, 11.708, 5.7737, 7.8247, 7.0931, 5.0702, 5.8014, 11.7, 5.5416, 7.5402
                                                  //| , 5.3077, 7.4239, 7.6031, 6.3328, 6.3589, 6.2742, 5.6397, 9.3102, 9.4536, 8.
                                                  //| 8254, 5.1793, 21.279, 14.908, 18.959, 7.2182, 8.2951, 10.236, 5.4994, 20.341
                                                  //| , 10.136, 7.3345, 6.0062, 7.2259, 5.0269, 6.5479, 7.5386, 5.0365, 10.274, 5.
                                                  //| 1077, 5.7292, 5.1884, 6.3557, 9.7687, 6.5159, 8.5172, 9.1802, 6.002, 5.5204,
                                                  //|  5.0594, 5.7077, 7.6366, 5.8707, 5.3054, 8.2934, 13.394, 5.4369)
  val y= data(::, 1)                              //> y  : breeze.linalg.DenseVector[Double] = DenseVector(17.592, 9.1302, 13.662,
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
  val m = y.length                                //> m  : Int = 97
  //plotData(X,y)
  val ones=DenseVector.ones[Double](m)            //> ones  : breeze.linalg.DenseVector[Double] = DenseVector(1.0, 1.0, 1.0, 1.0, 
                                                  //| 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1
                                                  //| .0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.
                                                  //| 0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0
                                                  //| , 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
                                                  //|  1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 
                                                  //| 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1
                                                  //| .0, 1.0)
  val X=DenseMatrix.create(m,2,ones.toArray ++ X1.toArray)
                                                  //> X  : breeze.linalg.DenseMatrix[Double] = 1.0  6.1101  
                                                  //| 1.0  5.5277  
                                                  //| 1.0  8.5186  
                                                  //| 1.0  7.0032  
                                                  //| 1.0  5.8598  
                                                  //| 1.0  8.3829  
                                                  //| 1.0  7.4764  
                                                  //| 1.0  8.5781  
                                                  //| 1.0  6.4862  
                                                  //| 1.0  5.0546  
                                                  //| 1.0  5.7107  
                                                  //| 1.0  14.164  
                                                  //| 1.0  5.734   
                                                  //| 1.0  8.4084  
                                                  //| 1.0  5.6407  
                                                  //| 1.0  5.3794  
                                                  //| 1.0  6.3654  
                                                  //| 1.0  5.1301  
                                                  //| 1.0  6.4296  
                                                  //| 1.0  7.0708  
                                                  //| 1.0  6.1891  
                                                  //| 1.0  20.27   
                                                  //| 1.0  5.4901  
                                                  //| 1.0  6.3261  
                                                  //| 1.0  5.5649  
                                                  //| 1.0  18.945  
                                                  //| 1.0  12.828  
                                                  //| 1.0  10.957  
                                                  //| 1.0  13.176  
                                                  //| 1.0  22.203  
                                                  //| 1.0  5.2524  
                                                  //| 1.0  6.5894  
                                                  //| 1.0  9.2482  
                                                  //| 1.0  5.8918  
                                                  //| 1.0  8.2111  
                                                  //| 1.0  7.9334  
                                                  //| 1.0  8.0959  
                                                  //| 1.0  5.6063  
                                                  //| 1.0  12.836  
                                                  //| 1.0  6.3534  
                                                  //| 1.0  5.4069  
                                                  //| 1.0  6.8825  
                                                  //| 1.0  11.708  
                                                  //| 1.0  5.7737  
                                                  //| 1.0  7.8247  
                                                  //| 1.0  7.0931  
                                                  //| 1.0  5.0702  
                                                  //| 1.0  5.8014  
                                                  //| 1.0  11.7    
                                                  //| 1.0  5.5416  
                                                  //| 1.0  7.5402  
                                                  //| 1.0  5.3077  
                                                  //| 1.0  7.4239  
                                                  //| 1
                                                  //| Output exceeds cutoff limit.
  var theta=DenseVector.zeros[Double](2)          //> theta  : breeze.linalg.DenseVector[Double] = DenseVector(0.0, 0.0)
  val iterations = 400;                           //> iterations  : Int = 400
  val alpha = 0.01;                               //> alpha  : Double = 0.01
  println("Computing initial cost")               //> Computing initial cost
  def computeCost(X:DenseMatrix[Double],y:DenseVector[Double],theta:DenseVector[Double]):Double={
     val h= X * theta
     val cost=pow((h-y), 2).sum/(2 *m)
     cost
  }                                               //> computeCost: (X: breeze.linalg.DenseMatrix[Double], y: breeze.linalg.DenseV
                                                  //| ector[Double], theta: breeze.linalg.DenseVector[Double])Double
  computeCost(X,y,DenseVector(0,0))               //> Jul 03, 2017 7:48:47 PM com.github.fommil.jni.JniLoader liberalLoad
                                                  //| INFO: successfully loaded /var/folders/h3/h2v2mpm14s33j6p2q5dywfnh0000gn/T/
                                                  //| jniloader7169598912590549443netlib-native_system-osx-x86_64.jnilib
                                                  //| res1: Double = 32.072733877455654
  computeCost(X,y,DenseVector(-1,2))              //> res2: Double = 54.24245508201238
  def gradientDescent(m:Integer,X:DenseMatrix[Double],y:DenseVector[Double],theta:DenseVector[Double], alpha:Double, iterations:Integer):(DenseVector[Double],DenseVector[Double])={
     var result=DenseVector.zeros[Double](2)
     var costHistory=DenseVector.zeros[Double](iterations)
     for(i <- 0 to iterations-1){
       var h= X * result
       result =result - ((alpha/m) * (X.t * (h-y) ))
       costHistory(i)=computeCost(X,y,result)
     }
     (result,costHistory)
  }                                               //> gradientDescent: (m: Integer, X: breeze.linalg.DenseMatrix[Double], y: bree
                                                  //| ze.linalg.DenseVector[Double], theta: breeze.linalg.DenseVector[Double], al
                                                  //| pha: Double, iterations: Integer)(breeze.linalg.DenseVector[Double], breeze
                                                  //| .linalg.DenseVector[Double])
  val (t1,t2)=gradientDescent(m,X, y, theta, alpha, iterations)
                                                  //> t1  : breeze.linalg.DenseVector[Double] = DenseVector(-1.9639571760237742, 
                                                  //| 0.9989609991537096)
                                                  //| t2  : breeze.linalg.DenseVector[Double] = DenseVector(6.737190464870012, 5.
                                                  //| 931593568604957, 5.901154707081387, 5.89522858644422, 5.890094943117333, 5.
                                                  //| 885004158443646, 5.879932480491416, 5.874879094762575, 5.8698439118063845, 
                                                  //| 5.8648268653129305, 5.8598278899321805, 5.85484692057229, 5.849883892376585
                                                  //| , 5.844938740722036, 5.840011401218365, 5.835101809707228, 5.83020990226138
                                                  //| 9, 5.825335615183866, 5.820478885007099, 5.8156396484921515, 5.810817842627
                                                  //| 869, 5.806013404630042, 5.801226271940627, 5.796456382226899, 5.79170367338
                                                  //| 0652, 5.786968083517397, 5.782249550975539, 5.777548014315598, 5.7728634123
                                                  //| 19381, 5.768195683989212, 5.76354476854712, 5.758910605434049, 5.7542931343
                                                  //| 09077, 5.7496922950486296, 5.745108027745686, 5.740540272709014, 5.73598897
                                                  //| 0462384, 5.7314540617437935, 5.7269354875047025, 5.722433188909259, 5.71794
  println(t2)                                     //> DenseVector(6.737190464870012, 5.931593568604957, 5.901154707081387, 5.8952
                                                  //| 2858644422, 5.890094943117333, 5.885004158443646, 5.879932480491416, 5.8748
                                                  //| 79094762575, 5.8698439118063845, 5.8648268653129305, 5.8598278899321805, 5.
                                                  //| 85484692057229, 5.849883892376585, 5.844938740722036, 5.840011401218365, 5.
                                                  //| 835101809707228, 5.830209902261389, 5.825335615183866, 5.820478885007099, 5
                                                  //| .8156396484921515, 5.810817842627869, 5.806013404630042, 5.801226271940627,
                                                  //|  5.796456382226899, 5.791703673380652, 5.786968083517397, 5.782249550975539
                                                  //| , 5.777548014315598, 5.772863412319381, 5.768195683989212, 5.76354476854712
                                                  //| , 5.758910605434049, 5.754293134309077, 5.7496922950486296, 5.7451080277456
                                                  //| 86, 5.740540272709014, 5.735988970462384, 5.7314540617437935, 5.72693548750
                                                  //| 47025, 5.722433188909259, 5.7179471073335275, 5.7134771843647485, 5.7090233
                                                  //| 61800547, 5.704585581648199, 5.700163786123851, 5.695757917651815, 5.691367
                                                  //| 918863751, 5.6869937325
                                                  //| Output exceeds cutoff limit.
}