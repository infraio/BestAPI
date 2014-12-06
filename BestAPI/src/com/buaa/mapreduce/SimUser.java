package com.buaa.mapreduce;
import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SimUser {
	
	private static final int ACOL = 5825;
	private static final int AROW = 339;
	private static final int BCOL = 339;
	private static final int BROW = 5825;
	
	public static class SimUserMapper 
	extends Mapper<Object, Text, Text, Text> {
		
		private Text keyout = new Text();
		private Text valout = new Text();
		
		@Override
		protected void map(Object key, Text value,
				Mapper<Object, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			
			String[] ss = value.toString().split(" ");
			
			String i = ss[0];//matcher.group(1);
			String j = ss[1];//matcher.group(2); 
			String v = ss[2];//matcher.group(3);
			
			valout = new Text("a " + j + " " + v);
			for(int p = 0; p < BCOL; ++p) {
				keyout = new Text(i + " " + String.valueOf(p));
				context.write(keyout, valout);	
			}
			valout = new Text("b " + j + " " + v);
			for(int p = 0; p < AROW; ++p) {
				keyout = new Text(String.valueOf(p) + " " + i);
				context.write(keyout, valout);	
			}
		}
		
	}
	
	public static class SimUserReducer
	extends Reducer<Text, Text, Text, Text> {
		
		private double getAverage(double[] n) {
			double sum = 0;
			int num = 0;
			for(int i = 0; i < n.length; ++i)
				if(n[i] != -1 && n[i] != 0) {
					num ++;
					sum += n[i];
				}
			if(num != 0)
				return sum / (double) num;
			return -1;
		}
		
		@Override
		protected void reduce(Text key, Iterable<Text> values,
				Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			
			double[] u = new double[ACOL];
			double[] a = new double[BROW];
			Iterator<Text> itr = values.iterator();
			while(itr.hasNext()) {
				String value = itr.next().toString();
				String[] vs = value.split(" ");
				if(vs[0].equals("a")) {
					u[Integer.parseInt(vs[1])] = Double.parseDouble(vs[2]);
				} else {
					a[Integer.parseInt(vs[1])] = Double.parseDouble(vs[2]);
				}
			}
			double uavg = getAverage(u);
			double aavg = getAverage(a);
			double p = 0;
			double q = 0;
			double r = 0;
			double usim = -1;
			int unum = 0;
			int anum = 0;
			int internum = 0;
			for(int i = 0; i < u.length; ++i) {
				if(u[i] != -1 && u[i] != 0) unum++;
				if(a[i] != -1 && a[i] != 0) anum++;
				if(u[i] != -1 && a[i] != -1 && u[i] != 0 && a[i] != 0) {
					++internum;
					p += (a[i] - aavg) * (u[i] - uavg);
					q += Math.pow(a[i] - aavg, 2);
					r += Math.pow(u[i] - uavg, 2);
				}
			}
			if(q != 0 && r != 0 && anum != 0 && unum != 0)
				usim = ((double)2 * (double)internum) / ((double)anum + (double)unum) * (p / (Math.sqrt(q) * Math.sqrt(r)));
			context.write(key, new Text(String.valueOf(usim)));
		}
		
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
	    Job job = Job.getInstance(conf, "SimUser");
	    job.setNumReduceTasks(10);
	    job.setJarByClass(SimUser.class);
	    job.setMapperClass(SimUserMapper.class);
//	    job.setCombinerClass(SimUserReducer.class);
	    job.setReducerClass(SimUserReducer.class);
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(Text.class);
	    FileInputFormat.addInputPath(job, new Path(args[0]));
	    FileOutputFormat.setOutputPath(job, new Path(args[1]));
	    System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
