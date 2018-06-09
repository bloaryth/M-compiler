





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

extern memcpy
extern __stack_chk_fail
extern __isoc99_scanf
extern malloc
extern puts
extern __printf_chk


SECTION .text   6

print:
        mov     rdx, rdi
        mov     esi, L_123
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
        mov     edi, 256
        call    malloc
        mov     edi, L_123
        mov     rbx, rax
        mov     rsi, rax
        xor     eax, eax
        call    __isoc99_scanf
        mov     rax, rbx
        pop     rbx
        ret







ALIGN   16

getInt:
        sub     rsp, 24
        mov     edi, L_124


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
        push    r13
        push    r12
        push    rbp
        push    rbx
        sub     rsp, 8
        test    rdi, rdi
        je      L_008
        mov     rbx, rdi
        jns     L_009
        neg     rbx
L_002:



        db 49H, 0C7H, 0C4H, 0FFH, 0FFH, 0FFH, 0FFH

        db 0BDH, 01H, 00H, 00H, 00H
L_003:  mov     rcx, rbx
        mov     rsi, qword 6666666666666667H
        jmp     L_005





ALIGN   8
L_004:  mov     rbp, r13
L_005:  mov     rax, rcx
        sar     rcx, 63
        lea     r13, [rbp+1H]
        imul    rsi
        sar     rdx, 2
        sub     rdx, rcx
        mov     rcx, rdx
        jnz     L_004
        lea     rdi, [rbp+2H]
        call    malloc
        cmp     r12, -1
        mov     rsi, rax
        mov     byte [rax+r13], 0
        jne     L_010
        lea     rcx, [rax+rbp]
        mov     byte [rax], 45
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
        add     rsp, 8
        mov     rax, rsi
        pop     rbx
        pop     rbp
        pop     r12
        pop     r13
        ret





ALIGN   8
L_008:  mov     edi, 2
        call    malloc
        mov     byte [rax+1H], 0
        mov     byte [rax], 48
        mov     rsi, rax
        add     rsp, 8
        mov     rax, rsi
        pop     rbx
        pop     rbp
        pop     r12
        pop     r13
        ret





ALIGN   8
L_009:  mov     r12d, 1
        xor     ebp, ebp
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
        mov     rcx, rax
        mov     byte [rax+rbx+1H], 0
        js      L_013
        lea     rdx, [rbx+1H]
        lea     rsi, [r12+rbp]
        mov     rdi, rax
        call    memcpy
        mov     rcx, rax
L_013:  pop     rbx
        mov     rax, rcx
        pop     rbp
        pop     r12
        ret







ALIGN   16

parseInt:
        movsx   edx, byte [rdi]
        lea     eax, [rdx-30H]
        cmp     al, 9
        ja      L_015
        add     rdi, 1
        xor     eax, eax
L_014:  sub     edx, 48
        lea     rax, [rax+rax*4]
        add     rdi, 1
        movsxd  rdx, edx
        lea     rax, [rdx+rax*2]
        movsx   edx, byte [rdi-1H]
        lea     ecx, [rdx-30H]
        cmp     cl, 9
        jbe     L_014

        ret

