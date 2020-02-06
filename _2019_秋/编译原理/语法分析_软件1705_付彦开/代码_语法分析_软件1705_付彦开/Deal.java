package com.Bianyi;

/**
 * 1 头文件
 * 
 * 2    运算符
 * 
 * 3    分节符
 * 
 * 4    关键字
 * 
 * 5    标识符
 * 
 * 6    常数
 * 
 * 7 无识别
 */

import java.util.*;
import java.util.regex.Pattern;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class Deal {

	public char[] openFile(String url) throws IOException, InterruptedException {
		File selFile = new File(url);
		int length = (int) selFile.length();
		char buf[] = new char[length + 1];
		FileReader reader = new FileReader(selFile);
		String pattern = "^//.*$";
		reader.read(buf);
		reader.close();
//		String str = "";
//		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(selFile), "gbk"));
//		String line = null;
//		while ((line = reader.readLine()) != null) {
//			if(!Pattern.matches(pattern,line))
//			{
//				str=str+line;
//			}
//		}
//		buf=str.toCharArray();
		return buf;
	}

	private String keyWord[] = { "auto", "double", "int", "struct", "break", "else", "long", "switch", "case", "enum",
			"register", "typedef", "char", "extern", "return", "union", "const", "float", "short", "unsigned",
			"continue", "for", "signed", "void", "default", "goto", "sizeof", "volatile", "do", "if ", "while",
			"static" };
	private char ch;

	// 关键字
	boolean isKey(String str) {
		for (int i = 0; i < keyWord.length; i++) {
			if (keyWord[i].equals(str)) {
				return true;
			}
		}
		return false;
	}

	// 字母
	boolean isLetter(char letter) {
		if ((letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z')) {
			return true;
		} else {
			return false;
		}
	}

	// 头文件
	boolean isHead(String str) {
		String pattern = "^.*.h$";
		boolean isHead = Pattern.matches(pattern, str);
		return isHead;
	}

	// 数字
	boolean isDigit(char digit) {
		if (digit >= '0' && digit <= '9') {
			return true;
		} else {
			return false;
		}
	}

	void cifafenxi(char[] chars) {
		String arr = "";
		for (int i = 0; i < chars.length; i++) {
			ch = chars[i];
			arr = "";
			if (ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r') {

			} else if (isLetter(ch)) {
				while (isLetter(ch) || isDigit(ch) || ch == '.') {
					arr += ch;
					ch = chars[++i];
				}
				// 回退一个字符
				i--;
				if (isKey(arr)) {
					// 关键字
					Show.jta2Add(arr + "\t4" + "\t关键字" + "\n");
				} else if (isHead(arr)) {
					Show.jta2Add(arr + "\t1" + "\t头文件" + "\n");
				} else {
					// 标识符
					Show.jta2Add(arr + "\t5" + "\t标识符" + "\n");
				}
			} else if (isDigit(ch) || (ch == '.')) {
				while (isDigit(ch) || (ch == '.' && isDigit(chars[++i]))) {
					if (ch == '.')
						i--;
					arr = arr + ch;
					ch = chars[++i];
				}
				// 属于无符号常数
				Show.jta2Add(arr + "\t6" + "\t常数" + "\n");
			} else
				switch (ch) {
				// 运算符
				case '+': {
					ch = chars[++i];
					if (ch == '+') {
						Show.jta2Add("++" + "\t2" + "\t运算符" + "\n");
					} else {
						Show.jta2Add(ch + "\t2" + "\t运算符" + "\n");
						i--;
					}
				}
					break;
				case '-': {
					ch = chars[++i];
					if (ch == '>') {
						Show.jta2Add("->" + "\t2" + "\t运算符" + "\n");
					} else if (ch == '-') {
						Show.jta2Add("--" + "\t2" + "\t运算符" + "\n");
					} else {
						Show.jta2Add("-" + "\t2" + "\t运算符" + "\n");
						i--;
					}
				}
					break;
				case '*': {
					Show.jta2Add(ch + "\t2" + "\t运算符" + "\n");
					break;
				}
				case '/': {
					Show.jta2Add(ch + "\t2" + "\t运算符" + "\n");
					break;
				}
				case '%': {
					Show.jta2Add(ch + "\t2" + "\t运算符" + "\n");
					break;
				}
				// 分界符
				case '(': {
					Show.jta2Add(ch + "\t3" + "\t分界符" + "\n");
					break;
				}
				case ')': {
					Show.jta2Add(ch + "\t3" + "\t分界符" + "\n");
					break;
				}
				case '[': {
					Show.jta2Add(ch + "\t3" + "\t分界符" + "\n");
					break;
				}
				case ']': {
					Show.jta2Add(ch + "\t3" + "\t分界符" + "\n");
					break;
				}
				case ';': {
					Show.jta2Add(ch + "\t3" + "\t分界符" + "\n");
					break;
				}
				case '{': {
					Show.jta2Add(ch + "\t3" + "\t分界符" + "\n");
					break;
				}
				case '}': {
					Show.jta2Add(ch + "\t3" + "\t分界符" + "\n");
					break;
				}
				// 运算符
				case '=': {
					ch = chars[++i];
					if (ch == '=') {
						Show.jta2Add("==" + "\t2" + "\t运算符" + "\n");
					} else {
						Show.jta2Add("=" + "\t2" + "\t运算符" + "\n");
						i--;
					}
				}
					break;
				case '>': {
					ch = chars[++i];
					if (ch == '=') {
						Show.jta2Add(">=" + "\t2" + "\t运算符" + "\n");
					} else if (ch == '>') {
						Show.jta2Add(">>" + "\t2" + "\t运算符" + "\n");
					} else {
						Show.jta2Add(">" + "\t2" + "\t运算符" + "\n");

						i--;
					}
				}
					break;
				case '<': {
					ch = chars[++i];
					if (ch == '=') {
						Show.jta2Add("<=" + "\t2" + "\t运算符" + "\n");

					} else if (ch == '<') {
						Show.jta2Add("<<" + "\t2" + "\t运算符" + "\n");
					} else {
						Show.jta2Add("<" + "\t2" + "\t运算符" + "\n");
						i--;
					}
				}
					break;
				case '&': {
					ch = chars[++i];
					if (ch == '&') {
						Show.jta2Add("&&" + "\t2" + "\t运算符" + "\n");

					} else {
						Show.jta2Add("&" + "\t2" + "\t运算符" + "\n");
						i--;
					}
				}
					break;
				case '!': {
					ch = chars[++i];
					if (ch == '=') {
						Show.jta2Add("!=" + "\t2" + "\t运算符" + "\n");

					} else {
						Show.jta2Add("=" + "\t2" + "\t运算符" + "\n");
						i--;
					}
				}
					break;
				case '|': {
					ch = chars[++i];
					if (ch == '|') {
						Show.jta2Add("||" + "\t2" + "\t运算符" + "\n");

					} else {
						Show.jta2Add("|" + "\t2" + "\t运算符" + "\n");
						i--;
					}
				}
					break;
				case '^': {
					Show.jta2Add(ch + "\t2" + "\t运算符" + "\n");
				}
					break;
				// 无识别
				default: {
					Show.jta2Add(ch + "\t7" + "\t无识别符" + "\n");
				}
				}
		}
	}

}
