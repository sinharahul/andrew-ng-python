#include "linearregression.h"
#include <QApplication>
#include <QtWidgets/QApplication>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QApplication>
#include <QtWidgets/QMainWindow>
#include <QtCharts/QChartView>
#include <QtCharts/QLineSeries>
#include <QScatterSeries>
#include <iostream>
#include <armadillo>
#include <algorithm>
#include <cmath>
using namespace std;
using namespace arma;

LinearRegression::LinearRegression()
{

}
LinearRegression::~LinearRegression(){

}
mat loadFile() {
    mat A;
    A.load("/Users/rahulsinha/projects/python/andrew-ng-python/machine-learning-ex1/ex1/ex1data1.txt",csv_ascii);
    cout << "Running warm up exercise" << endl;
    return A;
}
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
    vec r(numIters);
    for(int i=0;i<numIters;i++){
        vec h=X * theta;
        theta -= (alpha/m)*(X.t() * (h-y));
      //  cout << "\nIter " << i << "theta=" << theta << endl;
        r(i)=computeCost(X,y,theta);
    }
    return r;
}
vec run(mat A){
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
    vec r=gradientDescent(X,y,theta1,alpha,iterations);
    cout << "\nRunning gradient descent " << r;
    return r;
}

QCustomPlot *LinearRegression::plotGradient(){
   // QList<QChartView *> m_charts;
    mat A=loadFile();
    vec x= A.col(0);
    vec y=A.col(1);
    int len=x.size();
    cout << "len=" << len << endl;
    warmupExercise();
    vec r=run(A);
    int iters=r.size();
    QCustomPlot *customPlot=new QCustomPlot();
   // generate some data:
    QVector<double> xv(iters), yv(iters); // initialize with entries 0..100
    for (int i=0; i<iters; ++i)
    {
      xv[i] = i; // x goes from -1 to 1
      yv[i] = r(i); // let's plot a quadratic function
      cout << "\ni=" << i << "\t" << yv[i];
    }
    // create graph and assign data to it:
    customPlot->addGraph();
    customPlot->graph(0)->setData(xv, yv);
    // give the axes some labels:
    customPlot->xAxis->setLabel("Iterations");
    customPlot->yAxis->setLabel("Cost");
    // set axes ranges, so we see all data:
    customPlot->xAxis->setRange(0, iters);
    customPlot->yAxis->setRange(4.5, 6.5 );
    customPlot->replot();
    A.min(),A.max();
    return customPlot;
}
