import random as rd
f=open('goodset.txt')
lines=f.readlines()
print(lines)
def matches(arr,element):
           dict={}
           flag=True
           for n in arr:
                      dict[n]=n
           for i in range(len(arr)):
                      for j in range(i+1,len(arr)):
                                 key=i+j
                                 if(dict.get(key)==key):
                                            flag=False
                                 
def readAsNumArray(arr):
           result=[]
           for str in arr:
                    result.append(int(str))
           return result
def goodset(N):
           gs=[]
           for i in range(N):
                      I=rd.randint(1,500)
                      while(matches(gs,I)==False):
                                 I=getNextInt() 
                      gs.append(I)           
           return gs
numArr=readAsNumArray(lines)
print(numArr)
#Remove first element
#arr=numArr.remove(numArr[0])
for num in numArr:
           print(goodset(num))
    
print(numArr)
           
           
                   
