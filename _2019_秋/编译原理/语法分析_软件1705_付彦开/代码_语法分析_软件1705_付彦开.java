package com.Bianyi;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class test {
	public ArrayList<String[]> in = new ArrayList<String[]>();
	public ArrayList<String[]> first = new ArrayList<String[]>();
	public ArrayList<String[]> follow = new ArrayList<String[]>();
	public ArrayList<String[]> track = new ArrayList<String[]>();

	public test() {
		Scanner sc = new Scanner(System.in);
		System.out.println("请分行输入一个完整文法:(end结束)");
		String sline = "";
		sline = sc.nextLine();
		while (!sline.startsWith("end")) {
			StringBuffer buffer = new StringBuffer(sline);
			int l = buffer.indexOf(" ");
			while (l >= 0) {// 去空格
				buffer.delete(l, l + 1);
				l = buffer.indexOf(" ");
			}
			sline = buffer.toString();
			String s[] = sline.split("->");
			if (s.length == 1)
				s = sline.split("→");
			if (s.length == 1)
				s = sline.split("=>");
			if (s.length == 1) {
				System.out.println("文法有误");
				System.exit(0);
			}
			StringTokenizer fx = new StringTokenizer(s[1], "|︱");
			while (fx.hasMoreTokens()) {
				String[] one = new String[2];
				one[0] = s[0];
				one[1] = fx.nextToken();
				in.add(one);
			}
			sline = sc.nextLine();
		}
		// 求First集过程
		this.process("First");
		/*
		 * 打印First集算法和First集
		 */
		// System.out.println("\nFirst集算法：");
		// this.print(track);// 打印First集算法
		System.out.println("\nFirst集：");
		for (int i = 0; i < first.size(); i++) {
			String[] r = first.get(i);
			System.out.print("First(" + r[0] + ")={");
			for (int j = 1; j < r.length; j++) {
				System.out.print(r[j]);
				if (j < r.length - 1)
					System.out.print(",");
			}
			System.out.println("}");
		}
		track.clear();// 因为下面还要用，这里就先清空了
		// 求Follow集过程
		this.process("Follow");
		// System.out.println("\nFollow集算法：");
		// for (int i = 0; i < track.size(); i++) {
		// String[] one = track.get(i);
		// System.out.print("Follow(" + follow.get(i)[0] + "):\t");
		// for (int j = 0; j < one.length; j++)
		// System.out.print(one[j] + "\t");
		// System.out.println();
		// }

		System.out.println("\nFollow集：");
		for (int i = 0; i < follow.size(); i++) {
			String[] r = follow.get(i);
			System.out.print("Follow(" + r[0] + ")={");
			for (int j = 1; j < r.length; j++) {
				System.out.print(r[j]);
				if (j < r.length - 1)
					System.out.print(",");
			}
			System.out.println("}");
		}
	}

	public void process(String firstORfollow) {
		for (int i = 0; i < in.size(); i++) {
			boolean bool = true;
			for (int j = 0; j < i; j++)
				if (in.get(j)[0].equals(in.get(i)[0]))
					bool = false;
			if (bool) {
				ArrayList<String> a = null;
				if (firstORfollow.equals("First"))
					a = this.getFirst(in.get(i)[0], "First(" + in.get(i)[0] + ")/");
				else if (firstORfollow.equals("Follow"))
					a = this.getFollow(in.get(i)[0], in.get(i)[0], "");
				String[] sf = new String[a.size() / 2 + 1];
				String[] st = new String[a.size() / 2];
				sf[0] = in.get(i)[0];
				for (int j = 0; j < a.size(); j++) {
					if (j % 2 == 0)
						sf[j / 2 + 1] = a.get(j);
					else
						st[j / 2] = a.get(j);
				}
				if (firstORfollow.equals("First"))
					first.add(sf);
				else if (firstORfollow.equals("Follow"))
					follow.add(sf);
				track.add(st);
			}
		}
	}

	public ArrayList<String> getFirst(String s, String track1) {
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> result1 = new ArrayList<String>();
		if (Character.isUpperCase(s.charAt(0))) {
			for (int i = 0; i < in.size(); i++) {
				String[] one = in.get(i);
				if (s.equals(one[0])) {
					if (track1.substring(0, track1.length() - 9).indexOf("First(" + s + ")") >= 0)
						;
					else if (one[1].length() == 1 || one[1].charAt(1) != '\'' && one[1].charAt(1) != '’')
						result1 = getFirst(one[1].charAt(0) + "", track1 + "First(" + one[1].charAt(0) + ")/");
					else if (one[1].length() > 1 && one[1].charAt(1) == '\'' || one[1].charAt(1) == '’')
						result1 = this.getFirst(one[1].substring(0, 2),
								track1 + "First(" + one[1].substring(0, 2) + ")/");
					result = addArrayString(result, result1);
					result1.clear();
				}
			}
		} else {
			if (s.equals("ε"))
				result1.add("#");
			else
				result1.add(s);
			result1.add(track1);
			result = result1;
		}
		return result;
	}

	public ArrayList<String> getFollow(String s, String element, String track1) {
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> result1 = new ArrayList<String>();
		if (Character.isUpperCase(s.charAt(0))) {
			for (int i = 0; i < in.size(); i++) {
				String[] one = in.get(i);
				int slen = s.length();
				int olen = one[1].length();
				if (element.equals(in.get(0)[0])) {
					result1.add("#");
					result1.add(in.get(0)[0] + "→" + in.get(0)[0] + "\t");
					result = addArrayString(result, result1);
					result1.clear();
				}
				if (one[1].indexOf(s) >= 0 && track1.indexOf((char) ('a' + i) + "") >= 0)
					;
				else if (one[1].indexOf(s) >= 0
						&& (olen - slen == one[1].indexOf(s) || slen == 2 || one[1].charAt(one[1].indexOf(s) + 1) != '’'
								&& one[1].charAt(one[1].indexOf(s) + 1) != '\'')) {
					int index = -1;
					index = one[1].indexOf(s, 0);
					while (index >= 0) {
						if (olen - slen == index) {
							result1 = getFollow(one[0], element, track1 + (char) ('a' + i));
							result = addArrayString(result, result1);
							result1.clear();
						} else {
							int t = index + slen;
							result1 = returnFirstofFollow(s, element, track1, one[0], one[1], index, t);
							result = addArrayString(result, result1);
							result1.clear();
						}
						index = one[1].indexOf(s, index + 1);
					} // endwhile
				}
				if (one[1].endsWith(element)) {
					result1.add("#");
					result1.add(in.get(0)[0] + "→" + one[1] + "\t");
					result = addArrayString(result, result1);
					result1.clear();
				}
			} // endfor
		}
		return result;
	}

	public ArrayList<String> returnFirstofFollow(String s, String element, String track1, String one0, String one1,
			int index, int t) {
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> result1 = new ArrayList<String>();
		ArrayList<String> beckFirst;
		String lsh;
		if (t + 1 < one1.length() && (one1.charAt(t + 1) == '’' || one1.charAt(t + 1) == '\''))// 如果随后的非终结符还带了一撇符
			lsh = one1.substring(t, t + 2);
		else
			lsh = one1.substring(t, t + 1);
		String[] ls = null;
		int beflen = 2;
		if (track1.length() > 0) {
			ls = in.get((int) (track1.charAt(track1.length() - 1) - 'a'));
			if (Character.isUpperCase(ls[1].charAt(ls[1].length() - 1)))
				beflen = 1;
		}
		beckFirst = this.getFirst(lsh, "First(" + lsh + ")/");
		for (int j = 0; j < beckFirst.size() / 2; j++) {
			String lh = "";
			if (beckFirst.get(j * 2).equals("#")) {
				result1.add(beckFirst.get(j * 2));
				if (ls == null)
					lh = in.get(0)[0] + "→" + one1 + "→" + one1.substring(0, index) + element + "ε"
							+ one1.substring(t + lsh.length(), one1.length());
				else
					lh = in.get(0)[0] + "→" + one1 + "→" + one1.substring(0, index) + ls[1]
							+ one1.substring(index + s.length(), one1.length()) + "→." + element + "ε"
							+ one1.substring(t + lsh.length(), one1.length());
				result1.add(lh);
				result = addArrayString(result, result1);
				result1.clear();
				if (1 + index + lsh.length() < one1.length())
					result1 = returnFirstofFollow(s, element, track1, one0, one1, index, t + lsh.length());
				else
					result1 = getFollow(one0, element, track1);
			} else {
				if (Character.isUpperCase(one1.charAt(t))) {
					if (ls == null)
						lh = in.get(0)[0] + "→" + one1 + "→" + one1.substring(0, index) + element + beckFirst.get(j * 2)
								+ one1.substring(t + lsh.length(), one1.length());
					else
						lh = in.get(0)[0] + "→" + one1 + "→" + one1.substring(0, index) + ls[1]
								+ one1.substring(index + s.length(), one1.length()) + "→." + element
								+ beckFirst.get(j * 2) + one1.substring(t + lsh.length(), one1.length());
				} else {
					if (ls == null) {
						if (element == in.get(0)[0] || s.equals(element))
							lh = in.get(0)[0] + "→" + one1.substring(0, index) + element
									+ one1.substring(t, one1.length()) + "\t";
						else
							lh = in.get(0)[0] + "→" + one1 + "→" + one1.substring(0, index) + element
									+ one1.substring(t, one1.length()) + "\t";
					} else {
						if (ls[1].length() == 1 || ls[1].length() == 2 && !ls[1].endsWith("’") && !ls[1].endsWith("\'"))
							lh = in.get(0)[0] + "→" + one1 + "→" + one1.substring(0, index) + element
									+ one1.substring(t, one1.length());
						else
							lh = in.get(0)[0] + "→" + one1 + "→" + one1.substring(0, index) + ls[1]
									+ one1.substring(index + s.length(), one1.length()) + "→." + element
									+ one1.substring(t, one1.length()) + "!";
					}
				}
				result1.add(beckFirst.get(j * 2));
				result1.add(lh);
			}
		}
		result = addArrayString(result, result1);
		result1.clear();
		return result;
	}

	public ArrayList<String> addArrayString(ArrayList<String> a, ArrayList<String> b) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < a.size(); i += 2) {
			String s = a.get(i);
			if (result.contains(s) || s.equals("")) {
				int index = result.indexOf(s);
				if (result.get(index + 1).length() > a.get(i + 1).length()) {
					result.set(index, s);
					result.set(index + 1, a.get(i + 1));
				}
				continue;
			}
			result.add(s);
			result.add(a.get(i + 1));
		}
		for (int i = 0; i < b.size(); i += 2) {
			String s = b.get(i);
			if (result.contains(s) || s.equals("")) {
				int index = result.indexOf(s);
				if (result.get(index + 1).length() > b.get(i + 1).length()) {
					result.set(index, s);
					result.set(index + 1, b.get(i + 1));
				}
				continue;
			}
			result.add(s);
			result.add(b.get(i + 1));
		}
		return result;
	}

	public void print(ArrayList<String[]> list) {
		for (int i = 0; i < list.size(); i++) {
			String[] one = list.get(i);
			String[][] strings = new String[one.length][];
			String[] finals = new String[one.length];
			int number = 0;
			int max = 0;
			for (int j = 0; j < one.length; j++) {
				strings[j] = one[j].split("/");
				if (strings[j].length > max)
					max = strings[j].length;
			}
			for (int j = 0; j < max; j++) {
				number = 0;
				for (int k = 0; k < strings.length; k++) {
					String lsh = "";
					if (j >= strings[k].length) {
						lsh = strings[k][strings[k].length - 1];
					} else {
						lsh = strings[k][j];
					}
					int m = 0;
					for (m = 0; m < number; m++) {
						if (lsh.equals(finals[m]))
							break;
					}
					if (m == number) {
						finals[number] = lsh;
						number++;
					}
				}
				for (int k = 0; k < number; k++) {
					System.out.print(finals[k]);
					if (k != number - 1)
						System.out.print(" + ");
				}
				if (j < max - 1)
					System.out.print(" = ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		new test();
	}
}