grammar M;

prog:   progSection* EOF;

progSection
    :   classDeclaration
    |   funcDeclaration
    |   varDeclaration
    ;

classDeclaration: 'class' Id '{' classBlockItem* '}';

classBlockItem
    :   varDeclaration
    |   funcDeclaration
    ;

funcDeclaration: classes? Id '(' varDeclaration* ')' statement;

statement
    :   blockStatement
    |   branchStatement
    |   loopStatement
    |   exprStatement
    |   jumpStatement
    ;

blockStatement: '{' blockItem* '}';

blockItem
    :   varDeclaration
    |   statement
    ;

branchStatement: If '(' expr ')' statement (Else statement)? ;

loopStatement
    : For '(' expr ';' expr ';' expr ')' statement
    | While '(' expr ')' statement
    ;

exprStatement:  expr ';';

jumpStatement
    :   Return expr ';'
    |   Break ';'
    |   Continue ';'
    ;

varDeclaration
    :   classes Id ';'
    |   classes Id '=' expr ';'
    ;

classes
    :   arrayClass
    |   nonArrayClass
    ;

arrayClass:   nonArrayClass ('['']')+;

nonArrayClass
    :   Bool
    |   Int
    |   Void
    |   String
    |   Id
    ;

expr
    :   expr op=('++'|'--')
    |   expr '(' paramList? ')'
    |   expr '[' expr ']'
    |   expr '.' Id

    |   <assoc=right> op=('++'|'--') expr
    |   <assoc=right> op=('+'|'-') expr
    |   <assoc=right> op=('!'|'`') expr
    |   <assoc=right> 'new' newObject

    |   expr op=('*'|'/'|'%') expr
    |   expr op=('-'|'+') expr
    |   expr op=('<<'|'>>') expr
    |   expr op=('<'|'<='|'>'|'>=') expr
    |   expr op=('=='|'!=') expr
    |   expr op='&' expr
    |   expr op='^' expr
    |   expr op='|' expr
    |   expr op='&&' expr
    |   expr op='||' expr
    |   <assoc=right> expr op='=' expr

    |   Id
    |   constant
    |   '(' expr ')'
    ;

paramList:   (expr ',')* expr;

newObject
    :   nonArrayClass '(' paramList ')'
    |   nonArrayClass ('[' expr ']')+ ('['']')?
    ;

constant
    :   BoolConst
    |   Num
    |   Str
    |   Null
    ;

//------ Reseversed keywords
Bool    : 'bool';
Int : 'int';
String  : 'string';
Void    : 'void';
// Null are defined as Null later
// True False are defined as BoolConst later
If  : 'if';
Else    : 'else';
For : 'for';
While   : 'while';
Break   : 'break';
Continue: 'continue';
Return  : 'return';
New : 'new';
Class   : 'class';
This    : 'this';

//------ Symbols
Add : '+';
Sub : '-';
Mul : '*';
Div : '/';
Mod : '%';

LT  : '<';
BT  : '>';
LE  : '<=';
BE  : '>=';
EQ  : '==';
NE  : '!=';

And : '&&';
Or  : '||';
Not : '!';

LShift  : '<<';
RShift  : '>>';
BNot    : '~';  // B -> Bitwise
BOr : '|';
BXor    : '^';
BAnd    : '&';

Assign  : '=';

AddAdd  : '++';
SubSub  : '--';

Semi    : ';';
Comma   : ',';
Dot : '.';

LParen  : '(';
RParen  : ')';
LBracket    : '[';
RBracket    : ']';
LBrace  : '{';
RBrace  : '}';

//------ Constants
BoolConst: 'true' | 'false';
Num : [0-9]+;
Str : '"' ('\\'[btnr"\\] | .)*? '"';
Null    : 'null';

//------ Indentifiers
Id  : [a-zA-Z][a-zA-Z_0-9]*;

//------ WhiteSpace
WhiteSpace  : [ \t\n\r]+ -> channel (HIDDEN);

//------ Comments
LineComment  : '//' .*? '\n' -> channel (HIDDEN);
BlockComment : '/*' .*? '*/' -> channel (HIDDEN);
