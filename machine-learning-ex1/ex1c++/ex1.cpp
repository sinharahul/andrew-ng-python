#include <iostream>
#include <armadillo>
#include <algorithm>
#include <cmath>
using namespace std;
using namespace arma;

mat warmupExercise(){
	mat A(5,5);
	return A.eye();
} 
double computeCost(mat X,vec y,vec theta){
	double cost=0.0;
	int m=y.size();
	vec h=X * theta;
	cost=sum(pow((h-y),2))/(2.0*m);
	return cost;
} 
vec gradientDescent(mat X,vec y,vec theta,double alpha,int numIters){
	int m=y.size();
	for(int i=0;i<numIters;i++){
		vec h=X * theta;
		theta -= (alpha/m)*(X.t() * (h-y));
		cout << "\nIter " << i << "theta=" << theta << endl;
	}
	return theta;
}
int main()
  {
  mat A;
  A.load("ex1data1.txt",csv_ascii);
  cout << "Running warm up exercise" << endl;
  cout << warmupExercise() << endl;
  vec x= A.col(0);
  vec y=A.col(1);
  int len=x.size();
  cout << "len=" << len << endl;
  vec ones(len,fill::ones);
  mat X;
  cout << "ones size" << endl << ones.size() << endl;
  cout << "x size" << x.size();
  X.insert_cols(0,ones);
  X.insert_cols(1,x);
  cout << "\nX=" << X.col(0) << endl;
  vec theta(2,fill::zeros);
  int iterations = 400;
  double alpha = 0.01;
  cout << "\nX=" << X << endl;
  cout << "Initial cost with theta 0=" << computeCost(X,y,theta) << endl;
  theta(0)=-1,theta(1)=2;  
  cout << "Cost with theta" << theta << computeCost(X,y,theta) << endl;
  vec theta1(2,fill::zeros);
  cout << "\nRunning gradient descent " << gradientDescent(X,y,theta1,alpha,iterations); 
  return 0;
  }
