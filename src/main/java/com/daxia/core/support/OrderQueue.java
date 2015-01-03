package com.daxia.core.support;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 新的订单队列，新下的单放到这里来，页面来读取，显示弹窗
 */
@Component
public class OrderQueue {
	private static Logger logger = Logger.getLogger(OrderQueue.class);
	
	private int maxSize = 500;
	private Map<Long, ArrayBlockingQueue<String>> map = new HashMap<Long, ArrayBlockingQueue<String>>();
	public void put(Long communityId, String msg) {
	        ArrayBlockingQueue<String> queue = getQueue(communityId);
			try {
				if (queue.size() >= maxSize) {
					queue.clear();
				}
				queue.add(msg);
            } catch (Exception e) {
            	logger.error(e, e);
            }
	}
	
	private ArrayBlockingQueue<String> getQueue(Long communityId) {
	    ArrayBlockingQueue<String> queue = map.get(communityId);
	    if (queue == null) {
	        queue = new ArrayBlockingQueue<String>(maxSize);
	        map.put(communityId, queue);
	    }
        return queue;
    }

    public int size(Long communityId) {
        ArrayBlockingQueue<String> queue = getQueue(communityId);
		return queue.size();
	}
	
	public void clear(Long communityId) {
	    ArrayBlockingQueue<String> queue = getQueue(communityId);
		queue.clear();
	}
	
	public String poll(Long communityId) {
	    ArrayBlockingQueue<String> queue = getQueue(communityId);
		return queue.poll();
	}
}
