import java.io.File;
import java.util.Scanner;

public class ScheduleList {
    private Schedule[] sList=new Schedule[100];
	private int num=0;
	private String fname;
	
	public ScheduleList(){
	}
	
	//생성자
	public ScheduleList(String fileName){
		this.fname=fileName;
		scanSchedule();
	}
	
	//Schedule 갯수를 반환
	public int numSchedules() {
		return this.num;
	}
	
	//index i에 대한 Schedule을 반환 
	public Schedule getSchedule(int i) {
		return this.sList[i];
	}
	
	//파일을 스캔함
	public void scanSchedule() {
		File file = new File(this.fname);
		Scanner input;
		
		try {
			input = new Scanner(file);
		} 
		catch(Exception e) { 
			System.out.println("Unknown File");
			return;
		}
		while (input.hasNext()) { 
			String line = input.nextLine();
			scheduleParsing(line);
		}
		input.close();
	}
	
	//한 줄을 파싱, 에러 체크, list의 연결함
	public void scheduleParsing(String line) {
		Schedule tSchedule=new Schedule(line);
		if(tSchedule.getErrorCheck()==1) {
			//System.out.println("No String ; Skip the input line : "+line);
		}
		else if(tSchedule.getErrorCheck()==2)
			System.out.println("No Schedule Title ; Skip the input line : "+line);
		else if(tSchedule.getErrorCheck()==3)
			System.out.println("Wrong Data Format ; Skip the input line : "+line);
		else if(tSchedule.getErrorCheck()==4)
			System.out.println("Start Time is later than End Time ; Skip the input line : "+line);
		else {
			sList[num]=tSchedule;
			num++;
		}
	}
	
	//모든 Schedule을 console에 출력
	public void printSchedules() {
		for(int i=0;i<numSchedules();i++)
			getSchedule(i).print();
	}
	
	//file에 Schedule을 저장
	public void saveSchedule() {
		File file = new File(this.fname);
		java.io.PrintWriter input;
		
		try {
			input = new java.io.PrintWriter(file);
		} 
		catch(Exception e) { 
			 return;
		}
		for(int i=0;i<numSchedules();i++)
			input.println(getSchedule(i).getSchedule());
		input.close();
	}
}
