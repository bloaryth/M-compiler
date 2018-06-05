





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
extern printf


SECTION .text   

print:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 16
        mov     qword [rbp-8H], rdi
        mov     rax, qword [rbp-8H]
        mov     rsi, rax
        mov     edi, L_043
        mov     eax, 0
        call    printf
        nop
        leave
        ret


println:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 16
        mov     qword [rbp-8H], rdi
        mov     rax, qword [rbp-8H]
        mov     rdi, rax
        call    puts
        nop
        leave
        ret


getString:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 16
        mov     rax, qword [rbp-8H]
        mov     rsi, rax
        mov     edi, L_043
        mov     eax, 0
        call    __isoc99_scanf
        mov     rax, qword [rbp-8H]
        leave
        ret


getInt:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 16


        mov     rax, qword [fs:abs 28H]
        mov     qword [rbp-8H], rax
        xor     eax, eax
        lea     rax, [rbp-10H]
        mov     rsi, rax
        mov     edi, L_044
        mov     eax, 0
        call    __isoc99_scanf
        mov     rax, qword [rbp-10H]
        mov     rdx, qword [rbp-8H]


        xor     rdx, qword [fs:abs 28H]
        jz      L_001
        call    __stack_chk_fail
L_001:  leave
        ret


toString:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 64
        mov     qword [rbp-38H], rdi
        mov     qword [rbp-28H], 0
        mov     qword [rbp-20H], 1
        cmp     qword [rbp-38H], 0
        jnz     L_002
        mov     qword [rbp-28H], 1
L_002:  cmp     qword [rbp-38H], 0
        jns     L_003
        neg     qword [rbp-38H]
        mov     qword [rbp-20H], -1
        add     qword [rbp-28H], 1
L_003:  mov     rax, qword [rbp-38H]
        mov     qword [rbp-18H], rax
        jmp     L_005

L_004:  add     qword [rbp-28H], 1
        mov     rcx, qword [rbp-18H]
        mov     rdx, qword 6666666666666667H
        mov     rax, rcx
        imul    rdx
        sar     rdx, 2
        mov     rax, rcx
        sar     rax, 63
        sub     rdx, rax
        mov     rax, rdx
        mov     qword [rbp-18H], rax
L_005:  cmp     qword [rbp-18H], 0
        jg      L_004
        mov     rax, qword [rbp-28H]
        add     rax, 1
        mov     rdi, rax
        call    malloc
        mov     qword [rbp-8H], rax
        mov     rdx, qword [rbp-28H]
        mov     rax, qword [rbp-8H]
        add     rax, rdx
        mov     byte [rax], 0
        mov     rax, qword [rbp-8H]
        mov     qword [rbp-10H], rax
        cmp     qword [rbp-20H], -1
        jnz     L_006
        mov     rax, qword [rbp-10H]
        mov     byte [rax], 45
L_006:  mov     rax, qword [rbp-28H]
        lea     rdx, [rax-1H]
        mov     rax, qword [rbp-8H]
        add     rax, rdx
        mov     qword [rbp-10H], rax
        cmp     qword [rbp-38H], 0
        jnz     L_008
        mov     rax, qword [rbp-10H]
        mov     byte [rax], 48
        jmp     L_008

L_007:  mov     rcx, qword [rbp-38H]
        mov     rdx, qword 6666666666666667H
        mov     rax, rcx
        imul    rdx
        sar     rdx, 2
        mov     rax, rcx
        sar     rax, 63
        sub     rdx, rax
        mov     rax, rdx
        shl     rax, 2
        add     rax, rdx
        add     rax, rax
        sub     rcx, rax
        mov     rdx, rcx
        mov     eax, edx
        add     eax, 48
        mov     edx, eax
        mov     rax, qword [rbp-10H]
        mov     byte [rax], dl
        sub     qword [rbp-10H], 1
        mov     rcx, qword [rbp-38H]
        mov     rdx, qword 6666666666666667H
        mov     rax, rcx
        imul    rdx
        sar     rdx, 2
        mov     rax, rcx
        sar     rax, 63
        sub     rdx, rax
        mov     rax, rdx
        mov     qword [rbp-38H], rax