L_015:  xor     eax, eax
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
        push    r15
        push    r14
        mov     r14, rsi
        push    r13
        push    r12
        push    rbp
        push    rbx
        movsxd  rbp, edi
        lea     eax, [rbp+1H]
        sub     rsp, 328
        mov     rbx, qword [rsi]
        cdqe
        cmp     rax, rbx
        je      L_050
        movsxd  rax, ebp
        add     rax, 1
        mov     r12, qword [rsi+rax*8]
        lea     r13, [rax*8]
        lea     rax, [r12+1H]
        lea     rdi, [rax*8]
        mov     qword [rsp+0E8H], rax
        call    malloc
        test    r12, r12
        mov     qword [rsp+50H], rax
        mov     qword [rax], r12
        jle     L_049
        lea     eax, [rbp+2H]
        lea     edx, [rbp+3H]
        lea     r15d, [rbp+9H]
        mov     qword [rsp+88H], 1
        cdqe
        movsxd  rcx, edx
        mov     qword [rsp+0E0H], rax
        lea     rax, [r14+r13+8H]
        lea     rdx, [r14+rcx*8+8H]
        mov     qword [rsp+118H], rcx
        mov     qword [rsp+0B0H], rax
        lea     rax, [r14+r13+10H]
        mov     qword [rsp+0C0H], rdx
        lea     edx, [rbp+5H]
        mov     qword [rsp+0B8H], rax
        lea     eax, [rbp+4H]
        movsxd  rdx, edx
        cdqe
        lea     rcx, [r14+rdx*8+8H]
        mov     qword [rsp+128H], rdx
        mov     qword [rsp+120H], rax
        lea     rax, [r14+rax*8+8H]
        lea     edx, [rbp+7H]
        mov     qword [rsp+0D0H], rcx
        mov     qword [rsp+0C8H], rax
        lea     eax, [rbp+6H]
        movsxd  rcx, edx
        lea     rdx, [r14+rcx*8+8H]
        mov     qword [rsp+138H], rcx
        cdqe
        mov     qword [rsp+130H], rax
        lea     rax, [r14+rax*8+8H]
        mov     qword [rsp+38H], rdx
        mov     qword [rsp+0D8H], rax
        lea     eax, [rbp+8H]
        cdqe
        mov     qword [rsp+48H], rax
        lea     rax, [r14+rax*8+8H]
        mov     qword [rsp+18H], rax
        movsxd  rax, r15d
        mov     qword [rsp+20H], rax
L_016:  cmp     qword [rsp+0E0H], rbx
        mov     rax, qword [rsp+0B0H]
        je      L_047
        mov     rbp, qword [rax]
        lea     rax, [rbp+1H]
        lea     rdi, [rax*8]
        mov     qword [rsp+0F0H], rax
        call    malloc
        test    rbp, rbp
        mov     qword [rsp+58H], rax
        mov     qword [rax], rbp
        jle     L_046
        mov     qword [rsp+90H], 1
L_017:  cmp     qword [rsp+118H], rbx
        mov     rax, qword [rsp+0B8H]
        je      L_044
        mov     rbp, qword [rax]
        lea     rax, [rbp+1H]
        lea     rdi, [rax*8]
        mov     qword [rsp+0F8H], rax
        call    malloc
        test    rbp, rbp
        mov     qword [rsp+60H], rax
        mov     qword [rax], rbp
        jle     L_043
        mov     qword [rsp+80H], 1
L_018:  cmp     qword [rsp+120H], rbx
        mov     rax, qword [rsp+0C0H]
        je      L_041
        mov     rbp, qword [rax]
        lea     rax, [rbp+1H]
        lea     rdi, [rax*8]
        mov     qword [rsp+100H], rax
        call    malloc
        test    rbp, rbp
        mov     qword [rsp+68H], rax
        mov     qword [rax], rbp
        jle     L_040
        mov     qword [rsp+98H], 1
L_019:  cmp     qword [rsp+128H], rbx
        mov     rax, qword [rsp+0C8H]
        je      L_038
        mov     rbp, qword [rax]
        lea     rax, [rbp+1H]
        lea     rdi, [rax*8]
        mov     qword [rsp+108H], rax
        call    malloc
        test    rbp, rbp
        mov     qword [rsp+70H], rax
        mov     qword [rax], rbp
        jle     L_037
        mov     qword [rsp+0A0H], 1
L_020:  cmp     qword [rsp+130H], rbx
        mov     rax, qword [rsp+0D0H]
        je      L_035
        mov     rbp, qword [rax]
        lea     rax, [rbp+1H]
        lea     rdi, [rax*8]
        mov     qword [rsp+110H], rax
        call    malloc
        test    rbp, rbp
        mov     qword [rsp+78H], rax
        mov     qword [rax], rbp
        jle     L_034
        mov     qword [rsp+0A8H], 1
L_021:  cmp     qword [rsp+138H], rbx
        mov     rax, qword [rsp+0D8H]
        je      L_032
        mov     rbp, qword [rax]
        lea     rax, [rbp+1H]
        lea     rdi, [rax*8]
        mov     qword [rsp+40H], rax
        call    malloc
        test    rbp, rbp
        mov     qword [rsp+30H], rax
        mov     qword [rax], rbp
        jle     L_031
        cmp     qword [rsp+48H], rbx
        mov     qword [rsp+28H], 1
        mov     rax, qword [rsp+38H]
        je      L_029
        nop
