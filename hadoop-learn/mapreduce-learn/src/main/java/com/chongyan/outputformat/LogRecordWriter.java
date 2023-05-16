package com.chongyan.outputformat;

import com.chongyan.constant.PathConstant;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

public class LogRecordWriter extends RecordWriter<Text, NullWritable> {

    private FSDataOutputStream bilibiliOut;
    private FSDataOutputStream otherOut;

    public LogRecordWriter(TaskAttemptContext job) {
        try {
            //获取文件系统对象
            FileSystem fs = FileSystem.get(job.getConfiguration());
            //用文件系统对象创建两个输出流对应不同的目录
            bilibiliOut = fs.create(new Path(PathConstant.OUTPUT_FORMAT_OUTPUT_PATH.getValue() + "\\bilibili.log"));
            otherOut = fs.create(new Path(PathConstant.OUTPUT_FORMAT_OUTPUT_PATH.getValue() + "\\other.log"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Text key, NullWritable value) throws IOException, InterruptedException {
        String log = key.toString();
        //根据一行的log数据是否包含atguigu,判断两条输出流输出的内容
        if (log.contains("bilibili")) {
            bilibiliOut.writeBytes(log + "\n");
        } else {
            otherOut.writeBytes(log + "\n");
        }
    }

    @Override
    public void close(TaskAttemptContext context) {
        //关流
        IOUtils.closeStream(bilibiliOut);
        IOUtils.closeStream(otherOut);
    }
}

