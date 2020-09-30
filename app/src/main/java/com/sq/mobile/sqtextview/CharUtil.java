package com.sq.mobile.sqtextview;


public class CharUtil {
    /**
     * 去掉空白字符，这里只去掉了尾部的空白字符
     *
     * @param sequence 需处理的字符
     * @return 处理过的字符
     */
    public static CharSequence trimFrom(CharSequence sequence) {
        int len = sequence.length();
        int first = 0;
        int last;

        for (last = len - 1; last > first; last--) {
            if (!matches(sequence.charAt(last))) {
                break;
            }
        }

        return sequence.subSequence(first, last + 1);
    }

    private static boolean matches(char c) {
        switch (c) {
            case '\t':
            case '\n':
            case '\013':
            case '\f':
            case '\r':
            case ' ':
            case '\u0085':
            case '\u1680':
            case '\u2028':
            case '\u2029':
            case '\u205f':
            case '\u3000':
                return true;
            case '\u2007':
                return false;
            default:
                return c >= '\u2000' && c <= '\u200a';
        }
    }
}
