package com.buaa.algorithm;
import com.buaa.model.*;
import java.io.FileWriter;
import java.io.IOException;


public class Prediction {

	private static final int k = 20;
	private static final double lamda = 0.1;
	
	private User user;
	private WebService api;
	private double[][] matrix;
	
	public Prediction(User user, WebService api, double[][] matrix) {
		this.user = user;
		this.api = api;
		this.matrix = matrix;
	}
	
	public void saveSimilarUsers() throws IOException {
		double[] usim = findSimilarUsers();
		FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/data/similarUsers.txt", true);
		for(int i = 0; i < usim.length; ++i)
			fw.append(usim[i] + "\t");
		fw.append("\r\n");
		fw.close();
	}
		
	public void saveSimilarApis() throws IOException {	
		double[] isim = findSimilarApis();
		FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/data/similarApis.txt", true);
		for(int i = 0; i < isim.length; ++i)
			fw.append(isim[i] + "\t");
		fw.append("\r\n");
		fw.close();
	}
	
	public double predict() {
//		double[] usim = findSimilarUsers();
//		double[] isim = findSimilarApis();
		if(user.getId() == 3)
			System.out.println();
		double[] usim = Test.usims[user.getId()];
		double[] isim = Test.isims[api.getId()];
		int[] utopk = findUserTopK(usim);
		int[] itopk = findApiTopK(isim);
		double upre = predictByUser(usim, utopk);
		double ipre = predictByApi(isim, itopk);
		if(upre == 0 && ipre == 0)
			return -1;
		else if (upre == 0)
			return ipre;
		else if (ipre == 0)
			return upre;
		double ucon = getUserConfidenceWeights(usim);
		double icon = getApiConfidenceWeights(isim);
		if(ucon == 0 && icon == 0)
			return -1;
		double uw = ucon * lamda / (ucon * lamda + icon * (1 - lamda));
		double iw = icon * (1 - lamda) / (ucon * lamda + icon * (1 - lamda));
		return uw * upre + iw * ipre;
	}
	
	private double getApiConfidenceWeights(double[] isim) {
		double p = 0;
		for(int i = 0; i < k; ++i) {
			p += isim[i];
		}
		double q = 0;
		for(int i = 0; i < k; ++i) {
			q += isim[i] / p * isim[i];
		}
		return q;
	}

	private double getUserConfidenceWeights(double[] usim) {
		double p = 0;
		for(int i = 0; i < k; ++i) {
			p += usim[i];
		}
		double q = 0;
		for(int i = 0; i < k; ++i) {
			q += usim[i] / p * usim[i];
		}
		return q;
	}

	private double predictByUser(double[] usim, int[] utopk) {
		double[] urow = matrix[user.getId()];
		double uavg = getAverage(urow);
		double p = 0;
		double q = 0;
		int actk = 0;
		for(int i = 0; i < k; ++i) {
			if(usim[i] > 0 && matrix[usim.length-i-2][api.getId()] != 0 && matrix[usim.length-i-2][api.getId()] != -1) {
				double[] arow = matrix[usim.length-i-2];
				double aavg = getAverage(arow);
				p += usim[i] * (matrix[usim.length-i-2][api.getId()] - aavg);
				q += usim[i];
				++actk;
			}
		}
		System.out.println("actk " + actk);
		if(q != 0)
			return uavg + p / q;
		return 0;
	}
	
	private double predictByApi(double[] isim, int[] itopk) {
		double[] icol = new double[matrix.length];
		for(int i = 0; i < matrix.length; ++i)
			icol[i] = matrix[i][api.getId()];
		double iavg = getAverage(icol);
		double p = 0;
		double q = 0;
		int actk = 0;
		for(int i = 0; i < k; ++i) {
			if(isim[i] > 0 && matrix[user.getId()][isim.length-i-2] != 0 && matrix[user.getId()][isim.length-i-2] != -1) {
				double[] jcol = new double[matrix.length];
				for(int j = 0; j < matrix.length; ++j)
					jcol[j] = matrix[j][isim.length-i-2];
				double javg = getAverage(jcol);
				p += isim[i] * (matrix[user.getId()][isim.length-i-2] - javg);
				q += isim[i];
				++actk;
			}
		}
		System.out.println("actk " + actk);
		if(q != 0)
			return iavg + p / q;
		return 0;
	}
	
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
	
