package Boundary;

import Controller.*;
import Entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This is the main class.
 * @author Tan Chuan Liang
 * @version 1.2
 * @since 2022-11-10
 */
class MainApp {
	/**
	 * The main menu. Shows choices for type of user (Movie Goer or Admin Staff)
	 *
	 * @param args The arguments passed into main.
	 */
	public static void main(String[] args) {
		CineplexController cineplexController = new CineplexController();
		SeatListController seatListController = new SeatListController();
		MovieController movieController = new MovieController();
		ShowTimeController showTimeController = new ShowTimeController();
		UserController userController = new UserController();
		TicketPriceController ticketPriceController = new TicketPriceController();
		HolidayController holidayController = new HolidayController();
		SystemSettingsController systemSettingsController = new SystemSettingsController();


		try{
			ArrayList<Cinema> orchardCinList= new ArrayList<Cinema>();
			orchardCinList.add(new Cinema("scr1", "ORCHASCRN1"));
			orchardCinList.add(new CinemaPlatinumSuite("scr2", "ORCHASCRN2"));
			orchardCinList.add(new Cinema3D("scr3", "ORCHASCRN3"));
			cineplexController.createCineplex("Orchard Cineplex", orchardCinList);
			ArrayList<Cinema> causewayCinList= new ArrayList<Cinema>();
			causewayCinList.add(new Cinema("scr1", "CAUSESCRN1"));
			causewayCinList.add(new Cinema("scr2", "CAUSESCRN2"));
			causewayCinList.add(new Cinema("scr3", "CAUSESCRN3"));
			cineplexController.createCineplex("Orchard Cineplex", causewayCinList);
			ArrayList<Cinema> amkCinList= new ArrayList<Cinema>();
			amkCinList.add(new Cinema("scr1", "AMKHUSCRN1"));
			amkCinList.add(new Cinema("scr2", "AMKHUSCRN2"));
			amkCinList.add(new Cinema("scr3", "AMKHUSCRN3"));
			cineplexController.createCineplex("Orchard Cineplex", amkCinList);
			ArrayList<Cinema> downtownEastCinList= new ArrayList<Cinema>();
			downtownEastCinList.add(new Cinema("scr1", "DNTNESCRN1"));
			downtownEastCinList.add(new Cinema("scr2", "DNTNESCRN2"));
			downtownEastCinList.add(new Cinema("scr3", "DNTNESCRN3"));
			cineplexController.createCineplex("Orchard Cineplex", downtownEastCinList);

			//System.out.println(cineplexController.read().get(0).toString());
		} catch (Exception e){
			//System.out.println(e.getMessage());
		}

		try {
			movieController.addMovie("Jaws", Movie.ShowStatus.NOWSHOWING, "Shark eats man", "Steven Spielberg",
					new String[]{"Roy Scheider", "Robert Shaw", "Richard Dreyluss"}, Movie.MovieType.TYPE_REGULAR, 124, Movie.MovieRating.PG);

			movieController.addMovie("Jaws 2", Movie.ShowStatus.NOWSHOWING, "Shark eats man", "Steven Spielberg",
					new String[]{"Roy Scheider", "Robert Shaw", "Richard Dreyluss"}, Movie.MovieType.TYPE_BLOCKBUSTER, 124, Movie.MovieRating.PG13);

			movieController.addReview("Jaws", "TCL","good",4);
			movieController.addReview("Jaws", "KEK","bad",2);
			//System.out.println(movieController.read().get(0).toString());
			//movieController.changeMovieStatus("Jaws", Movie.ShowStatus.ENDOFSHOWING);
			//movieController.getFullMovieCatalog();
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}

		try {
			showTimeController.createShowtime("Jaws", LocalDateTime.parse("2022-12-03T10:15:30"), "ORCHASCRN1");
			showTimeController.createShowtime("Jaws 2", LocalDateTime.parse("2022-12-03T19:15:30"), "ORCHASCRN1");
			//System.out.println(showTimeController.read().get(0).toString());

			showTimeController.updateSeatStatus(showTimeController.read().get(1), showTimeController.read().get(1).getShowSeatPlan().bookSeat('B', 2));
			showTimeController.getSeatingForShowtime(1);

			//showTimeController.getAllShowTimesForMovie("Jaws");
			//System.out.println(showTimeController.getShowTime("Jaws", LocalDateTime.parse("2022-12-03T10:15:30"), "ORCHASCRN1").getMovie().toString());
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}

		try{
			userController.addStaff("admin", "admin");
			userController.addMovieGoer("watcher", "password", "Tan CL", 91234567, "a@b.com", 22, new ArrayList<Booking>());
			//System.out.println(userController.read().get(0));
			ticketPriceController.initialisePricesList();
			holidayController.initialiseHolidays();
			systemSettingsController.initialiseSettings();
			//System.out.println("End of init");
		} catch (Exception e){
			//System.out.println(e.getMessage());
		}


		boolean isRunning = true;
		while (isRunning) {
			System.out.println("MOBLIMA MAIN MENU");
			System.out.println("""
					Available actions:
					1. Movie Goer login
					2. Admin Staff login
					3. Exit application
					Enter your selection:\040""");
			switch (ScannerController.getInputInt()) {
				case 1 -> MovieGoerMenu();
				case 2 -> StaffMenu();
				case 3 -> {
					// --- Insert deletion functions below to remove saved information on app exit ---
					//showTimeController.deleteShowtime("Jaws", LocalDateTime.parse("2022-12-03T10:15:30"), "ORCHASCRN1");
					//showTimeController.deleteShowtime("Jaws 2", LocalDateTime.parse("2022-12-03T19:15:30"), "ORCHASCRN1");

					//movieController.deleteMovie("Jaws");
					//movieController.deleteMovie("Jaws 2");

					//cineplexController.deleteCineplex("Orchard Cineplex");
					isRunning = false;
					System.out.println("Exiting...");
				}
				default -> System.out.println("Please enter a valid selection");
			}
		}
		ScannerController.closeScanner();
	}

