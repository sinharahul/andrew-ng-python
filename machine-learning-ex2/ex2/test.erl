-module(test).
-compile(export_all).

ex2()->
	L=logistic_regression:load("ex2data1.txt"),
	%%io:format("\nL=~p\n",[L]),
	X=logistic_regression:addOnes(L),
	Y=logistic_regression:y(X),
	{M,N}=logistic_regression:size(X).
	
	