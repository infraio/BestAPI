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

public class SimItem {
	private static final int AROW = 101;
	private static final int ACOL = 150;
	private static final int BROW = 150;
	private static final int BCOL = 101;
	
	public static class MatrixMapper 
	extends Mapper<Object, Text, Text, Text> {
		
		private Text keyout = new Text();
		private Text valout = new Text();
		
		@Override
		protected void map(Object key, Text value,
				Mapper<Object, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			
			String[] ss = value.toString().split(" ");
			String i = ss[0];
			String j = ss[1]; 
			String v = ss[2];
			
			valout = new Text("a" + " " + i + " " + v);
			for(int p = 0; p < BCOL; ++p) {
				keyout = new Text(j + " " + String.valueOf(p));
				context.write(keyout, valout);	
			}
			
			valout = new Text("b" + " " + i + " " + v);
			for(int p = 0; p < AROW; ++p) {
				keyout = new Text(String.valueOf(p) + " " + j);
				context.write(keyout, valout);
			}
		}
		
	}
	
	public static class MatrixReducer
	extends Reducer<Text, Text, Text, Text> {
		
		@Override
		protected void reduce(Text key, Iterable<Text> values,
				Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			
			double[] a = new double[ACOL];
			double[] b = new double[BROW];
			Iterator<Text> itr = values.iterator();
			while(itr.hasNext()) {
				String value = itr.next().toString();
				String[] vs = value.split(" ");
				if(vs[0].equals("a")) {
					a[Integer.parseInt(vs[1])] = Double.parseDouble(vs[2]);
				} else {
					b[Integer.parseInt(vs[1])] = Double.parseDouble(vs[2]);
				}
			}
			double iavg = getAverage(a);
			double javg = getAverage(b);
			double p = 0;
			double q = 0;
			double r = 0;
			int inum = 0;
			int jnum = 0;
			int internum = 0;
			for(int k = 0; k < ACOL; ++k) {
				if(a[k] != -1.0 && a[k] != 0.0) inum++;
				if(b[k] != -1.0 && b[k] != 0.0) jnum++;
				if(a[k] != -1.0 && a[k] != 0.0 && b[k] != -1.0 && b[k] != 0.0) {
					++internum;
					p += (a[k] - iavg) * (b[k] - javg);
					q += Math.pow(a[k] - iavg, 2);
					r += Math.pow(b[k] - javg, 2);
				}
			}
			double sum = -1.0;
			if(q != 0 && r != 0 && inum != 0 && jnum != 0)
				sum = ((double)2 * (double)internum) / ((double)inum + (double)jnum) * (p / (Math.sqrt(q) * Math.sqrt(r)));
			context.write(key, new Text(String.valueOf(sum)));
		}
		
		private double getAverage(double[] n) {
			double sum = 0;
			int num = 0;
			for(int i = 0; i < n.length; ++i)
				if(n[i] != -1.0 && n[i] != 0.0) {
					num ++;
					sum += n[i];
				}
			if(num != 0)
				return sum / (double) num;
			return 0.0;
		}
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
	    Job job = Job.getInstance(conf, "SimItem");
	    job.setNumReduceTasks(10);
	    job.setJarByClass(SimItem.class);
	    job.setMapperClass(MatrixMapper.class);
//	    job.setCombinerClass(MatrixReducer.class);
	    job.setReducerClass(MatrixReducer.class);
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(Text.class);
	    FileInputFormat.addInputPath(job, new Path(args[0]));
	    FileOutputFormat.setOutputPath(job, new Path(args[1]));
	    System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
