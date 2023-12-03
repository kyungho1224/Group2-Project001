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
    private int genre;

    public MovieDTO() {
    }

    public MovieDTO(String title, String major, int runningTime, float rating, int genre) {
        this.title = title;
        this.major = major;
        this.runningTime = runningTime;
        this.rating = rating;
        this.genre = genre;
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

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public String printMovieInfo() {
        String header = "************************";
        String body = "영화제목: " + title + "\n주인공: " + major + "\n상영시간: " + runningTime + "\n평점: " + rating + "\n장르: " + genre;
        String footer = "************************";
        return header + "\n" + body + "\n" + footer;
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "title='" + title + '\'' +
                ", major='" + major + '\'' +
                ", runningTime=" + runningTime +
                ", rating=" + rating +
                ", genre=" + genre +
                '}';
    }

}
