grammar Calculator;

expr : '(' expr ')'
     | expr ('*'|'/') expr
     | expr ('+'|'-') expr
     | FLOAT
     ;
line : expr EOF ;

WS : [ \t\n\r]+ -> skip;
FLOAT : DIGIT+ '.' DIGIT* EXPONET?
      | '.' DIGIT+ EXPONET?
      | DIGIT+ EXPONET?
      ;

fragment DIGIT : '0'..'9' ;
fragment EXPONET : ('e'|'E') ('+'|'-')? DIGIT+ ;