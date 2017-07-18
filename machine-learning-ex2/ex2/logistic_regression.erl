-module(logistic_regression).
-compile(export_all).
load(File)->
	 {ResultCode, InputFile} = file:open(File, [read]),
     IntegerList=parse(InputFile,[]).
	 
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
	
	
