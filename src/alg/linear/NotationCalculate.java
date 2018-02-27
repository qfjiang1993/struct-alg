package alg.linear;

import java.util.Stack;

/**
 * @author QFJiang on 2017/10/12
 */
public class NotationCalculate {

    public static void main(String[] args) {

        String infix = "1-2+(3+4)*5-6";
        String postfix = "12-34+5*+6-";
        String prefix = "-+-12*+3456";

        System.out.println("Infix:         " + infix);
        System.out.println("Infix2Postfix: " + infix2postfix(infix));
        System.out.println("_2Infix:       " + _2infix(postfix));
        System.out.println("Infix2Prefix:  " + infix2prefix(infix));
        System.out.println("_2Infix:       " + _2infix(prefix));

    }

//    /**
//     * 前缀转中缀
//     *
//     * @param prefix 前缀表达式
//     * @return 中缀表达式
//     */
//    public static String prefix2infix(String prefix) {
//        StringBuilder builder = new StringBuilder();
//        Stack<CharSequence> notations = new Stack<>();
//        Operator prevOp = null;
//        Operator currOp = null;
//        for (int i = prefix.length() - 1; i >= 0; i--) {
//            char cur = prefix.charAt(i);
//            if (isDigit(cur)) {
//                notations.push(String.valueOf(cur));
//            } else {
//                currOp = charToOperator(cur);
//                assert currOp != null;
//                CharSequence left = notations.pop();
//                CharSequence right = notations.pop();
//                builder.setLength(0);
//                if (prevOp == null || prevOp.getPriority() >= currOp.getPriority()) {
//                    builder.append(left);
//                } else {
//                    builder.append('(');
//                    builder.append(left);
//                    builder.append(')');
//                }
//                builder.append(cur);
//                builder.append(right);
//                notations.push(builder.toString());
//                prevOp = currOp;
//            }
//        }
//        return builder.toString();
//    }
//
//    /**
//     * 后缀转中缀
//     *
//     * @param postfix 后缀表达式
//     * @return 中缀表达式
//     */
//    public static String postfix2infix(String postfix) {
//        StringBuilder builder = new StringBuilder();
//        Stack<CharSequence> notations = new Stack<>();
//        Operator prevOp = null;
//        Operator currOp = null;
//        for (int i = 0; i < postfix.length(); i++) {
//            char cur = postfix.charAt(i);
//            if (isDigit(cur)) {
//                notations.push(String.valueOf(cur));
//            } else {
//                currOp = charToOperator(cur);
//                assert currOp != null;
//                CharSequence right = notations.pop();
//                CharSequence left = notations.pop();
//                builder.setLength(0);
//                if (prevOp == null || prevOp.getPriority() >= currOp.getPriority()) {
//                    builder.append(left);
//                } else {
//                    builder.append('(');
//                    builder.append(left);
//                    builder.append(')');
//                }
//                builder.append(cur);
//                builder.append(right);
//                notations.push(builder.toString());
//                prevOp = currOp;
//            }
//        }
//        return builder.toString();
//    }

    /**
     * 前缀或后缀表达式转中缀表达式
     *
     * @param fix 表达式
     * @return 中缀表达式
     */
    private static String _2infix(String fix) {
        if (fix.length() <= 0) {
            return fix;
        }

        StringBuilder builder = new StringBuilder();
        Stack<CharSequence> notations = new Stack<>();
        Operator prevOp = null;
        Operator currOp = null;

        // 标志：前缀（-1，从后到前遍历）或后缀（1，从前到后遍历）
        int flag = 0;
        int index = 0;
        if (isDigit(fix.charAt(0))) {
            flag = 1;
            index = 0;
        }
        if (isOperator(fix.charAt(0))) {
            flag = -1;
            index = fix.length() - 1;
        }

        while (index >= 0 && index <= fix.length() - 1) {
            char cur = fix.charAt(index);
            if (isDigit(cur)) {
                notations.push(String.valueOf(cur));
            } else {
                currOp = charToOperator(cur);
                assert currOp != null;
                CharSequence right;
                CharSequence left;
                if (flag == 1) {
                    right = notations.pop();
                    left = notations.pop();
                } else {
                    left = notations.pop();
                    right = notations.pop();
                }
                builder.setLength(0);
                if (prevOp == null || prevOp.getPriority() >= currOp.getPriority()) {
                    builder.append(left);
                } else {
                    builder.append('(');
                    builder.append(left);
                    builder.append(')');
                }
                builder.append(cur);
                builder.append(right);
                notations.push(builder.toString());
                prevOp = currOp;
            }
            index += flag;
        }
        return builder.toString();
    }

