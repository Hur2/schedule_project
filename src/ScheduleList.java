import java.io.File;
import java.util.Scanner;

public class ScheduleList {
    private Schedule[] sList=new Schedule[100];
	private int num=0;
	private String fname;
	
	public ScheduleList(){
	}
	
	//������
	public ScheduleList(String fileName){
		this.fname=fileName;
		scanSchedule();
	}
	
	//Schedule ������ ��ȯ
	public int numSchedules() {
		return this.num;
	}
	
	//index i�� ���� Schedule�� ��ȯ 
	public Schedule getSchedule(int i) {
		return this.sList[i];
	}
	
	//������ ��ĵ��
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
	
	//�� ���� �Ľ�, ���� üũ, list�� ������
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
	
	//��� Schedule�� console�� ���
	public void printSchedules() {
		for(int i=0;i<numSchedules();i++)
			getSchedule(i).print();
	}
	
	//file�� Schedule�� ����
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
