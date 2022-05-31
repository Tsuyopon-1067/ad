class MyTokenizer {
    String str;
    int index;
    MyTokenizer(String s) {
        str = new String(s);
        clearIndex();
    }

    public void clearIndex() {
        index = 0;
    }

    public boolean hasNextToken() {
        return str.length() > index ? true : false;
    }

    public MyToken nextToken() {
        MyToken newToken;
        while (hasNextToken() && index < str.length() &&
            ' ' == str.charAt(index)) {
            index++;
        }
        if (!hasNextToken()) {
            newToken = new MyToken(MyToken.EOS);
            return newToken;
        }
        if ('0' <= str.charAt(index) && str.charAt(index) <= '9') {
            newToken = new MyToken(MyToken.NUMBER);
            while(hasNextToken() && '0' <= str.charAt(index) &&
            str.charAt(index) <= '9') {
                newToken.value = 10 * newToken.value + (int)(str.charAt(index) - '0');
                index++;
            }
            return newToken;
        }
        char ch = str.charAt(index);
        switch(ch) {
        case '+':
        case '-':
        case '*':
        case '/': newToken = new MyToken(MyToken.OP, (int)ch);   break;
        case '(': newToken = new MyToken(MyToken.OPEN_BRACKET);  break;
        case ')': newToken = new MyToken(MyToken.CLOSE_BRACKET); break;
        default:  newToken = new MyToken(MyToken.ERROR);
        }
        index++;
        return newToken;
    }

    public MyTreeNode parse() {
        MyToken token;
        if (!hasNextToken()) {
            return null;
        }
        token = nextToken();
        if (token.type == MyToken.NUMBER) {
            MyTreeNode node = new MyTreeNode();
            node.token = token;
            node.left  = null;
            node.right = null;
            return node;
        } else if (token.type == MyToken.OPEN_BRACKET) {
            token = nextToken();
            MyTreeNode node = new MyTreeNode();
            if (token.type == MyToken.OP) {
                node.token = token;
                node.left  = parse();
                node.right = parse();
                token = nextToken();
                if (token.type == MyToken.CLOSE_BRACKET) {
                    return node;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
