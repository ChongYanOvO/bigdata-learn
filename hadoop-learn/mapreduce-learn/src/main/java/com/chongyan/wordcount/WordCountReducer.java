package com.chongyan.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * <p>KEYIN:Reduce阶段输入的key的类型 Text</p>
 * <p>VALUEIN:Reduce阶段输入的value的类型 IntWritable</p>
 * <p>KEYOUT:Reduce阶段输出的key的类型 Text</p>
 * <p>VALUEOUT:Reduce阶段输出的value的类型 IntWritable</p>
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) {
        // 1.定义一个计数器
        int count = 0;
        // 2.遍历一组迭代器,把每一个数量1累加起来就构成了单词的总次数
        for (IntWritable value : values) {
            count += value.get();
        }
        // 3.把最终的结果输出
        try {
            context.write(key, new IntWritable(count));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
