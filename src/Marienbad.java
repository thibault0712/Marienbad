/**
* Code who allow to play a famous game called Marienbad
* @author T.FALEZAN
*/


class Marienbad{

	void principal(){
		launchHome();
	}
	
	/**
	* Home screen with interaction
	*/
	void launchHome(){
		clearDisplay();
		displayHome();
		String selectedParam = SimpleInput.getString("\u001B[34m[MENU PRINCIPAL]\u001B[0m Entrer votre sélection > ");
		
		while(selectedParam.equalsIgnoreCase("a") && selectedParam.equalsIgnoreCase("b")){
			clearDisplay();
			displayHome();
			System.out.println("ERREUR : Sélection mauvaise");
			selectedParam = SimpleInput.getString("\u001B[34m[MENU PRINCIPAL]\u001B[0m Entrer votre sélection > ");
		}

		if(selectedParam.equalsIgnoreCase("a")){
			launchGame();
		}else{
			testPlayerWon();
			SimpleInput.getString("\u001B[34m[MENU PRINCIPAL]\u001B[0m Appuyer sur ENTER pour relancer > ");
			launchHome();
		}
	}
	
	/**
	* Show the home screen without textInput
	*/
	void displayHome(){
		System.out.println("\n\n");
		System.out.println("\t\u001B[34m░▒▓██████████████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░░▒▓█▓▒░▒▓████████▓▒░▒▓███████▓▒░░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░ \n" +  
						 "\t\u001B[34m░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ \n" +
						 "\t\u001B[34m░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ \n" +
						 "\t\u001B[34m░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓████████▓▒░▒▓███████▓▒░░▒▓█▓▒░▒▓██████▓▒░ ░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░ \n" +
						 "\t\u001B[34m░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ \n" +
						 "\t\u001B[34m░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ \n" +
						 "\t\u001B[34m░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░ \n");
		System.out.println("\t\t\t\t\t\t\u001B[34mMade by : Gérémie LETHANG / Thibault FALEZAN\u001B[0m");
		System.out.println("\n\n");
		System.out.println("\t\t\t\t\t   a : \u001B[32mLancer le jeu\u001B[0m \t b : \u001B[32mLancer les tests unitaires\u001B[0m");
		System.out.println("\n\n");	
	}
	
