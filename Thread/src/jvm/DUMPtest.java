package jvm;

import java.util.ArrayList;
import java.util.List;

public class DUMPtest {
	byte[] by = new byte[1*1024*1024];
	public static void main(String[] args) {
		List<DUMPtest> list = new ArrayList<>();
		int count = 0;
		try {
			while (true) {
				list.add(new DUMPtest());
				count+=1;
			}
		} catch (Throwable e) {
			e.getStackTrace();
			System.out.println("***************count:"+count);
		} finally {

		}
	}

}
