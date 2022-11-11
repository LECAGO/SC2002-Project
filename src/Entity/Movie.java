package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * This Movie class contains data about itself.
 * @author Tan Chuan Liang
 * @version 1.0
 * @since 2022-11-05
 */
public class Movie implements Serializable {
	private String title;
	private ShowStatus status;
	private String synopsis;
	private String director;
	private String[] cast;
	private ArrayList<Review> reviews;
	private double overallReviewScore;
	private MovieType type;
	private int duration;
	private MovieRating rating;
	double totalSales;

	/**
	 * Constructor for a Movie.
	 * @param title Title of movie.
	 * @param status Showing status of movie (preview, nowshowing, comingsoon, endofshowing).
	 * @param synopsis Synopsis of movie.
	 * @param director Director of movie.
	 * @param cast Cast of movie.
	 * @param type Type of movie (type_regular, type_3d, type_blockbuster).
	 * @param duration Duration of movie in minutes.
	 * @param rating Censorship rating of movie (G, PG13, PG, NC16, M18, R21)
	 */
	public Movie(String title, ShowStatus status, String synopsis, String director, String[] cast, MovieType type, int duration, MovieRating rating) {
		this.title = title;
		this.status = status;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		this.reviews = new ArrayList<Review>();	//by default, initialise with no reviews
		this.overallReviewScore = 0.0;
		this.type = type;
		this.duration = duration;
		this.rating = rating;
	}

	/**
	 * Enum containing values for movies' show status.
	 */
	public enum ShowStatus{
		PREVIEW("preview"),
		NOWSHOWING("nowshowing"),
		COMINGSOON("comingsoon"),
		ENDOFSHOWING("endofshowing");
		private final String text;
		ShowStatus(final String text){
			this.text = text;
		}
		public String toString(){
			return text;
		}
	}

	/**
	 * Enum containing values for type of movies.
	 */
	public enum MovieType{
		TYPE_REGULAR("regular"),
		TYPE_3D("3D"),
		TYPE_BLOCKBUSTER("blockbuster");
		private final String text;
		MovieType(final String text){
			this.text = text;
		}
		public String toString(){
			return text;
		}
	}

	/**
	 * Enum containing values for censorship rating of movie.
	 */
	public enum MovieRating{
		G("G"),
		PG("PG"),
		PG13("PG13"),
		NC16("NC16"),
		M18("M18"),
		R21("R21");
		private final String text;
		MovieRating(final String text){
			this.text = text;
		}
		public String toString(){
			return text;
		}
	}

	/**
	 * Get movie's title.
	 * @return Movie's title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set movie's title.
	 * @param title Movie's title.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get movie's showing status.
	 * @return Movie's showing status.
	 */
	public ShowStatus getStatus() {
		return status;
	}

	/**
	 * Set movie's showing status.
	 * @param status Movie's showing status
	 */
	public void setStatus(ShowStatus status) {
		this.status = status;
	}

	/**
	 * Get movie's synopsis.
	 * @return Movie's synopsis.
	 */
	public String getSynopsis() {
		return synopsis;
	}

	/**
	 * Set movie's synopsis.
	 * @param synopsis Movie's synopsis.
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	/**
	 * Get movie's director.
	 * @return Movie's director.
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Set movie's director.
	 * @param director Movie's director.
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * Get movie's cast.
	 * @return Movie's cast.
	 */
	public String[] getCast() {
		return cast;
	}

	/**
	 * Set movie's cast.
	 * @param cast Movie's cast.
	 */
	public void setCast(String[] cast) {
		this.cast = cast;
	}

	/**
	 * Get movie's reviews.
	 * @return Movie's reviews.
	 */
	public ArrayList<Review> getReviews() {
		return reviews;
	}

	/**
	 * Set movie's reviews.
	 * @param reviews Movie's reviews.
	 */
	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	/**
	 * Get movie's overall review score (1-5)
	 * @return Movie's overall review score.
	 */
	public double getOverallReviewScore() {
		return overallReviewScore;
	}

	/**
	 * Set movie's overall review score (1-5)
	 * @param overallReviewScore Movie's overall review score.
	 */
	public void setOverallReviewScore(double overallReviewScore) {
		this.overallReviewScore = overallReviewScore;
	}

	/**
	 * Get movie's type.
	 * @return Movie's type.
	 */
	public MovieType getType() {
		return type;
	}

	/**
	 * Set movie's type.
	 * @param type Movie's type.
	 */
	public void setType(MovieType type) {
		this.type = type;
	}

	/**
	 * Get movie's duration in minutes.
	 * @return Movie's duration in minutes.
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Set movie's duration in minutes.
	 * @param duration Movie's duration in minutes
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Get movie's censorship rating.
	 * @return Movie's censorship rating.
	 */
	public MovieRating getRating() {
		return rating;
	}

	/**
	 * Set movie's censorship rating.
	 * @param rating Movie's censorship rating.
	 */
	public void setRating(MovieRating rating) {
		this.rating = rating;
	}

	/**
	 * Get movie's total sales.
	 * @return Movie's total sales.
	 */
	public double getTotalSales() {
		return totalSales;
	}

	/**
	 * Set movie's total sales.
	 * @param totalSales Movie's total sales.
	 */
	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}

	/**
	 * Compile all the movie's details into a printable string.
	 * @return String containing all the movie's details.
	 */
	public String toString(){
		String reviewString;
		if(overallReviewScore==0.0){
			reviewString = "NA";
		} else {
			reviewString = String.valueOf(overallReviewScore);
		}
		String outputString = "";
		outputString = "Movie: " + title +  "\n" +
						" status: " + status + "\n" +
						" synopsis: " + synopsis + "\n" +
						" director: " + director + "\n" +
						" cast: " + Arrays.toString(cast) + "\n" +
						" type: " + type + "\n" +
						" rating: " + rating + "\n" +
						" total sales: " + totalSales + "\n" +
						" overall review score: " + reviewString + "\n" +
						" reviews: \n";
		if(reviews != null && reviews.size() != 0){
			for(Review rev : reviews){
				outputString += "  author: " + rev.getAuthor() + "\n"
				+ "  review: " + rev.getReviewText() + "\n"
				+ "  score: " + rev.getReviewScore() + "\n";
			}
		}
		return outputString;
	}
}
