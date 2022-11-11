package Boundary;

import Controller.MovieController;
import Controller.SystemSettingsController;
import Entity.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Runnable app for MovieGoer to list top 5 movies by sales or rating (specified by admin).
 * @author Tan Chuan Liang
 * @version 1.0
 * @since 2022-11-10
 */
public class ListTopMoviesApp {
	MovieController movieController = new MovieController();
	SystemSettingsController systemSettingsController = new SystemSettingsController();

	/**
	 * Main runnable function for MovieGoer to list top 5 movies by sales or rating (specified by admin).
	 */
	public void main(){
		int toggle = systemSettingsController.read().getMovieDisplayToggle();
		if(toggle == 0){
			System.out.println("Top 5 movies by sales:");
			ArrayList<Movie> movieList = movieController.read();
			Collections.sort(movieList, new Comparator<Movie>(){
				public int compare(Movie o1, Movie o2){
					if(o1.getTotalSales() == o2.getTotalSales())
						return 0;
					return o1.getTotalSales() > o2.getTotalSales() ? -1 : 1;
				}
			});
			int i = 1;
			for(Movie movie : movieList){
				if(movie.getStatus() != Movie.ShowStatus.ENDOFSHOWING){
					System.out.println(i + ": " + movie.getTitle() + " (" +movie.getRating() + ") - $" + movie.getTotalSales());
					i++;
				}
				if(i>=5){
					break;
				}
			}
		} else if(toggle == 1){
			System.out.println("Top 5 movies by rating:");
			ArrayList<Movie> movieList = movieController.read();
			Collections.sort(movieList, new Comparator<Movie>(){
				public int compare(Movie o1, Movie o2){
					if(o1.getOverallReviewScore() == o2.getOverallReviewScore())
						return 0;
					return o1.getOverallReviewScore() > o2.getOverallReviewScore() ? -1 : 1;
				}
			});
			int i = 1;
			for(Movie movie : movieList){
				if(movie.getStatus() != Movie.ShowStatus.ENDOFSHOWING){
					String overallScore;
					if(movie.getOverallReviewScore() == 0.0){
						overallScore = "NA";
					} else {
						overallScore = String.valueOf(movie.getOverallReviewScore());
					}
					System.out.println(i + ": " + movie.getTitle() + " (" +movie.getRating() + ") - Rating " + overallScore);
					i++;
				}
				if(i>=5){
					break;
				}
			}
		} else {
			//
		}

	}
}
