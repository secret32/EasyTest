package org.mytest.es.util;

import java.util.Arrays;

public enum FileSuffixType {

    /**
     * 文本文件
     */
    TXT(".txt", true),
    /**
     * Markdown文件
     */
    MD(".md", true),
    /**
     * Java文件
     */
    JAVA(".java", true)
    ;

    private String suffix;

    private boolean indexContent;

    FileSuffixType(String suffix, boolean indexContent) {
        this.suffix = suffix;
        this.indexContent = indexContent;
    }

    public static boolean needIndexContent(String filename) {
        return Arrays.stream(values()).filter(it -> filename.endsWith(it.suffix))
                .findFirst().map(it -> it.indexContent).orElse(Boolean.FALSE);
    }

}
