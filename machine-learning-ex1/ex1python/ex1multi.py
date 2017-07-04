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
      t1=(dot(X,theta)-y).transpose()
      t2=dot(X,theta)-y
      J=dot(t1,t2)/(2*m)
      return J
def computeThetaTerm(X,y,alpha,theta):
     m=len(y)
     #3 * 47 and 47 *1 = 3* 1
     theta-= (alpha/m) * dot(X.transpose(),(dot(X,theta)-y));

def gradientDescentMulti(X,y,theta,alpha,num_iters):
      m=len(y)
      J_history=np.zeros((num_iters,1))
      for iter in range(num_iters):
            #Pass by reference
            computeThetaTerm(X,y,alpha,theta)
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
gradientDescentMulti(X,y,theta,alpha,num_iters)
print("Theta from gradient descent")
x1=(1650-mean_array[0])/std_array[0]
x2=(3-mean_array[1])/std_array[1]

predict=np.dot(np.array([1,x1,x2]),theta)

print("Price of 1650 sqft ,3 bedroom house  using gradient descent  =",predict)

theta=normalEquations(data)
print(theta)
predictNormal=dot(np.array([1,1650,3]),theta)
print("Price od 1650 sqft ,3 bedroom house  using normal equations  =",predictNormal)

