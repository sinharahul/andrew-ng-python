#include "linearregression.h"
#include "mainwindow.h"
#include "qcustomplot.h"
#include <QtWidgets/QApplication>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QApplication>
#include <QtWidgets/QMainWindow>
#include <QtCharts/QChartView>
#include <QtCharts/QLineSeries>
QT_CHARTS_USE_NAMESPACE
int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    LinearRegression lr;
    QMainWindow window;
    QCustomPlot *customPlot=lr.plotGradient();
    window.setCentralWidget(customPlot);
    window.resize(900, 600);
    window.show();
    return a.exec();
}
