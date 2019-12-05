DATA SEGMENT
     INFOR1 DB 0AH,0DH,"PLEASE PRESS ANY KEY TO INPUT A LETTER:$"
     INFOR2 DB 0AH,0DH,"YOU INPUT A LOWERCASE LETTER!$"
     INFOR3 DB 0AH,0DH,"YOU INPUT A UPPERCASE LETTER!$"
     INFOR4 DB 0AH,0DH,"YOU INPUT A DIGIT!$"
     INFOR5 DB 0AH,0DH,"YOU INPUT A OTHER LETTER!$"
DATA ENDS
CODE SEGMENT
     ASSUME CS:CODE,DS:DATA
START:
       MOV AX,DATA		;
       MOV DS,AX		;
       MOV DX,OFFSET INFOR1;
       MOV AH,09H  		;
       INT 21H      		;系统调用，显示字符串
       MOV AH,01H  		;
       INT 21H		;系统调用，输入一个char到AL（ax的低八位）
       CMP AL,'0'		;比较‘0’和al
       JB OTHER		;如果al小于0就跳转到other
       CMP AL,'9'		;比较al和‘9’
       JBE DIGIT		;如果al小于等于9就跳转到digit
       CMP AL,'A'		;
       JB OTHER		;
       CMP AL,'Z'		;
       JBE UPPER		;
       CMP AL,'A'		;
       JB OTHER		;
       CMP AL,'Z'		;
       JBE LOWER		;
       JMP PEND		;

LOWER: MOV DX,OFFSET INFOR2		;把infor2的信息移动到dx
       MOV AH,09H		;
       INT 21H		;系统调用显示信息
       JMP PEND		;跳到推出
UPPER: MOV DX,OFFSET INFOR3		;
       MOV AH,09H		;
       INT 21H		;
       JMP PEND		;
DIGIT: MOV DX,OFFSET INFOR4		;
       MOV AH,09H		;
       INT 21H		;
       JMP PEND		;
OTHER: MOV DX,OFFSET INFOR5		;
       MOV AH,09H		;
       INT 21H		;
       JMP PEND		;

PEND:  MOV AH,4CH	;
       INT 21H		;

CODE ENDS		;
     END START		;