L_008:  cmp     qword [rbp-38H], 0
        jg      L_007
        mov     rax, qword [rbp-8H]
        leave
        ret


length:
        push    rbp
        mov     rbp, rsp
        mov     qword [rbp-18H], rdi
        mov     qword [rbp-8H], 0
        jmp     L_010

L_009:  add     qword [rbp-8H], 1
L_010:  mov     rdx, qword [rbp-8H]
        mov     rax, qword [rbp-18H]
        add     rax, rdx
        movzx   eax, byte [rax]
        test    al, al
        jnz     L_009
        mov     rax, qword [rbp-8H]
        pop     rbp
        ret


substring:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 48
        mov     qword [rbp-18H], rdi
        mov     qword [rbp-20H], rsi
        mov     qword [rbp-28H], rdx
        mov     rax, qword [rbp-28H]
        sub     rax, qword [rbp-20H]
        add     rax, 2
        mov     rdi, rax
        call    malloc
        mov     qword [rbp-8H], rax
        mov     rax, qword [rbp-28H]
        sub     rax, qword [rbp-20H]
        lea     rdx, [rax+1H]
        mov     rax, qword [rbp-8H]
        add     rax, rdx
        mov     byte [rax], 0
        mov     qword [rbp-10H], 0
        jmp     L_012

L_011:  mov     rdx, qword [rbp-10H]
        mov     rax, qword [rbp-8H]
        add     rdx, rax
        mov     rcx, qword [rbp-20H]
        mov     rax, qword [rbp-10H]
        add     rax, rcx
        mov     rcx, rax
        mov     rax, qword [rbp-18H]
        add     rax, rcx
        movzx   eax, byte [rax]
        mov     byte [rdx], al
        add     qword [rbp-10H], 1
L_012:  mov     rax, qword [rbp-28H]
        sub     rax, qword [rbp-20H]
        add     rax, 1
        cmp     rax, qword [rbp-10H]
        jg      L_011
        mov     rax, qword [rbp-8H]
        leave
        ret


parseInt:
        push    rbp
        mov     rbp, rsp
        mov     qword [rbp-18H], rdi
        mov     qword [rbp-10H], 0
        mov     qword [rbp-8H], 0
        jmp     L_014

L_013:  mov     rdx, qword [rbp-10H]
        mov     rax, rdx
        shl     rax, 2
        add     rax, rdx
        add     rax, rax
        mov     qword [rbp-10H], rax
        mov     rdx, qword [rbp-8H]
        mov     rax, qword [rbp-18H]
        add     rax, rdx
        movzx   eax, byte [rax]
        movsx   eax, al
        sub     eax, 48
        cdqe
        add     qword [rbp-10H], rax
L_014:  mov     rdx, qword [rbp-8H]
        mov     rax, qword [rbp-18H]
        add     rax, rdx
        movzx   eax, byte [rax]
        cmp     al, 47
        jle     L_015
        mov     rdx, qword [rbp-8H]
        mov     rax, qword [rbp-18H]
        add     rax, rdx
        movzx   eax, byte [rax]
        cmp     al, 57
        jle     L_013
L_015:  mov     rax, qword [rbp-10H]
        pop     rbp
        ret


ord:
        push    rbp
        mov     rbp, rsp
        mov     qword [rbp-8H], rdi
        mov     qword [rbp-10H], rsi
        mov     rdx, qword [rbp-10H]
        mov     rax, qword [rbp-8H]
        add     rax, rdx
        movzx   eax, byte [rax]
        movsx   rax, al
        pop     rbp
        ret