L_022:  mov     rbp, qword [rax]
        lea     rax, [rbp+1H]
        lea     rdi, [rax*8]
        mov     qword [rsp+10H], rax
        call    malloc
        test    rbp, rbp
        mov     qword [rsp+8H], rax
        mov     qword [rax], rbp
        jle     L_027
        cmp     qword [rsp+20H], rbx
        mov     r12d, 1
        mov     rax, qword [rsp+18H]
        jz      L_026




ALIGN   8
L_023:  mov     r13, qword [rax]
        lea     rbp, [r13+1H]
        lea     rdi, [rbp*8]
        call    malloc
        test    r13, r13
        mov     rbx, rax
        mov     qword [rax], r13
        jle     L_025
        mov     r13d, 1




ALIGN   8
L_024:  mov     rsi, r14
        mov     edi, r15d
        call    _newArray
        mov     qword [rbx+r13*8], rax
        add     r13, 1
        cmp     r13, rbp
        jnz     L_024
L_025:  mov     rax, qword [rsp+8H]
        mov     qword [rax+r12*8], rbx
        add     r12, 1
        cmp     r12, qword [rsp+10H]
        jz      L_027
        mov     rbx, qword [r14]
        cmp     qword [rsp+20H], rbx
        mov     rax, qword [rsp+18H]
        jnz     L_023
L_026:  mov     rbp, qword [rax]
        lea     rdi, [rbp*8+8H]
        call    malloc
        mov     qword [rax], rbp
        mov     rbx, rax
        jmp     L_025





ALIGN   8
L_027:  mov     r13, rax
L_028:  mov     rax, qword [rsp+28H]
        mov     rcx, qword [rsp+30H]
        mov     qword [rcx+rax*8], r13
        add     rax, 1
        cmp     rax, qword [rsp+40H]
        mov     qword [rsp+28H], rax
        jz      L_030
        mov     rbx, qword [r14]
        cmp     qword [rsp+48H], rbx
        mov     rax, qword [rsp+38H]
        jne     L_022
L_029:  mov     rbx, qword [rax]
        lea     rdi, [rbx*8+8H]
        call    malloc
        mov     qword [rax], rbx
        mov     r13, rax
        jmp     L_028

L_030:  mov     rax, rcx
L_031:  mov     rcx, qword [rsp+0A8H]
        mov     rdx, qword [rsp+78H]
        mov     qword [rdx+rcx*8], rax
        add     rcx, 1
        cmp     rcx, qword [rsp+110H]
        mov     qword [rsp+0A8H], rcx
        jz      L_033
        mov     rbx, qword [r14]
        jmp     L_021

L_032:  mov     rbx, qword [rax]
        lea     rdi, [rbx*8+8H]
        call    malloc
        mov     qword [rax], rbx
        jmp     L_031

L_033:  mov     rax, rdx
L_034:  mov     rdx, qword [rsp+0A0H]
        mov     rcx, qword [rsp+70H]
        mov     qword [rcx+rdx*8], rax
        add     rdx, 1
        cmp     rdx, qword [rsp+108H]
        mov     qword [rsp+0A0H], rdx
        jz      L_036
        mov     rbx, qword [r14]
        jmp     L_020

L_035:  mov     rbx, qword [rax]
        lea     rdi, [rbx*8+8H]
        call    malloc
        mov     qword [rax], rbx
        jmp     L_034

L_036:  mov     rax, rcx
L_037:  mov     rcx, qword [rsp+98H]
        mov     rdx, qword [rsp+68H]
        mov     qword [rdx+rcx*8], rax
        add     rcx, 1
        cmp     rcx, qword [rsp+100H]
        mov     qword [rsp+98H], rcx
        jz      L_039
        mov     rbx, qword [r14]
        jmp     L_019

L_038:  mov     rbx, qword [rax]
        lea     rdi, [rbx*8+8H]
        call    malloc
        mov     qword [rax], rbx
        jmp     L_037