    /**
     * 中缀转前缀
     *
     * @param infix 中缀表达式
     * @return 前缀表达式
     */
    public static String infix2prefix(String infix) {
        StringBuilder builder = new StringBuilder();
        Stack<Operator> operators = new Stack<>();
        for (int i = infix.length() - 1; i > 0; i--) {
            char cur = infix.charAt(i);
            if (isDigit(cur)) {
                builder.append(cur);
            } else if (isOperator(cur)) {
                Operator curOp = charToOperator(cur);
                assert curOp != null;
                while (!operators.empty()) {
                    Operator top = operators.peek();
                    if (top.getOp() == ')') {
                        break;
                    } else if (curOp.getPriority() >= top.getPriority()) {
                        break;
                    } else {
                        builder.append(operators.pop().getOp());
                    }
                }
                operators.push(curOp);
            } else if (cur == ')') {
                operators.push(charToOperator(cur));
            } else {
                while (!operators.empty()) {
                    Operator top = operators.pop();
                    if (top.getOp() != ')') {
                        builder.append(top.getOp());
                    } else {
                        break;
                    }
                }
            }
        }
        while (!operators.empty()) {
            Operator top = operators.pop();
            if (top.getOp() != ')')
                builder.append(top.getOp());
        }
        return builder.reverse().toString();
    }

    /**
     * 中缀转后缀
     *
     * @param infix 中缀表达式
     * @return 后缀表达式
     */
    public static String infix2postfix(String infix) {
        StringBuilder builder = new StringBuilder();
        Stack<Operator> operators = new Stack<>();
        for (int i = 0; i < infix.length(); i++) {
            char cur = infix.charAt(i);
            if (isDigit(cur)) {
                builder.append(cur);
            } else if (isOperator(cur)) {
                Operator curOp = charToOperator(cur);
                while (!operators.empty()) {
                    Operator top = operators.peek();
                    assert curOp != null;
                    if (top.getOp() == '(') {
                        break;
                    } else if (curOp.getPriority() > top.getPriority()) {
                        break;
                    } else {
                        builder.append(operators.pop().getOp());
                    }
                }
                operators.push(curOp);
            } else if (cur == '(') {
                operators.push(charToOperator(cur));
            } else {
                while (!operators.empty()) {
                    Operator top = operators.pop();
                    if (top.getOp() != '(') {
                        builder.append(top.getOp());
                    } else {
                        break;
                    }
                }
            }
        }
        while (!operators.empty()) {
            Operator top = operators.pop();
            if (top.getOp() != '(')
                builder.append(top.getOp());
        }
        return builder.toString();
    }

    /**
     * 计算前缀表达式的值
     *
     * @param prefix 前缀表达式
     * @return 表达式的值
     */
    public static int calculatePrefixNotation(String prefix) {
        Stack<Integer> stack = new Stack<>();
        for (int i = prefix.length() - 1; i >= 0; i--) {
            char cur = prefix.charAt(i);
            if (!isOperator(cur)) {
                stack.push(Integer.valueOf("" + cur));
            } else {
                int op1 = stack.pop();
                int op2 = stack.pop();
                stack.push(calculateTwoOperands(cur, op1, op2));
            }
        }
        return stack.pop();
    }

    /**
     * 计算后缀表达式的值
     *
     * @param postfix 后缀表达式
     * @return 表达式的值
     */
    public static int calculatePostfixNotation(String postfix) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < postfix.length(); i++) {
            char cur = postfix.charAt(i);
            if (!isOperator(cur)) {
                stack.push(Integer.valueOf("" + cur));
            } else {
                int op2 = stack.pop();
                int op1 = stack.pop();
                stack.push(calculateTwoOperands(cur, op1, op2));
            }
        }
        return stack.pop();
    }

    /**
     * 计算两个操作数的运算结果
     *
     * @param operator 操作符
     * @param op1      左操作数
     * @param op2      右操作数
     * @return 运算结果
     */
    private static int calculateTwoOperands(char operator, int op1, int op2) {
        int result = 0;
        if (operator == '+')
            result = op1 + op2;
        if (operator == '-')
            result = op1 - op2;
        if (operator == '*')
            result = op1 * op2;
        if (operator == '/')
            result = op1 / op2;
        if (operator == '^')
            result = (int) Math.pow(op1, op2);
        return result;
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private static Operator charToOperator(char c) {
        if (c == '+')
            return Operator.OP_PLUS;
        if (c == '-')
            return Operator.OP_MINUS;
        if (c == '*')
            return Operator.OP_MULTI;
        if (c == '/')
            return Operator.OP_DIVEDE;
        if (c == '^')
            return Operator.OP_POWER;
        if (c == '(')
            return Operator.OP_LBRACKET;
        if (c == ')')
            return Operator.OP_RBRACKET;
        return null;
    }

    public enum Operator {

        OP_PLUS('+', 1),
        OP_MINUS('-', 1),
        OP_MULTI('*', 2),
        OP_DIVEDE('/', 2),
        OP_POWER('^', 3),
        OP_LBRACKET('(', 9),
        OP_RBRACKET(')', 9);

        private char op;
        private int priority;

        Operator(char op, int priority) {
            this.op = op;
            this.priority = priority;
        }

        public char getOp() {
            return op;
        }

        public int getPriority() {
            return priority;
        }
    }
}
