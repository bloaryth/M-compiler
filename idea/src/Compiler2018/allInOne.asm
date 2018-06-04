





default rel

global print
global println
global getString
global getInt
global toString
global length
global substring
global parseInt
global ord
global address
global _malloc
global _newArray
global newArray
global size
global _strADD
global _strLT
global _strGT
global _strLE
global _strGE
global _strEQ
global _strNE

extern malloc
extern __stack_chk_fail
extern __isoc99_scanf
extern puts
extern __printf_chk


SECTION .text   6

print:
        mov     rdx, rdi
        mov     esi, L_064
        mov     edi, 1
        xor     eax, eax
        jmp     __printf_chk







ALIGN   16

println:
        jmp     puts


        nop





ALIGN   16

getString:
        push    rbx
        xor     ebx, ebx
        mov     edi, L_064
        mov     rsi, rbx
        xor     eax, eax
        call    __isoc99_scanf
        mov     rax, rbx
        pop     rbx
        ret







ALIGN   16

getInt:
        sub     rsp, 24
        mov     edi, L_065


        mov     rax, qword [fs:abs 28H]
        mov     qword [rsp+8H], rax
        xor     eax, eax
        mov     rsi, rsp
        call    __isoc99_scanf
        mov     rdx, qword [rsp+8H]


        xor     rdx, qword [fs:abs 28H]
        mov     rax, qword [rsp]
        jnz     L_001
        add     rsp, 24
        ret

L_001:  call    __stack_chk_fail
        nop
ALIGN   16

toString:
        test    rdi, rdi
        push    r12
        push    rbp
        push    rbx
        je      L_008
        mov     rbx, rdi
        jns     L_009
        neg     rbx
L_002:



        db 49H, 0C7H, 0C4H, 0FFH, 0FFH, 0FFH, 0FFH

        db 0BFH, 01H, 00H, 00H, 00H
L_003:  mov     rcx, rbx
        mov     rsi, qword 6666666666666667H
        jmp     L_005





ALIGN   8
L_004:  mov     rdi, rbp
L_005:  mov     rax, rcx
        sar     rcx, 63
        lea     rbp, [rdi+1H]
        imul    rsi
        sar     rdx, 2
        sub     rdx, rcx
        mov     rcx, rdx
        jnz     L_004
        add     rdi, 3
        call    malloc
        cmp     r12, -1
        mov     byte [rax+rbp+1H], 0
        mov     rsi, rax
        mov     byte [rax], bpl

        jne     L_010
        lea     rcx, [rax+rbp]
        mov     byte [rax+1H], 45
L_006:  mov     rdi, qword 6666666666666667H




ALIGN   8
L_007:  mov     rax, rbx
        sub     rcx, 1
        imul    rdi
        mov     rax, rbx
        sar     rax, 63
        sar     rdx, 2
        sub     rdx, rax
        lea     rax, [rdx+rdx*4]
        add     rax, rax
        sub     rbx, rax
        add     ebx, 48
        mov     byte [rcx+1H], bl
        test    rdx, rdx
        mov     rbx, rdx
        jnz     L_007
        pop     rbx
        mov     rax, rsi
        pop     rbp
        pop     r12
        ret

L_008:  mov     edi, 3
        call    malloc
        mov     rsi, rax
        mov     byte [rax+2H], 0
        mov     byte [rax], 1
        mov     byte [rax+1H], 48
        mov     rax, rsi
        pop     rbx
        pop     rbp
        pop     r12
        ret

L_009:  mov     r12d, 1
        xor     edi, edi
        jmp     L_003





ALIGN   8
L_010:  lea     rcx, [rax+rbp]
        jmp     L_006








ALIGN   16

length:
        xor     eax, eax
        cmp     byte [rdi], 0
        jz      L_012





ALIGN   16
L_011:  add     rax, 1
        cmp     byte [rdi+rax], 0
        jnz     L_011

        ret

L_012:

        ret






ALIGN   8

substring:
        push    r12
        push    rbp
        mov     r12, rdi
        push    rbx
        mov     rbx, rdx
        mov     rbp, rsi
        sub     rbx, rsi
        lea     rdi, [rbx+2H]
        call    malloc
        test    rbx, rbx
        mov     byte [rax+rbx+1H], 0
        lea     rsi, [r12+rbp]
        js      L_014
        lea     rdi, [rbx+1H]
        xor     ecx, ecx




