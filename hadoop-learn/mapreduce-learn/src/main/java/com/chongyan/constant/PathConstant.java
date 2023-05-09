package com.chongyan.constant;

/**
 * 输入输出路径常量
 */
public enum PathConstant {
    BASE_PATH(System.getProperty("user.dir") + "\\data\\mapreduce"),
    WORD_COUNT_INPUT_PATH(BASE_PATH.value + "\\wordcount\\input\\*"),
    WORD_COUNT_OUTPUT_PATH(BASE_PATH.value + "\\wordcount\\output"),
    WRITABLE_INPUT_PATH(BASE_PATH.value + "\\writable\\input\\*"),
    WRITABLE_OUTPUT_PATH(BASE_PATH.value + "\\writable\\output"),
    PARTITIONER_INPUT_PATH(BASE_PATH.value + "\\partitioner\\input\\*"),
    PARTITIONER_OUTPUT_PATH(BASE_PATH.value + "\\partitioner\\output"),
    COMPARE_INPUT_PATH(BASE_PATH.value + "\\compare\\input\\*"),
    COMPARE_OUTPUT_PATH(BASE_PATH.value + "\\compare\\output");
    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    PathConstant(String value) {
        this.value = value;
    }


}
