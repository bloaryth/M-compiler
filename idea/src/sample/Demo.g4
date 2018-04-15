grammar Demo;

//parser
prog:stat
;
stat:expr|NEWLINE
;

expr:multExpr(('+'|'-')multExpr)*
;
multExpr:atom(('*'|'/')atom)*
;
atom:'('expr')'
    |INT
    |ID
;

//lexer

INT:'0'..'9'+;
NEWLINE:'\r'?'\n';
WS:(' '|'\t'|'\n'|'\r')+{skip();};