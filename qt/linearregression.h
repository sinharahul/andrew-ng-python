#ifndef LINEARREGRESSION_H
#define LINEARREGRESSION_H
#include <iostream>
#include <armadillo>
#include <algorithm>
#include <cmath>
#include "qcustomplot.h"
using namespace std;
using namespace arma;
#include <QtCharts/QLineSeries>
#include <QtCharts/QChartView>
QT_CHARTS_USE_NAMESPACE

class LinearRegression
{
public:
    explicit LinearRegression();
    ~LinearRegression();
    void runGradientDescent();
    QCustomPlot* plotGradient();
private:
    vec th;
    vec run(mat A);
    vec gradientDescent(mat X,vec y,vec theta,double alpha,int numIters);

};

#endif // LINEARREGRESSION_H
