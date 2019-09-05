package com.syntun.collect.consumeHZ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import com.syntun.etl.tools.ConvertTools;
import com.syntun.etl.tools.SyntunDate;

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
	static int id = 0;

	public void reduce(Text key, Iterable<Text> value, Context context) throws IOException, InterruptedException {
		String keyValue = new String(key.getBytes(), 0, key.getLength(), "UTF-8");
		if(keyValue.split(":").length != 2){
			return;
		}
		String platformId = keyValue.split(":")[0];
		String userName = keyValue.split(":")[1];
		String platformName = "null";
		
		List<String> list = new ArrayList<String>();
		for (Text content : value) {
			String line = new String((content).getBytes(), 0, (content).getLength(), "UTF-8");
			Map<String, String> map = ConvertTools.convertStrToMapR(line);
//			if (map == null) {
//				continue;
//			}
			if (map.get("platform_name") != null && !map.get("platform_name").equals("null")) {
				platformName = map.get("platform_name");
			}
			list.add(map.get("product_comment_time"));
		}
		Collections.sort(list);
        //System.out.println(list);
        String commentTime = "";
		int i = 0;
		int a = 0;
		int aCou = 0;
		int b = 0;
		int bCou = 0;
		int c = 0;
		int cCou = 0;
		int d = 0;
		int dCou = 0;
		int e = 0;
		int eCou = 0;
		for (String day:list){
			if(i==0){
				commentTime = day;
				i = 1;
			}else if(i == 1 && commentTime.indexOf("2014") != -1 && day.indexOf("2014") != -1){
				a++;
				aCou = aCou + SyntunDate.getChaDate(commentTime, day);
				commentTime = day;
			}else if(i == 1 && commentTime.indexOf("2015") != -1 && day.indexOf("2015") != -1){
				b++;
				bCou = bCou + SyntunDate.getChaDate(commentTime, day);
				commentTime = day;
			}else if(i == 1 && commentTime.indexOf("2016") != -1 && day.indexOf("2016") != -1){
				c++;
				cCou = cCou + SyntunDate.getChaDate(commentTime, day);
				commentTime = day;
			}else if(i == 1 && commentTime.indexOf("2017") != -1 && day.indexOf("2017") != -1){
				d++;
				dCou = dCou + SyntunDate.getChaDate(commentTime, day);
				commentTime = day;
			}else if(i == 1 && commentTime.indexOf("2018") != -1 && day.indexOf("2018") != -1){
				e++;
				eCou = eCou + SyntunDate.getChaDate(commentTime, day);
				commentTime = day;
			}else{
				commentTime = day;
			}
		}
		int consume2014 = 1;
		int consume2015 = 1;
		int consume2016 = 1;
		int consume2017 = 1;
		int consume2018 = 1;
		
		if(a != 0){
			consume2014 = Integer.parseInt(String.valueOf(aCou/a));
			//consume2014 = consume2014==0?1:consume2014;
		}
		if(b != 0){
			consume2015 = Integer.parseInt(String.valueOf(bCou/b));
			//consume2015 = consume2015==0?1:consume2015;
		}
		if(c != 0){
			consume2016 = Integer.parseInt(String.valueOf(cCou/c));
			//consume2016 = consume2016==0?1:consume2016;
		}
		if(d != 0){
			consume2017 = Integer.parseInt(String.valueOf(dCou/d));
			//consume2017 = consume2017==0?1:consume2017;
		}
		if(e != 0){
			consume2018 = Integer.parseInt(String.valueOf(eCou/e));
			//consume2018 = consume2018==0?1:consume2018;
		}
		
		String v = consume2014 + "	" +
					consume2015 + "	" +
					consume2016 + "	" +
					consume2017 + "	" +
					consume2018;
		try {
			context.write(new Text(id+"	"+platformId+"	"+platformName+"	"+userName), new Text(v.getBytes("UTF-8")));
			id ++;
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}
