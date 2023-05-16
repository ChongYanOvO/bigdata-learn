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
    COMPARE_OUTPUT_PATH(BASE_PATH.value + "\\compare\\output"),
    COMBINER_INPUT_PATH(BASE_PATH.value + "\\combiner\\input\\*"),
    COMBINER_OUTPUT_PATH(BASE_PATH.value + "\\combiner\\output"),
    OUTPUT_FORMAT_INPUT_PATH(BASE_PATH.value + "\\outputformat\\input\\*"),
    OUTPUT_FORMAT_OUTPUT_PATH(BASE_PATH.value + "\\outputformat\\output"),
    REDUCE_JOIN_INPUT_PATH(BASE_PATH.value + "\\reducejoin\\input\\*"),
    REDUCE_JOIN_OUTPUT_PATH(BASE_PATH.value + "\\reducejoin\\output"),
    MAP_JOIN_INPUT_PATH(BASE_PATH.value + "\\mapjoin\\input\\*"),
    MAP_JOIN_OUTPUT_PATH(BASE_PATH.value + "\\mapjoin\\output"),
    MAP_JOIN_CACHE_PATH("file:///F:/IdeaProjects/bigdata-learn/data/mapreduce/mapjoin/cache/pd.txt"),
    ETL_INPUT_PATH(BASE_PATH.value + "\\etl\\input\\*"),
    ETL_OUTPUT_PATH(BASE_PATH.value + "\\etl\\output");

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