	private static void heapSort(double[] array, int[] index) {
		if (array == null || array.length <= 1) {
			return;
		}
		buildMaxHeap(array, index);
		for (int i = array.length-1; i >= array.length-k-1; i--) {
			swap(array, index, 0, i);
//			System.out.println(array[i] + " " + index[i]);
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
	
	private int[] findApiTopK(double[] isim) {
		int[] itopk = new int[k];
		int[] iindex = new int[isim.length];
		for(int i = 0; i < isim.length; ++i) {
			iindex[i] = i;
		}
		heapSort(isim, iindex);
		for(int i = 0; i < k; ++i)
			itopk[i] = iindex[iindex.length-i-2];
		return itopk;
	}
	
	private double[] findSimilarUsers() {
		double[] usim = new double[matrix.length];
		double[] urow = matrix[user.getId()];
		double uavg = getAverage(urow);
		for(int i = 0; i < matrix.length; ++i) {
			double[] arow = matrix[i];
			double aavg = getAverage(arow);
			
			double p = 0;
			double q = 0;
			double r = 0;
			int unum = 0;
			int anum = 0;
			int internum = 0;
			for(int j = 0; j < arow.length; ++j) {
				if(arow[j] != -1 && arow[j] != 0) anum++;
				if(urow[j] != -1 && urow[j] != 0) unum++;
				if(arow[j] != -1 && urow[j] != -1 && arow[j] != 0 && urow[j] != 0) {
					++internum;
					p += (arow[j] - aavg) * (urow[j] - uavg);
					q += Math.pow(arow[j] - aavg, 2);
					r += Math.pow(urow[j] - uavg, 2);
				}
			}
			if(q != 0 && r != 0 && anum != 0 && unum != 0)
				usim[i] = ((double)2 * (double)internum) / ((double)anum + (double)unum) * (p / (Math.sqrt(q) * Math.sqrt(r)));
			else
				usim[i] = (double)-1;
		}
		return usim;
	}
	
	private double[] findSimilarApis() {
		double[] isim = new double[matrix[0].length];
		double[] icol = new double[matrix.length];
		for(int i = 0; i < matrix.length; ++i)
			icol[i] = matrix[i][api.getId()];
		double iavg = getAverage(icol);
		for(int i = 0; i < matrix[0].length; ++i) {
			if(i == 5815)
				System.out.println();
			double[] jcol = new double[matrix.length];
			for(int j = 0; j < matrix.length; ++j) {
				jcol[j] = matrix[j][i];
			}
			double javg = getAverage(jcol);
			
			double p = 0;
			double q = 0;
			double r = 0;
			int inum = 0;
			int jnum = 0;
			int internum = 0;
			for(int j = 0; j < matrix.length; ++j) {
				if(icol[j] != -1 && icol[j] != 0) inum++;
				if(jcol[j] != -1 && jcol[j] != 0) jnum++;
				if(icol[j] != -1 && jcol[j] != -1 && icol[j] != 0 && jcol[j] != 0) {
					++internum;
					p += (icol[j] - iavg) * (jcol[j] - javg);
					q += Math.pow(icol[j] - iavg, 2);
					r += Math.pow(jcol[j] - javg, 2);
				}
			}
			if(q != 0 && r != 0 && inum != 0 && jnum != 0)
				isim[i] = ((double)2 * (double)internum) / ((double)inum + (double)jnum) * (p / (Math.sqrt(q) * Math.sqrt(r)));
			else
				isim[i] = (double)-1;
		}
		return isim;
	}
}
