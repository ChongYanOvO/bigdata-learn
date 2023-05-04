package com.chongyan.wordcount;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class WordCountDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Path inPutPath = new Path(System.getProperty("user.dir") + "\\data\\mapreduce\\wordcount\\input\\wordcount.txt");
        Path outPutPath = new Path(System.getProperty("user.dir") + "\\data\\mapreduce\\wordcount\\output");



        // 1.获取job对象
        Job job = Job.getInstance(new Configuration());

        // 2.关联本Driver类
        job.setJarByClass(WordCountDriver.class);

        // 3.关联Map和Reduce类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        // 4.设置Mapper阶段输出数据的key和value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 5.设置最终数据输出的key和value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 6.设置输入路径和输出路径
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        if (outPutPath.getFileSystem(job.getConfiguration()).exists(outPutPath)) {
            outPutPath.getFileSystem(job.getConfiguration()).delete(outPutPath, true);
        }

        FileInputFormat.setInputPaths(job, inPutPath);
        FileOutputFormat.setOutputPath(job, outPutPath);

        // 7.提交job
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);

    }
}