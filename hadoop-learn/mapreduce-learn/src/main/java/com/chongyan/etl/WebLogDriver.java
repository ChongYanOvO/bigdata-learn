package com.chongyan.etl;

import com.chongyan.constant.PathConstant;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WebLogDriver {
    public static void main(String[] args) throws Exception {

        Path inPutPath = new Path(PathConstant.ETL_INPUT_PATH.getValue());
        Path outPutPath = new Path(PathConstant.ETL_OUTPUT_PATH.getValue());

        // 1. 获取job信息
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        // 2. 加载jar包
        job.setJarByClass(WebLogDriver.class);

        // 3. 关联map
        job.setMapperClass(WebLogMapper.class);

        // 4. 设置最终输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        // 设置reducetask个数为0
        job.setNumReduceTasks(0);

        if (outPutPath.getFileSystem(job.getConfiguration()).exists(outPutPath)) {
            outPutPath.getFileSystem(job.getConfiguration()).delete(outPutPath, true);
        }

        // 5. 设置输入和输出路径
        FileInputFormat.setInputPaths(job, inPutPath);
        FileOutputFormat.setOutputPath(job, outPutPath);

        // 6 提交
        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}

