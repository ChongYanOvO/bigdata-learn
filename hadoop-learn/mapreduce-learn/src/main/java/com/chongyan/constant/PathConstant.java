package com.chongyan.constant;

/**
 * 输入输出路径常量
 */
public enum PathConstant {
    BASE_PATH(System.getProperty("user.dir")+"\\data\\mapreduce"),
    WORD_COUNT_INPUT_PATH(BASE_PATH + "\\wordcount\\input\\wordcount.txt"),
    WORD_COUNT_OUTPUT_PATH(BASE_PATH + "\\wordcount\\output"),
    WRITABLE_INPUT_PATH(BASE_PATH + "\\writable\\input\\phone_data.txt"),
    WRITABLE_OUTPUT_PATH(BASE_PATH + "\\writable\\output"),
    PARTITIONER_INPUT_PATH(BASE_PATH + "\\partitioner\\input\\phone_data.txt"),
    PARTITIONER_OUTPUT_PATH(BASE_PATH + "\\partitioner\\output");
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
