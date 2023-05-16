package com.chongyan.mapjoin;

import com.chongyan.constant.PathConstant;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MapJoinDriver {

    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException, InterruptedException {
        Path inPutPath = new Path(PathConstant.MAP_JOIN_INPUT_PATH.getValue());
        Path outPutPath = new Path(PathConstant.MAP_JOIN_OUTPUT_PATH.getValue());

        // 1. 获取job信息
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        // 2. 设置加载jar包路径
        job.setJarByClass(MapJoinDriver.class);
        // 3. 关联mapper
        job.setMapperClass(MapJoinMapper.class);
        // 4. 设置Map输出KV类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        // 5. 设置最终输出KV类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        // 加载缓存数据
//        job.addCacheFile(new URI(PathConstant.MAP_JOIN_CACHE_PATH.getValue()));
        job.addCacheFile(new URI("file:///F:/IdeaProjects/bigdata-learn/data/mapreduce/mapjoin/cache/pd.txt"));

        // Map端Join的逻辑不需要Reduce阶段，设置reduceTask数量为0
        job.setNumReduceTasks(0);

        if (outPutPath.getFileSystem(job.getConfiguration()).exists(outPutPath)) {
            outPutPath.getFileSystem(job.getConfiguration()).delete(outPutPath, true);
        }

        // 6. 设置输入输出路径
        FileInputFormat.setInputPaths(job, inPutPath);
        FileOutputFormat.setOutputPath(job, outPutPath);

        // 7. 提交
        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}

