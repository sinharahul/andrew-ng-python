-module(logistic_regression).
-compile(export_all).
load(File)->
	 {ResultCode, InputFile} = file:open(File, [read]),
     IntegerList=parse(InputFile,[]).
	 
addOnes(X)->
	[[1,A,B]|| [A,B,_] <- X].
		 
y(X) ->
	[C|| [_,_,C] <- X].	
	
size(X)->
	L=length(X),
	A=lists:nth(1,X), 
	{L,length(A)}.
		
zeros(N)->
	zeros(N,[]).
zeros(N,L) when N >=1 ->
	zeros(N-1,lists:append(0,L));
zeros(0,L)->
	L.
%%https://www.ugrad.cs.ubc.ca/~cs418/2013-1/lecture/09-24.pdf	
transpose([]) -> []; 
transpose([[]|_]) -> []; 
transpose(M) ->
    [ [H || [H | T] <- M ] 
      | transpose([ T || [ H | T] <- M ]) 
    ].
	
dot_prod(V1, V2) ->
lists:foldl(
fun({X,Y},Sum) ->
	  Sum + X*Y end,
      0, lists:zip(V1, V2)).

dotProd([], []) -> 0;
dotProd([A | Atl], [B | Btl]) -> A*B + dotProd(Atl, Btl).

mult(A, B) ->
  BT = transpose(B),
  lists:map(
    fun(Row_of_A) ->
       lists:map(
           fun(Col_of_B) ->
              dot_prod(Row_of_A, Col_of_B)
           end, BT)
    end, A).
					
parse(InputFile,List)->
      case io:get_line(InputFile,"") of
	    eof -> file:close(InputFile),
		       List;
		Line -> NL=number_list(Line),
		        parse(InputFile,lists:append(List,[NL]))
	  end.	 
	  		 
convert(S)->
	FL=binary_to_list(S),
	FA=list_to_float(FL).
convert_to_integer(S)->
	I=binary_to_list(S),
	list_to_integer(I).	
number_list(Line)->
	[A,B,C,_]=re:split(Line,"[,\n]+"),
	L=[convert(A),convert(B),convert_to_integer(C)].
	
	