address:
        push    rbp
        mov     rbp, rsp
        mov     qword [rbp-8H], rdi
        mov     qword [rbp-10H], rsi
        mov     rax, qword [rbp-10H]
        add     rax, 1
        lea     rdx, [rax*8]
        mov     rax, qword [rbp-8H]
        add     rax, rdx
        pop     rbp
        ret


_malloc:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 32
        mov     qword [rbp-18H], rdi
        mov     rax, qword [rbp-18H]
        add     rax, 1
        shl     rax, 3
        mov     rdi, rax
        call    malloc
        mov     qword [rbp-8H], rax
        mov     rax, qword [rbp-8H]
        mov     rdx, qword [rbp-18H]
        mov     qword [rax], rdx
        mov     rax, qword [rbp-8H]
        leave
        ret


_newArray:
        push    rbp
        mov     rbp, rsp
        push    rbx
        sub     rsp, 56
        mov     dword [rbp-34H], edi
        mov     qword [rbp-40H], rsi
        mov     eax, dword [rbp-34H]
        add     eax, 1
        movsxd  rdx, eax
        mov     rax, qword [rbp-40H]
        mov     rax, qword [rax]
        cmp     rdx, rax
        jnz     L_016
        mov     eax, dword [rbp-34H]
        movsxd  rdx, eax
        mov     rax, qword [rbp-40H]
        mov     rsi, rdx
        mov     rdi, rax
        call    address
        mov     rax, qword [rax]
        mov     rdi, rax
        call    _malloc
        jmp     L_019

L_016:  mov     eax, dword [rbp-34H]
        movsxd  rdx, eax
        mov     rax, qword [rbp-40H]
        mov     rsi, rdx
        mov     rdi, rax
        call    address
        mov     rax, qword [rax]
        mov     qword [rbp-20H], rax
        mov     rax, qword [rbp-20H]
        mov     rdi, rax
        call    _malloc
        mov     qword [rbp-18H], rax
        mov     dword [rbp-24H], 0
        jmp     L_018

L_017:  mov     eax, dword [rbp-24H]
        movsxd  rdx, eax
        mov     rax, qword [rbp-18H]
        mov     rsi, rdx
        mov     rdi, rax
        call    address
        mov     rbx, rax
        mov     eax, dword [rbp-34H]
        lea     edx, [rax+1H]
        mov     rax, qword [rbp-40H]
        mov     rsi, rax
        mov     edi, edx
        call    _newArray
        mov     qword [rbx], rax
        add     dword [rbp-24H], 1
L_018:  mov     eax, dword [rbp-24H]
        cdqe
        cmp     rax, qword [rbp-20H]
        jl      L_017
        mov     rax, qword [rbp-18H]
L_019:  add     rsp, 56
        pop     rbx
        pop     rbp
        ret


newArray:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 16
        mov     qword [rbp-8H], rdi
        mov     rax, qword [rbp-8H]
        mov     rsi, rax
        mov     edi, 0
        call    _newArray
        leave
        ret


size:
        push    rbp
        mov     rbp, rsp
        mov     qword [rbp-8H], rdi
        mov     rax, qword [rbp-8H]
        mov     rax, qword [rax]
        pop     rbp
        ret


_strADD:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 48
        mov     qword [rbp-28H], rdi
        mov     qword [rbp-30H], rsi
        mov     rax, qword [rbp-28H]
        mov     rdi, rax
        call    length
        mov     qword [rbp-18H], rax
        mov     rax, qword [rbp-30H]
        mov     rdi, rax
        call    length
        mov     qword [rbp-10H], rax
        mov     rdx, qword [rbp-18H]
        mov     rax, qword [rbp-10H]
        add     rax, rdx
        add     rax, 1
        mov     rdi, rax
        call    malloc
        mov     qword [rbp-8H], rax
        mov     dword [rbp-1CH], 0
        jmp     L_021

