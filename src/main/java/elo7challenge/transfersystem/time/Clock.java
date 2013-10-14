package elo7challenge.transfersystem.time;

import java.util.Calendar;
import java.util.Date;

public class Clock {

	public Date getCurrentDate() {
		return new Date();
	}

	public Calendar getCurrentDateAsCalendar() {
		return Calendar.getInstance();
	}

}
