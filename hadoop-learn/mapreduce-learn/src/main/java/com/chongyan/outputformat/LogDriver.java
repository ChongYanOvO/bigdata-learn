package com.chongyan.outputformat;

import com.chongyan.constant.PathConstant;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class LogDriver {


    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Path inPutPath = new Path(PathConstant.OUTPUT_FORMAT_INPUT_PATH.getValue());
        Path outPutPath = new Path(PathConstant.OUTPUT_FORMAT_OUTPUT_PATH.getValue());

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(LogDriver.class);
        job.setMapperClass(LogMapper.class);
        job.setReducerClass(LogReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        //设置自定义的outputformat
        job.setOutputFormatClass(LogOutputFormat.class);

        if (outPutPath.getFileSystem(job.getConfiguration()).exists(outPutPath)) {
            outPutPath.getFileSystem(job.getConfiguration()).delete(outPutPath, true);
        }

        FileInputFormat.setInputPaths(job, inPutPath);
        //虽然自定义了 outputformat ,但是因为 outputformat 继承自 fileoutputformat
        //而 fileoutputformat 要输出一个 _SUCCESS 文件,所以在这还得指定一个输出目录
        FileOutputFormat.setOutputPath(job, outPutPath);


        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
