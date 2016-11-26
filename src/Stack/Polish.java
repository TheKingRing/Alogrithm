package Stack;

import java.util.Stack;

/**
 * Created by TheKingRing on 2016/11/17.
 */
public class Polish {
    public static void main(String[] args) {
        String[] str={"25","13","5","/","+"};
        System.out.println(evalRPN(str));
    }
    public static int evalRPN(String[] tokens) {
        Stack<Integer> oprand=new Stack<>();
        for(int i=0;i<tokens.length;i++){
            int len=tokens[i].length();
            char ch=tokens[i].charAt(len-1);
            if(ch!='/'&&ch!='+'&&ch!='-'&&ch!='*'){
                oprand.push(Integer.parseInt(tokens[i]));
            }else {
                int a=oprand.pop();
                if (oprand.isEmpty())return solve(a,Integer.parseInt(tokens[i+1]),ch);
                int b=oprand.pop();
                oprand.push(solve(a,b,ch));
            }

        }
        return oprand.pop();
    }

    private static int solve(int a, int b, Character pop) {
        switch (pop){
            case '/':return b/a;
            case '*':return a*b;
            case '-':return b-a;
            case '+':return a+b;
        }
        return 0;
    }


}