	/**
	* Remove all text (clear console)
	*/
	void clearDisplay(){
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	/**
	* Create lines for the game, line number is set by players
	* @return a table with the stick number per lines
	*/
	int[] initializeGameLines(){
		int lineNumber = SimpleInput.getInt("Entrer le nombre de ligne à utiliser pour jouer : ");
		while(lineNumber < 2 || lineNumber > 15){
			clearDisplay();
			System.out.println("\u001B[41m ERREUR : \u001B[0m nombre de ligne incorrect, le nombre de ligne doit être compris entre 2 et 15");
			lineNumber = SimpleInput.getInt("Entrer le nombre de ligne à utiliser pour jouer : ");
		}
		
		int lineContent = 1;
		int[] linesContent = new int[lineNumber];
		for (int i = 0; i < lineNumber; i++){
			linesContent[i] = lineContent;
			lineContent += 2;
		}
		
		clearDisplay();
		
		return linesContent;
	}
	
	/**
	* Show sticks per lines
	* @param linesContent is a table with the stick number of all lines
	*/
	void displayLines(int[] linesContent, String playerName){
		System.out.println("Au tour de " + playerName + " de jouer : \n");
		for (int i = 0; i < linesContent.length; i++){
			System.out.print(i + " : ");
			final int stickNumber = linesContent[i];
			for(int j = 0; j < stickNumber; j++){
				System.out.print(" |");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	/**
	* Show sticks per lines with the line selected highlighted
	* @param linesContent is a table with the stick number of all lines
	*/
	void displaySelectedLines(int[] linesContent, String playerName, int selectedLine){
		System.out.println("Au tour de " + playerName + " de jouer : \n");
		for (int i = 0; i < linesContent.length; i++){
			if (i == selectedLine){
				System.out.print("\u001B[44m");
			}
			System.out.print(i + " : ");
			final int stickNumber = linesContent[i];
			for(int j = 0; j < stickNumber; j++){
				System.out.print(" |");
			}
			System.out.print("\u001B[0m\n");
		}
		System.out.print("\n");
	}
	
	/**
	* Verify if a player has won or not
	* @param linesContent is a table with the stick number of all lines
	* @return true -> player won | false -> any player won
	*/
	boolean playerWon(int[] linesContent){
		boolean win = false;
		int totalStickAvailable = 0;
		int i = 0;
		
		while(i < linesContent.length && totalStickAvailable <= 0){
			totalStickAvailable += linesContent[i];
			i += 1; 
		}
		
		if (totalStickAvailable == 0){
			win = true;
		}
		
		return win;
	}
	
	/**
	* Test methode playerWon()
	*/
	void testPlayerWon () {
		System.out.println ();
		final int[] winTab = {0, 0, 0};
		final int[] notWinTab = {0, 0, 1};

		System.out.println("\u001B[44m INFO \u001B[0m  test PlayerWon()");
		System.out.println("\u001B[36m========================\u001B[0m");
		testCasPlayerWon(notWinTab, false);
		testCasPlayerWon(winTab, true);
		System.out.println("\u001B[36m========================\u001B[0m");
		System.out.println("");
	}
	
	/**
	* Test methode playerWon()
	* @param n interger array with stick content per line
	* @param result expected result
	*/
	void testCasPlayerWon (int[] n, boolean result) {
		boolean resExec = playerWon(n);
		if (resExec == result){
			System.out.print ("\u001B[42m PASS \u001B[0m");
		} else {
			System.err.print ("\u001B[41m ERROR \u001B[0m");
		}
		// Affichage
		System.out.print ("  testPlayerWon (");
		displayTab(n);
		System.out.println(") = " + result);
	}
	
	
	
	/**
	* Start the game and manage who should play 
	* @param player1 is the name of the first player
	* @param player2 is the name of the second player
	* @param linesContent is a table with the stick number of all lines
	*/
	void launchGame(){
		clearDisplay();
		String player1 = SimpleInput.getString("Entrer le nom du premier joueur : ");
		clearDisplay();
		String player2 = SimpleInput.getString("Entrer le nom du second joueur : ");
		clearDisplay();
		int[] linesContent = initializeGameLines();
		
		int playerWhoPlay = 1; //1 -> First Player || 2 -> Second Player
		
		while(!playerWon(linesContent)){
			clearDisplay();
			if (playerWhoPlay == 1){
				displayLines(linesContent, player1);
				removeStick(linesContent, player1);
				playerWhoPlay = 2;
			}else{
				displayLines(linesContent, player2);
				removeStick(linesContent, player2);
				playerWhoPlay = 1;
			}
		}
		
		//A player won
		if(playerWhoPlay == 1){
			System.out.println(player2 + " a gagné. Bien joué à lui"); //player2 because the while loop switch playerWhoPlay before leave
		}else{
			System.out.println(player1 + " a gagné. Bien joué à lui"); //Same
		}
	}
	
	/**
	* Remove sticks in a line and verify if it's possible
	* @param linesContent is a table with the stick number of all lines
	*/
	void removeStick(int[] linesContent, String playerName){
		boolean removeDone = false;
		int line;
		int stickNumberToRemove;
		
		do{
			line = SimpleInput.getInt("Entrer la ligne à éditer : ");
			clearDisplay();
			displaySelectedLines(linesContent, playerName, line);
			if(line > linesContent.length - 1 || line < 0){
				System.out.println("\u001B[41m ERREUR : \u001B[0m numéro de ligne mauvais");
			}else{
				stickNumberToRemove = SimpleInput.getInt("Entrer le nombre de baton à retirer : ");
				clearDisplay();
				displayLines(linesContent, playerName);
				if(stickNumberToRemove > linesContent[line]){
					System.out.println("\u001B[41m ERREUR : \u001B[0m impossible de retirer " + stickNumberToRemove + " le nombre de baton à retirer est trop élevé pour cette ligne");
				}else if(stickNumberToRemove <= 0){
					System.out.println("\u001B[41m ERREUR : \u001B[0m impossible de retirer " + stickNumberToRemove + " le nombre de baton à retirer est trop faible pour cette ligne");
				}else{
					linesContent[line] -= stickNumberToRemove;
					removeDone = true; 
				}
			}
		}while(!removeDone);
	}
	
	/**
	* Show table content
	* @param t integer array
	**/
	void displayTab(int[] t){
		int i = 0;
		System.out.print("{");
		if (t.length != 0){
			while(i<t.length-1){
				System.out.print(t[i] + ",");
				i=i+1;
			}
			System.out.print(t[i]+"}");
		}else{
			System.out.print(" }");
		}
	}
}
