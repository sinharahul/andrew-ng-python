#include "linearregression.h"
#include "mainwindow.h"
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
    window.setCentralWidget(lr.createChartView());
    window.resize(900, 600);
    window.show();
    return a.exec();
}
