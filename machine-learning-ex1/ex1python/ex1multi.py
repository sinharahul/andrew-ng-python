"""
  Pythonic solution of ex1.m
"""
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from numpy import dot,sum
from numpy.linalg import inv
#Reshape numpy matrices before doing matrix mult 
#or elem wise mult
def warmUpExercise():
      return np.identity(5)
def plotData(data):
      plt.scatter(X,y)
      plt.xlabel("Populationof city in 10,000's")
      plt.ylabel("Profit in $10,000s")
def addOnes(X):
      n=X.shape[0]
      ones=np.ones(n).reshape(n,1)
      return np.concatenate((ones,X),1)
def computeCost(X,y,theta):
      m=len(y)
      y=y.reshape(m,1)
      t1=(dot(X,theta)-y).transpose()
      t2=dot(X,theta)-y
      J=dot(t1,t2)/(2*m)
      print("J=",J)
      return J
def computeThetaTerm(X,y,alpha,theta):
      m=len(y)
      h=np.dot(X,theta)
      x=(h-y)
      t1=X[:,0].reshape(m,1)
      t2=X[:,1].reshape(m,1)
      t3=X[:,2].reshape(m,1)
      theta[0] -= (alpha /m) * np.sum(x)
      theta[1] -= (alpha /m) * np.sum(x*t2)
      theta[2] -= (alpha /m) * np.sum(x*t3)
      return theta
      #theta -= (np.dot(x,X)*(alpha/m))

def gradientDescentMulti(X,y,theta,alpha,num_iters):
      m=len(y)
      J_history=np.zeros((num_iters,1))
      for iter in range(num_iters):
            print("Before Theta in ",iter,theta)
            theta=computeThetaTerm(X,y,alpha,theta)
            print("Theta in ",iter,theta)
            cost=computeCost(X,y,theta)
            J_history[iter]=cost
      return (theta,J_history)
#Called before ones column added      
def featureNormalize(X):
      mean_array=X.mean(0)
      #Setting degrees of freedom same to give
      #Same result as octave
      std_array=X.std(0, ddof=1)
      mean_for_area=mean_array[0]
      mean_for_rooms=mean_array[1]
      std_area=std_array[0]
      std_rooms=std_array[1]
      X[:,0]=(X[:,0]-mean_for_area)/std_area
      X[:,1]=(X[:,1]-mean_for_rooms)/std_rooms
 
      return (X,mean_array,std_array)
def normalEquations(data):
    X=np.array([data['Area'],data['No of bedrooms']], dtype=np.float64)
    X=np.transpose(X)
    X=addOnes(X)
    t1=inv(dot(X.transpose(),X))
    t2=dot(X.transpose(),y)
    return dot(t1,t2)      
print("Loading data")
data=pd.read_csv('ex1data2.txt',header=None,names = ["Area", "No of bedrooms","Price"])
matrix=pd.DataFrame.as_matrix(data)
X=np.array([data['Area'],data['No of bedrooms']], dtype=np.float64)
X=np.transpose(X)
(X,mean_array,std_array)=featureNormalize(X)
X=addOnes(X)
theta=np.zeros((3,1), dtype=np.float64)
alpha,num_iters=0.01,400
y=np.array(data['Price'])
m=len(y)
#Very important
y=y.reshape(m,1) 
(theta,J_history)=gradientDescentMulti(X,y,theta,alpha,num_iters)
print(theta)
x1=(1650-mean_array[0])/std_array[0]
x2=(3-mean_array[1])/std_array[1]

predict=np.dot(np.array([1,x1,x2]),theta)

print("Price od 1650 sq ftc3 bedroom house is using gradient descent  ",predict)

theta=normalEquations(data)
print(theta)
price1=dot(np.array([1,1650,3]),theta)
print(price1)