L_039:  mov     rax, rdx
L_040:  mov     rdx, qword [rsp+80H]
        mov     rcx, qword [rsp+60H]
        mov     qword [rcx+rdx*8], rax
        add     rdx, 1
        cmp     rdx, qword [rsp+0F8H]
        mov     qword [rsp+80H], rdx
        jz      L_042
        mov     rbx, qword [r14]
        jmp     L_018

L_041:  mov     rbx, qword [rax]
        lea     rdi, [rbx*8+8H]
        call    malloc
        mov     qword [rax], rbx
        jmp     L_040

L_042:  mov     rax, rcx
L_043:  mov     rcx, qword [rsp+90H]
        mov     rdx, qword [rsp+58H]
        mov     qword [rdx+rcx*8], rax
        add     rcx, 1
        cmp     rcx, qword [rsp+0F0H]
        mov     qword [rsp+90H], rcx
        jz      L_045
        mov     rbx, qword [r14]
        jmp     L_017

L_044:  mov     rbx, qword [rax]
        lea     rdi, [rbx*8+8H]
        call    malloc
        mov     qword [rax], rbx
        jmp     L_043

L_045:  mov     rax, rdx
L_046:  mov     rdx, qword [rsp+88H]
        mov     rcx, qword [rsp+50H]
        mov     qword [rcx+rdx*8], rax
        add     rdx, 1
        cmp     rdx, qword [rsp+0E8H]
        mov     qword [rsp+88H], rdx
        jz      L_048
        mov     rbx, qword [r14]
        jmp     L_016

L_047:  mov     rbx, qword [rax]
        lea     rdi, [rbx*8+8H]
        call    malloc
        mov     qword [rax], rbx
        jmp     L_046

L_048:  mov     rax, rcx
L_049:  add     rsp, 328
        pop     rbx
        pop     rbp
        pop     r12
        pop     r13
        pop     r14
        pop     r15
        ret

L_050:  mov     rbx, qword [rsi+rbp*8+8H]
        lea     rdi, [rbx*8+8H]
        call    malloc
        mov     qword [rax], rbx
        jmp     L_049







ALIGN   16

newArray:
        push    r13
        push    r12
        mov     r12, rdi
        push    rbp
        push    rbx
        sub     rsp, 8
        cmp     qword [rdi], 1
        mov     rbx, qword [rdi+8H]
        jz      L_053
        lea     r13, [rbx+1H]
        lea     rdi, [r13*8]
        call    malloc
        test    rbx, rbx
        mov     rbp, rax
        mov     qword [rax], rbx
        jle     L_052
        mov     ebx, 1




ALIGN   16
L_051:  mov     rsi, r12
        mov     edi, 1
        call    _newArray
        mov     qword [rbp+rbx*8], rax
        add     rbx, 1
        cmp     r13, rbx
        jnz     L_051
L_052:  add     rsp, 8
        mov     rax, rbp
        pop     rbx
        pop     rbp
        pop     r12
        pop     r13
        ret





ALIGN   8
L_053:  lea     rdi, [rbx*8+8H]
        call    malloc
        mov     qword [rax], rbx
        add     rsp, 8
        pop     rbx
        pop     rbp
        pop     r12
        pop     r13
        ret






ALIGN   8

size:
        mov     rax, qword [rdi]
        ret







ALIGN   16

_strADD:
        push    r15
        push    r14
        mov     r15, rdi
        push    r13
        push    r12
        mov     r14, rsi
        push    rbp
        push    rbx
        sub     rsp, 8
        cmp     byte [rdi], 0
        je      L_062
        xor     eax, eax
        jmp     L_055





ALIGN   8
L_054:  mov     rax, rbx
L_055:  cmp     byte [r15+rax+1H], 0
        lea     rbx, [rax+1H]
        mov     r12, rbx
        jnz     L_054
        cmp     byte [r14], 0
        jz      L_061
L_056:  xor     ebp, ebp




ALIGN   8
L_057:  add     rbp, 1
        cmp     byte [r14+rbp], 0
        jnz     L_057
        lea     r12, [rbp+rbx]
        lea     rdi, [r12+1H]
        call    malloc
        test    rbx, rbx
        mov     r13, rax
        jz      L_059
