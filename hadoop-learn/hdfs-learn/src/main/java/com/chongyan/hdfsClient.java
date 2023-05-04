package com.chongyan;

import org.apache.hadoop.fs.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.hadoop.conf.Configuration;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class hdfsClient {

    private FileSystem fs;

    @Before
    public void init() throws URISyntaxException, IOException, InterruptedException {
        Configuration configuration = new Configuration();
        // 设置副本数为2
        // configuration.set("dfs.replication", "2");
        fs = FileSystem.get(new URI("hdfs://master:9000"), configuration, "root");
    }

    @After
    public void close() throws IOException {
        // 3 关闭资源
        fs.close();
    }

    /**
     * <p>创建目录</p>
     *
     * @throws IOException 异常
     */
    @Test
    public void mkdir() throws IOException {
        fs.mkdirs(new Path("/hdfs-client/upload"));
    }

    /**
     * <p>上传文件</p>
     * <p>参数优先级</p>
     * <p>1.客户端代码中设置的值 fs.setReplication()</p>
     * <p>2.classpath下的用户自定义配置文件 hdfs-site.xml</p>
     * <p>3.然后是服务器的配置 hdfs-site.xml</p>
     * <p>4.最后是服务器的默认配置 hdfs-default.xml</p>
     *
     * @throws IOException 异常
     */
    @Test
    public void upload() throws IOException {
        // 设置副本数为2
        // fs.setReplication(new Path("/hdfs-client/upload"), (short) 2);
        String filePath = System.getProperty("user.dir") + "\\data\\data.txt";
        // 参数一:是否删除源文件 参数二:是否覆盖 参数三:源文件路径 参数四:目标路径
        fs.copyFromLocalFile(false, true, new Path(filePath), new Path("/hdfs-client/upload/upload.txt"));
    }

    /**
     * <p>下载文件</p>
     *
     * @throws IOException 异常
     */
    @Test
    public void get() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\data\\download.txt";
        // 参数一:是否删除源文件 参数二:源文件路径 参数三:目标路径 参数四:是否开启本地校验
        fs.copyToLocalFile(false, new Path("/hdfs-client/upload/upload.txt"), new Path(filePath), true);
    }

    /**
     * <p>删除文件</p>
     *
     * @throws IOException 异常
     */
    @Test
    public void delete() throws IOException {
        // 参数一:目标路径 参数二:是否递归删除
        fs.delete(new Path("/hdfs-client/upload.txt"), true);

    }

    /**
     * <p>文件的重命名和移动</p>
     *
     * @throws IOException 异常
     */
    @Test
    public void mv() throws IOException {
        // 参数一:源文件路径 参数二:目标路径
        fs.rename(new Path("/hdfs-client/upload/upload.txt"), new Path("/hdfs-client/upload.txt"));

    }

    /**
     * <p>文件详情查看</p>
     *
     * @throws IOException 异常
     */
    @Test
    public void fileDetail() throws IOException {
        // 参数一:文件路径 参数二:是否递归查看
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/hdfs-client"), true);

        // 遍历文件
        while (listFiles.hasNext()) {
            LocatedFileStatus fileStatus = listFiles.next();
            System.out.println("文件名:" + fileStatus.getPath().getName());
            System.out.println("文件大小:" + fileStatus.getLen());
            System.out.println("文件权限:" + fileStatus.getPermission());
            System.out.println("文件路径:" + fileStatus.getPath());
            System.out.println("文件块大小:" + fileStatus.getBlockSize());
            System.out.println("文件副本数量:" + fileStatus.getReplication());
            System.out.println("文件块信息:" + Arrays.toString(fileStatus.getBlockLocations()));
            System.out.println("-----------------------------------");
        }

    }

    /**
     * <p>判断是文件还是文件夹</p>
     *
     * @throws IOException 异常
     */
    @Test
    public void isFile() throws IOException {
        FileStatus[] fileStatuses = fs.listStatus(new Path("/hdfs-client"));
        for (FileStatus status : fileStatuses) {
            if (status.isFile()) {
                System.out.println("文件:" + status.getPath().getName());
            } else {
                System.out.println("文件夹:" + status.getPath().getName());
            }
        }
    }
}
