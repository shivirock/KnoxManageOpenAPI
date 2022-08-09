package com.sspl.knoxmanageopenapi.volley.apirequest.models;

public class ClientProfileUpdated{
    public int date;
    public int hours;
    public int seconds;
    public int month;
    public int timezoneOffset;
    public int year;
    public int minutes;
    public long time;
    public int day;

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getTimezoneOffset() {
        return timezoneOffset;
    }

    public void setTimezoneOffset(int timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "ClientProfileUpdated{" +
                "date=" + date +
                ", hours=" + hours +
                ", seconds=" + seconds +
                ", month=" + month +
                ", timezoneOffset=" + timezoneOffset +
                ", year=" + year +
                ", minutes=" + minutes +
                ", time=" + time +
                ", day=" + day +
                '}';
    }
}

