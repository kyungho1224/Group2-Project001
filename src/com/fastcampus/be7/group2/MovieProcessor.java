package com.fastcampus.be7.group2;

import com.fastcampus.be7.group2.model.MovieDTO;

import java.util.Scanner;

/**
 * Created by KimKyungHo on 2023-12-01(001)
 */

/* 메인 클래스 */
public class MovieProcessor {

    public static void main(String[] args) {

        // 기능 구현 클래스
        MovieOperations movieOperations = new MovieOperations();

        while (true) {

            System.out.println("========== 장르별 영화 검색 프로그램(2조-잠은 죽어서 자야조) ==========");
            System.out.println("1.영화입력(I)\t2.영화출력(P)\t3.영화검색(S)\t4.영화예매(R)\t5.종료(E)");
            System.out.println("===============================================================");
            System.out.print("메뉴입력 : ");

            // 사용자 입력을 받을 스캐너
            Scanner scanner = new Scanner(System.in);
            String menu = scanner.nextLine();

            if (menu.equalsIgnoreCase("I")) {
                // TODO 영화입력

                if (movieOperations.getMaxMovieCount() == 0) {
                    // - 먼저, 영화 데이터의 수를 입력하고
                    System.out.print("영화 데이터의 수를 입력해주세요 : ");
                    movieOperations.initMovie(scanner.nextInt());
                    scanner.nextLine();
                }

                // MovieDTO 배열을 사용하여 각 영화의 세부 사항을 저장합니다.
                System.out.print("제목을 입력해주세요 : ");
                String title = scanner.nextLine();
                if (title.equals("-1")) {
                    System.out.println("영화 입력을 중단합니다.");
                    continue;
                }
                System.out.print("주인공을 입력해주세요 : ");
                String major = scanner.nextLine();
                if (major.equals("-1")) {
                    System.out.println("영화 입력을 중단합니다.");
                    continue;
                }
                System.out.print("상영시간을 입력해주세요 : ");
                int runningTime = scanner.nextInt();
                scanner.nextLine();
                if (runningTime == -1) {
                    System.out.println("영화 입력을 중단합니다.");
                    continue;
                }
                System.out.print("평점을 입력해주세요 : ");
                float rating = scanner.nextFloat();
                scanner.nextLine();
                if ((int) rating == -1) {
                    System.out.println("영화 입력을 중단합니다.");
                    continue;
                }
                System.out.print("장르를 입력해주세요 : ");
                String genre = scanner.nextLine();
                if (genre.equals("-1")) {
                    System.out.println("영화 입력을 중단합니다.");
                    continue;
                }

                // 최초에 지정한 영화 데이터의 수를 넘어가면 배열을 늘린다.
                if (movieOperations.getMaxMovieCount() == movieOperations.getCurrentMovieCount()) {
                    movieOperations.increaseMaxCount();
                }
                // MovieDTO 저장
                MovieDTO movieDTO = new MovieDTO(title, major, runningTime, rating, genre);
                movieOperations.addMovie(movieDTO);
                System.out.println("영화 저장이 완료되었습니다.");
                System.out.println("총 " + movieOperations.getCurrentMovieCount() + "개의 영화가 저장되어 있습니다.");
                System.out.println();

            } else if (menu.equalsIgnoreCase("P")) {
                // TODO 영화출력

                // 저장된 영화가 없을 때 영화출력을 요청했을 때 처리
                if (movieOperations.getCurrentMovieCount() > 0) {
                    System.out.println("총 " + movieOperations.getCurrentMovieCount() + "개의 영화가 있습니다.");
                    MovieDTO[] movies = movieOperations.getMovieList();
                    for (MovieDTO movie : movies) {
                        if (movie != null) {
                            System.out.println(movie.printMovieInfo());
                        }
                    }
                } else {
                    System.out.println("저장된 영화가 없습니다!");
                }
                System.out.println();
            } else if (menu.equalsIgnoreCase("S")) {
                // TODO 영화 장르 검색

                while (true) {
                    if (movieOperations.getCurrentMovieCount() > 0) {
                        System.out.print("검색할 장르를 입력해주세요 : ");
                        String genre = scanner.nextLine();
                        if (genre.equals("1") || genre.equals("2") || genre.equals("3")) {
                            int searchCount = movieOperations.searchCount(genre);
                            if (searchCount == 0) {
                                System.out.println("선택하신 장르의 영화가 없습니다!");
                            } else {
                                System.out.println("요청하신 장르의 영화가 " + movieOperations.searchCount(genre) + "건 있습니다.");
                                MovieDTO[] movies = movieOperations.searchMovieByGenre(genre);
                                for (MovieDTO movie : movies) {
                                    System.out.println(movie.printMovieInfo());
                                }
                            }
                            System.out.println();
                            break;
                        } else {
                            System.out.println("장르의 종류는 1, 2, 3 중 하나를 입력해야 합니다!");
                            System.out.println();
                        }
                    } else {
                        System.out.println("저장된 영화가 없습니다!");
                        System.out.println();
                        break;
                    }
                }

            } else if (menu.equalsIgnoreCase("E") || menu.equals("-1")) {
                // TODO 종료

                System.out.println("프로그램을 종료합니다. 감사합니다!");
                System.out.println();
                break;
            } else if (menu.equalsIgnoreCase("R")) {
                // TODO 영화 예매

                if (movieOperations.getCurrentMovieCount() == 0) {
                    System.out.println("예매 가능한 영화가 없습니다!");
                    System.out.println();
                    continue;
                }
                while (true) {
                    System.out.println();
                    System.out.println("======== 영화 예매 ========");
                    System.out.println("1.예매하기\t2.예매내역확인\t3.뒤로가기");

                    System.out.print("메뉴입력 : ");

                    String menu2 = scanner.nextLine();

                    if (menu2.equals("1")) {
                        // TODO 예매하기

                        System.out.print("예매하실 영화의 제목을 입력해주세요 : ");
                        String movieName = scanner.nextLine();
                        int index = movieOperations.searchMovieByName(movieName);
                        if (index == -1) {
                            System.out.println("선택하신 영화가 없습니다!");
                            System.out.println();
                        } else {
                            System.out.print("예매하실 인원 수를 입력해주세요 : ");
                            char[] bookSeats = scanner.nextLine().toCharArray();
                            boolean isdigit = true;

                            for (int i = 0; i < bookSeats.length; i++) {
                                if (!Character.isDigit(bookSeats[i])) {
                                    System.out.println("잘못된 입력입니다.");
                                    System.out.println();
                                    isdigit = false;
                                    break;
                                }
                            }

                            if (isdigit) {
                                MovieDTO[] movies = movieOperations.getMovieList();
                                if (movies[index].getSeat() >= Integer.parseInt(String.valueOf(bookSeats))) {
                                    movies[index].setSeat(movies[index].getSeat() - Integer.parseInt(String.valueOf(bookSeats)));
                                    System.out.println("영화 예매가 완료되었습니다.");
                                    System.out.println();
                                } else {
                                    System.out.println("잔여 좌석이 부족합니다.");
                                    System.out.println();
                                }
                            }
                        }


                    } else if (menu2.equals("2")) {
                        // TODO 예매내역확인

                        MovieDTO[] movies = movieOperations.getMovieList();
                        boolean check = false;
                        for (MovieDTO movie : movies) {
                            if (movie != null && movie.getSeat() != 100) {
                                System.out.println(movie.printMovieInfo());
                                System.out.println("예매한 좌석 수 : " + (100 - movie.getSeat()));
                                System.out.println();
                                check = true;
                            }
                        }
                        if (!check) {
                            System.out.println("현재 예매된 영화가 없습니다.");
                            System.out.println();
                        }
                    } else if (menu2.equals("3")) {
                        // TODO 뒤로가기

                        System.out.println();
                        break;
                    } else {
                        System.out.println("잘못된 입력입니다. 메뉴를 다시 확인하고 입력해주세요.");
                        System.out.println();
                    }
                }
            } else {
                // TODO 메뉴에 없는 입력을 받았을 때 오류 처리
                System.out.println("잘못된 입력입니다. 메뉴를 다시 확인하고 입력해주세요.");
                System.out.println();
            }
        }
    }
}
