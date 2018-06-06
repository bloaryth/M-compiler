





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

main:
	push rbp
	mov rbp, rsp
	sub rsp, 296
_main.entry.0:
	;	MOVU $11 5
	mov r12, 5
	mov qword [rbp - 8], r12
	;	MOV $6 $11
	mov r13, qword [rbp - 8]
	mov r12, r13
	mov qword [rbp - 16], r12
	;	MOVU $12 0
	mov r12, 0
	mov qword [rbp - 24], r12
	;	MOV $7 $12
	mov r13, qword [rbp - 24]
	mov r12, r13
	mov qword [rbp - 32], r12
	;	MOVU $14 0
	mov r12, 0
	mov qword [rbp - 40], r12
	;	NE $7 $14
	mov r14, qword [rbp - 32]
	mov r15, qword [rbp - 40]
	cmp r14, r15
	;	SET=NE $15
	mov r12, 0
	setne r12b
	mov qword [rbp - 48], r12
	;	MOVU $16 1
	mov r12, 1
	mov qword [rbp - 56], r12
	;	EQ $15 $16
	mov r14, qword [rbp - 48]
	mov r15, qword [rbp - 56]
	cmp r14, r15
	;	BR and_lhs_true.9 Init_false.8
	je and_lhs_true.9
	jne Init_false.8
Init_true.7:
	;	MOVU $13 1
	mov r12, 1
	mov qword [rbp - 64], r12
	;	JUMP Init_merge.10
	jmp Init_merge.10
Init_false.8:
	;	MOVU $13 0
	mov r12, 0
	mov qword [rbp - 64], r12
	;	JUMP Init_merge.10
	jmp Init_merge.10
and_lhs_true.9:
	;	DIV $17 $6 $7
	mov r14, qword [rbp - 16]
	mov r15, qword [rbp - 32]
	mov rdx, 0
	mov rax, r14
	idiv r15
	mov r12, rax
	mov qword [rbp - 72], r12
	;	MOVU $18 1
	mov r12, 1
	mov qword [rbp - 80], r12
	;	NE $17 $18
	mov r14, qword [rbp - 72]
	mov r15, qword [rbp - 80]
	cmp r14, r15
	;	SET=NE $19
	mov r12, 0
	setne r12b
	mov qword [rbp - 88], r12
	;	MOVU $20 1
	mov r12, 1
	mov qword [rbp - 96], r12
	;	EQ $19 $20
	mov r14, qword [rbp - 88]
	mov r15, qword [rbp - 96]
	cmp r14, r15
	;	BR Init_true.7 Init_false.8
	je Init_true.7
	jne Init_false.8
Init_merge.10:
	;	MOV $9 $13
	mov r13, qword [rbp - 64]
	mov r12, r13
	mov qword [rbp - 104], r12
	;	MOVU $21 1
	mov r12, 1
	mov qword [rbp - 112], r12
	;	EQ $21 $9
	mov r14, qword [rbp - 112]
	mov r15, qword [rbp - 104]
	cmp r14, r15
	;	BR if_true.11 if_false.12
	je if_true.11
	jne if_false.12
if_true.11:
	;	MOVU $22 10
	mov r12, 10
	mov qword [rbp - 120], r12
	;	MOV $8 $22
	mov r13, qword [rbp - 120]
	mov r12, r13
	mov qword [rbp - 128], r12
	;	JUMP if_merge.13
	jmp if_merge.13
if_false.12:
	;	MOVU $23 20
	mov r12, 20
	mov qword [rbp - 136], r12
	;	MOV $8 $23
	mov r13, qword [rbp - 136]
	mov r12, r13
	mov qword [rbp - 128], r12
	;	JUMP if_merge.13
	jmp if_merge.13
if_merge.13:
	;	MOVU $27 10
	mov r12, 10
	mov qword [rbp - 144], r12
	;	EQ $8 $27
	mov r14, qword [rbp - 128]
	mov r15, qword [rbp - 144]
	cmp r14, r15
	;	SET=EQ $28
	mov r12, 0
	sete r12b
	mov qword [rbp - 152], r12
	;	MOVU $29 1
	mov r12, 1
	mov qword [rbp - 160], r12
	;	EQ $28 $29
	mov r14, qword [rbp - 152]
	mov r15, qword [rbp - 160]
	cmp r14, r15
	;	BR and_lhs_true.23 Assign_true.20
	je and_lhs_true.23
	jne Assign_true.20
