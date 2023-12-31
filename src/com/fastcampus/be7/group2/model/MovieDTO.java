package com.fastcampus.be7.group2.model;

/**
 * Created by KimKyungHo on 2023-12-01(001)
 */

/* Movie DTO */
public class MovieDTO {

    private String title;
    private String major;
    private int runningTime;
    private float rating;
    private String genre;
    private int seat;

    private MovieDTO() {
    }

    public MovieDTO(String title, String major, int runningTime, float rating, String genre) {
        this.title = title;
        this.major = major;
        this.runningTime = runningTime;
        this.rating = rating;
        this.genre = genre;
        this.seat = 100;
    }

    public MovieDTO(String title, String major, int runningTime, float rating, String genre, int seat) {
        this.title = title;
        this.major = major;
        this.runningTime = runningTime;
        this.rating = rating;
        this.genre = genre;
        this.seat = seat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    /**
     * @return 영화정보 출력
     */
    public String printMovieInfo() {
        String header = "************************";
        String body = "영화제목: " + title + "\n주인공: " + major + "\n상영시간: " + runningTime + "\n평점: " + rating + "\n장르: " + genre + "\n잔여좌석: " + seat;
        String footer = "************************";
        return header + "\n" + body + "\n" + footer;
    }

    public String printReservationInfo() {
        return "영화제목: " + title + "\t주인공: " + major + "\t상영시간: " + runningTime + "\t평점: " + rating + "\t장르: " + genre + "\t잔여좌석: " + seat;
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "title='" + title + '\'' +
                ", major='" + major + '\'' +
                ", runningTime=" + runningTime +
                ", rating=" + rating +
                ", genre='" + genre + '\'' +
                ", seat=" + seat +
                '}';
    }
}
