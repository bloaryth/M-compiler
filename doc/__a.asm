





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

extern __stack_chk_fail
extern __isoc99_scanf
extern malloc
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
        mov     edi, L_039
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
        mov     edi, 256
        call    malloc
        mov     qword [rbp-8H], rax
        mov     rax, qword [rbp-8H]
        mov     rsi, rax
        mov     edi, L_039
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
        mov     edi, L_040
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
        add     qword [rbp-8H], 1
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
        mov     rax, qword [rbp-18H]
        add     rax, rdx
        mov     rdx, rax
        mov     rax, qword [rbp-8H]
        add     rdx, rax
        mov     eax, dword [rbp-1CH]
        movsxd  rcx, eax
        mov     rax, qword [rbp-30H]
        add     rax, rcx
        movzx   eax, byte [rax]
        mov     byte [rdx], al
        add     dword [rbp-1CH], 1
L_023:  mov     eax, dword [rbp-1CH]
        cdqe
        cmp     rax, qword [rbp-10H]
        jl      L_022
        mov     rdx, qword [rbp-18H]
        mov     rax, qword [rbp-10H]
        add     rax, rdx
        mov     rdx, rax
        mov     rax, qword [rbp-8H]
        add     rax, rdx
        mov     byte [rax], 0
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
        mov     qword [rbp-18H], rax
        mov     rax, qword [rbp-30H]
        mov     rdi, rax
        call    length
        mov     qword [rbp-10H], rax
        mov     rax, qword [rbp-18H]
        cmp     qword [rbp-10H], rax
        cmovle  rax, qword [rbp-10H]
        mov     qword [rbp-8H], rax
        mov     dword [rbp-1CH], 0
        jmp     L_027

L_024:  mov     eax, dword [rbp-1CH]
        movsxd  rdx, eax
        mov     rax, qword [rbp-28H]
        add     rax, rdx
        movzx   edx, byte [rax]
        mov     eax, dword [rbp-1CH]
        movsxd  rcx, eax
        mov     rax, qword [rbp-30H]
        add     rax, rcx
        movzx   eax, byte [rax]
        cmp     dl, al
        jge     L_025
        mov     eax, 1
        jmp     L_028

L_025:  mov     eax, dword [rbp-1CH]
        movsxd  rdx, eax
        mov     rax, qword [rbp-28H]
        add     rax, rdx
        movzx   edx, byte [rax]
        mov     eax, dword [rbp-1CH]
        movsxd  rcx, eax
        mov     rax, qword [rbp-30H]
        add     rax, rcx
        movzx   eax, byte [rax]
        cmp     dl, al
        jle     L_026
        mov     eax, 0
        jmp     L_028

L_026:  add     dword [rbp-1CH], 1
L_027:  mov     eax, dword [rbp-1CH]
        cdqe
        cmp     rax, qword [rbp-8H]
        jl      L_024
        mov     rax, qword [rbp-18H]
        cmp     rax, qword [rbp-10H]
        setl    al
        movzx   eax, al
L_028:  leave
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
        mov     qword [rbp-18H], rax
        mov     rax, qword [rbp-30H]
        mov     rdi, rax
        call    length
        mov     qword [rbp-10H], rax
        mov     rax, qword [rbp-18H]
        cmp     qword [rbp-10H], rax
        cmovle  rax, qword [rbp-10H]
        mov     qword [rbp-8H], rax
        mov     dword [rbp-1CH], 0
        jmp     L_032

L_029:  mov     eax, dword [rbp-1CH]
        movsxd  rdx, eax
        mov     rax, qword [rbp-28H]
        add     rax, rdx
        movzx   edx, byte [rax]
        mov     eax, dword [rbp-1CH]
        movsxd  rcx, eax
        mov     rax, qword [rbp-30H]
        add     rax, rcx
        movzx   eax, byte [rax]
        cmp     dl, al
        jge     L_030
        mov     eax, 1
        jmp     L_033

L_030:  mov     eax, dword [rbp-1CH]
        movsxd  rdx, eax
        mov     rax, qword [rbp-28H]
        add     rax, rdx
        movzx   edx, byte [rax]
        mov     eax, dword [rbp-1CH]
        movsxd  rcx, eax
        mov     rax, qword [rbp-30H]
        add     rax, rcx
        movzx   eax, byte [rax]
        cmp     dl, al
        jle     L_031
        mov     eax, 0
        jmp     L_033

L_031:  add     dword [rbp-1CH], 1
L_032:  mov     eax, dword [rbp-1CH]
        cdqe
        cmp     rax, qword [rbp-8H]
        jl      L_029
        mov     rax, qword [rbp-18H]
        cmp     rax, qword [rbp-10H]
        setle   al
        movzx   eax, al
L_033:  leave
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
        jz      L_034
        mov     eax, 0
        jmp     L_038

L_034:  mov     dword [rbp-14H], 0
        jmp     L_037

L_035:  mov     eax, dword [rbp-14H]
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
        jz      L_036
        mov     eax, 0
        jmp     L_038

L_036:  add     dword [rbp-14H], 1
L_037:  mov     eax, dword [rbp-14H]
        cdqe
        cmp     rax, qword [rbp-10H]
        jl      L_035
        mov     eax, 1
L_038:  leave
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

L_039:
        db 25H, 73H, 00H

L_040:
        db 25H, 6CH, 6CH, 64H, 00H


global main

SECTION .text

_Npointpoint:
	push rbp
	mov rbp, rsp
	sub rsp, 56
	mov qword [rbp -8], rdi
