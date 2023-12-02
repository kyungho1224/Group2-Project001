package com.fastcampus.be7.group2;

import com.fastcampus.be7.group2.model.MovieDTO;

/**
 * Created by KimKyungHo on 2023-12-01(001)
 */

/* 기능 구현 클래스 */
public class MovieProcessor {

    private MovieDTO[] movies;
    private int maxMovieCount = 0;  // 저장할 영화의 최대 개수
    private int currentMovieCount = 0;  // 현재 저장된 영화 개수

    public MovieProcessor() {
    }

    // 영화의 데이터 수를 입력하고...라는 명세서 내용때문에 초기화하는 부분
    public void initMovie(int movieCount) {
        // - 먼저, 영화 데이터의 수를 입력하고
        maxMovieCount = movieCount;
        movies = new MovieDTO[movieCount];
    }

    public int getMaxMovieCount() {
        return maxMovieCount;
    }

    public void increaseMaxCount() {
        maxMovieCount++;
        MovieDTO[] afterMovies = new MovieDTO[maxMovieCount];
        for (int i = 0; i < movies.length; i++) {
            afterMovies[i] = movies[i];
        }
        movies = afterMovies;
    }

    public int getCurrentMovieCount() {
        return currentMovieCount;
    }

    public void addMovie(MovieDTO movie) {
        movies[currentMovieCount] = movie;
        currentMovieCount++;
    }

    /**
     * 평점을 기준으로 내림차순 정렬하여 영화목록을 출력
     **/
    public MovieDTO[] getMovieList() {
        if (currentMovieCount > 1) {
            return sortMovieList();
        }
        return movies;
    }

    public MovieDTO[] sortMovieList() {
        for (int i = 0; i < currentMovieCount; i++) {
            for (int j = i + 1; j < currentMovieCount; j++) {
                if (movies[i].getRating() < movies[j].getRating()) {
                    MovieDTO tmpMovie;
                    tmpMovie = movies[i];
                    movies[i] = movies[j];
                    movies[j] = tmpMovie;
                }
            }
        }
        return movies;
    }

}
