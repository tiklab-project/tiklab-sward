package io.tiklab.sward.confluence.service;
import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class InvalidXMLCharFilter extends FilterReader {
    public InvalidXMLCharFilter(Reader in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int ch = super.read();
        // 跳过非法的 XML 字符
        if (ch == 0xc) {
            return read(); // 忽略并继续读取
        }
        return ch;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int numRead = super.read(cbuf, off, len);
        if (numRead == -1) return -1;

        // Remove invalid characters from buffer
        int validChars = off;
        for (int i = off; i < off + numRead; i++) {
            if (cbuf[i] != 0xC) {
                cbuf[validChars++] = cbuf[i];
            }
        }
        return validChars - off;
    }
}