_Npointpoint.entry.0:
	;	MOVU $28 0
	mov r12, 0
	mov qword [rbp - 16], r12
	;	LEA $29 $19 # 0
	mov r13, qword [rbp - 8]
	lea r12, [r13]
	mov qword [rbp - 24], r12
	;	MOV [$29] $28
	mov r13, qword [rbp - 16]
	mov qword [r12], r13
	mov qword [rbp - 24], r12
	;	MOVU $30 0
	mov r12, 0
	mov qword [rbp - 32], r12
	;	LEA $31 $19 # 8
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 8]
	mov qword [rbp - 40], r12
	;	MOV [$31] $30
	mov r13, qword [rbp - 32]
	mov qword [r12], r13
	mov qword [rbp - 40], r12
	;	MOVU $32 0
	mov r12, 0
	mov qword [rbp - 48], r12
	;	LEA $33 $19 # 16
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 16]
	mov qword [rbp - 56], r12
	;	MOV [$33] $32
	mov r13, qword [rbp - 48]
	mov qword [r12], r13
	mov qword [rbp - 56], r12
	;	RET void
	leave
	ret

_Npointset:
	push rbp
	mov rbp, rsp
	sub rsp, 64
	mov qword [rbp -8], rdi
	mov qword [rbp -16], rsi
	mov qword [rbp -24], rdx
	mov qword [rbp -32], rcx
_Npointset.entry.1:
	;	LEA $34 $20 # 0
	mov r13, qword [rbp - 8]
	lea r12, [r13]
	mov qword [rbp - 40], r12
	;	MOV [$34] $6
	mov r13, qword [rbp - 16]
	mov qword [r12], r13
	mov qword [rbp - 40], r12
	;	LEA $35 $20 # 8
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 8]
	mov qword [rbp - 48], r12
	;	MOV [$35] $7
	mov r13, qword [rbp - 24]
	mov qword [r12], r13
	mov qword [rbp - 48], r12
	;	LEA $36 $20 # 16
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 16]
	mov qword [rbp - 56], r12
	;	MOV [$36] $8
	mov r13, qword [rbp - 32]
	mov qword [r12], r13
	mov qword [rbp - 56], r12
	;	MOVU $37 0
	mov r12, 0
	mov qword [rbp - 64], r12
	;	RET $37
	mov rax, qword [rbp - 64]
	leave
	ret

_NpointsqrLen:
	push rbp
	mov rbp, rsp
	sub rsp, 136
	mov qword [rbp -8], rdi
_NpointsqrLen.entry.2:
	;	LEA $39 $21 # 0
	mov r13, qword [rbp - 8]
	lea r12, [r13]
	mov qword [rbp - 16], r12
	;	LEA $40 $21 # 0
	mov r13, qword [rbp - 8]
	lea r12, [r13]
	mov qword [rbp - 24], r12
	;	MUL $41 [$39] [$40]
	mov r14, qword [rbp - 16]
	mov r15, qword [rbp - 24]
	mov r13, qword [r15]
	mov r12, qword [r14]
	imul r12, r13
	mov qword [rbp - 32], r12
	;	LEA $43 $21 # 8
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 8]
	mov qword [rbp - 48], r12
	;	LEA $44 $21 # 8
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 8]
	mov qword [rbp - 56], r12
	;	MUL $45 [$43] [$44]
	mov r14, qword [rbp - 48]
	mov r15, qword [rbp - 56]
	mov r13, qword [r15]
	mov r12, qword [r14]
	imul r12, r13
	mov qword [rbp - 64], r12
	;	ADD $47 $41 $45
	mov r14, qword [rbp - 32]
	mov r15, qword [rbp - 64]
	mov r12, r14
	add r12, r15
	mov qword [rbp - 80], r12
	;	LEA $48 $21 # 16
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 16]
	mov qword [rbp - 88], r12
	;	LEA $49 $21 # 16
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 16]
	mov qword [rbp - 96], r12
	;	MUL $50 [$48] [$49]
	mov r14, qword [rbp - 88]
	mov r15, qword [rbp - 96]
	mov r13, qword [r15]
	mov r12, qword [r14]
	imul r12, r13
	mov qword [rbp - 104], r12
	;	ADD $52 $47 $50
	mov r14, qword [rbp - 80]
	mov r15, qword [rbp - 104]
	mov r12, r14
	add r12, r15
	mov qword [rbp - 120], r12
	;	MOV $38 $52
	mov r13, qword [rbp - 120]
	mov r12, r13
	mov qword [rbp - 128], r12
	;	RET $38
	mov rax, qword [rbp - 128]
	leave
	ret
	;	MOVU $53 0
	mov r12, 0
	mov qword [rbp - 136], r12
	;	RET $53
	mov rax, qword [rbp - 136]
	leave
	ret

_NpointsqrDis:
	push rbp
	mov rbp, rsp
	sub rsp, 264
	mov qword [rbp -8], rdi
	mov qword [rbp -16], rsi
