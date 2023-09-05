package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
	boolean quit;
	Scanner sc = new Scanner(System.in);

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

	private void test() throws SQLException {
//    Film film = db.findFilmById(1);
//    System.out.println(film);
//    Actor actor = db.findActorById(1);
//    System.out.println(actor);
//	  List<Actor> actors = db.findActorsByFilmId(1);
//	  for (Actor actor : actors) {
//		  System.out.println(actor);
//	}
//	  
//	  List<Film> films = db.findFilmsByKeyword("zoo");
//	  for (Film film : films) {
//		  System.out.println(film);
//	}

//	 System.out.println( db.findFilmLanguage(13));
	}

	private void launch() {
		startUserInterface();
		sc.close();
	}

	private void startUserInterface() {
		System.out.println("Welcome to the Film Query App!");
		do {
			menu();
			int choice = sc.nextInt();
			sc.nextLine(); // flush

			menuSwitch(choice);
		} while (!quit);

	}

	private void menu() {
		System.out.println("+---------------------------------+");
		System.out.println("|              MENU               |");
		System.out.println("+---------------------------------+");
		System.out.println("| 1. Look Up Film by ID           |");
		System.out.println("| 2. Look Up Film(s) by Keyword   |");
		System.out.println("| 3. Exit                         |");
		System.out.println("+---------------------------------+");
		System.out.print("Choose: ");
	}

	private void menuSwitch(int choice) {
		switch (choice) {
		case 1:
			// look up film by id
			Film filmById = filmByID();
			if (filmById == null) {
				System.err.println("Film ID does not exist.");
				startUserInterface();
			} else {
				setFilmDetails(filmById);
				System.out.println(filmById.simpleDisplay());
				seeAllSingle(filmById);
			}
			break;
		case 2:
			// look up film by keyword
			List<Film> films = filmsByKeyword();
			if (films.size() == 0) {
				System.err.println("No films matched your search.");
				startUserInterface();
			} else {
				for (Film film : films) {
					setFilmDetails(film);
					System.out.println(film.simpleDisplay());
				}

				seeAllList(films);
			}
			break;
		case 3:
			// exit
			System.out.println("Thanks for using the Film Query App. Goodbye!");
			quit = true;
			break;
		default:
			System.err.println("Invalid Input. Please try again.");
			break;
		}
	}

	private Film filmByID() {
		System.out.print("Enter Film ID: ");
		int id = sc.nextInt();
		sc.nextLine(); // flush

		return db.findFilmById(id);

	}

	private List<Film> filmsByKeyword() {
		System.out.print("Enter Keyword: ");
		String keyword = sc.nextLine();

		return db.findFilmsByKeyword(keyword);

	}

	private void setFilmDetails(Film film) {
		film.setLanguage(db.findFilmLanguage(film.getId()).getLanguage());
		film.setActors(db.findActorsByFilmId(film.getId()));
		film.setCategory(db.findFilmCategory(film.getId()).getCategory());
		film.setInventory(db.findFilmInventory(film.getId()));
	}

	private void seeAllSingle(Film film) {
		System.out.println("1. Return to Main Menu");
		System.out.println("2. View All Film Details");
		System.out.print("Choose: ");
		int choice = sc.nextInt();
		sc.nextLine(); // flush

		switch (choice) {
		case 1:
			// return to main menu
			startUserInterface();
			break;
		case 2:
			// see all film details
			System.out.println(film.fullDetails());
			break;
		default:
			System.err.println("Invalid Input. Please Try again.");
			break;
		}
	}

	private void seeAllList(List<Film> films) {
		System.out.println("1. Return to Main Menu");
		System.out.println("2. View All Film Details");
		System.out.print("Choose: ");
		int choice = sc.nextInt();
		sc.nextLine(); // flush

		switch (choice) {
		case 1:
			// return to main menu
			startUserInterface();
			break;
		case 2:
			// see all film details
			for (Film film : films) {

				System.out.println(film.fullDetails());
			}
			break;
		default:
			System.err.println("Invalid Input. Please Try again.");
			break;
		}
	}

}
