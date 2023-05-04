# 上传
# (upload-file) -moveFromLocal:从本地剪切粘贴到HDFS
hadoop fs  -moveFromLocal  ./upload-file.txt  /hdfs-learn

# (2) -copyFromLocal:从本地文件系统中拷贝文件到HDFS路径
hadoop fs  -copyFromLocal  ./upload-file.txt  /hdfs-learn

# (3) -put:等同于copyFromLocal,生产环境更习惯用put
hadoop fs  -put  ./upload-file.txt  /hdfs-learn

# (4) -appendToFile:追加一个文件到已经存在的文件末尾
hadoop fs  -appendToFile  ./append-file.txt  /hdfs-learn/upload-file.txt


# 下载
# (1) -copyToLocal:从HDFS拷贝到本地
hadoop fs  -copyToLocal  /hdfs-learn/upload-file.txt  ./download-file.txt

# (2) -get:等同于copyToLocal,生产环境更习惯用get
hadoop fs  -get  /hdfs-learn/upload-file.txt  ./download-file.txt


# HDFS直接操作
# (1) -ls:显示目录信息
hadoop fs  -ls  /hdfs-learn

# (2) -cat:显示文件内容
hadoop fs  -cat  /hdfs-learn/upload-file.txt

# (3) -chgrp、-chmod、-chown:Linux文件系统中的用法一样,修改文件所属权限
hadoop fs  -chgrp  hadoop  /hdfs-learn/upload-file.txt
hadoop fs  -chmod  777  /hdfs-learn/upload-file.txt
hadoop fs  -chown  hadoop  /hdfs-learn/upload-file.txt

# (4) -mkdir:创建目录
hadoop fs  -mkdir  /hdfs-learn/mkdir

# (5) -cp:复制文件
hadoop fs  -cp  /hdfs-learn/upload-file.txt  /hdfs-learn/cp-file.txt

# (6) -mv:移动文件
hadoop fs  -mv  /hdfs-learn/upload-file.txt  /hdfs-learn/mv-file.txt

# (7) -tail:显示文件末尾内容
hadoop fs  -tail  /hdfs-learn/upload-file.txt

# (8) -rm:删除文件或文件夹
hadoop fs  -rm  /hdfs-learn/upload-file.txt
hadoop fs  -rm  -r  /hdfs-learn/mkdir

# (9) -rm -r:删除文件夹
hadoop fs  -rm  -r  /hdfs-learn/mkdir

# (10) -du:统计文件夹的大小信息
hadoop fs  -du  /hdfs-learn

# (11) -setrep:设置文件的副本数
hadoop fs  -setrep  2  /hdfs-learn/upload-file.txt
