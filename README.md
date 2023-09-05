# FilmQueryProject

## Description
Welcome to the Film Query App! Users can access details of films stored in a database. When the application begins, users are prompted with three options:
1. Look Up Film by ID
2. Look Up Film(s) by Keyword
3. Exit  

Choosing option 1 prompts the user to enter the film ID. If a film with the user-supplied ID exists in the database, the following film details will print:
- Title
- Release Year
- Rating
- Language
- Category
- Description
- List of Actors

Choosing option 2 prompts the user to enter the search keyword. If a film with the user-supplied keyword exists in the database, the same film details listed above will print for all films matching the keyword anywhere in the Title or Description.

In both cases, after the simple details are displayed, the user is prompted with a submenu:
1. Return to Main Menu
2. View All Film Details

Users can return to the Main Menu by pressing option 1, or view the following film details by pressing option 2:

- Film ID
- Title
- Release Year
- Language ID
- Rental Duration
- Rental Rate
- Length
- Replacement Cost
- Rating
- Special Features
- Description
- List of all copies of the film in Inventory, with their condition

## Lessons Learned
This project presented a unique opportunity to understand and navigate relational databases as they relate to my current understanding of Java Applications. The biggest hurdle I had to jump was writing my DAO methods in such a way that they returned objects from the Classes in my project. Initially, my methods for finding the language and category of particular films returned type String:
	
	public String findFilmLanguage(int filmID);

	public String findFilmCategory(int filmID);
	
Why doesn't this work? Well, *technically*, it does work. I could write a SQL statement to access just the name of the language or category for a particular film's ID and return that value as a String, then use a setter to set the data to an instance of the film. A better question is: why shouldn't we do this? The purpose of the DAO is to hide the database implementation from the rest of the application. To accomplish this, the DAO methods should only return instances.

When I began to tackle changing this code, I wrote my methods as such:

	@Override
	public Film findFilmLanguage(int filmID) {
		Film film = null;
		String language = "";

		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);

			String sql = "select l.name from language l join film f on l.id = f.language_id where f.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmID);

			ResultSet langResult = stmt.executeQuery();
			if (langResult.next()) {
				language = langResult.getString("name");
				film = new Film();
				film.setLanguage(language);
			}
			langResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return film;
	}
	
To use this method, my code in the FilmQueryApp read:

	film.setLanguage(db.findFilmLanguage(film.getId()).getLanguage());
	
What's the issue here? The method is returning an instance of Film - this works, right? Well again, *technically*, yes it assigned the correct language to a film; however, if I had left my code this way, I essentially created a new instance of a random Film with the language I needed, used a getter to get the String from the random film, and set that String to an instance of a Film I was already using. By the end of the application, I would've created several "dummy" instances just floating around in my code.

Eventually, I wrote my methods in a way that passed in an already created instance of the Film. The DAO method retrieved the correct language or category, and set that field to the Film passed in, returning the same instance of the film with the new field attributed.

## Technologies Used
- Java
- Eclipse
- MySQL
- MAMP
- Maven
- XML
- Git
- GitHub