_NpointsqrDis.entry.3:
	;	LEA $55 $22 # 0
	mov r13, qword [rbp - 8]
	lea r12, [r13]
	mov qword [rbp - 24], r12
	;	LEA $56 $9 # 0
	mov r13, qword [rbp - 16]
	lea r12, [r13]
	mov qword [rbp - 32], r12
	;	SUB $57 [$55] [$56]
	mov r14, qword [rbp - 24]
	mov r15, qword [rbp - 32]
	mov r13, qword [r15]
	mov r12, qword [r14]
	sub r12, r13
	mov qword [rbp - 40], r12
	;	LEA $59 $22 # 0
	mov r13, qword [rbp - 8]
	lea r12, [r13]
	mov qword [rbp - 56], r12
	;	LEA $60 $9 # 0
	mov r13, qword [rbp - 16]
	lea r12, [r13]
	mov qword [rbp - 64], r12
	;	SUB $61 [$59] [$60]
	mov r14, qword [rbp - 56]
	mov r15, qword [rbp - 64]
	mov r13, qword [r15]
	mov r12, qword [r14]
	sub r12, r13
	mov qword [rbp - 72], r12
	;	MUL $63 $57 $61
	mov r14, qword [rbp - 40]
	mov r15, qword [rbp - 72]
	mov r12, r14
	imul r12, r15
	mov qword [rbp - 88], r12
	;	LEA $64 $22 # 8
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 8]
	mov qword [rbp - 96], r12
	;	LEA $65 $9 # 8
	mov r13, qword [rbp - 16]
	lea r12, [r13 + 8]
	mov qword [rbp - 104], r12
	;	SUB $66 [$64] [$65]
	mov r14, qword [rbp - 96]
	mov r15, qword [rbp - 104]
	mov r13, qword [r15]
	mov r12, qword [r14]
	sub r12, r13
	mov qword [rbp - 112], r12
	;	LEA $68 $22 # 8
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 8]
	mov qword [rbp - 128], r12
	;	LEA $69 $9 # 8
	mov r13, qword [rbp - 16]
	lea r12, [r13 + 8]
	mov qword [rbp - 136], r12
	;	SUB $70 [$68] [$69]
	mov r14, qword [rbp - 128]
	mov r15, qword [rbp - 136]
	mov r13, qword [r15]
	mov r12, qword [r14]
	sub r12, r13
	mov qword [rbp - 144], r12
	;	MUL $72 $66 $70
	mov r14, qword [rbp - 112]
	mov r15, qword [rbp - 144]
	mov r12, r14
	imul r12, r15
	mov qword [rbp - 160], r12
	;	ADD $73 $63 $72
	mov r14, qword [rbp - 88]
	mov r15, qword [rbp - 160]
	mov r12, r14
	add r12, r15
	mov qword [rbp - 168], r12
	;	LEA $74 $22 # 16
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 16]
	mov qword [rbp - 176], r12
	;	LEA $75 $9 # 16
	mov r13, qword [rbp - 16]
	lea r12, [r13 + 16]
	mov qword [rbp - 184], r12
	;	SUB $76 [$74] [$75]
	mov r14, qword [rbp - 176]
	mov r15, qword [rbp - 184]
	mov r13, qword [r15]
	mov r12, qword [r14]
	sub r12, r13
	mov qword [rbp - 192], r12
	;	LEA $78 $22 # 16
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 16]
	mov qword [rbp - 208], r12
	;	LEA $79 $9 # 16
	mov r13, qword [rbp - 16]
	lea r12, [r13 + 16]
	mov qword [rbp - 216], r12
	;	SUB $80 [$78] [$79]
	mov r14, qword [rbp - 208]
	mov r15, qword [rbp - 216]
	mov r13, qword [r15]
	mov r12, qword [r14]
	sub r12, r13
	mov qword [rbp - 224], r12
	;	MUL $82 $76 $80
	mov r14, qword [rbp - 192]
	mov r15, qword [rbp - 224]
	mov r12, r14
	imul r12, r15
	mov qword [rbp - 240], r12
	;	ADD $83 $73 $82
	mov r14, qword [rbp - 168]
	mov r15, qword [rbp - 240]
	mov r12, r14
	add r12, r15
	mov qword [rbp - 248], r12
	;	MOV $54 $83
	mov r13, qword [rbp - 248]
	mov r12, r13
	mov qword [rbp - 256], r12
	;	RET $54
	mov rax, qword [rbp - 256]
	leave
	ret
	;	MOVU $84 0
	mov r12, 0
	mov qword [rbp - 264], r12
	;	RET $84
	mov rax, qword [rbp - 264]
	leave
	ret

_Npointdot:
	push rbp
	mov rbp, rsp
	sub rsp, 144
	mov qword [rbp -8], rdi
	mov qword [rbp -16], rsi
_Npointdot.entry.4:
	;	LEA $86 $23 # 0
	mov r13, qword [rbp - 8]
	lea r12, [r13]
	mov qword [rbp - 24], r12
	;	LEA $87 $10 # 0
	mov r13, qword [rbp - 16]
	lea r12, [r13]
	mov qword [rbp - 32], r12
	;	MUL $88 [$86] [$87]
	mov r14, qword [rbp - 24]
	mov r15, qword [rbp - 32]
	mov r13, qword [r15]
	mov r12, qword [r14]
	imul r12, r13
	mov qword [rbp - 40], r12
	;	LEA $90 $23 # 8
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 8]
	mov qword [rbp - 56], r12
	;	LEA $91 $10 # 8
	mov r13, qword [rbp - 16]
	lea r12, [r13 + 8]
	mov qword [rbp - 64], r12
	;	MUL $92 [$90] [$91]
	mov r14, qword [rbp - 56]
	mov r15, qword [rbp - 64]
	mov r13, qword [r15]
	mov r12, qword [r14]
	imul r12, r13
	mov qword [rbp - 72], r12
	;	ADD $94 $88 $92
	mov r14, qword [rbp - 40]
	mov r15, qword [rbp - 72]
	mov r12, r14
	add r12, r15
	mov qword [rbp - 88], r12
	;	LEA $95 $23 # 16
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 16]
	mov qword [rbp - 96], r12
	;	LEA $96 $10 # 16
	mov r13, qword [rbp - 16]
	lea r12, [r13 + 16]
	mov qword [rbp - 104], r12
	;	MUL $97 [$95] [$96]
	mov r14, qword [rbp - 96]
	mov r15, qword [rbp - 104]
	mov r13, qword [r15]
	mov r12, qword [r14]
	imul r12, r13
	mov qword [rbp - 112], r12
	;	ADD $99 $94 $97
	mov r14, qword [rbp - 88]
	mov r15, qword [rbp - 112]
	mov r12, r14
	add r12, r15
	mov qword [rbp - 128], r12
	;	MOV $85 $99
	mov r13, qword [rbp - 128]
	mov r12, r13
	mov qword [rbp - 136], r12
	;	RET $85
	mov rax, qword [rbp - 136]
	leave
	ret
	;	MOVU $100 0
	mov r12, 0
	mov qword [rbp - 144], r12
	;	RET $100
	mov rax, qword [rbp - 144]
	leave
	ret

