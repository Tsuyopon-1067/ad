int hash(char key[], int len) {
    int r = 0;
    for (int i = 0; i < len; i++) {
        int buf =  key[i];
        r = r + buf * buf;
    }
    return r % len;
}