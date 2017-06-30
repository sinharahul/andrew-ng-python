#include <iostream>
#include <armadillo>

using namespace std;
using namespace arma;

int main()
  {
  mat A = randu<mat>(1000,1000	);
  A.load("ex1data1.txt",csv_ascii);
  cout << A.col(0);
  
  return 0;
  }