_Npointcross:
	push rbp
	mov rbp, rsp
	sub rsp, 272
	mov qword [rbp -8], rdi
	mov qword [rbp -16], rsi
_Npointcross.entry.5:
	;	MOVU $102 32
	mov r12, 32
	mov qword [rbp - 24], r12
	;	CALL _malloc $101 <- $102 
	mov rdi, qword [rbp - 24]
	call _malloc
	mov qword [rbp - 32], rax
	;	CALL _Npointpoint void <- $101 
	mov rdi, qword [rbp - 32]
	call _Npointpoint
	;	MOV $14 $101
	mov r13, qword [rbp - 32]
	mov r12, r13
	mov qword [rbp - 40], r12
	;	LEA $103 $24 # 8
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 8]
	mov qword [rbp - 48], r12
	;	LEA $104 $11 # 16
	mov r13, qword [rbp - 16]
	lea r12, [r13 + 16]
	mov qword [rbp - 56], r12
	;	MUL $105 [$103] [$104]
	mov r14, qword [rbp - 48]
	mov r15, qword [rbp - 56]
	mov r13, qword [r15]
	mov r12, qword [r14]
	imul r12, r13
	mov qword [rbp - 64], r12
	;	LEA $107 $24 # 16
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 16]
	mov qword [rbp - 80], r12
	;	LEA $108 $11 # 8
	mov r13, qword [rbp - 16]
	lea r12, [r13 + 8]
	mov qword [rbp - 88], r12
	;	MUL $109 [$107] [$108]
	mov r14, qword [rbp - 80]
	mov r15, qword [rbp - 88]
	mov r13, qword [r15]
	mov r12, qword [r14]
	imul r12, r13
	mov qword [rbp - 96], r12
	;	SUB $111 $105 $109
	mov r14, qword [rbp - 64]
	mov r15, qword [rbp - 96]
	mov r12, r14
	sub r12, r15
	mov qword [rbp - 112], r12
	;	LEA $112 $24 # 16
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 16]
	mov qword [rbp - 120], r12
	;	LEA $113 $11 # 0
	mov r13, qword [rbp - 16]
	lea r12, [r13]
	mov qword [rbp - 128], r12
	;	MUL $114 [$112] [$113]
	mov r14, qword [rbp - 120]
	mov r15, qword [rbp - 128]
	mov r13, qword [r15]
	mov r12, qword [r14]
	imul r12, r13
	mov qword [rbp - 136], r12
	;	LEA $116 $24 # 0
	mov r13, qword [rbp - 8]
	lea r12, [r13]
	mov qword [rbp - 152], r12
	;	LEA $117 $11 # 16
	mov r13, qword [rbp - 16]
	lea r12, [r13 + 16]
	mov qword [rbp - 160], r12
	;	MUL $118 [$116] [$117]
	mov r14, qword [rbp - 152]
	mov r15, qword [rbp - 160]
	mov r13, qword [r15]
	mov r12, qword [r14]
	imul r12, r13
	mov qword [rbp - 168], r12
	;	SUB $120 $114 $118
	mov r14, qword [rbp - 136]
	mov r15, qword [rbp - 168]
	mov r12, r14
	sub r12, r15
	mov qword [rbp - 184], r12
	;	LEA $121 $24 # 0
	mov r13, qword [rbp - 8]
	lea r12, [r13]
	mov qword [rbp - 192], r12
	;	LEA $122 $11 # 8
	mov r13, qword [rbp - 16]
	lea r12, [r13 + 8]
	mov qword [rbp - 200], r12
	;	MUL $123 [$121] [$122]
	mov r14, qword [rbp - 192]
	mov r15, qword [rbp - 200]
	mov r13, qword [r15]
	mov r12, qword [r14]
	imul r12, r13
	mov qword [rbp - 208], r12
	;	LEA $125 $24 # 8
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 8]
	mov qword [rbp - 224], r12
	;	LEA $126 $11 # 0
	mov r13, qword [rbp - 16]
	lea r12, [r13]
	mov qword [rbp - 232], r12
	;	MUL $127 [$125] [$126]
	mov r14, qword [rbp - 224]
	mov r15, qword [rbp - 232]
	mov r13, qword [r15]
	mov r12, qword [r14]
	imul r12, r13
	mov qword [rbp - 240], r12
	;	SUB $129 $123 $127
	mov r14, qword [rbp - 208]
	mov r15, qword [rbp - 240]
	mov r12, r14
	sub r12, r15
	mov qword [rbp - 256], r12
	;	CALL _Npointset void <- $14 $111 $120 $129 
	mov rdi, qword [rbp - 40]
	mov rsi, qword [rbp - 112]
	mov rdx, qword [rbp - 184]
	mov rcx, qword [rbp - 256]
	call _Npointset
	;	MOV $130 $14
	mov r13, qword [rbp - 40]
	mov r12, r13
	mov qword [rbp - 264], r12
	;	RET $130
	mov rax, qword [rbp - 264]
	leave
	ret
	;	MOVU $131 0
	mov r12, 0
	mov qword [rbp - 272], r12
	;	RET $131
	mov rax, qword [rbp - 272]
	leave
	ret