ALIGN   8
L_013:  movzx   edx, byte [rsi+rcx]
        mov     byte [rax+rcx], dl
        add     rcx, 1
        cmp     rcx, rdi
        jnz     L_013
L_014:  pop     rbx
        pop     rbp
        pop     r12
        ret


        nop





ALIGN   16

parseInt:
        movzx   eax, byte [rdi]
        sub     eax, 48
        cmp     al, 9
        ja      L_016
L_015:  jmp     L_015





ALIGN   8
L_016:  xor     eax, eax
        ret







ALIGN   16

ord:
        movsx   rax, byte [rdi+rsi]
        ret







ALIGN   16

address:
        lea     rax, [rdi+rsi*8+8H]
        ret







ALIGN   16

_malloc:
        push    rbx
        mov     rbx, rdi
        lea     rdi, [rdi*8+8H]
        call    malloc
        mov     qword [rax], rbx
        pop     rbx
        ret







ALIGN   16

_newArray:
        push    r14
        push    r13
        lea     r13d, [rdi+1H]
        push    r12
        push    rbp
        movsxd  rdi, edi
        push    rbx
        movsxd  rax, r13d
        cmp     rax, qword [rsi]
        mov     r12, rsi
        mov     rbx, qword [rsi+rdi*8+8H]
        jz      L_019
        lea     r14, [rbx+1H]
        lea     rdi, [r14*8]
        call    malloc
        test    rbx, rbx
        mov     rbp, rax
        mov     qword [rax], rbx
        jle     L_018
        mov     ebx, 1
L_017:  mov     rsi, r12
        mov     edi, r13d
        call    _newArray
        mov     qword [rbp+rbx*8], rax
        add     rbx, 1
        cmp     r14, rbx
        jnz     L_017
L_018:  pop     rbx
        mov     rax, rbp
        pop     rbp
        pop     r12
        pop     r13
        pop     r14
        ret





ALIGN   8
L_019:  lea     rdi, [rbx*8+8H]
        call    malloc
        mov     qword [rax], rbx
        pop     rbx
        pop     rbp
        pop     r12
        pop     r13
        pop     r14
        ret







ALIGN   16

newArray:
        mov     rsi, rdi
        xor     edi, edi
        jmp     _newArray






ALIGN   8

size:
        mov     rax, qword [rdi]
        ret







ALIGN   16

_strADD:
        push    r12
        push    rbp
        mov     rbp, rdi
        push    rbx
        cmp     byte [rdi], 0
        jz      L_030
        xor     eax, eax
        jmp     L_021

L_020:  mov     rax, r12
L_021:  cmp     byte [rbp+rax+1H], 0
        lea     r12, [rax+1H]
        jnz     L_020
        cmp     byte [rsi], 0
        jz      L_029
L_022:  xor     ebx, ebx




ALIGN   8
L_023:  add     rbx, 1
        cmp     byte [rsi+rbx], 0
        jnz     L_023
        lea     rdi, [rbx+r12+1H]
        call    malloc
        test    r12, r12
        jz      L_026
L_024:  xor     edx, edx




ALIGN   8
L_025:  movzx   ecx, byte [rbp+rdx]
        mov     byte [rax+rdx], cl
        add     rdx, 1
        cmp     r12, rdx
        jg      L_025
        test    rbx, rbx
        jz      L_028
L_026:  lea     rsi, [rax+rbx]
        xor     edx, edx




ALIGN   8
L_027:  movzx   ecx, byte [rbp+rdx]
        mov     byte [rsi+rdx], cl
        add     rdx, 1
        cmp     rbx, rdx
        jnz     L_027
L_028:  pop     rbx
        pop     rbp
        pop     r12
        ret

L_029:  lea     rdi, [rax+2H]
        xor     ebx, ebx
        call    malloc
        jmp     L_024

L_030:  xor     r12d, r12d
        cmp     byte [rsi], 0
        jnz     L_022
        pop     rbx
        pop     rbp
        pop     r12
        mov     edi, 1
        jmp     malloc







ALIGN   16

_strLT:
        movzx   r8d, byte [rdi]
        test    r8b, r8b
        je      L_042
        xor     edx, edx
        nop
L_031:  add     rdx, 1
        cmp     byte [rdi+rdx], 0
        jnz     L_031
        movzx   r9d, byte [rsi]
        test    r9b, r9b
        jz      L_040
L_032:  xor     ecx, ecx




ALIGN   8
L_033:  add     rcx, 1
        cmp     byte [rsi+rcx], 0
        jnz     L_033
        cmp     rcx, rdx
        mov     eax, 1
        jg      L_039
