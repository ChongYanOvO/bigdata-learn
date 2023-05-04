package com.chongyan.partitioner;

import com.chongyan.writable.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowReducer extends Reducer<Text, com.chongyan.writable.FlowBean, Text, com.chongyan.writable.FlowBean> {
    private com.chongyan.writable.FlowBean outV = new com.chongyan.writable.FlowBean();
    @Override
    protected void reduce(Text key, Iterable<com.chongyan.writable.FlowBean> values, Context context) throws IOException, InterruptedException {

        long totalUp = 0;
        long totalDown = 0;

        //1 遍历values,将其中的上行流量,下行流量分别累加
        for (FlowBean flowBean : values) {
            totalUp += flowBean.getUpFlow();
            totalDown += flowBean.getDownFlow();
        }

        //2 封装outKV
        outV.setUpFlow(totalUp);
        outV.setDownFlow(totalDown);
        outV.setSumFlow();

        //3 写出outK outV
        context.write(key,outV);
    }
}
