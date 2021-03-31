import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Schedule {
	private String name, memo="NULL";
	private LocalDateTime startTime, endTime;
	private int errorNum;
	
	public Schedule(){
	}
	
	public Schedule(String line){
		String[] str=line.split("//");
		errorNum=errorCheck(str);
		setSchedule(str);
	}
	
	public void setSchedule(String[] str) {
		if(errorNum==0) {
		setName(str[0].trim());
		setStartTime(LocalDateTime.parse(str[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
		setEndTime(LocalDateTime.parse(str[2].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
		if(str.length==4) 
			setMemo(str[3].trim());
		}
	}
	
	//������ ������ �������� üũ�Ѵ�.
	public int errorCheck(String[] str) {
		if(str.length<=2||str[0].trim().startsWith(";"))
			return 1;
		
		if((str[0].trim()).equals(""))
			return 2;
		
		String[] dt=str[1].trim().split("[- :]");
		if(dt.length==5) {
			for(int i=0;i<5;i++)
				if(!isNumber(dt[i]))
					return 3;
			if(dt[0].length()!=4)
				return 3;
			for(int i=1;i<5;i++)
				if(dt[i].length()!=2)
					return 3;
		}else
			return 3;
		dt=str[2].trim().split("[- :]");
		if(dt.length==5) {
			for(int i=0;i<5;i++)
				if(!isNumber(dt[i]))
					return 3;
			if(dt[0].length()!=4)
				return 3;
			for(int i=1;i<5;i++)
				if(dt[i].length()!=2)
					return 3;
		}else
			return 3;
		
		LocalDateTime st = LocalDateTime.parse(str[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		LocalDateTime et = LocalDateTime.parse(str[2].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		if(!st.isBefore(et))
			return 4;
		
		return 0;
	}
	
	//���ڿ��� ���빰�� �������� �Ǻ��Ѵ�.
	public boolean isNumber(String str) {
		char ch;
		for(int i=0;i<str.length();i++) {
			ch=str.charAt(i);
			if(!Character.isDigit(ch))
				return false;
		}
		return true;
	}
	
	//���� 4���� �⺻ ������ �ִ� �Լ��̴�.
	public void setName(String str) {
		name=str;
	}
	
	public void setMemo(String str) {
		memo=str;
	}
	
	public void setStartTime(LocalDateTime str) {
		startTime=str;
	}
	
	public void setEndTime(LocalDateTime str) {
		endTime=str;
	}
	
	//���� 4���� �⺻ ������ ��ȯ�ϴ� �Լ��̴�.
	public String getName() {
		return this.name;
	}
	
	public String getMemo() {
		return this.memo;
	}
	
	public LocalDateTime getStartTime() {
		return this.startTime;
	}
	
	public LocalDateTime getEndTime() {
		return this.endTime;
	}
	
	//schedule ��ü�� ��¿� �ʿ��� �������� ��ȯ
	public String getSchedule() {
		return name+"//"+LDTtoString(startTime)+"//"+LDTtoString(endTime)+"//"+memo;
	}
	
	//���� ������ ��ȯ
	public int getErrorCheck() {
		return errorNum;
	}
	
	//schedule�� ����Ѵ�
	public void print() {
		System.out.println(name+"//"+LDTtoString(startTime)+"//"+LDTtoString(endTime)+"//"+memo);
	}
	
	//LDT�� ���ڿ��� ��ȯ��Ų��.
	public String LDTtoString(LocalDateTime time) {
		return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}
}
