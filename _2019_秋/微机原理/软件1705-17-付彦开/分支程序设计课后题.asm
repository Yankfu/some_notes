DATA SEGMENT
     INFOR1 DB 0AH,0DH,"ARE YOU REALLY WANT TO EXIT?$"
     INFOR2 DB 0AH,0DH,"THANK YOU FOR YOUR USING!$"
     INFOR3 DB 0AH,0DH,"LET'S CONTINUE!$"
     INFOR4 DB 0AH,0DH,"YOU PRESS AN ERROR KEY!$"
DATA ENDS

CODE SEGMENT
     ASSUME CS:CODE,DS:DATA
START:
     MOV AX,DATA
     MOV DS,AX

     MOV DX,OFFSET INFOR1
     MOV AH,09H
     INT 21H
	
     MOV AH,01H
     INT 21H
     CMP AL,'Y'
     JZ EXIT
     CMP AL,'Y'
     JZ EXIT
     CMP AL,'N'
     JZ CONTINUE
     CMP AL,'N'
     JZ CONTINUE
     MOV DX,OFFSET INFOR4
     MOV AH,09H
     INT 21H
     JMP PEND
EXIT:  
     MOV DX,OFFSET INFOR2
     MOV AH,09H
     INT 21H
     JMP PEND
CONTINUE:   
     MOV DX,OFFSET INFOR3
     MOV AH,09H
     INT 21H
     JMP PEND
PEND:   
     MOV AH,4CH
     INT 21H

CODE ENDS
     END START