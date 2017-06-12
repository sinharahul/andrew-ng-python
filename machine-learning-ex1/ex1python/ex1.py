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
def addOnes(x):
      n=len(x)
      ones=np.ones(n)
      return np.transpose(np.array([ones,x]))
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
      theta[0] -= np.sum(x*t1)*alpha/m
      theta[1] -= np.sum(x*t2)*alpha/m
def gradientDescent(X,y,theta,alpha,num_iters):
      m=len(y)
      J_history=np.zeros((num_iters,1))
      for iter in range(num_iters):
            computeThetaTerm(X,y,alpha,theta)
            J_history[iter]=computeCost(X,y,theta)
      print(J_history)
      return J_history
print("Running warm up exercise")
print("5 * 5 identity matrix")
print(warmUpExercise())
print("Program paused.Press enter to continue\n")
#input("..")
print("Plotting data")
data=pd.read_csv('ex1data1.txt',header=None,names = ["Population", "Profit"])
X=data["Population"];y=data["Profit"]
m=len(y)
#Plot data
#plt.show()
plotData(data)
#Convert to numpy matrix for easy math
matrix=pd.DataFrame.as_matrix(data)
#Add a column of ones to X
#X=np.array([np.ones(m)],[matrix[:,0]])
X=addOnes(matrix[:,0])
theta=np.zeros( (2,1) )
y=np.transpose(np.array([matrix[:,1]]))
# Some gradient descent settings
iterations = 1500
#learning rate
alpha = 0.01         
print("Testing the cost function")
#Compute and display the initial cost
J = computeCost(X, y, theta)
print(J)
theta1=np.zeros( (2,1) )
theta1[0,0]=-1
theta1[1,0]=2
#Testing computeCost
print (computeCost(X, y, theta1))
#Apllying gradient descent
gradientDescent(X,y,theta,alpha,iterations)
