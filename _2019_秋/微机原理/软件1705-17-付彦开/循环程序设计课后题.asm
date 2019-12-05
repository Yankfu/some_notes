DATAS   SEGMENT
    n   DW ?
  sum   DW ?
    m   dw 15h
OBUF    DB 6 DUP(?)
DATAS   ENDS
CODES   SEGMENT
MAIN    PROC    FAR
        ASSUME CS:CODES,DS:DATAS
START:  PUSH    DS
        MOV     AX,0
        PUSH    AX
        MOV     AX,DATAS
        MOV     DS,AX
        MOV     AX,0
        MOV     BX,0
        MOV     CX,0
LOOPT:  INC     BX
        ADD     AX,BX
        INC     CX
        CMP     AX,60000
        JBE     LOOPT
        mov     n,cx
        mov     sum,ax
        
        mov     bx,n
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

        MOV     DX,bx
        MOV     AH,09H
        INT     21H
                      
        mov     dl,0ah
        mov     ah,2
        int     21h

        mov     ax,n
        mov     cx,0
        mov     dx,0
        mov     bx,m
        MOV     BX,OFFSET OBUF+5
        MOV     BYTE PTR [BX],'$'
        MOV     CX,10
LOOP2:  MOV     DX,0
        DIV     CX
        ADD     DL,30H
        DEC     BX
        MOV     [BX],DL
        OR      AX,AX
        JNZ     LOOP2
        MOV     DX,bx
        MOV     AH,09H
        INT     21H
        RET
MAIN     ENDP
CODES   ENDS
END     START
