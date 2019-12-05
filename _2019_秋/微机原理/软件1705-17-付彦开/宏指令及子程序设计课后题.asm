DATA    SEGMENT
        INF1    DB "Please input a number(1-361):$"
        IBUF    DB 7,0,6 DUP(?)
        OBUF    DB 6 DUP(?)
        SHOW1   DB "1+...+n$"
        SHOW2   DB "=$"
DATA    ENDS
CODE    SEGMENT
        main proc far
        ASSUME CS:CODE,DS:DATA
START:  
	MOV     AX,DATA
        MOV     DS,AX

xianshi	macro	opr
	push	dx
	push	ax
	mov	dx,offset opr
	mov	dx,09h
	int	21h
	pop	ax
	pop	dx
	exdm        

        xianshi INF1
        
        MOV     DX,OFFSET IBUF
        MOV     AH,0AH
        INT     21H
        MOV     CL,IBUF+1
        MOV     CH,0,
        MOV     SI,OFFSET IBUF+2
        MOV     AX,0
AGAIN:  MOV     DX,10
        MUL     DX
        AND     BYTE PTR [SI],0FH
        ADD     AL,[SI]
        ADC     AH,0
        INC     SI
        LOOP    AGAIN
	call	leijia

        call	zhuanhuan

        mov     dl,0ah
        mov     ah,2
        int     21h

        ;xian shi
        mov     dx,offset show1
        mov     ah,09h
        int     21h
        ;mov     dx,bx
        ;mov     ah,09h
        ;int     21h
        mov     dx,offset show2
        mov     ah,09h
        int     21h
        mov     ah,4ch
        int     21h
        ret
        main    endp
	leijia	proc
                MOV     CX,AX
                MOV     AX,0
                MOV     BX,1
        LOOP2:  ADD     AX,BX
                INC     BX
                LOOP    LOOP2
                ret
	leijia	endp

	zhuanhuan	proc
		MOV     BX,OFFSET OBUF+5
                MOV     BYTE PTR [BX],'$'
                MOV     CX,10
        LOOP1:  MOV     DX,0
                DIV     CX
                ADD     DL,30H
                DEC     BX
                MOV     [BX],DL
                OR      AX,AX
                JNZ     LOOP1
                ret
	zhuanhuan	endp
CODE    ENDS
        END     START