L_058:  test    rbx, rbx
        mov     edx, 1
        mov     rsi, r15
        cmovg   rdx, rbx
        mov     rdi, r13
        call    memcpy
        test    rbp, rbp
        jz      L_060
L_059:  lea     rdi, [r13+rbx]
        mov     rdx, rbp
        mov     rsi, r14
        call    memcpy
L_060:  mov     byte [r13+r12], 0
        add     rsp, 8
        mov     rax, r13
        pop     rbx
        pop     rbp
        pop     r12
        pop     r13
        pop     r14
        pop     r15
        ret

L_061:  lea     rdi, [rax+2H]
        xor     ebp, ebp
        call    malloc
        mov     r13, rax
        jmp     L_058

L_062:  xor     ebx, ebx
        cmp     byte [rsi], 0
        jne     L_056
        mov     edi, 1
        xor     r12d, r12d
        call    malloc
        mov     r13, rax
        jmp     L_060






ALIGN   8

_strLT:
        movzx   eax, byte [rdi]
        test    al, al
        jz      L_069
        xor     edx, edx




ALIGN   8
L_063:  add     rdx, 1
        cmp     byte [rdi+rdx], 0
        jnz     L_063
        movzx   r8d, byte [rsi]
        test    r8b, r8b
        jz      L_071
L_064:  xor     ecx, ecx




ALIGN   8
L_065:  add     rcx, 1
        cmp     byte [rsi+rcx], 0
        jnz     L_065
        cmp     rcx, rdx
        mov     r10, rdx
        cmovle  r10, rcx
        test    r10, r10
        jz      L_072
        cmp     al, r8b
        jl      L_068
        mov     eax, 1
        jle     L_067
        jmp     L_070





ALIGN   8
L_066:  movzx   r9d, byte [rdi+rax]
        movzx   r8d, byte [rsi+rax]
        cmp     r9b, r8b
        jl      L_068
        add     rax, 1
        cmp     r9b, r8b
        jg      L_070
L_067:  cmp     rax, r10
        jnz     L_066
        xor     eax, eax
        cmp     rcx, rdx
        setg    al
        ret






ALIGN   16
L_068:  mov     eax, 1
        ret

L_069:  movzx   r8d, byte [rsi]
        xor     edx, edx
        test    r8b, r8b
        jnz     L_064
L_070:  xor     eax, eax
        ret

L_071:  xor     ecx, ecx
L_072:  xor     eax, eax
        cmp     rdx, rcx
        setl    al
        ret


        nop

ALIGN   16
_strGT:
        movzx   eax, byte [rdi]
        test    al, al
        jz      L_080
        xor     edx, edx




ALIGN   8
L_073:  add     rdx, 1
        cmp     byte [rdi+rdx], 0
        jnz     L_073
        movzx   r8d, byte [rsi]
        test    r8b, r8b
        jz      L_082
L_074:  xor     ecx, ecx




ALIGN   8
L_075:  add     rcx, 1
        cmp     byte [rsi+rcx], 0
        jnz     L_075
        cmp     rcx, rdx
        mov     r10, rdx
        cmovle  r10, rcx
        test    r10, r10
        jz      L_078
        cmp     al, r8b
        jl      L_079
        mov     eax, 1
        jle     L_077
        jmp     L_081





ALIGN   8
L_076:  movzx   r9d, byte [rdi+rax]
        movzx   r8d, byte [rsi+rax]
        cmp     r9b, r8b
        jl      L_079
        add     rax, 1
        cmp     r9b, r8b
        jg      L_081
L_077:  cmp     rax, r10
        jnz     L_076
L_078:  xor     eax, eax
        cmp     rcx, rdx
        setle   al
        ret






ALIGN   16
L_079:  xor     eax, eax
        ret

L_080:  movzx   r8d, byte [rsi]
        xor     edx, edx
        test    r8b, r8b
        jnz     L_074
L_081:  mov     eax, 1
        ret

L_082:  xor     ecx, ecx
        jmp     L_078






ALIGN   16

_strLE:
        movzx   eax, byte [rdi]
        test    al, al
        jz      L_088
        xor     edx, edx