Assign_true.20:
	;	MOVU $26 0
	mov r12, 0
	mov qword [rbp - 168], r12
	;	MOVU $25 0
	mov r12, 0
	mov qword [rbp - 176], r12
	;	MOVU $38 1
	mov r12, 1
	mov qword [rbp - 184], r12
	;	XOR $24 $25 $38
	mov r14, qword [rbp - 176]
	mov r15, qword [rbp - 184]
	mov r12, r14
	xor r12, r15
	mov qword [rbp - 192], r12
	;	JUMP Ret_merge.24
	jmp Ret_merge.24
Assign_false.21:
	;	MOVU $25 1
	mov r12, 1
	mov qword [rbp - 176], r12
	;	MOVU $38 1
	mov r12, 1
	mov qword [rbp - 184], r12
	;	XOR $24 $25 $38
	mov r14, qword [rbp - 176]
	mov r15, qword [rbp - 184]
	mov r12, r14
	xor r12, r15
	mov qword [rbp - 192], r12
	;	JUMP Ret_merge.24
	jmp Ret_merge.24
and_lhs_true.23:
	;	DIV $30 $6 $7
	mov r14, qword [rbp - 16]
	mov r15, qword [rbp - 32]
	mov rdx, 0
	mov rax, r14
	idiv r15
	mov r12, rax
	mov qword [rbp - 200], r12
	;	MOVU $31 0
	mov r12, 0
	mov qword [rbp - 208], r12
	;	EQ $30 $31
	mov r14, qword [rbp - 200]
	mov r15, qword [rbp - 208]
	cmp r14, r15
	;	SET=EQ $32
	mov r12, 0
	sete r12b
	mov qword [rbp - 216], r12
	;	MOVU $33 1
	mov r12, 1
	mov qword [rbp - 224], r12
	;	EQ $32 $33
	mov r14, qword [rbp - 216]
	mov r15, qword [rbp - 224]
	cmp r14, r15
	;	BR and_lhs_true.22 Assign_true.20
	je and_lhs_true.22
	jne Assign_true.20
	;	MOVU $34 1
	mov r12, 1
	mov qword [rbp - 232], r12
	;	EQ $26 $34
	mov r14, qword [rbp - 168]
	mov r15, qword [rbp - 232]
	cmp r14, r15
	;	BR and_lhs_true.22 Assign_true.20
	je and_lhs_true.22
	jne Assign_true.20
and_lhs_true.22:
	;	MOVU $26 1
	mov r12, 1
	mov qword [rbp - 168], r12
	;	MOVU $35 5
	mov r12, 5
	mov qword [rbp - 240], r12
	;	EQ $6 $35
	mov r14, qword [rbp - 16]
	mov r15, qword [rbp - 240]
	cmp r14, r15
	;	SET=EQ $36
	mov r12, 0
	sete r12b
	mov qword [rbp - 248], r12
	;	MOVU $37 1
	mov r12, 1
	mov qword [rbp - 256], r12
	;	EQ $36 $37
	mov r14, qword [rbp - 248]
	mov r15, qword [rbp - 256]
	cmp r14, r15
	;	BR Assign_false.21 Assign_true.20
	je Assign_false.21
	jne Assign_true.20
	;	MOVU $38 1
	mov r12, 1
	mov qword [rbp - 184], r12
	;	XOR $24 $25 $38
	mov r14, qword [rbp - 176]
	mov r15, qword [rbp - 184]
	mov r12, r14
	xor r12, r15
	mov qword [rbp - 192], r12
Ret_merge.24:
	;	MOV $10 $24
	mov r13, qword [rbp - 192]
	mov r12, r13
	mov qword [rbp - 264], r12
	;	MOVU $39 1
	mov r12, 1
	mov qword [rbp - 272], r12
	;	EQ $39 $10
	mov r14, qword [rbp - 272]
	mov r15, qword [rbp - 264]
	cmp r14, r15
	;	BR if_true.25 if_merge.26
	je if_true.25
	jne if_merge.26
if_true.25:
	;	MOVU $40 30
	mov r12, 30
	mov qword [rbp - 280], r12
	;	MOV $8 $40
	mov r13, qword [rbp - 280]
	mov r12, r13
	mov qword [rbp - 128], r12
	;	JUMP if_merge.26
	jmp if_merge.26
if_merge.26:
	;	MOV $41 $8
	mov r13, qword [rbp - 128]
	mov r12, r13
	mov qword [rbp - 288], r12
	;	RET $41
	mov rax, qword [rbp - 288]
	leave
	ret
	;	MOVU $42 0
	mov r12, 0
	mov qword [rbp - 296], r12
	;	RET $42
	mov rax, qword [rbp - 296]
	leave
	ret

SECTION .data

SECTION .rodata.str1.1


