





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
	sub rsp, 336
_main.entry.0:
	;	MOVU $11 5
	mov rbx, 5
	;	MOV $6 $11
	;	MOVU $12 0
	mov rsi, 0
	;	MOV $7 $12
	;	MOVU $14 0
	mov rdi, 0
	;	NE $7 $14
	cmp rsi, rdi
	;	SET=NE $15
	mov rdi, 0
	setne dil
	;	MOVU $16 1
	mov r8, 1
	;	EQ $15 $16
	cmp rdi, r8
	;	BR and_lhs_true.9 Init_false.8
	je and_lhs_true.9
	jne Init_false.8
Init_true.7:
	;	MOVU $13 1
	mov rdi, 1
	;	JUMP Init_merge.10
	jmp Init_merge.10
Init_false.8:
	;	MOVU $13 0
	mov rdi, 0
	;	JUMP Init_merge.10
	jmp Init_merge.10
and_lhs_true.9:
	;	DIV $17 $6 $7
	mov rax, rbx
	cqo
	idiv rsi
	mov rdi, rax
	;	MOVU $19 1
	mov r8, 1
	;	NE $17 $19
	cmp rdi, r8
	;	SET=NE $20
	mov rdi, 0
	setne dil
	;	MOVU $21 1
	mov r8, 1
	;	EQ $20 $21
	cmp rdi, r8
	;	BR Init_true.7 Init_false.8
	je Init_true.7
	jne Init_false.8
Init_merge.10:
	;	MOV $9 $13
	;	MOVU $22 1
	mov r8, 1
	;	EQ $22 $9
	cmp r8, rdi
	;	BR if_true.11 if_false.12
	je if_true.11
	jne if_false.12
if_true.11:
	;	MOVU $23 10
	mov rdi, 10
	;	MOV $8 $23
	;	JUMP if_merge.13
	jmp if_merge.13
if_false.12:
	;	MOVU $24 20
	mov rdi, 20
	;	MOV $8 $24
	;	JUMP if_merge.13
	jmp if_merge.13
if_merge.13:
	;	MOVU $28 10
	mov r8, 10
	;	EQ $8 $28
	cmp rdi, r8
	;	SET=EQ $29
	mov r8, 0
	sete r8b
	;	MOVU $30 1
	mov r9, 1
	;	EQ $29 $30
	cmp r8, r9
	;	BR and_lhs_true.23 Assign_true.20
	je and_lhs_true.23
	jne Assign_true.20
Assign_true.20:
	;	MOVU $27 0
	mov rsi, 0
	;	MOVU $26 0
	mov rbx, 0
	;	MOVU $40 1
	mov rsi, 1
	;	XOR $25 $26 $40
	xor rbx, rsi
	;	JUMP Ret_merge.24
	jmp Ret_merge.24
Assign_false.21:
	;	MOVU $26 1
	mov rbx, 1
	;	MOVU $40 1
	mov rsi, 1
	;	XOR $25 $26 $40
	xor rbx, rsi
	;	JUMP Ret_merge.24
	jmp Ret_merge.24
and_lhs_true.23:
	;	DIV $31 $6 $7
	mov r13, rsi
	mov rax, rbx
	cqo
	idiv r13
	mov rsi, rax
	;	MOVU $33 0
	mov r8, 0
	;	EQ $31 $33
	cmp rsi, r8
	;	SET=EQ $34
	mov rsi, 0
	sete sil
	;	MOVU $35 1
	mov r8, 1
	;	EQ $34 $35
	cmp rsi, r8
	;	BR and_lhs_true.22 Assign_true.20
	je and_lhs_true.22
	jne Assign_true.20
	;	MOVU $36 1
	mov r8, 1
	;	EQ $27 $36
	cmp rsi, r8
	;	BR and_lhs_true.22 Assign_true.20
	je and_lhs_true.22
	jne Assign_true.20
and_lhs_true.22:
	;	MOVU $27 1
	mov rsi, 1
	;	MOVU $37 5
	mov rsi, 5
	;	EQ $6 $37
	cmp rbx, rsi
	;	SET=EQ $38
	mov rbx, 0
	sete bl
	;	MOVU $39 1
	mov rsi, 1
	;	EQ $38 $39
	cmp rbx, rsi
	;	BR Assign_false.21 Assign_true.20
	je Assign_false.21
	jne Assign_true.20
	;	MOVU $40 1
	mov rsi, 1
	;	XOR $25 $26 $40
	xor rbx, rsi
Ret_merge.24:
	;	MOV $10 $25
	;	MOVU $44 1
	mov rsi, 1
	;	EQ $44 $10
	cmp rsi, rbx
	;	BR if_true.25 if_merge.26
	je if_true.25
	jne if_merge.26
if_true.25:
	;	MOVU $45 30
	mov rbx, 30
	;	MOV $8 $45
	mov rdi, rbx
	;	JUMP if_merge.26
	jmp if_merge.26
if_merge.26:
	;	MOV $46 $8
	mov rbx, rdi
	;	RET $46
	mov rax, rbx
	leave
	ret
	;	MOVU $47 0
	mov rbx, 0
	;	RET $47
	mov rax, rbx
	leave
	ret

SECTION .data

SECTION .rodata.str1.1


