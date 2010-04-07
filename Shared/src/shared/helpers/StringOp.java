package shared.helpers;

public class StringOp {
	
	public static int find ( String[] arr , String str)
	{
		for (int i = 0; i < arr.length; i++) {
			if(arr[i].equals(str)) {
				return i;
			}
		}
		return -1;
	}

}