L_034:  xor     eax, eax
        cmp     rcx, rdx
        jl      L_038
        test    rdx, rdx
        jz      L_038
        cmp     r9b, r8b
        jg      L_041
        jl      L_038
        mov     eax, 1
        jmp     L_036






ALIGN   16
L_035:  movzx   r8d, byte [rdi+rax]
        movzx   ecx, byte [rsi+rax]
        cmp     r8b, cl
        jl      L_041
        add     rax, 1
        cmp     r8b, cl
        jg      L_037
L_036:  cmp     rdx, rax
        jnz     L_035
L_037:  xor     eax, eax
L_038:

        ret

L_039:

        ret

L_040:  xor     ecx, ecx
        jmp     L_034






ALIGN   16
L_041:  mov     eax, 1
        ret

L_042:  movzx   r9d, byte [rsi]
        xor     edx, edx
        test    r9b, r9b
        jnz     L_032
        xor     eax, eax
        jmp     L_038


        nop





ALIGN   16

_strGT:
        call    _strLT
        test    rax, rax
        sete    al
        movzx   eax, al
        ret


        nop

ALIGN   16
_strLE:
        movzx   r8d, byte [rdi]
        test    r8b, r8b
        je      L_054
        xor     edx, edx
        nop
L_043:  add     rdx, 1
        cmp     byte [rdi+rdx], 0
        jnz     L_043
        movzx   r9d, byte [rsi]
        test    r9b, r9b
        jz      L_052
L_044:  xor     ecx, ecx




ALIGN   8
L_045:  add     rcx, 1
        cmp     byte [rsi+rcx], 0
        jnz     L_045
        cmp     rcx, rdx
        mov     eax, 1
        jg      L_051
L_046:  xor     eax, eax
        cmp     rcx, rdx
        jl      L_050
        test    rdx, rdx
        jz      L_049
        cmp     r9b, r8b
        jg      L_049
        jl      L_050
        mov     eax, 1
        jmp     L_048






ALIGN   16
L_047:  movzx   r8d, byte [rdi+rax]
        movzx   ecx, byte [rsi+rax]
        cmp     r8b, cl
        jl      L_049
        add     rax, 1
        cmp     r8b, cl
        jg      L_053
L_048:  cmp     rdx, rax
        jnz     L_047
L_049:  mov     eax, 1
L_050:

        ret





ALIGN   8
L_051:

        ret

L_052:  xor     ecx, ecx
        jmp     L_046





ALIGN   8
L_053:  xor     eax, eax
        ret

L_054:  movzx   r9d, byte [rsi]
        xor     edx, edx
        test    r9b, r9b
        jnz     L_044
        jmp     L_049


_strGE:
        call    _strLE
        test    rax, rax
        sete    al
        movzx   eax, al
        ret


        nop

ALIGN   16
_strEQ:
        movzx   r9d, byte [rdi]
        xor     ecx, ecx
        test    r9b, r9b
        jz      L_056




ALIGN   8
L_055:  add     rcx, 1
        cmp     byte [rdi+rcx], 0
        jnz     L_055
L_056:  movzx   r8d, byte [rsi]
        test    r8b, r8b
        jz      L_063
        xor     edx, edx




ALIGN   8
L_057:  add     rdx, 1
        cmp     byte [rsi+rdx], 0
        jnz     L_057
        xor     eax, eax
        cmp     rdx, rcx
        jz      L_059
L_058:

        ret





ALIGN   8
L_059:  cmp     r8b, r9b
        jnz     L_058
        mov     eax, 1
        jmp     L_061





ALIGN   8
L_060:  movzx   ecx, byte [rdi+rax]
        add     rax, 1
        cmp     cl, byte [rsi+rax-1H]
        jnz     L_062
L_061:  cmp     rdx, rax
        jnz     L_060
        mov     eax, 1
        ret





ALIGN   8
L_062:  xor     eax, eax
        ret

L_063:  xor     eax, eax
        test    rcx, rcx
        sete    al
        ret






ALIGN   8

_strNE:
        call    _strEQ
        test    rax, rax
        sete    al
        movzx   eax, al
        ret



SECTION .data   


SECTION .bss    


SECTION .rodata.str1.1 

L_064:
        db 25H, 73H, 00H

L_065:
        db 25H, 6CH, 6CH, 64H, 00H


SECTION .text.unlikely 


