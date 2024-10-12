import java.util.Arrays;

/**
* Code who allow to play a famous game called Marienbad
* @author T.FALEZAN A.LETAN
*/

class MarienbadBot {

	void principal(){
		launchHome();
	}
	
	/**
	* Home screen with interaction
	*/
	void launchHome(){
		displayClear();
		displayHome();
		char selectedParam = SimpleInput.getChar("\u001B[34m[MENU PRINCIPAL]\u001B[0m Entrer votre sélection > ");
		
		while(selectedParam != 'a' && selectedParam != 'b'){
			displayClear();
			displayHome();
			System.out.println("\u001B[31m[ERREUR]\u001B[0m Sélection mauvaise");
			selectedParam = SimpleInput.getChar("\u001B[34m[MENU PRINCIPAL]\u001B[0m Entrer votre sélection > ");
		}

		if(selectedParam == 'a'){
			launchGame();
		}else{
			testPlayerWon();
			testGenerateLinesContent();
			SimpleInput.getString("\u001B[34m[MENU PRINCIPAL]\u001B[0m Appuyer sur ENTER pour relancer > ");
			launchHome();
		}
	}

	/**
	 * Start the game and manage who should play
	 */
	void launchGame(){
		int playerWhoPlay; //1 -> First Player || 2 -> bot
		int lineNumber;

		String player1 = SimpleInput.getString("\u001B[34m[PARAMETRAGE]\u001B[0m Entrer le nom joueur : ");
        playerWhoPlay = SimpleInput.getInt("\u001B[34m[PARAMETRAGE]\u001B[0m Choisisser le joueur qui commence ? (1 -> " + player1 + " | 2 -> Robot) : ");
		while (playerWhoPlay != 1 && playerWhoPlay != 2){
			System.out.println("\u001B[31m[ERREUR]\u001B[0m Sélection mauvaise");
			playerWhoPlay = SimpleInput.getInt("\u001B[34m[PARAMETRAGE]\u001B[0m Choisisser le joueur qui commence ? (1 -> " + player1 + " | 2 -> Robot) : ");

		}

		lineNumber = SimpleInput.getInt("\u001B[34m[PARAMETRAGE]\u001B[0m Entrer le nombre de ligne à utiliser pour jouer : ");
		while(lineNumber < 2 || lineNumber > 15){
			System.out.println("\u001B[31m[ERREUR]\u001B[0m nombre de ligne incorrect, le nombre de ligne doit être compris entre 2 et 15");
			lineNumber = SimpleInput.getInt("\u001B[34m[PARAMETRAGE]\u001B[0m Entrer le nombre de ligne à utiliser pour jouer : ");
		}
		int[] linesContent = generateLinesContent(lineNumber);

		while(!playerWon(linesContent)){
			displayClear();
			if (playerWhoPlay == 1){
				displayGame(linesContent, player1);
				playerRemoveStick(linesContent, player1);
				playerWhoPlay = 2;
			}else{
				botRemoveStick(linesContent);
				playerWhoPlay = 1;
			}
		}

		//A player won
		displayClear();
		System.out.println("\n\n");
		System.out.println("\t\t═════════════════════════════════════════════════════════════════════════════════════════");
		if(playerWhoPlay == 1){
			System.out.println("\t\t                                    Le robot a gagné !"); //player2 because the while loop switch playerWhoPlay before leave
		}else{
			System.out.println("\t\t                                " + player1 + " a gagné. Bien joué à toi"); //Same
		}
		System.out.println("\t\t═════════════════════════════════════════════════════════════════════════════════════════");

		char response = SimpleInput.getChar("\t\t\u001B[34m[MENU PRINCIPAL]\u001B[0m Entrer q pour quitter ou entrer n'importe quelle touche pour relancer > ");
		if (response != 'q') {
			launchHome();
		}else{
			System.out.println("\t\t\u001B[34m[MENU PRINCIPAL]\u001B[0m Merci d'avoir joué à Marienbad");
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
		System.out.println("\t\t\t\t\t\t\u001B[34mMade by : Augustin LETANG / Thibault FALEZAN\u001B[0m");
		System.out.println("\n\n");
		System.out.println("\t\t\t\t\t   a : \u001B[32mLancer le jeu\u001B[0m \t b : \u001B[32mLancer les tests unitaires\u001B[0m");
		System.out.println("\n\n");	
	}
	
	/**
	* Remove all text (clear console)
	*/
	void displayClear(){
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	/**
	 * Show sticks per lines
	 * @param linesContent is a table with the stick number of all lines
	 */
	void displayGame(int[] linesContent, String playerName){
		System.out.println("\n\n");
		System.out.println("\t\t═════════════════════════════════════════════════════════════════════════════════════════");
		System.out.println("\t\t                            Au tour de " + playerName + " de jouer");
		System.out.println("\t\t═════════════════════════════════════════════════════════════════════════════════════════");
		System.out.println("\t\t  ╔═══════════╦═══════════════════════════════════════════════════════════════╗  ");
		for (int i = 0; i < linesContent.length; i++){
			final int stickNumber = linesContent[i];
			if (i < 10){ //To avoid a gap due to number
				System.out.print("\t\t  ║ Ligne " + i + "   ║");
			}else{
				System.out.print("\t\t  ║ Ligne " + i + "  ║");
			}
			for(int j = 0; j < stickNumber; j++){
				System.out.print(" |");
			}
			System.out.println(createSpace(63 - stickNumber * 2) + "║");
		}
		System.out.println("\t\t  ╚═══════════╩═══════════════════════════════════════════════════════════════╝  ");
	}

	/**
	 * Show sticks per lines with the line selected highlighted
	 * @param linesContent is a table with the stick number of all lines
	 */
	void displayGameWithHighlight(int[] linesContent, String playerName, int selectedLine){
		System.out.println("\n\n");
		System.out.println("\t\t═════════════════════════════════════════════════════════════════════════════════════════");
		System.out.println("\t\t                            Au tour de " + playerName + " de jouer");
		System.out.println("\t\t═════════════════════════════════════════════════════════════════════════════════════════");
		System.out.println("\t\t  ╔═══════════╦═══════════════════════════════════════════════════════════════╗  ");
		for (int i = 0; i < linesContent.length; i++){
			final int stickNumber = linesContent[i];
			if (i < 10){ //To avoid a gap due to number
				System.out.print("\t\t  ║ Ligne " + i + "   ║");
			}else{
				System.out.print("\t\t  ║ Ligne " + i + "  ║");
			}
			if (i == selectedLine){
				System.out.print("\u001B[44m");
			}
			for(int j = 0; j < stickNumber; j++){
				System.out.print(" |");
			}
			if (i == selectedLine){
				System.out.print("\u001B[0m");
			}
			System.out.println(createSpace(63 - stickNumber * 2) + "║");
		}
		System.out.println("\t\t  ╚═══════════╩═══════════════════════════════════════════════════════════════╝  ");
	}
	
	/**
	* Create lines for the game
	* @return a table with the stick number per lines
	*/	
	int[] generateLinesContent(int lineNumber){
		int lineContent = 1;
		int[] linesContent = new int[lineNumber];
		for (int i = 0; i < lineNumber; i++){
			linesContent[i] = lineContent;
			lineContent += 2;
		}
				
		return linesContent;
	}
	
	/**
	* Test methode generateLinesContent()
	*/
	void testGenerateLinesContent () {
		int[] result1 = {1, 3, 5, 7, 9};
		int[] result2 = {1, 3, 5, 7, 9, 11};

		System.out.println ();
		System.out.println("\u001B[44m INFO \u001B[0m  test generateLinesContent()");
		System.out.println("\u001B[36m========================\u001B[0m");
		testCasGenerateLinesContent(5, result1);
		testCasGenerateLinesContent(6, result2);
		System.out.println("\u001B[36m========================\u001B[0m");
		System.out.println();
	}
	
	/**
	* Test methode generateLinesContent()
	* @param n the number of line we want
	* @param result expected result
	*/
	void testCasGenerateLinesContent (int n, int[] result) {
		int[] resExec = generateLinesContent(n);
		if (Arrays.equals(resExec, result)){ //Arrays.equals to avoid to add a loop who compare each char
			System.out.print ("\u001B[42m PASS \u001B[0m");
		} else {
			System.err.print ("\u001B[41m ERROR \u001B[0m");
		}
		System.out.print ("  testGenerateLinesContent (");
		System.out.print(") = ");
		displayTab(result);
		System.out.println();
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
		System.out.println();
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
	* Create an interface for player to remove sticks in a line and verify if it's possible
	* @param linesContent is a table with the stick number of all lines
	*/
	void playerRemoveStick(int[] linesContent, String playerName){
		boolean removeDone = false;
		int line;
		int stickNumberToRemove;
		
		do{
			System.out.println("\t\t═════════════════════════════════════════════════════════════════════════════════════════");
			line = SimpleInput.getInt("\t\t                         Entrer la ligne à éditer : ");
			displayClear();
			displayGameWithHighlight(linesContent, playerName, line);
			if(line > linesContent.length - 1 || line < 0){
				System.out.println("\t\t\u001B[31m  [ERREUR]\u001B[0m numéro de ligne mauvais");
			}else{
				System.out.println("\t\t═════════════════════════════════════════════════════════════════════════════════════════");
				stickNumberToRemove = SimpleInput.getInt("\t\t                  Entrer le nombre de baton à retirer : ");
				displayClear();
				displayGame(linesContent, playerName);
				if(stickNumberToRemove > linesContent[line]){
					System.out.println("\t\t\u001B[31m  [ERREUR]\u001B[0m impossible de retirer " + stickNumberToRemove + " le nombre de baton à retirer est trop élevé pour cette ligne");
				}else if(stickNumberToRemove <= 0){
					System.out.println("\t\t\u001B[31m  [ERREUR]\u001B[0m impossible de retirer " + stickNumberToRemove + " le nombre de baton à retirer est trop faible pour cette ligne");
				}else{
					linesContent[line] -= stickNumberToRemove;
					removeDone = true;
				}
			}
		}while(!removeDone);
	}

	/**
	 * Remove sticks in a line by a bot
	 * @param linesContent is a table with the stick number of all lines
	 */
	void botRemoveStick(int[] linesContent){
		int randomLine;
		do {
			randomLine = (int) (linesContent.length * Math.random());
		}while(linesContent[randomLine] <= 0);
		int randomStickNumber = (int) (linesContent[randomLine] * Math.random()) + 1;
		linesContent[randomLine] -= randomStickNumber;
	}

	/**
	 * Create a space string
	 * @param n number of space
	 * @return a string with n space
	 */
	String createSpace(int n){
		String space = "";
		for (int i = 0; i < n; i++){
			space += " ";
		}
		return space;
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
