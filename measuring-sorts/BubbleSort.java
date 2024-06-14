package measuringSorts;

public class BubbleSort extends Instrumentation {
	
	static void bubbleSort(int arr[]) {
		int i, j;
		int n = arr.length;
		for (i=0; i < n-1; i++) {
			for (j=0; j < n-i-1; j++) {
				if (arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ins.activate(true);
		ins.startTiming("BubbleSort");
		bubbleSort(TestClass.populateArray());
		ins.stopTiming("BubbleSort");
	}
}