L_020:  mov     eax, dword [rbp-1CH]
        movsxd  rdx, eax
        mov     rax, qword [rbp-8H]
        add     rdx, rax
        mov     eax, dword [rbp-1CH]
        movsxd  rcx, eax
        mov     rax, qword [rbp-28H]
        add     rax, rcx
        movzx   eax, byte [rax]
        mov     byte [rdx], al
        add     dword [rbp-1CH], 1
L_021:  mov     eax, dword [rbp-1CH]
        cdqe
        cmp     rax, qword [rbp-18H]
        jl      L_020
        mov     dword [rbp-1CH], 0
        jmp     L_023

L_022:  mov     eax, dword [rbp-1CH]
        movsxd  rdx, eax
        mov     rax, qword [rbp-10H]
        add     rax, rdx
        mov     rdx, rax
        mov     rax, qword [rbp-8H]
        add     rdx, rax
        mov     eax, dword [rbp-1CH]
        movsxd  rcx, eax
        mov     rax, qword [rbp-28H]
        add     rax, rcx
        movzx   eax, byte [rax]
        mov     byte [rdx], al
        add     dword [rbp-1CH], 1
L_023:  mov     eax, dword [rbp-1CH]
        cdqe
        cmp     rax, qword [rbp-10H]
        jl      L_022
        mov     rax, qword [rbp-8H]
        leave
        ret


_strLT:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 48
        mov     qword [rbp-28H], rdi
        mov     qword [rbp-30H], rsi
        mov     rax, qword [rbp-28H]
        mov     rdi, rax
        call    length
        mov     qword [rbp-10H], rax
        mov     rax, qword [rbp-30H]
        mov     rdi, rax
        call    length
        mov     qword [rbp-8H], rax
        mov     rax, qword [rbp-10H]
        cmp     rax, qword [rbp-8H]
        jge     L_024
        mov     eax, 1
        jmp     L_030

L_024:  mov     rax, qword [rbp-10H]
        cmp     rax, qword [rbp-8H]
        jle     L_025
        mov     eax, 0
        jmp     L_030

L_025:  mov     dword [rbp-14H], 0
        jmp     L_029

L_026:  mov     eax, dword [rbp-14H]
        movsxd  rdx, eax
        mov     rax, qword [rbp-28H]
        add     rax, rdx
        movzx   edx, byte [rax]
        mov     eax, dword [rbp-14H]
        movsxd  rcx, eax
        mov     rax, qword [rbp-30H]
        add     rax, rcx
        movzx   eax, byte [rax]
        cmp     dl, al
        jge     L_027
        mov     eax, 1
        jmp     L_030

L_027:  mov     eax, dword [rbp-14H]
        movsxd  rdx, eax
        mov     rax, qword [rbp-28H]
        add     rax, rdx
        movzx   edx, byte [rax]
        mov     eax, dword [rbp-14H]
        movsxd  rcx, eax
        mov     rax, qword [rbp-30H]
        add     rax, rcx
        movzx   eax, byte [rax]
        cmp     dl, al
        jle     L_028
        mov     eax, 0
        jmp     L_030

L_028:  add     dword [rbp-14H], 1
L_029:  mov     eax, dword [rbp-14H]
        cdqe
        cmp     rax, qword [rbp-10H]
        jl      L_026
        mov     eax, 0
L_030:  leave
        ret


_strGT:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 16
        mov     qword [rbp-8H], rdi
        mov     qword [rbp-10H], rsi
        mov     rdx, qword [rbp-10H]
        mov     rax, qword [rbp-8H]
        mov     rsi, rdx
        mov     rdi, rax
        call    _strLT
        test    rax, rax
        sete    al
        movzx   eax, al
        leave
        ret


_strLE:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 48
        mov     qword [rbp-28H], rdi
        mov     qword [rbp-30H], rsi
        mov     rax, qword [rbp-28H]
        mov     rdi, rax
        call    length
        mov     qword [rbp-10H], rax
        mov     rax, qword [rbp-30H]
        mov     rdi, rax
        call    length
        mov     qword [rbp-8H], rax
        mov     rax, qword [rbp-10H]
        cmp     rax, qword [rbp-8H]
        jge     L_031
        mov     eax, 1
        jmp     L_037

