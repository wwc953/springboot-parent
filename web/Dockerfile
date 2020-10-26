FROM lwieske/java-8:latest
EXPOSE 9090
#配置时区
ENV TZ="Asia/ShangHai"
#添加jar
ADD target/xxx.jar /home/admin/app
#其他配置
ENV JAVA_OPTS="-Xmx8g -Xms8g \
-Xss 2048k \
-Xmn 2048m \
-XX:NewRatio=2 \
-XX:+UseConcMarkSweepGC \
-XX:+CMSParallelRemarkEnabled \
-XX:+UseFastAccessorMethods \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=/home/admin/app/heapdump.hprof \
-XX:+UseParNewGC
"