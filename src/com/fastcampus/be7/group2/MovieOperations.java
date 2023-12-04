package com.fastcampus.be7.group2;

import com.fastcampus.be7.group2.model.MovieDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KimKyungHo on 2023-12-01(001)
 */

/* 기능 구현 클래스 */
public class MovieOperations {

    private MovieDTO[] movies;
    private List<MovieDTO> reservationList;
    private int maxMovieCount = 0;  // 저장할 영화의 최대 개수
    private int currentMovieCount = 0;  // 현재 저장된 영화 개수

    public MovieOperations() {
    }

    /**
     * 영화의 데이터 수를 입력하고...라는 명세서 내용때문에 초기화하는 부분
     *
     * @param movieCount 최초 영화 데이터의 수를 입력받아 배열을 초기화 하기 위한 숫자
     * @author KimKyungHo
     */
    public void initMovie(int movieCount) {
        // - 먼저, 영화 데이터의 수를 입력하고
        maxMovieCount = movieCount;
        movies = new MovieDTO[movieCount];
        reservationList = new ArrayList<>();
    }

    /**
     * @return 영화 데이터의 최대 개수
     * @author KimKyungHo
     */
    public int getMaxMovieCount() {
        return maxMovieCount;
    }

    /**
     * @author KimKyungHo
     * 최초 설정한 영화 데이터의 개수보다 더 많은 데이터를 입력받게 되면 최대 개수를 늘린다
     */
    public void increaseMaxCount() {
        maxMovieCount++;
        MovieDTO[] afterMovies = new MovieDTO[maxMovieCount];
        for (int i = 0; i < movies.length; i++) {
            afterMovies[i] = movies[i];
        }
        movies = afterMovies;
    }

    /**
     * @return 현재까지 입력된 영화 데이터의 개수
     * @author KimKyungHo
     */
    public int getCurrentMovieCount() {
        return currentMovieCount;
    }

    /**
     * @param movie 영화 데이터의 상세정보를 입력받아 저장하고
     *              현재까지 입력된 영화 데이터의 개수를 늘린다
     * @author KimKyungHo
     */
    public void addMovie(MovieDTO movie) {
        movies[currentMovieCount] = movie;
        currentMovieCount++;
    }

    /**
     * @return 영화 데이터의 목록
     * 현재까지 입력된 영화의 수가 2개 이상이라면 내림차순 정리하여 값을 넘겨주고
     * 1개라면 정렬하지 않고 그냥 넘겨준다
     * @author KimKyungHo
     */
    public MovieDTO[] getMovieList() {
        if (currentMovieCount > 1) {
            return sortMovieList();
        }
        return movies;
    }

    /**
     * @return 영화 데이터를 내림차순으로 정렬한 목록
     * @author KimKyungHo
     */
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

    /**
     * @param genre 검색 기준이 될 장르를 입력받는다
     * @return 저장된 영화 데이터 목록 중 입력받은 장르에 해당하는 영화의 개수
     * @author KimKyungHo
     * @see #searchMovieByGenre
     */
    public int searchCount(String genre) {
        int count = 0;
        for (MovieDTO movie : movies) {
            if (movie != null) {
                if (movie.getGenre().equals(genre)) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * @param genre 검색 기준이 될 장르를 입력받는다
     * @return 저장된 영화 데이터 목록 중 입력받은 장르에 해당하는 영화의 목록
     * @author KimKyungHo
     * @see #searchCount
     */
    public MovieDTO[] searchMovieByGenre(String genre) {
        MovieDTO[] searchMovies = new MovieDTO[searchCount(genre)];
        int index = 0;
        for (MovieDTO movie : movies) {
            if (movie != null) {
                if (movie.getGenre().equals(genre)) {
                    searchMovies[index] = movie;
                    index++;
                }
            }
        }
        return searchMovies;
    }

    /**
     * @param title 예매를 원하는 영화의 제목
     * @return 입력받은 영화의 제목이 영화 데이터 목록에 있다면 true, 없다면 false
     */
    public boolean isContain(String title) {
        for (MovieDTO movie : movies) {
            if (movie != null) {
                if (movie.getTitle().equals(title)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param title 예매를 원하는 영화의 제목
     * @return 입력받은 영화의 제목을 가진 movieDTO
     */
    public MovieDTO getChoiceMovie(String title) {
        for (MovieDTO movie : movies) {
            if (movie != null) {
                if (movie.getTitle().equals(title)) {
                    return movie;
                }
            }
        }
        return null;
    }

    /**
     * 예매완료 후 영화정보의 데이터를 업데이트 하는 메서드
     * 1. 예매목록에는 예매영화의 정보와 예약좌석의 수를 추가한다
     * 2. 영화목록에서는 잔여좌석에서 예약좌석의 수만큼 뺀 후 목록의 내용을 변경한다
     *
     * @param movie 예매 영화
     * @param numberOfPeople 예매 인원수
     */
    public void updateMovieData(MovieDTO movie, int numberOfPeople) {
        int remainingSeat = movie.getSeat();
        movie.setSeat(remainingSeat - numberOfPeople);

        reservationList.add(new MovieDTO(movie.getTitle(), movie.getMajor(), movie.getRunningTime(), movie.getRating(), movie.getGenre(), numberOfPeople));
        int index = -1;
        for (int i = 0; i < movies.length; i++) {
            if (movies[i] != null) {
                if (movies[i].getTitle().equals(movie.getTitle())) {
                    index = i;
                }
            }
        }
        if (index > -1) {
            movies[index] = movie;
        }
    }

    /**
     * @return 예매한 영화의 목록의 수
     */
    public int getReservationCount() {
        return reservationList.size();
    }

    /**
     * 가독성을 위해 예매목록의 마지막 영화가 아닐때만 개행문자를 추가한다
     * @return 예매한 영화의 정보 출력을 위해 문자열을 돌려준다
     */
    public String getReservationHistory() {
        String history = "";

        for (int i = 0; i < reservationList.size(); i++) {
            history += "영화제목: " + reservationList.get(i).getTitle()
                    + "\t주인공: " + reservationList.get(i).getMajor()
                    + "\t상영시간: " + reservationList.get(i).getRunningTime()
                    + "\t평점: " + reservationList.get(i).getRating()
                    + "\t장르: " + reservationList.get(i).getGenre()
                    + "\t예약좌석: " + reservationList.get(i).getSeat();
            if (i < reservationList.size() - 1) {
                history = history + "\n";
            }
        }

        return history;
    }

}
