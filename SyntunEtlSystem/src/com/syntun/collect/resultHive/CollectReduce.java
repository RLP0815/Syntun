package com.syntun.collect.resultHive;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class CollectReduce extends Reducer<Text, Text, Text, Text> {
	private MultipleOutputs<Text, Text> mos;
	
	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		mos = new MultipleOutputs<Text, Text>(context);
		super.setup(context);
	}

	@Override
	protected void cleanup(Context context) throws IOException, InterruptedException {
		mos.close();
		super.cleanup(context);
	}
	
	public void reduce(Text key, Iterable<Text> value, Context context) throws IOException, InterruptedException {
		
		//String k = new String((key).getBytes(), 0, (key).getLength(), "UTF-8");
		for (Text content : value) {
			//String v = new String((content).getBytes(), 0, (content).getLength(), "UTF-8");
			context.write(key, content);
		}

	}
}
