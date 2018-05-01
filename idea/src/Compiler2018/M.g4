grammar M;

program:   programSection* EOF;

// AbstractDecl
programSection
    :   classDeclaration    # ClassDecl
    |   functionDeclaration # FuncDecl
    |   variableDeclarationStatement # VarDecl
    ;

classDeclaration: 'class' Identifier classBlock;        // --> ClassDecl

functionDeclaration :   classType Identifier '(' functionParameters? ')' blockStatement;    // --> FuncDecl

variableDeclaration :   classType Identifier ('=' expression)?;     // --> VarDecl

variableDeclarationStatement : variableDeclaration ';'; // -- VarDecl --

functionParameters  :   (variableDeclaration ',')* variableDeclaration;

classBlock: '{' classBlockItem* '}';

// AbstractClassItem
classBlockItem
    :   variableDeclarationStatement # ClassVarDecl  // --> ClassVarDecl
    |   constructorDeclaration  # ClassCstrDecl
    |   functionDeclaration # ClassFuncDecl // --> ClassFuncDecl
    ;

constructorDeclaration :   Identifier '(' functionParameters? ')' blockStatement;   // --> ClassCstrDecl

// ABstractStmt
statement
    :   blockStatement  # BlockStmt
    |   variableDeclarationStatement # VarDeclStmt
    |   branchStatement # BranchStmt
    |   loopStatement   # LoopStmt
    |   expression ';'  # ExprStmt
    |   jumpStatement   # JumpStmt
    |   ';' # EmptyStmt
    ;

blockStatement: '{' statement* '}'; // --> BlockStmt

branchStatement: If '(' expression ')' statement (Else statement)? ;    // --> BranchStmt

// AbstractLoopStmt
loopStatement
    : 'for' '(' init=expression ';' cond=expression? ';' step=expression ')' statement    # ForStmt   // --> ForStmt
    | 'while' '(' expression ')' statement  # WhileStmt // --> WhileStmt
    ;

// AbstractJumpStmt
jumpStatement
    :   'return' expression? ';' # ReturnStmt    // --> ReturnStmt
    |   'break' ';' # BreakStmt // --> BreakStmt
    |   'continue' ';'  # ContinueStmt  // --> ContinueStmt
    ;

// ClassType
classType
    :   arrayClass  # ArrayType
    |   nonArrayClass   # NonArrayType
    ;

arrayClass:   nonArrayClass (brackets)+;    // --> ClassType

nonArrayClass   // --> ClassType
    :   type='bool'
    |   type='int'
    |   type='void'
    |   type='string'
    |   type=Identifier
    ;

// AbstractExpr
expression
    :   expression op=('++'|'--')   # PostfixIncDec // --> UnaryExpr
    |   expression '(' callParameter? ')'   # FunctionCall  // --> FunctionCall
    |   expression '[' expression ']'   # ArrayAcess    // --> ArrayAcess
    |   expression '.' Identifier     # MemberAcess // --> MemberAcess

    |   <assoc=right> op=('++'|'--') expression # UnaryExpr // --> UnaryExpr
    |   <assoc=right> op=('+'|'-') expression   # UnaryExpr // --> UnaryExpr
    |   <assoc=right> op=('!'|'~') expression   # UnaryExpr // --> UnaryExpr
    |   <assoc=right> 'new' newObject   # NewExpr   // --> NewExpr

    |   expression op=('*'|'/'|'%') expression # BinaryExpr // --> BinaryExpr
    |   expression op=('-'|'+') expression  # BinaryExpr    // --> BinaryExpr
    |   expression op=('<<'|'>>') expression    # BinaryExpr    // --> BinaryExpr
    |   expression op=('<'|'<='|'>'|'>=') expression    # BinaryExpr    // --> BinaryExpr
    |   expression op=('=='|'!=') expression    # BinaryExpr    // --> BinaryExpr
    |   expression op='&' expression    # BinaryExpr    // --> BinaryExpr
    |   expression op='^' expression    # BinaryExpr    // --> BinaryExpr
    |   expression op='|' expression    # BinaryExpr    // --> BinaryExpr
    |   expression op='&&' expression   # BinaryExpr    // --> BinaryExpr
    |   expression op='||' expression   # BinaryExpr    // --> BinaryExpr
    |   <assoc=right> expression op='=' expression  # BinaryExpr    // --> BinaryExpr

    |   Identifier  # Identifier        // --> Identifier
    |   constant    # Const
    |   '(' expression ')'  # SubExpr
    ;

callParameter:   (expression ',')* expression;

// AbstractNewObeject
newObject
    :   nonArrayClass ('[' expression ']')+ (brackets)+ ('[' expression ']')+    # NewError  // throw
    |   nonArrayClass ('[' expression ']')+ (brackets)*   # NewArray    // --> NewArray
    |   nonArrayClass ('(' callParameter? ')')? # NewNonArray   // --> NewNonArray
    ;

brackets: '[' ']';

// AbstractConst
constant
    :   type=BoolConst  // --> BoolConst
    |   type=NumConst   // --> NumConst
    |   type=StrConst   // --> StrConst
    |   type=NullConst  // --> NullConst
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

//------ Symbols
Add : '+';
Sub : '-';
Mul : '*';
Div : '/';
Mod : '%';

LT  : '<';
GT  : '>';
LE  : '<=';
GE  : '>=';
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
NumConst : [0-9]+;
StrConst : '"' ('\\'[btnr"\\] | .)*? '"';
NullConst    : 'null';

//------ Indentifiers
Identifier  : [a-zA-Z][a-zA-Z_0-9]*;

//------ WhiteSpace
WhiteSpace  : [ \t\n\r]+ -> channel (HIDDEN);

//------ Comments
LineComment  : '//' .*? '\n' -> channel (HIDDEN);
BlockComment : '/*' .*? '*/' -> channel (HIDDEN);
