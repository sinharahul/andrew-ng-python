#ifndef LINEARREGRESSION_H
#define LINEARREGRESSION_H
#include <iostream>
#include <armadillo>
#include <algorithm>
#include <cmath>

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
   QChartView* createChartView();
};

#endif // LINEARREGRESSION_H