ALIGN   8
L_083:  add     rdx, 1
        cmp     byte [rdi+rdx], 0
        jnz     L_083
        movzx   r8d, byte [rsi]
        test    r8b, r8b
        jz      L_091
L_084:  xor     ecx, ecx




ALIGN   8
L_085:  add     rcx, 1
        cmp     byte [rsi+rcx], 0
        jnz     L_085
        cmp     rcx, rdx
        mov     r10, rdx
        cmovle  r10, rcx
        test    r10, r10
        jz      L_092
        cmp     al, r8b
        jl      L_089
        mov     eax, 1
        jle     L_087
        jmp     L_090





ALIGN   8
L_086:  movzx   r9d, byte [rdi+rax]
        movzx   r8d, byte [rsi+rax]
        cmp     r9b, r8b
        jl      L_089
        add     rax, 1
        cmp     r9b, r8b
        jg      L_090
L_087:  cmp     rax, r10
        jnz     L_086
        xor     eax, eax
        cmp     rcx, rdx
        setge   al
        ret

L_088:  movzx   r8d, byte [rsi]
        xor     edx, edx
        test    r8b, r8b
        jnz     L_084
L_089:  mov     eax, 1
        ret






ALIGN   16
L_090:  xor     eax, eax
        ret

L_091:  xor     ecx, ecx
L_092:  xor     eax, eax
        cmp     rdx, rcx
        setle   al
        ret






ALIGN   8

_strGE:
        movzx   eax, byte [rdi]
        test    al, al
        jz      L_099
        xor     edx, edx




ALIGN   8
L_093:  add     rdx, 1
        cmp     byte [rdi+rdx], 0
        jnz     L_093
        movzx   r8d, byte [rsi]
        test    r8b, r8b
        jz      L_102
L_094:  xor     ecx, ecx




ALIGN   8
L_095:  add     rcx, 1
        cmp     byte [rsi+rcx], 0
        jnz     L_095
        cmp     rcx, rdx
        mov     r10, rdx
        cmovle  r10, rcx
        test    r10, r10
        jz      L_098
        cmp     al, r8b
        jl      L_100
        mov     eax, 1
        jle     L_097
        jmp     L_101





ALIGN   8
L_096:  movzx   r9d, byte [rdi+rax]
        movzx   r8d, byte [rsi+rax]
        cmp     r9b, r8b
        jl      L_100
        add     rax, 1
        cmp     r9b, r8b
        jg      L_101
L_097:  cmp     rax, r10
        jnz     L_096
L_098:  xor     eax, eax
        cmp     rcx, rdx
        setl    al
        ret

L_099:  movzx   r8d, byte [rsi]
        xor     edx, edx
        test    r8b, r8b
        jnz     L_094
L_100:  xor     eax, eax
        ret





ALIGN   8
L_101:  mov     eax, 1
        ret

L_102:  xor     ecx, ecx
        jmp     L_098







ALIGN   16

_strEQ:
        movzx   r9d, byte [rdi]
        xor     ecx, ecx
        test    r9b, r9b
        jz      L_104




ALIGN   8
L_103:  add     rcx, 1
        cmp     byte [rdi+rcx], 0
        jnz     L_103
L_104:  movzx   r8d, byte [rsi]
        test    r8b, r8b
        jz      L_111
        xor     edx, edx




ALIGN   8
L_105:  add     rdx, 1
        cmp     byte [rsi+rdx], 0
        jnz     L_105
        xor     eax, eax
        cmp     rdx, rcx
        jz      L_107
L_106:

        ret





ALIGN   8
L_107:  cmp     r8b, r9b
        jnz     L_106
        mov     eax, 1
        jmp     L_109





ALIGN   8
L_108:  movzx   ecx, byte [rdi+rax]
        add     rax, 1
        cmp     cl, byte [rsi+rax-1H]
        jnz     L_110
L_109:  cmp     rdx, rax
        jnz     L_108
        mov     eax, 1
        ret





ALIGN   8
L_110:  xor     eax, eax
        ret

L_111:  xor     eax, eax
        test    rcx, rcx
        sete    al
        ret






ALIGN   8

_strNE:
        movzx   r8d, byte [rdi]
        test    r8b, r8b
        jz      L_121
        xor     ecx, ecx




