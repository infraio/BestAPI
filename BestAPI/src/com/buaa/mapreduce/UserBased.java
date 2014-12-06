package com.buaa.mapreduce;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class UserBased {
	
	private static final int k = 10;
	private static final int ACOL = 339;
	private static final int AROW = 339;
	private static final int BCOL = 5825;
	private static final int BROW = 339;
	
	public static class UserBasedMapper 
	extends Mapper<Object, Text, Text, Text> {
		
		private Text keyout = new Text();
		private Text valout = new Text();
		
		@Override
		protected void map(Object key, Text value,
				Mapper<Object, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			
			String[] ss = new String[4];
			int n = 0;
			StringTokenizer itr = new StringTokenizer(value.toString());
			while (itr.hasMoreTokens()) {
				ss[n++] = itr.nextToken();
		    }
			String m = ss[0];
			String i = ss[1];
			String j = ss[2];
			String v = ss[3];
			
			if(m.equals("a")) {
				valout = new Text(m + " " + j + " " + v);
				for(int p = 0; p < BCOL; ++p) {
					keyout = new Text(i + " " + String.valueOf(p));
					context.write(keyout, valout);	
				}
			} else {
				valout = new Text(m + " " + i + " " + v);
				for(int p = 0; p < AROW; ++p) {
					keyout = new Text(String.valueOf(p) + " " + j);
					context.write(keyout, valout);	
				}
			}
		}
		
	}
	
	public static class UserBasedReducer
	extends Reducer<Text, Text, Text, Text> {
		
		private static void heapSort(double[] array, int[] index) {
			if (array == null || array.length <= 1) {
				return;
			}
			buildMaxHeap(array, index);
			for (int i = array.length-1; i >= array.length-k-1; i--) {
				swap(array, index, 0, i);
//				System.out.println(array[i] + " " + index[i]);
				maxHeap(array, index, i, 0);
			}
		}

		private static void buildMaxHeap(double[] array, int[] index) {
			if (array == null || array.length <= 1) {
				return;
			}
			int half = array.length / 2;
			for (int i = half; i >= 0; i--) {
				maxHeap(array, index, array.length, i);
			}
		}

		private static void maxHeap(double[] array, int[] index, int heapSize, int in) {
			int left = in * 2 + 1;
			int right = in * 2 + 2;
			int largest = in;
			if (left < heapSize && array[left] > array[in]) {
				largest = left;
			}
			if (right < heapSize && array[right] > array[largest]) {
				largest = right;
			}
			if (in != largest) {
				swap(array, index, in, largest);
				maxHeap(array, index, heapSize, largest);
			}
		}
		
		private static void swap(double[] array, int[] index, int i, int j) {
			double temp1;
			int temp2;
			temp1 = array[i];
			temp2 = index[i];
			array[i] = array[j];
			index[i] = index[j];
			array[j] = temp1;
			index[j] = temp2;
		}
		
//		private double getAverage(double[] n) {
//			double sum = 0;
//			int num = 0;
//			for(int i = 0; i < n.length; ++i)
//				if(n[i] != -1 && n[i] != 0) {
//					num ++;
//					sum += n[i];
//				}
//			if(num != 0)
//				return sum / (double) num;
//			return -1;
//		}
		
		private int[] findUserTopK(double[] usim) {
			int[] utopk = new int[k];
			int[] uindex = new int[usim.length];
			for(int i = 0; i < usim.length; ++i) {
				uindex[i] = i;
			}
			heapSort(usim, uindex);
			for(int i = 0; i < k; ++i)
				utopk[i] = uindex[uindex.length-i-2];
			return utopk;
		}
		
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
			int[] utopk = findUserTopK(a);
			
			double p = 0;
			double q = 0;
			for(int i = 0; i < k; ++i) {
				if(a[a.length-i-2] > 0 && b[utopk[i]] != 0 && b[utopk[i]] != -1) {
					p += a[a.length-i-2] * b[utopk[i]];
					q += a[a.length-i-2];
				}
			}
			double r = 0.0;
			if(q != 0)
				r =  p / q;
			context.write(key, new Text(String.valueOf(r)));
		}
		
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
	    Job job = Job.getInstance(conf, "SimUser");
	    job.setNumReduceTasks(10);
	    job.setJarByClass(UserBased.class);
	    job.setMapperClass(UserBasedMapper.class);
//	    job.setCombinerClass(UserBasedReducer.class);
	    job.setReducerClass(UserBasedReducer.class);
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(Text.class);
	    FileInputFormat.addInputPath(job, new Path(args[0]));
	    FileOutputFormat.setOutputPath(job, new Path(args[1]));
	    System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
