package com.example.tools.zkLock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: TODO
 * @author: wangwc
 * @date: 2020/7/31 16:46
 */
public class ZKLock implements Watcher {

    private ZooKeeper zk;
    //当前锁
    private String currentLock;
    //资源名称
    private final String lockName;
    //锁根节点
    private final String ROOT_LOCK = "/root_lock";
    //锁的各个资源根节点
    private String tmpRootLock;
    //由于zookeeper监听节点状态会立即返回, 所以需要使用CountDownLatch(也可使用信号量等其他机制)
    private CountDownLatch latch;

    public ZKLock(String zkAddress, String lockName) {
        this.lockName = "" + System.nanoTime();
        try {
            zk = new ZooKeeper(zkAddress, 30000, this);
            createZNode(ROOT_LOCK, CreateMode.PERSISTENT);
            tmpRootLock = ROOT_LOCK + "/" + lockName;
            createZNode(tmpRootLock, CreateMode.PERSISTENT);//****zk临时节点下不能创建临时顺序节点
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createZNode(String node, CreateMode mode) throws KeeperException, InterruptedException {
        //获取根节点状态
        Stat stat = zk.exists(node, false);
        //如果根节点不存在，则创建根节点，根节点类型为永久节点
        if (stat == null) {
            zk.create(node, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, mode);
        }
    }

    public void lock() {
        try {
            //在根节点下创建临时顺序节点，返回值为创建的节点路径
            currentLock = zk.create(tmpRootLock + "/" + lockName, new byte[0],
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            //获取根节点下的所有临时顺序节点，不设置监视器
            List<String> children = zk.getChildren(tmpRootLock, false);
            //对根节点下的所有临时顺序节点进行从小到大排序
            Collections.sort(children);
            //判断当前节点是否为最小节点，如果是则获取锁，若不是，则找到自己的前一个节点，监听其存在状态
            int curIndex = children.indexOf(currentLock.substring(currentLock.lastIndexOf("/") + 1));
            if (curIndex != 0) {
                //获取当前节点前一个节点的路径
                String prev = children.get(curIndex - 1);
                //监听当前节点的前一个节点的状态，null则节点不存在
                Stat stat = zk.exists(tmpRootLock + "/" + prev, true);
                //此处再次判断该节点是否存在
                if (stat != null) {
                    latch = new CountDownLatch(1);
                    //进入等待锁状态
                    latch.await();
                    latch = null;
                }
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //释放锁
    public void unlock() {
        try {
            //删除创建的节点
            zk.delete(currentLock, -1);
//            List<String> children = zk.getChildren(tmpRootLock, false);
//            if (children.size() == 0) {
//                zk.delete(tmpRootLock, -1);
//                //关闭zookeeper连接
//                zk.close();
//            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        currentLock = null;
    }

    @Override
    public void process(WatchedEvent event) {
        if (this.latch != null) {
            latch.countDown();
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 7; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ZKLock lock = new ZKLock("124.223.82.36:12181", "hhh");
                    lock.lock();
                    System.out.println("枷锁成功！！！");
                    lock.unlock();
                }
            }).start();
        }

    }
}