L_031:  mov     rax, qword [rbp-10H]
        cmp     rax, qword [rbp-8H]
        jle     L_032
        mov     eax, 0
        jmp     L_037

L_032:  mov     dword [rbp-14H], 0
        jmp     L_036

L_033:  mov     eax, dword [rbp-14H]
        movsxd  rdx, eax
        mov     rax, qword [rbp-28H]
        add     rax, rdx
        movzx   edx, byte [rax]
        mov     eax, dword [rbp-14H]
        movsxd  rcx, eax
        mov     rax, qword [rbp-30H]
        add     rax, rcx
        movzx   eax, byte [rax]
        cmp     dl, al
        jge     L_034
        mov     eax, 1
        jmp     L_037

L_034:  mov     eax, dword [rbp-14H]
        movsxd  rdx, eax
        mov     rax, qword [rbp-28H]
        add     rax, rdx
        movzx   edx, byte [rax]
        mov     eax, dword [rbp-14H]
        movsxd  rcx, eax
        mov     rax, qword [rbp-30H]
        add     rax, rcx
        movzx   eax, byte [rax]
        cmp     dl, al
        jle     L_035
        mov     eax, 0
        jmp     L_037

L_035:  add     dword [rbp-14H], 1
L_036:  mov     eax, dword [rbp-14H]
        cdqe
        cmp     rax, qword [rbp-10H]
        jl      L_033
        mov     eax, 1
L_037:  leave
        ret


_strGE:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 16
        mov     qword [rbp-8H], rdi
        mov     qword [rbp-10H], rsi
        mov     rdx, qword [rbp-10H]
        mov     rax, qword [rbp-8H]
        mov     rsi, rdx
        mov     rdi, rax
        call    _strLE
        test    rax, rax
        sete    al
        movzx   eax, al
        leave
        ret


_strEQ:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 48
        mov     qword [rbp-28H], rdi
        mov     qword [rbp-30H], rsi
        mov     rax, qword [rbp-28H]
        mov     rdi, rax
        call    length
        mov     qword [rbp-10H], rax
        mov     rax, qword [rbp-30H]
        mov     rdi, rax
        call    length
        mov     qword [rbp-8H], rax
        mov     rax, qword [rbp-10H]
        cmp     rax, qword [rbp-8H]
        jz      L_038
        mov     eax, 0
        jmp     L_042

L_038:  mov     dword [rbp-14H], 0
        jmp     L_041

L_039:  mov     eax, dword [rbp-14H]
        movsxd  rdx, eax
        mov     rax, qword [rbp-28H]
        add     rax, rdx
        movzx   edx, byte [rax]
        mov     eax, dword [rbp-14H]
        movsxd  rcx, eax
        mov     rax, qword [rbp-30H]
        add     rax, rcx
        movzx   eax, byte [rax]
        cmp     dl, al
        jz      L_040
        mov     eax, 0
        jmp     L_042

L_040:  add     dword [rbp-14H], 1
L_041:  mov     eax, dword [rbp-14H]
        cdqe
        cmp     rax, qword [rbp-10H]
        jl      L_039
        mov     eax, 1
L_042:  leave
        ret


_strNE:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 16
        mov     qword [rbp-8H], rdi
        mov     qword [rbp-10H], rsi
        mov     rdx, qword [rbp-10H]
        mov     rax, qword [rbp-8H]
        mov     rsi, rdx
        mov     rdi, rax
        call    _strEQ
        test    rax, rax
        sete    al
        movzx   eax, al
        leave
        ret



SECTION .data   


SECTION .bss    


SECTION .rodata 

L_043:
        db 25H, 73H, 00H

L_044:
        db 25H, 6CH, 6CH, 64H, 00H


