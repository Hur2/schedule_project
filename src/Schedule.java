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
	
	//에러의 유형이 무엇인지 체크한다.
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
	
	//문자열에 내용물이 숫자인지 판별한다.
	public boolean isNumber(String str) {
		char ch;
		for(int i=0;i<str.length();i++) {
			ch=str.charAt(i);
			if(!Character.isDigit(ch))
				return false;
		}
		return true;
	}
	
	//이하 4개는 기본 정보를 넣는 함수이다.
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
	
	//이하 4개는 기본 정보를 반환하는 함수이다.
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
	
	//schedule 전체를 출력에 필요한 형식으로 반환
	public String getSchedule() {
		return name+"//"+LDTtoString(startTime)+"//"+LDTtoString(endTime)+"//"+memo;
	}
	
	//에러 유형을 반환
	public int getErrorCheck() {
		return errorNum;
	}
	
	//schedule을 출력한다
	public void print() {
		System.out.println(name+"//"+LDTtoString(startTime)+"//"+LDTtoString(endTime)+"//"+memo);
	}
	
	//LDT를 문자열로 변환시킨다.
	public String LDTtoString(LocalDateTime time) {
		return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}
}
