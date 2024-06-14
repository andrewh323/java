package measuringSorts;

import java.util.Random;

public class TestClass extends Instrumentation {
	
	public static int n = 99999;
	
	static int[] populateArray() {
		ins.startTiming("PopulateArray");
		int[] a = new int[n];
		for (int i=0; i<n; i++) {
			Random r = new Random();
			a[i] = r.nextInt((n - 1) + 1) + 1;
		}
		ins.stopTiming("PopulateArray");
		return a;
	}
	
	public static void main(String[] args) {
		ins.activate(true);
		callBubble();
		callQuick();
		String totalStr = "TOTAL TIME: " + total + "ms";
		ins.comment(totalStr);
		ins.dump(null);
	}
	
	static void callBubble() {
		ins.startTiming("BubbleSort");
		BubbleSort.bubbleSort(populateArray());
		ins.stopTiming("BubbleSort");
	}
	
	static void callQuick() {
		ins.startTiming("QuickSort");
		QuickSort.quickSort(populateArray(), 0, n-1);
		ins.stopTiming("QuickSort");
	}
}
