package com.chongyan.partitioner;

import com.chongyan.writable.FlowBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

public class FlowDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Path inPutPath = new Path(System.getProperty("user.dir") + "\\data\\mapreduce\\writable\\input\\phone_data.txt");
        Path outPutPath = new Path(System.getProperty("user.dir") + "\\data\\mapreduce\\writable\\output");


        // 1. 获取job对象
        Job job = Job.getInstance(new Configuration());

        // 2. 关联本Driver类
        job.setJarByClass(FlowDriver.class);

        // 3. 关联Mapper和Reducer类
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);

        // 4. 4.设置Mapper阶段输出数据的key和value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(com.chongyan.writable.FlowBean.class);

        // 5.设置最终数据输出的key和value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);


        // 8. 指定自定义分区器
        job.setPartitionerClass(ProvincePartitioner.class);

        // 9. 同时指定相应数量的ReduceTask
        job.setNumReduceTasks(5);


        // 6. 设置程序的输入输出路径
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        if (outPutPath.getFileSystem(job.getConfiguration()).exists(outPutPath)) {
            outPutPath.getFileSystem(job.getConfiguration()).delete(outPutPath, true);
        }

        FileInputFormat.setInputPaths(job, inPutPath);
        FileOutputFormat.setOutputPath(job, outPutPath);

        // 7. 提交Job
        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
