package tutorial;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Exam {
	public static void main(String[] args) throws IOException{
		
		//읽어올 파일 및 경로
		String in = "e:/D_Other/Tulips.jpg";
		
		//복사해서 붙일 파일 및 경로
		String out = "e:/D_Other/복사본_Tulips.jpg";
		
		//일반 스트림 생성
		FileInputStream fis = new FileInputStream(in);
		FileOutputStream fout = new FileOutputStream(out);
		
		//보조 스트림 생성
		BufferedOutputStream buo = new BufferedOutputStream(fout);
		
		//지속적으로 읽어 담을 변수
		int c;
		
		//읽을 값이 없을때 -1이 되므로 -1이 아니면 반복
		while((c = fis.read()) != -1) {
			//지속적으로 버퍼의 기본크기 8kb 스트림을 이용하여 효율적 생성
			buo.write(c);
		}
		//8kb가 되지 않아 남은 값들을 flush 처리 및 close 진행
		buo.close();
		
	}
}
