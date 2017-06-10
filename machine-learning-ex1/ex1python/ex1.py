"""
  Pythonic solution of ex1
"""
import numpy as np
def warmUpExercise():
      return np.identity(5) 
print("Running warm up exercise")
print("5 * 5 identity matrix")
print(warmUpExercise())
print("Program paused.Press enter to continue\n")
input("..")
print("Plotting data")
import pandas as pd
import matplotlib.pyplot as plt
data=pd.read_csv('ex1data1.txt',header=None,names = ["Population", "Profit"])
X=data["Population"];y=data["Profit"]
m=len(y)
print(data)
          
