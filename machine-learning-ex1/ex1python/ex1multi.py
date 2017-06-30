"""
  Pythonic solution of ex1.m
"""
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
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
      J=0
      h=np.dot(X,theta)
      x=(h-y)
      J=np.sum(x ** 2)
      J=J/ (2*m)
      return J
def computeThetaTerm(X,y,alpha,theta):
      m=len(y)
      h=np.dot(X,theta)
      x=(h-y)
      t1=X[:,0].reshape(m,1)
      t2=X[:,1].reshape(m,1)
      t3=X[:,2].reshape(m,1)
      theta[0] -= np.sum(x*t1)*alpha/m
      theta[1] -= np.sum(x*t2)*alpha/m
      theta[2] -= np.sum(x*t3)*alpha/m
      #theta -= (np.dot(x,X)*(alpha/m))
def gradientDescent(X,y,theta,alpha,num_iters):
      m=len(y)
      J_history=np.zeros((num_iters,1))
      for iter in range(num_iters):
            computeThetaTerm(X,y,alpha,theta)
            J_history[iter]=computeCost(X,y,theta)
      print(J_history)
      return J_history
def gradientDescentMulti(X,y,theta,alpha,num_iters):
      m=len(y)
      J_history=np.zeros((num_iters,1))
      for iter in range(num_iters):
            computeThetaTerm(X,y,alpha,theta)
            J_history[iter]=computeCost(X,y,theta)
      print(J_history)
      return J_history
      
def featureNormalize(X):
      mean_array=X.mean(0)
      std_array=X.std(0)
      mean_for_area=mean_array[0]
      mean_for_rooms=mean_array[1]
      std_area=std_array[0]
      std_rooms=std_array[1]
      X[:,0]=(X[:,0]-mean_for_area)/std_area
      X[:,1]=(X[:,1]-mean_for_rooms)/std_rooms
 
      return (X,mean_array,std_array)
print("Loading data")
data=pd.read_csv('ex1data2.txt',header=None,names = ["Area", "No of bedrooms","Price"])
matrix=pd.DataFrame.as_matrix(data)
X=np.array([data['Area'],data['No of bedrooms']])
X=np.transpose(X)
(X,mean_array,std_array)=featureNormalize(X)
X=addOnes(X)
print(X)
theta=np.zeros((3,1))
alpha,num_iters=0.01,400
y=np.array(data['Price'])
print(gradientDescentMulti(X,y,theta,alpha,num_iters))
predict=np.dot(np.array([1,1650,3]),theta)
print(predict)
