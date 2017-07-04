import java.io.File
import breeze.linalg._
object test {


  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val simpleMatrix=DenseMatrix((1,2,3),(11,12,13),(21,22,23))
                                                  //> simpleMatrix  : breeze.linalg.DenseMatrix[Int] = 1   2   3   
                                                  //| 11  12  13  
                                                  //| 21  22  23  
  val identityMatrix=DenseMatrix.eye[Int](3)      //> identityMatrix  : breeze.linalg.DenseMatrix[Int] = 1  0  0  
                                                  //| 0  1  0  
                                                  //| 0  0  1  
  val randomMatrix=DenseMatrix.rand(4, 4)         //> randomMatrix  : breeze.linalg.DenseMatrix[Double] = 0.055533809731851846  0.
                                                  //| 8061927411731291  0.8481953381572347   0.11771411085088346  
                                                  //| 0.33791879080172715   0.8163574493926786  0.07750692011312976  0.12803823380
                                                  //| 136548  
                                                  //| 0.8892328071964051    0.4185076594959356  0.22000796960899138  0.68522164815
                                                  //| 57853   
                                                  //| 0.17959292260941817   0.7595834511373114  0.41069124974700855  0.75142121295
                                                  //| 43729   
 val vectFromArray=new DenseMatrix(2,2,Array(2,3,4,5))
                                                  //> vectFromArray  : breeze.linalg.DenseMatrix[Int] = 2  4  
                                                  //| 3  5  
 val vertConcatMatrix=DenseMatrix.vertcat(identityMatrix, simpleMatrix)
                                                  //> vertConcatMatrix  : breeze.linalg.DenseMatrix[Int] = 1   0   0   
                                                  //| 0   1   0   
                                                  //| 0   0   1   
                                                  //| 1   2   3   
                                                  //| 11  12  13  
                                                  //| 21  22  23  
 val elementWiseMulti=identityMatrix :* simpleMatrix
                                                  //> elementWiseMulti  : breeze.linalg.DenseMatrix[Int] = 1  0   0   
                                                  //| 0  12  0   
                                                  //| 0  0   23  
 
 val onesMatrix=DenseMatrix(1,1,1)                //> onesMatrix  : breeze.linalg.DenseMatrix[Int] = 1  
                                                  //| 1  
                                                  //| 1  
 
 val X=DenseMatrix.horzcat(onesMatrix,simpleMatrix)
                                                  //> X  : breeze.linalg.DenseMatrix[Int] = 1  1   2   3   
                                                  //| 1  11  12  13  
                                                  //| 1  21  22  23  
}