package compress;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * <p>KEYIN:Map阶段输入的key的类型 LongWritable</p>
 * <p>VALUEIN:Map阶段输入的value的类型 Text</p>
 * <p>KEYOUT:Map阶段输出的key的类型 Text</p>
 * <p>VALUEOUT:Map阶段输出的value的类型 IntWritable</p>
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 1.获取一行
        String line = value.toString();
        // 2.切割单词
        String[] words = line.split(" ");
        // 3.循环写出
        for (String word : words) {
            // 4.写出
            context.write(new Text(word), new IntWritable(1));
        }
    }

}