_Npointadd:
	push rbp
	mov rbp, rsp
	sub rsp, 152
	mov qword [rbp -8], rdi
	mov qword [rbp -16], rsi
_Npointadd.entry.6:
	;	LEA $132 $25 # 0
	mov r13, qword [rbp - 8]
	lea r12, [r13]
	mov qword [rbp - 24], r12
	;	LEA $133 $12 # 0
	mov r13, qword [rbp - 16]
	lea r12, [r13]
	mov qword [rbp - 32], r12
	;	ADD $134 [$132] [$133]
	mov r14, qword [rbp - 24]
	mov r15, qword [rbp - 32]
	mov r13, qword [r15]
	mov r12, qword [r14]
	add r12, r13
	mov qword [rbp - 40], r12
	;	LEA $136 $25 # 0
	mov r13, qword [rbp - 8]
	lea r12, [r13]
	mov qword [rbp - 56], r12
	;	MOV [$136] $134
	mov r13, qword [rbp - 40]
	mov qword [r12], r13
	mov qword [rbp - 56], r12
	;	LEA $137 $25 # 8
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 8]
	mov qword [rbp - 64], r12
	;	LEA $138 $12 # 8
	mov r13, qword [rbp - 16]
	lea r12, [r13 + 8]
	mov qword [rbp - 72], r12
	;	ADD $139 [$137] [$138]
	mov r14, qword [rbp - 64]
	mov r15, qword [rbp - 72]
	mov r13, qword [r15]
	mov r12, qword [r14]
	add r12, r13
	mov qword [rbp - 80], r12
	;	LEA $141 $25 # 8
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 8]
	mov qword [rbp - 96], r12
	;	MOV [$141] $139
	mov r13, qword [rbp - 80]
	mov qword [r12], r13
	mov qword [rbp - 96], r12
	;	LEA $142 $25 # 16
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 16]
	mov qword [rbp - 104], r12
	;	LEA $143 $12 # 16
	mov r13, qword [rbp - 16]
	lea r12, [r13 + 16]
	mov qword [rbp - 112], r12
	;	ADD $144 [$142] [$143]
	mov r14, qword [rbp - 104]
	mov r15, qword [rbp - 112]
	mov r13, qword [r15]
	mov r12, qword [r14]
	add r12, r13
	mov qword [rbp - 120], r12
	;	LEA $146 $25 # 16
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 16]
	mov qword [rbp - 136], r12
	;	MOV [$146] $144
	mov r13, qword [rbp - 120]
	mov qword [r12], r13
	mov qword [rbp - 136], r12
	;	MOV $147 $25
	mov r13, qword [rbp - 8]
	mov r12, r13
	mov qword [rbp - 144], r12
	;	RET $147
	mov rax, qword [rbp - 144]
	leave
	ret
	;	MOVU $148 0
	mov r12, 0
	mov qword [rbp - 152], r12
	;	RET $148
	mov rax, qword [rbp - 152]
	leave
	ret

_Npointsub:
	push rbp
	mov rbp, rsp
	sub rsp, 152
	mov qword [rbp -8], rdi
	mov qword [rbp -16], rsi
_Npointsub.entry.7:
	;	LEA $149 $26 # 0
	mov r13, qword [rbp - 8]
	lea r12, [r13]
	mov qword [rbp - 24], r12
	;	LEA $150 $13 # 0
	mov r13, qword [rbp - 16]
	lea r12, [r13]
	mov qword [rbp - 32], r12
	;	SUB $151 [$149] [$150]
	mov r14, qword [rbp - 24]
	mov r15, qword [rbp - 32]
	mov r13, qword [r15]
	mov r12, qword [r14]
	sub r12, r13
	mov qword [rbp - 40], r12
	;	LEA $153 $26 # 0
	mov r13, qword [rbp - 8]
	lea r12, [r13]
	mov qword [rbp - 56], r12
	;	MOV [$153] $151
	mov r13, qword [rbp - 40]
	mov qword [r12], r13
	mov qword [rbp - 56], r12
	;	LEA $154 $26 # 8
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 8]
	mov qword [rbp - 64], r12
	;	LEA $155 $13 # 8
	mov r13, qword [rbp - 16]
	lea r12, [r13 + 8]
	mov qword [rbp - 72], r12
	;	SUB $156 [$154] [$155]
	mov r14, qword [rbp - 64]
	mov r15, qword [rbp - 72]
	mov r13, qword [r15]
	mov r12, qword [r14]
	sub r12, r13
	mov qword [rbp - 80], r12
	;	LEA $158 $26 # 8
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 8]
	mov qword [rbp - 96], r12
	;	MOV [$158] $156
	mov r13, qword [rbp - 80]
	mov qword [r12], r13
	mov qword [rbp - 96], r12
	;	LEA $159 $26 # 16
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 16]
	mov qword [rbp - 104], r12
	;	LEA $160 $13 # 16
	mov r13, qword [rbp - 16]
	lea r12, [r13 + 16]
	mov qword [rbp - 112], r12
	;	SUB $161 [$159] [$160]
	mov r14, qword [rbp - 104]
	mov r15, qword [rbp - 112]
	mov r13, qword [r15]
	mov r12, qword [r14]
	sub r12, r13
	mov qword [rbp - 120], r12
	;	LEA $163 $26 # 16
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 16]
	mov qword [rbp - 136], r12
	;	MOV [$163] $161
	mov r13, qword [rbp - 120]
	mov qword [r12], r13
	mov qword [rbp - 136], r12
	;	MOV $164 $26
	mov r13, qword [rbp - 8]
	mov r12, r13
	mov qword [rbp - 144], r12
	;	RET $164
	mov rax, qword [rbp - 144]
	leave
	ret
	;	MOVU $165 0
	mov r12, 0
	mov qword [rbp - 152], r12
	;	RET $165
	mov rax, qword [rbp - 152]
	leave
	ret