	/**
	 * The user menu for Movie Goer login. Shows user menu for Movie Goer actions.
	 * Asks for MovieGoer username and password with maximum of "wrongCount" tries.
	 * If password is correct, show the user menu for MovieGoer actions
	 */
	public static void MovieGoerMenu() {
		UserController userController = new UserController();
		int wrongCount = 3;
		boolean isRunning = true;
		boolean isLoginRunning = true;
		boolean hasAccess = false;
		String tempUsername = "";
		String tempPassword = "";
		MovieGoer currentUser = null;
		while (isLoginRunning) {
			System.out.println("MOBLIMA MOVIE GOER LOGIN");
			System.out.println("Please enter username: ");
			tempUsername = ScannerController.getInputString();
			System.out.println("Please enter password: ");
			tempPassword = ScannerController.getInputString();
			if(userController.isValidLogin(tempUsername, tempPassword) && !(userController.isStaff(tempUsername))) {
				currentUser = (MovieGoer) userController.getUserByUsername(tempUsername);
				System.out.println("Access granted.");
				hasAccess = true;
				isLoginRunning = false;
			} else {
				System.out.println("Wrong username or password");
				if(--wrongCount <= 0){
					System.out.println("Too many tries, exiting to main menu...");
					isLoginRunning = false;
					break;
				}
			}
		}
		if(hasAccess){
			while (isRunning) {
				System.out.println("MOBLIMA MOVIE GOER MENU");
				System.out.println("""
					Available actions:
					1. List movies & view movie details
					2. Check showtimes and seat availability
					3. Book and purchase ticket
					4. View booking history
					5. List top 5 movies by sales or ratings
					6. Exit to main menu
					Enter your selection:\040""");
				switch (ScannerController.getInputInt()) {
					case 1:
						ListMovieApp listMovieApp = new ListMovieApp();
						listMovieApp.main(currentUser);
						break;
					case 2:
						CheckSeatsApp checkSeatsApp = new CheckSeatsApp();
						checkSeatsApp.main();
						break;
					case 3:
						BookSeatsApp bookSeatsApp = new BookSeatsApp();
						bookSeatsApp.main(currentUser);
						break;
					case 4:
						ListBookingApp listBookingApp = new ListBookingApp();
						listBookingApp.main(currentUser);
						break;
					case 5:
						ListTopMoviesApp listTopMoviesApp = new ListTopMoviesApp();
						listTopMoviesApp.main();
						break;
					case 6:
						isRunning = false;
						System.out.println("Exiting Movie Goer...");
						break;
					default:
						System.out.println("Please enter a valid selection");
				}
			}
		}
	}

	/**
	 * The user menu for staff login. Asks for Staff username and password with maximum of "wrongCount" tries.
	 * If username and password is correct, show the user menu for staff actions
	 */
	public static void StaffMenu() {
		UserController userController = new UserController();
		int wrongCount = 3;
		boolean isRunning = true;
		boolean isLoginRunning = true;
		boolean hasAccess = false;
		String tempUsername = "";
		String tempPassword = "";
		while (isLoginRunning) {
			System.out.println("MOBLIMA ADMIN STAFF LOGIN");
			System.out.println("Please enter username: ");
			tempUsername = ScannerController.getInputString();
			System.out.println("Please enter password: ");
			tempPassword = ScannerController.getInputString();
			if(userController.isValidLogin(tempUsername, tempPassword) && userController.isStaff(tempUsername)) {
				System.out.println("Access granted.");
				hasAccess = true;
				isLoginRunning = false;
			} else {
				System.out.println("Wrong username or password, or not admin account");
				if(--wrongCount <= 0){
				System.out.println("Too many tries, exiting to main menu...");
				isLoginRunning = false;
				break;
				}
			}
		}

		if (hasAccess) {
			while (isRunning) {
				System.out.println("MOBLIMA ADMIN STAFF MENU");
				System.out.println("""
						Available actions:
						1. Create/Update/Remove movie listing
						2. Create/Update/Remove show times and movies
						3. Configure system settings
						4. Exit
						Enter your selection:\040""");
				switch (ScannerController.getInputInt()) {
					case 1:
						EditMovieApp editMovieApp = new EditMovieApp();
						editMovieApp.main();
						break;
					case 2:
						EditShowtimeApp editShowtimeApp = new EditShowtimeApp();
						editShowtimeApp.main();
						break;
					case 3:
						SystemSettingsApp systemSettingsApp = new SystemSettingsApp();
						systemSettingsApp.main();
						break;
					case 4:
						isRunning = false;
						System.out.println("Exiting Admin Staff...");
						break;
					default:
						System.out.println("Please enter a valid selection");
				}
			}
		}
	}
}