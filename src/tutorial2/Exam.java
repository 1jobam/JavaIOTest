package tutorial2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Exam {
	
	private String re = "e:/D_Other/phonenum/";
	private Scanner scan;
	private Map<String, PhoneVO> phoneBook;
	
	public Exam() {
		scan = new Scanner(System.in);
		phoneBook = new HashMap<>();
	}
	
	// 메뉴를 출력하는 메서드
	public void displayMenu(){
		System.out.println();
		System.out.println("메뉴를 선택하세요.");
		System.out.println(" 1. 전화번호 등록");
		System.out.println(" 2. 전화번호 수정");
		System.out.println(" 3. 전화번호 삭제");
		System.out.println(" 4. 전화번호 검색");
		System.out.println(" 5. 전화번호 전체 출력");
		System.out.println(" 0. 프로그램 종료");
		System.out.print(" 번호입력 >> ");		
	}
	
	// 프로그램을 시작하는 메서드
	public void phoneBookStart() throws IOException{
		System.out.println("===============================================");
		System.out.println("   전화번호 관리 프로그램(파일로 저장되지 않음)");
		System.out.println("===============================================");
		
		while(true){
			
			displayMenu();  // 메뉴 출력
			
			int menuNum = scan.nextInt();   // 메뉴 번호 입력
			
			switch(menuNum){
				case 1 : insert();		// 등록
					break;
				case 2 : update();		// 수정
					break;
				case 3 : delete();		// 삭제
					break;
				case 4 : search();		// 검색
					break;
				case 5 : displayAll();	// 전체 출력
					break;
				case 0 :
					System.out.println("프로그램을 종료합니다...");
					return;
				default :
					System.out.println("잘못 입력했습니다. 다시입력하세요.");
			} // switch문
		} // while문
	}
	
	/**
	 	이름을 이용한 전화번호 정보를 검색하는 메서드
	 * @throws IOException 
	 */
	private void search() throws IOException {
		System.out.println();
		System.out.println("검색할분의 이름을 입력하세요.");
		System.out.print("이 름 >> ");
		String name = scan.next();
		
		String a = re + name + ".txt";
		
		FileReader fr = new FileReader(a);

		int c;
		
		while((c = fr.read()) != -1){
			System.out.print((char)c);
		}
		fr.close();

	}

	/**
	 	전화번호 정보를 삭제하는 메서드
	 */
	private void delete() {
		System.out.println();
		System.out.println("삭제할분의 성함을 입력하세요.");
		System.out.print("이름>> ");
		String name = scan.next();
		
		String a = re + name + ".txt";
		
		File file = new File(a); 
		if( file.exists() ){ 
			if(file.delete()){ 
				System.out.println("파일삭제 성공"); 
			}else{ System.out.println("파일삭제 실패");} 
		}else{ 
			System.out.println("파일이 존재하지 않습니다.");}

		System.out.println("삭제 작업 완료...");
		
	}

	/**
	 	전화번호 정보를 수정하는 메서드
	 * @throws IOException 
	 */
	private void update() throws IOException {
		System.out.println();
		System.out.println("수정할분의 성함 및 정보들을 입력하세요.");
		System.out.print("이 름 >> ");
		String name = scan.next();
		
		System.out.print("전화번호 >> ");
		String tel = scan.next();
		
		System.out.print("주  소 >> ");
		scan.nextLine(); // 버퍼에 남아있을지 모를 엔터키값 제거
		String addr = scan.nextLine();
		
		PhoneVO p = new PhoneVO(name, tel, addr);
		phoneBook.put(name, p); // 같은 key값에 데이터를 저장하면 value값이 변경된다.
		
		FileWriter fwr = new FileWriter(re + name + ".txt");
		
		String a = "성함 : " + name + "\r\n";
		a += "전화번호 : " + tel + "\r\n";
		a += "주소 : " + addr + "\r\n";
		
		fwr.write(a);

		System.out.println("파일에 수정 작업 완료");
		
		fwr.close(); // 쓰기작업 완료 후 스트림 닫기
		
		System.out.println(name + "씨 정보 수정 완료...");
	}

	/**
		전체 자료를 출력하는 메서드
	 * @throws IOException 
	 */
	private void displayAll() throws IOException {
		File f = new File("e:\\D_Other\\phonenum\\");
		File[] files = f.listFiles();
		for(int i=0; i<files.length; i++){
			System.out.println("=============================");
			FileInputStream fin = new FileInputStream(files[i]);
			InputStreamReader isr = new InputStreamReader(fin, "UTF-8");
			
			int ch;
			
			while((ch =isr.read()) != -1) {
				System.out.print((char)ch);
			}
			System.out.println();
			
			fin.close();
		}
		System.out.println("=============================");
		System.out.println("출력 완료...");
	}

	/*
	 	새로운 전화번호 정보를 등록하는 메서드
	 	이미 등록된 사람은 등록되지 않는다.
	 */
	private void insert() throws IOException {
		
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이 름 >> ");
		String name = scan.next();
		
		// 이미 등록된 사람인지 검사
		// get()메서드로 값을 가져올때 가져올 자료가 없으면 null을 반환한다.
		if(phoneBook.get(name) != null) {
			System.out.println(name + "씨는 이미 등록된 사람입니다.");
			return; //메서드 종료
		}
		
		System.out.print("전화번호 >> ");
		String tel = scan.next();
		
		System.out.print("주   소 >> ");
		//입력버퍼에 남아 있는 엔터키까지 읽어와버리는 역할을 수행함.
		//next() 호출 후 nextLine()호출시 혹시 남아있을지 모를 쓰레기 값을 위해 한번 호출한다.
		scan.nextLine(); 
		String addr = scan.nextLine();
		
		phoneBook.put(name, new PhoneVO(name, tel, addr));
		
//		File ne = new File(re + name + ".txt");
//		ne.createNewFile();
		
			// 출력용 OutputStream 객체 생성
		FileWriter fwr = new FileWriter(re + name + ".txt");
		
			String a = "성함 : " + name + "\r\n";
			a += "전화번호 : " + tel + "\r\n";
			a += "주소 : " + addr + "\r\n";
			
			fwr.write(a);

			System.out.println("파일에 쓰기 작업 완료");
			
			fwr.close(); // 쓰기작업 완료 후 스트림 닫기
		
		System.out.println(name + "씨 등록 완료...");
		
	}

	public static void main(String[] args) throws IOException {
		new Exam().phoneBookStart();
	}

}

/**
 * 전화번호 정보를 저장할 수 있는 VO 클래스
 */
class PhoneVO{
	private String name; // 이름
	private String tel; // 전화번호
	private String addr; // 주소
	
	public PhoneVO(String name, String tel, String addr) {
		super();
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	@Override
	public String toString() {
		return "Phone [name=" + name + ", tel= " + tel + ", addr=" + addr + "]";
	}
	
}

