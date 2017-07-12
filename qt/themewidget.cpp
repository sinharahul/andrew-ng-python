#include "ex1.h"
#include <QtCharts/QChartView>
#include <QtCharts/QPieSeries>
#include <QtCharts/QPieSlice>
#include <QtCharts/QAbstractBarSeries>
#include <QtCharts/QPercentBarSeries>
#include <QtCharts/QStackedBarSeries>
#include <QtCharts/QBarSeries>
#include <QtCharts/QBarSet>
#include <QtCharts/QLineSeries>
#include <QtCharts/QSplineSeries>
#include <QtCharts/QScatterSeries>
#include <QtCharts/QAreaSeries>
#include <QtCharts/QLegend>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QFormLayout>
#include <QtWidgets/QComboBox>
#include <QtWidgets/QSpinBox>
#include <QtWidgets/QCheckBox>
#include <QtWidgets/QGroupBox>
#include <QtWidgets/QLabel>
#include <QtCore/QTime>
#include <QtCharts/QBarCategoryAxis>
#include <iostream>
#include <iostream>
#include <armadillo>
using namespace std;
using namespace arma;
ThemeWidget::ThemeWidget(QWidget *parent) :
    QWidget(parent),
    m_listCount(3),
    m_valueMax(10),
    m_valueCount(7),
    m_dataTable(generateRandomData(m_listCount, m_valueMax, m_valueCount))
{
   mat A = randu<mat>(5,5);
   mat B = randu<mat>(5,5);

   cout << A*B.t() << endl;
   QGridLayout *baseLayout = new QGridLayout();
   QHBoxLayout *settingsLayout = new QHBoxLayout();
   settingsLayout->addWidget(new QLabel("Theme:"));
   settingsLayout->addWidget(new QLabel("Animation:"));
   settingsLayout->addWidget(new QLabel("Legend:"));
   settingsLayout->addStretch();
   baseLayout->addLayout(settingsLayout, 0, 0, 1, 3);
   setLayout(baseLayout);
   QChartView *chartView = new QChartView(createScatterChart());
   baseLayout->addWidget(chartView, 1, 0);
   m_charts << chartView;
  baseLayout->addWidget(chartView, 1, 0);
  foreach (QChartView *chartView, m_charts) {
      chartView->chart()->legend()->setAlignment(0);
      chartView->chart()->legend()->show();
  }
}

ThemeWidget::~ThemeWidget()
{
}

QChart *ThemeWidget::createScatterChart() const
{
    // scatter chart
    QChart *chart = new QChart();
    chart->setTitle("Scatter chart");
    QString name("Series ");
    int nameIndex = 0;
    foreach (DataList list, m_dataTable) {
        QScatterSeries *series = new QScatterSeries(chart);
        foreach (Data data, list)
            series->append(data.first);
        series->setName(name + QString::number(nameIndex));
        nameIndex++;
        chart->addSeries(series);
    }
    chart->createDefaultAxes();
    return chart;
}
DataTable ThemeWidget::generateRandomData(int listCount, int valueMax, int valueCount) const
{
    DataTable dataTable;

    // set seed for random stuff
    qsrand(QTime(0, 0, 0).secsTo(QTime::currentTime()));

    // generate random data
    for (int i(0); i < listCount; i++) {
        DataList dataList;
        qreal yValue(0);
        for (int j(0); j < valueCount; j++) {
            yValue = yValue + (qreal)(qrand() % valueMax) / (qreal) valueCount;
            QPointF value((j + (qreal) rand() / (qreal) RAND_MAX) * ((qreal) m_valueMax / (qreal) valueCount),
                          yValue);
            QString label = "Slice " + QString::number(i) + ":" + QString::number(j);
            dataList << Data(value, label);
        }
        dataTable << dataList;
    }

    return dataTable;
}




