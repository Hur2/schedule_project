public class TestSchedule {
	public static void main(String[] args) {
		ScheduleList test = new ScheduleList("schedule-file.data");
		test.printSchedules();
		test.saveSchedule();
	}
}
