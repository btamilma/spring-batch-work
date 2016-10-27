package com.btamilmani.batch.partitioner;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

public class TobacoRangePartitioner implements Partitioner{

	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		Map<String, ExecutionContext> executionContextMap = null;
		
		executionContextMap = new HashMap<>();
		
		ExecutionContext executionContext = new ExecutionContext();
		executionContext.put("fromId", new Integer(1));
		executionContext.put("toId", new Integer(25));
		executionContextMap.put("partitionOne", executionContext);
		
		executionContext = new ExecutionContext();
		executionContext.put("fromId", new Integer(26));
		executionContext.put("toId", new Integer(50));
		executionContextMap.put("partitionTwo", executionContext);
		
		executionContext = new ExecutionContext();
		executionContext.put("fromId", new Integer(51));
		executionContext.put("toId", new Integer(75));
		executionContextMap.put("partitionThree", executionContext);
		
		
		return executionContextMap;
	}

}