_NpointprintPoint:
	push rbp
	mov rbp, rsp
	sub rsp, 168
	mov qword [rbp -8], rdi
_NpointprintPoint.entry.8:
	;	MOVU $166 (
	mov r12, __Label0
	mov qword [rbp - 16], r12
	;	LEA $167 $27 # 0
	mov r13, qword [rbp - 8]
	lea r12, [r13]
	mov qword [rbp - 24], r12
	;	MOV $168 [$167]
	mov r13, qword [rbp - 24]
	mov r12, qword [r13]
	mov qword [rbp - 32], r12
	;	CALL toString $169 <- $168 
	mov rdi, qword [rbp - 32]
	call toString
	mov qword [rbp - 40], rax
	;	CALL _strADD $170 <- $166 $169 
	mov rdi, qword [rbp - 16]
	mov rsi, qword [rbp - 40]
	call _strADD
	mov qword [rbp - 48], rax
	;	MOVU $171 , 
	mov r12, __Label1
	mov qword [rbp - 56], r12
	;	CALL _strADD $172 <- $170 $171 
	mov rdi, qword [rbp - 48]
	mov rsi, qword [rbp - 56]
	call _strADD
	mov qword [rbp - 64], rax
	;	LEA $173 $27 # 8
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 8]
	mov qword [rbp - 72], r12
	;	MOV $174 [$173]
	mov r13, qword [rbp - 72]
	mov r12, qword [r13]
	mov qword [rbp - 80], r12
	;	CALL toString $175 <- $174 
	mov rdi, qword [rbp - 80]
	call toString
	mov qword [rbp - 88], rax
	;	CALL _strADD $176 <- $172 $175 
	mov rdi, qword [rbp - 64]
	mov rsi, qword [rbp - 88]
	call _strADD
	mov qword [rbp - 96], rax
	;	MOVU $177 , 
	mov r12, __Label1
	mov qword [rbp - 104], r12
	;	CALL _strADD $178 <- $176 $177 
	mov rdi, qword [rbp - 96]
	mov rsi, qword [rbp - 104]
	call _strADD
	mov qword [rbp - 112], rax
	;	LEA $179 $27 # 16
	mov r13, qword [rbp - 8]
	lea r12, [r13 + 16]
	mov qword [rbp - 120], r12
	;	MOV $180 [$179]
	mov r13, qword [rbp - 120]
	mov r12, qword [r13]
	mov qword [rbp - 128], r12
	;	CALL toString $181 <- $180 
	mov rdi, qword [rbp - 128]
	call toString
	mov qword [rbp - 136], rax
	;	CALL _strADD $182 <- $178 $181 
	mov rdi, qword [rbp - 112]
	mov rsi, qword [rbp - 136]
	call _strADD
	mov qword [rbp - 144], rax
	;	MOVU $183 )
	mov r12, __Label2
	mov qword [rbp - 152], r12
	;	CALL _strADD $184 <- $182 $183 
	mov rdi, qword [rbp - 144]
	mov rsi, qword [rbp - 152]
	call _strADD
	mov qword [rbp - 160], rax
	;	CALL println void <- $184 
	mov rdi, qword [rbp - 160]
	call println
	;	MOVU $185 0
	mov r12, 0
	mov qword [rbp - 168], r12
	;	RET $185
	mov rax, qword [rbp - 168]
	leave
	ret

main:
	push rbp
	mov rbp, rsp
	sub rsp, 472
