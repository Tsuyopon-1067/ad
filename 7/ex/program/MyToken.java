class MyToken {
    static final int NON           = 0;
    static final int NUMBER        = 1;
    static final int OP            = 2;
    static final int OPEN_BRACKET  = 3;
    static final int CLOSE_BRACKET = 4;
    static final int ERROR         = 5;
    static final int EOS           = 6;

    int type;
    int value;

    MyToken(int tt) {
        type = tt;
    }

    MyToken(int tt, int v) {
        type = tt;
        value = v;
    }

    public String toString() {
        String str = "";
        switch(type) {
        case NON:           str = str + "NON";           break;
        case NUMBER:        str = str + "NUMBER";        break;
        case OP:            str = str + "OP";            break;
        case OPEN_BRACKET:  str = str + "OPEN_BRACKET";  break;
        case CLOSE_BRACKET: str = str + "CLOSE_BRACKET"; break;
        case ERROR:         str = str + "ERROR";         break;
        case EOS:           str = str + "EOS";           break;
        }
        if (type == OP) {
        str = str + "(" + (char)value + ")";
    } else if (type == NUMBER) {
        str = str + "(" + value + ")";
    }
        return str;
    }
}
