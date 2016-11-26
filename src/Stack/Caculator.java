package Stack;

import java.util.Stack;

/**
 * Created by TheKingRing on 2016/11/16.
 */
public class Caculator {
    public static void main(String[] args) {
        String st="1+1";
        System.out.println(calculate(st));
    }
    public static int calculate(String s) {
        s=s.replace(" ","");
        int p=0;int len=s.length();
        Stack<Integer> operand =new Stack<>();
        Stack<Character> operator=new Stack<>();
        int res=0;
        StringBuilder sb;
        while(p<len){
                char ch=s.charAt(p);
                switch (ch){
                    case '(':
                        operator.push(ch);p++;break;
                    case ')':
                        operator.pop();
                        if (!operand.isEmpty()){
                            char c=operator.pop();
                            if (c=='+')res+=operand.pop();
                            else res=operand.pop()-res;
                        }
                        p++;
                        break;
                    case '+':
                        if (s.charAt(p+1)=='('){
                            operand.push(res);operator.push(ch);p++;res=0;
                        }else {
                            sb=new StringBuilder();
                            while (p<len&&++p!=len){
                                ch=s.charAt(p);
                                if (ch!='('&&ch!='+'&&ch!='-'&&ch!=')'){
                                    sb.append(ch);
                                }else break;
                            }
                            res+=Integer.parseInt(sb.toString());
                        }

                        break;
                    case '-':
                        if (s.charAt(p+1)=='('){
                            operand.push(res);operator.push(ch);p++;res=0;
                        }else {
                            sb =new StringBuilder();
                            while (p<len){
                                ch=s.charAt(++p);
                                if (ch!='('&&ch!='+'&&ch!='-'&&ch!=')'){
                                    sb.append(ch);
                                }else break;
                            }
                            res-=Integer.parseInt(sb.toString());
                        }

                        break;
                    default:
                        sb=new StringBuilder();
                        while (p<len){
                            ch=s.charAt(p++);
                            if (ch!='('&&ch!='+'&&ch!='-'&&ch!=')'){
                                sb.append(ch);
                            }else break;
                        }
                        if (p==len&&operand.isEmpty())return res;
                        res=Integer.parseInt(sb.toString());p--;
                }

        }
        return res;
    }
}