_main.entry.9:
	;	MOVU $187 32
	mov r12, 32
	mov qword [rbp - 8], r12
	;	CALL _malloc $186 <- $187 
	mov rdi, qword [rbp - 8]
	call _malloc
	mov qword [rbp - 16], rax
	;	CALL _Npointpoint void <- $186 
	mov rdi, qword [rbp - 16]
	call _Npointpoint
	;	MOV $15 $186
	mov r13, qword [rbp - 16]
	mov r12, r13
	mov qword [rbp - 24], r12
	;	MOVU $189 32
	mov r12, 32
	mov qword [rbp - 32], r12
	;	CALL _malloc $188 <- $189 
	mov rdi, qword [rbp - 32]
	call _malloc
	mov qword [rbp - 40], rax
	;	CALL _Npointpoint void <- $188 
	mov rdi, qword [rbp - 40]
	call _Npointpoint
	;	MOV $16 $188
	mov r13, qword [rbp - 40]
	mov r12, r13
	mov qword [rbp - 48], r12
	;	MOVU $191 32
	mov r12, 32
	mov qword [rbp - 56], r12
	;	CALL _malloc $190 <- $191 
	mov rdi, qword [rbp - 56]
	call _malloc
	mov qword [rbp - 64], rax
	;	CALL _Npointpoint void <- $190 
	mov rdi, qword [rbp - 64]
	call _Npointpoint
	;	MOV $17 $190
	mov r13, qword [rbp - 64]
	mov r12, r13
	mov qword [rbp - 72], r12
	;	MOVU $193 32
	mov r12, 32
	mov qword [rbp - 80], r12
	;	CALL _malloc $192 <- $193 
	mov rdi, qword [rbp - 80]
	call _malloc
	mov qword [rbp - 88], rax
	;	CALL _Npointpoint void <- $192 
	mov rdi, qword [rbp - 88]
	call _Npointpoint
	;	MOV $18 $192
	mov r13, qword [rbp - 88]
	mov r12, r13
	mov qword [rbp - 96], r12
	;	CALL _NpointprintPoint void <- $15 
	mov rdi, qword [rbp - 24]
	call _NpointprintPoint
	;	MOVU $194 849
	mov r12, 849
	mov qword [rbp - 104], r12
	;	MOVU $195 463
	mov r12, 463
	mov qword [rbp - 112], r12
	;	NEG $196 $195
	mov r13, qword [rbp - 112]
	mov r12, r13
	neg r12
	mov qword [rbp - 120], r12
	;	MOVU $197 480
	mov r12, 480
	mov qword [rbp - 128], r12
	;	CALL _Npointset void <- $15 $194 $196 $197 
	mov rdi, qword [rbp - 24]
	mov rsi, qword [rbp - 104]
	mov rdx, qword [rbp - 120]
	mov rcx, qword [rbp - 128]
	call _Npointset
	;	MOVU $198 208
	mov r12, 208
	mov qword [rbp - 136], r12
	;	NEG $199 $198
	mov r13, qword [rbp - 136]
	mov r12, r13
	neg r12
	mov qword [rbp - 144], r12
	;	MOVU $200 585
	mov r12, 585
	mov qword [rbp - 152], r12
	;	MOVU $201 150
	mov r12, 150
	mov qword [rbp - 160], r12
	;	NEG $202 $201
	mov r13, qword [rbp - 160]
	mov r12, r13
	neg r12
	mov qword [rbp - 168], r12
	;	CALL _Npointset void <- $16 $199 $200 $202 
	mov rdi, qword [rbp - 48]
	mov rsi, qword [rbp - 144]
	mov rdx, qword [rbp - 152]
	mov rcx, qword [rbp - 168]
	call _Npointset
	;	MOVU $203 360
	mov r12, 360
	mov qword [rbp - 176], r12
	;	MOVU $204 670
	mov r12, 670
	mov qword [rbp - 184], r12
	;	NEG $205 $204
	mov r13, qword [rbp - 184]
	mov r12, r13
	neg r12
	mov qword [rbp - 192], r12
	;	MOVU $206 742
	mov r12, 742
	mov qword [rbp - 200], r12
	;	NEG $207 $206
	mov r13, qword [rbp - 200]
	mov r12, r13
	neg r12
	mov qword [rbp - 208], r12
	;	CALL _Npointset void <- $17 $203 $205 $207 
	mov rdi, qword [rbp - 72]
	mov rsi, qword [rbp - 176]
	mov rdx, qword [rbp - 192]
	mov rcx, qword [rbp - 208]
	call _Npointset
	;	MOVU $208 29
	mov r12, 29
	mov qword [rbp - 216], r12
	;	NEG $209 $208
	mov r13, qword [rbp - 216]
	mov r12, r13
	neg r12
	mov qword [rbp - 224], r12
	;	MOVU $210 591
	mov r12, 591
	mov qword [rbp - 232], r12
	;	NEG $211 $210
	mov r13, qword [rbp - 232]
	mov r12, r13
	neg r12
	mov qword [rbp - 240], r12
	;	MOVU $212 960
	mov r12, 960
	mov qword [rbp - 248], r12
	;	NEG $213 $212
	mov r13, qword [rbp - 248]
	mov r12, r13
	neg r12
	mov qword [rbp - 256], r12
	;	CALL _Npointset void <- $18 $209 $211 $213 
	mov rdi, qword [rbp - 96]
	mov rsi, qword [rbp - 224]
	mov rdx, qword [rbp - 240]
	mov rcx, qword [rbp - 256]
	call _Npointset
	;	CALL _Npointadd $214 <- $15 $16 
	mov rdi, qword [rbp - 24]
	mov rsi, qword [rbp - 48]
	call _Npointadd
	mov qword [rbp - 264], rax
	;	CALL _Npointadd $215 <- $16 $17 
	mov rdi, qword [rbp - 48]
	mov rsi, qword [rbp - 72]
	call _Npointadd
	mov qword [rbp - 272], rax
	;	CALL _Npointadd $216 <- $18 $17 
	mov rdi, qword [rbp - 96]
	mov rsi, qword [rbp - 72]
	call _Npointadd
	mov qword [rbp - 280], rax
	;	CALL _Npointsub $217 <- $17 $15 
	mov rdi, qword [rbp - 72]
	mov rsi, qword [rbp - 24]
	call _Npointsub
	mov qword [rbp - 288], rax
	;	CALL _Npointsub $218 <- $16 $18 
	mov rdi, qword [rbp - 48]
	mov rsi, qword [rbp - 96]
	call _Npointsub
	mov qword [rbp - 296], rax
	;	CALL _Npointsub $219 <- $18 $17 
	mov rdi, qword [rbp - 96]
	mov rsi, qword [rbp - 72]
	call _Npointsub
	mov qword [rbp - 304], rax
	;	CALL _Npointadd $220 <- $17 $16 
	mov rdi, qword [rbp - 72]
	mov rsi, qword [rbp - 48]
	call _Npointadd
	mov qword [rbp - 312], rax
	;	CALL _Npointadd $221 <- $15 $16 
	mov rdi, qword [rbp - 24]
	mov rsi, qword [rbp - 48]
	call _Npointadd
	mov qword [rbp - 320], rax
	;	CALL _Npointadd $222 <- $16 $16 
	mov rdi, qword [rbp - 48]
	mov rsi, qword [rbp - 48]
	call _Npointadd
	mov qword [rbp - 328], rax
	;	CALL _Npointadd $223 <- $17 $17 
	mov rdi, qword [rbp - 72]
	mov rsi, qword [rbp - 72]
	call _Npointadd
	mov qword [rbp - 336], rax
	;	CALL _Npointsub $224 <- $15 $18 
	mov rdi, qword [rbp - 24]
	mov rsi, qword [rbp - 96]
	call _Npointsub
	mov qword [rbp - 344], rax
	;	CALL _Npointadd $225 <- $15 $16 
	mov rdi, qword [rbp - 24]
	mov rsi, qword [rbp - 48]
	call _Npointadd
	mov qword [rbp - 352], rax
	;	CALL _Npointsub $226 <- $16 $17 
	mov rdi, qword [rbp - 48]
	mov rsi, qword [rbp - 72]
	call _Npointsub
	mov qword [rbp - 360], rax
	;	CALL _NpointsqrLen $227 <- $15 
	mov rdi, qword [rbp - 24]
	call _NpointsqrLen
	mov qword [rbp - 368], rax
	;	CALL toString $228 <- $227 
	mov rdi, qword [rbp - 368]
	call toString
	mov qword [rbp - 376], rax
	;	CALL println void <- $228 
	mov rdi, qword [rbp - 376]
	call println
	;	CALL _NpointsqrLen $229 <- $16 
	mov rdi, qword [rbp - 48]
	call _NpointsqrLen
	mov qword [rbp - 384], rax
	;	CALL toString $230 <- $229 
	mov rdi, qword [rbp - 384]
	call toString
	mov qword [rbp - 392], rax
	;	CALL println void <- $230 
	mov rdi, qword [rbp - 392]
	call println
	;	CALL _NpointsqrDis $231 <- $16 $17 
	mov rdi, qword [rbp - 48]
	mov rsi, qword [rbp - 72]
	call _NpointsqrDis
	mov qword [rbp - 400], rax
	;	CALL toString $232 <- $231 
	mov rdi, qword [rbp - 400]
	call toString
	mov qword [rbp - 408], rax
	;	CALL println void <- $232 
	mov rdi, qword [rbp - 408]
	call println
	;	CALL _NpointsqrDis $233 <- $18 $15 
	mov rdi, qword [rbp - 96]
	mov rsi, qword [rbp - 24]
	call _NpointsqrDis
	mov qword [rbp - 416], rax
	;	CALL toString $234 <- $233 
	mov rdi, qword [rbp - 416]
	call toString
	mov qword [rbp - 424], rax
	;	CALL println void <- $234 
	mov rdi, qword [rbp - 424]
	call println
	;	CALL _Npointdot $235 <- $17 $15 
	mov rdi, qword [rbp - 72]
	mov rsi, qword [rbp - 24]
	call _Npointdot
	mov qword [rbp - 432], rax
	;	CALL toString $236 <- $235 
	mov rdi, qword [rbp - 432]
	call toString
	mov qword [rbp - 440], rax
	;	CALL println void <- $236 
	mov rdi, qword [rbp - 440]
	call println
	;	CALL _Npointcross $237 <- $16 $18 
	mov rdi, qword [rbp - 48]
	mov rsi, qword [rbp - 96]
	call _Npointcross
	mov qword [rbp - 448], rax
	;	CALL _NpointprintPoint void <- $237 
	mov rdi, qword [rbp - 448]
	call _NpointprintPoint
	;	CALL _NpointprintPoint void <- $15 
	mov rdi, qword [rbp - 24]
	call _NpointprintPoint
	;	CALL _NpointprintPoint void <- $16 
	mov rdi, qword [rbp - 48]
	call _NpointprintPoint
	;	CALL _NpointprintPoint void <- $17 
	mov rdi, qword [rbp - 72]
	call _NpointprintPoint
	;	CALL _NpointprintPoint void <- $18 
	mov rdi, qword [rbp - 96]
	call _NpointprintPoint
	;	MOVU $239 0
	mov r12, 0
	mov qword [rbp - 456], r12
	;	MOV $238 $239
	mov r13, qword [rbp - 456]
	mov r12, r13
	mov qword [rbp - 464], r12
	;	RET $238
	mov rax, qword [rbp - 464]
	leave
	ret
	;	MOVU $240 0
	mov r12, 0
	mov qword [rbp - 472], r12
	;	RET $240
	mov rax, qword [rbp - 472]
	leave
	ret

SECTION .data

SECTION .rodata.str1.1

__Label0:
	db 40, 00

__Label1:
	db 44, 32, 00

__Label2:
	db 41, 00


