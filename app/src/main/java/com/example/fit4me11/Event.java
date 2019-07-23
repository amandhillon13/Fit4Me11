package com.example.fit4me11;


import java.util.Calendar;


public class Event {

    public static final int RUNTYPE=0;
    public static final int CYCLINGTYPE=1;
    public static final int FOOTBALLTYPE=2;
    public static final int CRICKETTYPE=3;
    public static final int WALKTYPE=4;
    public static final int OTHERTYPE=5;
    public static final int ACTIVE=0;
    public static final int RUNNING=1;
    public static final int CANCELLED=2;
    public static final int FINISHED=3;

    public String key;
    public Day day;
    public int hour;
    public int min;
    public String title;
    public String description;
    public String creatorID;
    public String creatorName;

    public int type;
    public double distanceInMeters;
    public long durationInSec;
    public int status;

    public Event(Day day, int hour, int min, String creatorID, String creatorName, int type, long durationInSec, String title, String description) {
        this.day = day;
        this.hour=hour;
        this.min=min;
        this.creatorID = creatorID;
        this.creatorName=creatorName;

        this.type = type;
        this.durationInSec = durationInSec;
        this.title=title;
        this.description=description;
        this.status=Event.ACTIVE;
    }

    public Event(Day day, int hour, int min, String creatorID, String creatorName , int type, double distanceInMeters, long durationInSec, String title, String description) {
        this.day = day;
        this.hour=hour;
        this.min=min;
        this.creatorID = creatorID;
        this.creatorName=creatorName;

        this.type = type;
        this.distanceInMeters = distanceInMeters;
        this.durationInSec = durationInSec;
        this.title=title;
        this.description=description;
        this.status=Event.ACTIVE;
    }

    public Event(String jsonStr) {

        JsonObjectParser jsonObjectParser=new JsonObjectParser(jsonStr);
        this.type=jsonObjectParser.getInt("type");
        this.hour=jsonObjectParser.getInt("hour");
        this.min=jsonObjectParser.getInt("min");
        this.title=jsonObjectParser.getString("title");
        this.description=jsonObjectParser.getString("description");
        this.creatorName=jsonObjectParser.getString("creatorName");
        this.day=new Day(jsonObjectParser.getString("day"));
        this.status=jsonObjectParser.getInt("status");
        this.creatorID=jsonObjectParser.getString("creatorID");
        this.durationInSec=jsonObjectParser.getLong("durationInSec");

        if(this.status!=Event.CANCELLED)
            this.status=getStatus();



    }

    public int getStatus(){
        if(this.status==Event.CANCELLED)
            return Event.CANCELLED;

        Calendar cal = Calendar.getInstance();
        int mincount=this.min-cal.get(Calendar.MINUTE);
        int hourcount=this.hour-cal.get(Calendar.HOUR_OF_DAY);
        int daycount=this.day.day-cal.get(Calendar.DAY_OF_MONTH);
        int monthcount=(this.day.month-1)-cal.get(Calendar.MONTH);
        int yearcount=this.day.year-cal.get(Calendar.YEAR);

        cal.add(Calendar.MINUTE,mincount);
        cal.add(Calendar.HOUR_OF_DAY,hourcount);
        cal.add(Calendar.DAY_OF_MONTH,daycount);
        cal.add(Calendar.MONTH,monthcount);
        cal.add(Calendar.YEAR,yearcount);

        if(cal.compareTo(Calendar.getInstance())>0)
        {
            return Event.ACTIVE;
        }
        cal.add(Calendar.SECOND,(int)this.durationInSec);

        if(cal.compareTo(Calendar.getInstance())>0)
        {
            return Event.RUNNING;
        }
        return Event.FINISHED;
    }

    @Override
    public String toString() {
        return "Event{" +
                "key='" + key + '\'' +
                ", day=" + day +
                ", hour=" + hour +
                ", min=" + min +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", creatorID='" + creatorID + '\'' +
                ", creatorName='" + creatorName + '\'' +

                ", type=" + type +
                ", distanceInMeters=" + distanceInMeters +
                ", durationInSec=" + durationInSec +
                ", status=" + status +
                '}';
    }
}