ALIGN   8
L_112:  add     rcx, 1
        cmp     byte [rdi+rcx], 0
        jnz     L_112
        movzx   r9d, byte [rsi]
        test    r9b, r9b
        jz      L_120
L_113:  xor     edx, edx




ALIGN   8
L_114:  add     rdx, 1
        cmp     byte [rsi+rdx], 0
        jnz     L_114
        cmp     rdx, rcx
        mov     eax, 1
        jz      L_115

        ret





ALIGN   8
L_115:  test    rdx, rdx
        jz      L_118
        cmp     r8b, r9b
        jnz     L_122
        mov     eax, 1
        jmp     L_117





ALIGN   8
L_116:  movzx   ecx, byte [rdi+rax]
        add     rax, 1
        cmp     cl, byte [rsi+rax-1H]
        jnz     L_120
L_117:  cmp     rdx, rax
        jnz     L_116
L_118:  xor     eax, eax
L_119:

        ret





ALIGN   8
L_120:  mov     eax, 1
        ret

L_121:  movzx   r9d, byte [rsi]
        xor     ecx, ecx
        test    r9b, r9b
        jnz     L_113
        xor     eax, eax
        jmp     L_119

L_122:

        ret



SECTION .data   


SECTION .bss    


SECTION .rodata.str1.1 

L_123:
        db 25H, 73H, 00H

L_124:
        db 25H, 6CH, 6CH, 64H, 00H


SECTION .text.unlikely 


global main

SECTION .text

main:
	push rbp
	mov rbp, rsp
	sub rsp, 336
_main.entry.0:
	mov rbx, 5


	mov rsi, 0


	mov rdi, 0

	cmp rsi, rdi

	mov rdi, 0
	setne dil

	mov r8, 1

	cmp rdi, r8

	je and_lhs_true.9
	jne Init_false.8

Init_true.7:
	mov rdi, 1

	jmp Init_merge.10

Init_false.8:
	mov rdi, 0

	jmp Init_merge.10

and_lhs_true.9:
	mov rax, rbx
	cqo
	idiv rsi
	mov rdi, rax

	mov r8, 1

	cmp rdi, r8

	mov rdi, 0
	setne dil

	mov r8, 1

	cmp rdi, r8

	je Init_true.7
	jne Init_false.8

Init_merge.10:

	mov r8, 1

	cmp r8, rdi

	je if_true.11
	jne if_false.12

if_true.11:
	mov rdi, 10


	jmp if_merge.13

if_false.12:
	mov rdi, 20


	jmp if_merge.13

if_merge.13:
	mov r8, 10

	cmp rdi, r8

	mov r8, 0
	sete r8b

	mov r9, 1

	cmp r8, r9

	je and_lhs_true.23
	jne Assign_true.20

Assign_true.20:
	mov rsi, 0

	mov rbx, 0

	mov rsi, 1

	xor rbx, rsi

	jmp Ret_merge.24

Assign_false.21:
	mov rbx, 1

	mov rsi, 1

	xor rbx, rsi

	jmp Ret_merge.24

and_lhs_true.23:
	mov rax, rbx
	cqo
	idiv rsi
	mov r8, rax

	mov rsi, 0

	cmp r8, rsi

	mov rsi, 0
	sete sil

	mov r8, 1

	cmp rsi, r8

	je and_lhs_true.22
	jne Assign_true.20

	mov r8, 1

	cmp rsi, r8

	je and_lhs_true.22
	jne Assign_true.20

and_lhs_true.22:
	mov rsi, 1

	mov rsi, 5

	cmp rbx, rsi

	mov rbx, 0
	sete bl

	mov rsi, 1

	cmp rbx, rsi

	je Assign_false.21
	jne Assign_true.20

	mov rsi, 1

	xor rbx, rsi

Ret_merge.24:

	mov rsi, 1

	cmp rsi, rbx

	je if_true.25
	jne if_merge.26

if_true.25:
	mov rbx, 30

	mov rdi, rbx

	jmp if_merge.26

if_merge.26:
	mov rbx, rdi

	mov rax, rbx
	leave
	ret

	mov rbx, 0

	mov rax, rbx
	leave
	ret


SECTION .data

SECTION .rodata.str1.1


