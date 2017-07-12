#-------------------------------------------------
#
# Project created by QtCreator 2017-07-07T15:26:11
#
#-------------------------------------------------

QT       += core gui charts printsupport

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = qt
TEMPLATE = app

# The following define makes your compiler emit warnings if you use
# any feature of Qt which as been marked as deprecated (the exact warnings
# depend on your compiler). Please consult the documentation of the
# deprecated API in order to know how to port your code away from it.
DEFINES += QT_DEPRECATED_WARNINGS

# You can also make your code fail to compile if you use deprecated APIs.
# In order to do so, uncomment the following line.
# You can also select to disable deprecated APIs only up to a certain version of Qt.
#DEFINES += QT_DISABLE_DEPRECATED_BEFORE=0x060000    # disables all the APIs deprecated before Qt 6.0.0


SOURCES += \
        main.cpp \
        mainwindow.cpp \
    linearregression.cpp \
    qcustomplot.cpp

HEADERS += \
        mainwindow.h \
    linearregression.h \
    qcustomplot.h

FORMS += \
        mainwindow.ui

macx: LIBS += -L$$PWD/../../../../../../usr/local/Cellar/armadillo/7.950.1/lib/ -larmadillo.7.95.1

INCLUDEPATH += $$PWD/../../../../../../usr/local/Cellar/armadillo/7.950.1/include
DEPENDPATH += $$PWD/../../../../../../usr/local/Cellar/armadillo/7.950.1/include
