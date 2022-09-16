/*Name: Bryan Le
 * Date: 9/7/2022
 * Description: This is a game containing methods for connect 4, connect 5, and connect 3. 
 * NOTE: I profusely apologize for how long this program is. I am learning on the go about java
 *  and this project will definitely be my worst in terms of organization. I hope you will go easy on me. 
 *  I could not find a style guide, nor do I believe the professor highlighted one aside from what was
 *  lightly touched upon in the PA. For my connect 3 and connect 5 methods, I copy pasted what I had for connect 4.
 *  So it is just a lot of copy paste with slight revisions in the numbers in terms of what will be counted as a win. 
 *  Once again, I am sorry. It will not happen again. 
 */

import java.util.Scanner;
public class ConnectFour {
	Scanner scanner_input = new Scanner(System.in);
	public static void main(String[] args){ 
		new ConnectFour();
	}
	public ConnectFour() {main();
	}
	
	public void main() 
	{
		/*
		 * By having the game replayability as a Boolean value it made it easier for my methods to return one to end it. 
		 */
		Boolean game_replay = true;

		while (game_replay) 
		{
			System.out.println("What connect game would you like to play? (3/4/5)");
			int connect_game_choice = scanner_input.nextInt();
			if (connect_game_choice == 4)
			{
				game_replay = connect_4(board_setup(), game_replay, column_counter());
			}
			else if (connect_game_choice == 5)
			{
				game_replay = connect_5(board_setup(),game_replay, column_counter());
			}
			else if (connect_game_choice == 3)
			{
				game_replay = connect_3(board_setup(),game_replay,column_counter());
			}	
		}
	}
	
	public boolean connect_4(String[][] board_setup,Boolean game_replay, int[] column_counter)
	{
		System.out.println("Enter player 1: ");
		String player_one = scanner_input.next();
		System.out.println("Enter player 2: ");
		String player_two = scanner_input.next();

		
		for (int i = 0; i < 6; i++)
		{
			for (int x = 0; x < 7; x++)
				{
				System.out.print("|" + board_setup[i][x]);
				}
			System.out.print("|");
			System.out.println("");
		}
		/*
		 * The board has been created in a separate method, this is the actual printing of the board. 
		 */
		
		System.out.println("---------------");
	
		Boolean player_turn = true;
		
		int total_rows = 42;
		/*
		 * Since the board will always be 6x7, if a player ends up exhausting all the slots without winning
		 * once this integer variable reaches 0 it will be a tie. 
		 */
		int red_int_turn = -1;
		int yellow_int_turn = -1;

		while (player_turn == true)
		{
			System.out.print("Red to play. Pick a column (1-7): ");
			red_int_turn = scanner_input.nextInt();
			if (total_rows == 0) 
			{
				System.out.println("NO ONE WINS (DRAW)");
				break;
			}
			if (red_int_turn > 7 || red_int_turn == 0 || column_counter[red_int_turn] == -1)
			{
				System.out.print("Not a valid move choose another spot, Red (1-7): ");
				red_int_turn = scanner_input.nextInt();
				/*
				 * This is the case for when the player makes an invalid move. (Repeated for yellow side also)
				 */
			}
			if (red_int_turn <= 7 && red_int_turn != 0)
				{
					board_setup[column_counter[red_int_turn]][red_int_turn - 1] = "R";
					column_counter[red_int_turn] -= 1;
					total_rows -= 1;
					for (int i = 0; i < 6; i++)
					{
						for (int x = 0; x < 7; x++)
							{
							System.out.print("|" + board_setup[i][x]);
							}
						System.out.print("|");
						System.out.println("");
					}
					System.out.println("---------------");
					// This is the horizontal check for red. 
					for (int i = 0; i < 6; i++) 
					{
						for (int x = 0; x < 7 - 3; x++) 
						{
						if (board_setup[i][x] != " ")
							{
								if (board_setup[i][x] == board_setup[i][x+1] && board_setup[i][x+1] == board_setup[i][x+2] && board_setup[i][x+2] == board_setup[i][x+3])
								{
										System.out.println("RED IS VICTORIOUS");
										System.out.print("Would you like to play again? (Y/N) ");
										String reply = scanner_input.next();
										if (reply.equals("Y") || reply.equals("y"))
										{
											return true;
										}
										else if (reply.equals("N") || reply.equals("n"))
										{
											return false;
										}
								}
							}
						}
					}
					// This is the vertical check for Red.
					for (int i = 0; i < 7; i++) 
					{
						for (int x = 0; x < 6-3; x++) 
						{
							if (board_setup[x][i] != " ")
							{
								if (board_setup[x][i] == board_setup[x+1][i] && board_setup[x+1][i] == board_setup[x+2][i] && board_setup[x+2][i] == board_setup[x+3][i])
								{
									System.out.println("RED IS VICTORIOUS");
									System.out.println("Would you like to play again? (Y/N) ");
									String reply = scanner_input.next();
									if (reply.equals("Y") || reply.equals("y"))
									{
										return true;
									}
									else if (reply.equals("N") || reply.equals("n"))
									{
										return false;
									}
								}
							}
						}
					}
					// This is the diagonal check for Red from the right.
					for (int i = 0; i < 6-3; i++) 
					{
						for (int x = 0; x < 7-3; x++) 
						{
							if (board_setup[i][x] != " ")
							{
								if (board_setup[i][x] == board_setup[i+1][x+1] && board_setup[i+1][x+1] == board_setup[i+2][x+2] && board_setup[i+2][x+2] == board_setup[i+3][x+3])
								{
									System.out.println("RED IS VICTORIOUS");
									System.out.print("Would you like to play again? (Y/N) ");
									String reply = scanner_input.next();
									if (reply.equals("Y") || reply.equals("y")) 
									{
										return true;
									}
									else if (reply.equals("N") || reply.equals("n"))
									{
										return false;
									}
								}
							}
						}
					}
					// This is the diagonal check from the left for red.
					for (int i = 0; i < 6; i++) 
					{
						for (int x = 0; x < 7-3; x++) 
						{
							if (board_setup[i][x] != " ")
							{
								if (board_setup[i][x] == board_setup[i-1][x+1] && board_setup[i-1][x+1] == board_setup[i-2][x+2] && board_setup[i-2][x+2] == board_setup[i-3][x+3])
								{
									System.out.println("RED IS VICTORIOUS");
									System.out.print("Would you like to play again? (Y/N) ");
									String reply = scanner_input.next();
									if (reply.equals("Y") || reply.equals("y")) 
									{
										return true;
									}
									else if (reply.equals("N") || reply.equals("n"))
									{
										return false;
									}
								}
							}
						}
					}
				}
			
			player_turn = false;
			/*
			 * Every time player turn is false, it will be yellow's turn to move. Everything that was featured in red will
			 * be rehashed for yellow, with the strings being altered to fit the turn. 
			 */
			
			while (player_turn == false)
			{
				System.out.print("Yellow to play. Pick a column (1-7): ");
				yellow_int_turn = scanner_input.nextInt();
				if (total_rows == 0) 
				{
					System.out.println("NO ONE WINS (DRAW)");
					break;
				}
				
				if (yellow_int_turn > 7 || yellow_int_turn == 0 || column_counter[yellow_int_turn] == -1)
				{
					System.out.print("Not a valid move choose another spot, Yellow (1-7): ");
					yellow_int_turn = scanner_input.nextInt();
				}
				
				if (yellow_int_turn <= 7 && yellow_int_turn != 0)
				{
					board_setup[column_counter[yellow_int_turn]][yellow_int_turn - 1] = "Y";
					column_counter[yellow_int_turn] -= 1;
					total_rows -= 1;
							
				for (int i = 0; i < 6; i++)
				{
					for (int x = 0; x < 7; x++)
						{
						System.out.print("|" + board_setup[i][x]);
						}
					System.out.print("|");
					System.out.println("");
				}
					System.out.println("---------------");
					// This is the horizontal check for yellow spots.
					for (int i = 0; i < 6; i++) 
					{
						for (int x = 0; x < 7 - 3; x++) 
						{
						if (board_setup[i][x] != " ")
							{
								if (board_setup[i][x] == board_setup[i][x+1] && board_setup[i][x+1] == board_setup[i][x+2] && board_setup[i][x+2] == board_setup[i][x+3])
								{
									System.out.println("YELLOW IS VICTORIOUS");
									System.out.print("Would you like to play again? (Y/N) ");
									String reply = scanner_input.next();
									if (reply.equals("Y") || reply.equals("y")) 
									{
										return true;
									}
									else if (reply.equals("N") || reply.equals("n"))
									{
										return false;
									}
								}
							}
							
						}
					}
					// This is the vertical check for yellow. 
					for (int i = 0; i < 7; i++) 
					{
						for (int x = 0; x < 6-3; x++) 
						{
							if (board_setup[x][i] != " ")
							{
								if (board_setup[x][i] == board_setup[x+1][i] && board_setup[x+1][i] == board_setup[x+2][i] && board_setup[x+2][i] == board_setup[x+3][i])
								{
									System.out.println("YELLOW IS VICTORIOUS");
									System.out.print("Would you like to play again? (Y/N) ");
									String reply = scanner_input.next();
									if (reply.equals("Y") || reply.equals("y")) 
									{
										return true;
									}
									else if (reply.equals("N") || reply.equals("n"))
									{
										return false;
									}
								}
							}
						}
						
					}
					// This is the diagonal from the right for yellow. 
					for (int i = 0; i < 6-3; i++) 
					{
						for (int x = 0; x < 7-3; x++) 
						{
							if (board_setup[i][x] != " ")
							{
								if (board_setup[i][x] == board_setup[i+1][x+1] && board_setup[i+1][x+1] == board_setup[i+2][x+2] && board_setup[i+2][x+2] == board_setup[i+3][x+3])
								{
									System.out.println("YELLOW IS VICTORIOUS");
									System.out.print("Would you like to play again? (Y/N) ");
									String reply = scanner_input.next();
									if (reply.equals("Y") || reply.equals("y")) 
									{
										return true;
									}
									else if (reply.equals("N") || reply.equals("n"))
									{
										return false;
									}
								}
							}
						}
					}
					// This is the diagonal check from the left for yellow.
					for (int i = 0; i < 6; i++) 
					{
						for (int x = 0; x < 7-3; x++) 
						{
							if (board_setup[i][x] != " ")
							{
								if (board_setup[i][x] == board_setup[i-1][x+1] && board_setup[i-1][x+1] == board_setup[i-2][x+2] && board_setup[i-2][x+2] == board_setup[i-3][x+3])
								{
									System.out.println("YELLOW IS VICTORIOUS");
									System.out.print("Would you like to play again? (Y/N) ");
									String reply = scanner_input.next();
									if (reply.equals("Y") || reply.equals("y")) 
									{
										return true;
									}
									else if (reply.equals("N") || reply.equals("n"))
									{
										return false;
									}
								}
							}
						}
					}	
				}
				player_turn = true;
			}
		}
		return true;
	}
	
	public boolean connect_3(String[][] board_setup,Boolean game_replay, int[] column_counter) 
	/*
	 * This method is basically a repeat of connect 4, but with the numbers slightly altered to 
	 * work for connect 3. 
	 */
	{
		System.out.println("Enter player 1: ");
		String player_one = scanner_input.next();
		System.out.println("Enter player 2: ");
		String player_two = scanner_input.next();
		
		for (int i = 0; i < 6; i++)
		{
			for (int x = 0; x < 7; x++)
				{
				System.out.print("|" + board_setup[i][x]);
				}
			System.out.print("|");
			System.out.println("");
		}
		
		System.out.println("---------------");
	
		Boolean player_turn = true;
		
		int total_rows = 42;
		int red_int_turn = -1;
		int yellow_int_turn = -1;

		while (player_turn == true)
		{
			System.out.print("Red to play. Pick a column (1-7): ");
			red_int_turn = scanner_input.nextInt();
			if (total_rows == 0) 
			{
				System.out.println("NO ONE WINS (DRAW)");
				break;
			}
			if (red_int_turn > 7 || red_int_turn == 0 || column_counter[red_int_turn] == -1)
			{
				System.out.print("Not a valid move choose another spot, Red (1-7): ");
				red_int_turn = scanner_input.nextInt();
			}
			if (red_int_turn <= 7 && red_int_turn != 0)
				{
					board_setup[column_counter[red_int_turn]][red_int_turn - 1] = "R";
					column_counter[red_int_turn] -= 1;
					total_rows -= 1;
					for (int i = 0; i < 6; i++)
					{
						for (int x = 0; x < 7; x++)
							{
							System.out.print("|" + board_setup[i][x]);
							}
						System.out.print("|");
						System.out.println("");
					}
					System.out.println("---------------");
					// This is the horizontal check for red. 
					for (int i = 0; i < 6; i++) 
					{
						for (int x = 0; x < 7 - 2; x++) 
						{
						if (board_setup[i][x] != " ")
							{
								if (board_setup[i][x] == board_setup[i][x+1] && board_setup[i][x+1] == board_setup[i][x+2])
								{
										System.out.println("RED IS VICTORIOUS");
										System.out.print("Would you like to play again? (Y/N) ");
										String reply = scanner_input.next();
										if (reply.equals("Y") || reply.equals("y"))
										{
											return true;
										}
										else if (reply.equals("N") || reply.equals("n"))
										{
											return false;
										}
								}
							}
						}
					}
					// This is the vertical check for Red.
					for (int i = 0; i < 7; i++) 
					{
						for (int x = 0; x < 6-2; x++) 
						{
							if (board_setup[x][i] != " ")
							{
								if (board_setup[x][i] == board_setup[x+1][i] && board_setup[x+1][i] == board_setup[x+2][i])
								{
									System.out.println("RED IS VICTORIOUS");
									System.out.println("Would you like to play again? (Y/N) ");
									String reply = scanner_input.next();
									if (reply.equals("Y") || reply.equals("y"))
									{
										return true;
									}
									else if (reply.equals("N") || reply.equals("n"))
									{
										return false;
									}
								}
							}
						}
					}
					// This is the diagonal check for Red from the right.
					for (int i = 0; i < 6-2; i++) 
					{
						for (int x = 0; x < 7-2; x++) 
						{
							if (board_setup[i][x] != " ")
							{
								if (board_setup[i][x] == board_setup[i+1][x+1] && board_setup[i+1][x+1] == board_setup[i+2][x+2])
								{
									System.out.println("RED IS VICTORIOUS");
									System.out.print("Would you like to play again? (Y/N) ");
									String reply = scanner_input.next();
									if (reply.equals("Y") || reply.equals("y")) 
									{
										return true;
									}
									else if (reply.equals("N") || reply.equals("n"))
									{
										return false;
									}
								}
							}
						}
					}
					// This is the diagonal check from the left for red.
					for (int i = 0; i < 6; i++) 
					{
						for (int x = 0; x < 7-2; x++) 
						{
							if (board_setup[i][x] != " ")
							{
								if (board_setup[i][x] == board_setup[i-1][x+1] && board_setup[i-1][x+1] == board_setup[i-2][x+2])
								{
									System.out.println("RED IS VICTORIOUS");
									System.out.print("Would you like to play again? (Y/N) ");
									String reply = scanner_input.next();
									if (reply.equals("Y") || reply.equals("y")) 
									{
										return true;
									}
									else if (reply.equals("N") || reply.equals("n"))
									{
										return false;
									}
								}
							}
						}
					}
				}
			
			player_turn = false;
			
			while (player_turn == false)
			{
				System.out.print("Yellow to play. Pick a column (1-7): ");
				yellow_int_turn = scanner_input.nextInt();
				if (total_rows == 0) 
				{
					System.out.println("NO ONE WINS (DRAW)");
					break;
				}
				
				if (yellow_int_turn > 7 || yellow_int_turn == 0 || column_counter[yellow_int_turn] == -1)
				{
					System.out.print("Not a valid move choose another spot, Yellow (1-7): ");
					yellow_int_turn = scanner_input.nextInt();
				}
				
				if (yellow_int_turn <= 7 && yellow_int_turn != 0)
				{
					board_setup[column_counter[yellow_int_turn]][yellow_int_turn - 1] = "Y";
					column_counter[yellow_int_turn] -= 1;
					total_rows -= 1;
							
				for (int i = 0; i < 6; i++)
				{
					for (int x = 0; x < 7; x++)
						{
						System.out.print("|" + board_setup[i][x]);
						}
					System.out.print("|");
					System.out.println("");
				}
					System.out.println("---------------");
					// This is the horizontal check for yellow spots.
					for (int i = 0; i < 6; i++) 
					{
						for (int x = 0; x < 7 - 2; x++) 
						{
						if (board_setup[i][x] != " ")
							{
								if (board_setup[i][x] == board_setup[i][x+1] && board_setup[i][x+1] == board_setup[i][x+2])
								{
									System.out.println("YELLOW IS VICTORIOUS");
									System.out.print("Would you like to play again? (Y/N) ");
									String reply = scanner_input.next();
									if (reply.equals("Y") || reply.equals("y")) 
									{
										return true;
									}
									else if (reply.equals("N") || reply.equals("n"))
									{
										return false;
									}
								}
							}
							
						}
					}
					// This is the vertical check for yellow. 
					for (int i = 0; i < 7; i++) 
					{
						for (int x = 0; x < 6-2; x++) 
						{
							if (board_setup[x][i] != " ")
							{
								if (board_setup[x][i] == board_setup[x+1][i] && board_setup[x+1][i] == board_setup[x+2][i])
								{
									System.out.println("YELLOW IS VICTORIOUS");
									System.out.print("Would you like to play again? (Y/N) ");
									String reply = scanner_input.next();
									if (reply.equals("Y") || reply.equals("y")) 
									{
										return true;
									}
									else if (reply.equals("N") || reply.equals("n"))
									{
										return false;
									}
								}
							}
						}
						
					}
					// This is the diagonal from the right for yellow. 
					for (int i = 0; i < 6-2; i++) 
					{
						for (int x = 0; x < 7-2; x++) 
						{
							if (board_setup[i][x] != " ")
							{
								if (board_setup[i][x] == board_setup[i+1][x+1] && board_setup[i+1][x+1] == board_setup[i+2][x+2])
								{
									System.out.println("YELLOW IS VICTORIOUS");
									System.out.print("Would you like to play again? (Y/N) ");
									String reply = scanner_input.next();
									if (reply.equals("Y") || reply.equals("y")) 
									{
										return true;
									}
									else if (reply.equals("N") || reply.equals("n"))
									{
										return false;
									}
								}
							}
						}
					}
					// This is the diagonal check from the left for yellow.
					for (int i = 0; i < 6; i++) 
					{
						for (int x = 0; x < 7-2; x++) 
						{
							if (board_setup[i][x] != " ")
							{
								if (board_setup[i][x] == board_setup[i-1][x+1] && board_setup[i-1][x+1] == board_setup[i-2][x+2])
								{
									System.out.println("YELLOW IS VICTORIOUS");
									System.out.print("Would you like to play again? (Y/N) ");
									String reply = scanner_input.next();
									if (reply.equals("Y") || reply.equals("y")) 
									{
										return true;
									}
									else if (reply.equals("N") || reply.equals("n"))
									{
										return false;
									}
								}
							}
						}
					}	
				}
				player_turn = true;
			}
		}
		return true;
	}
	public boolean connect_5(String[][] board_setup,Boolean game_replay, int[] column_counter)
	/*
	 * This code for connect 5 is a rehash of the previous 2 methods, the difference being the numbers being slightly altered
	 * to match for connect 5. 
	 */
	{
		System.out.println("Enter player 1: ");
		String player_one = scanner_input.next();
		System.out.println("Enter player 2: ");
		String player_two = scanner_input.next();
		
		for (int i = 0; i < 6; i++)
		{
			for (int x = 0; x < 7; x++)
				{
				System.out.print("|" + board_setup[i][x]);
				}
			System.out.print("|");
			System.out.println("");
		}
		
		System.out.println("---------------");
	
		Boolean player_turn = true;
		
		int total_rows = 42;
		int red_int_turn = -1;
		int yellow_int_turn = -1;

		while (player_turn == true)
		{
			System.out.print("Red to play. Pick a column (1-7): ");
			red_int_turn = scanner_input.nextInt();
			if (total_rows == 0) 
			{
				System.out.println("NO ONE WINS (DRAW)");
				break;
			}
			if (red_int_turn > 7 || red_int_turn == 0 || column_counter[red_int_turn] == -1)
			{
				System.out.print("Not a valid move choose another spot, Red (1-7): ");
				red_int_turn = scanner_input.nextInt();
			}
			if (red_int_turn <= 7 && red_int_turn != 0)
				{
					board_setup[column_counter[red_int_turn]][red_int_turn - 1] = "R";
					column_counter[red_int_turn] -= 1;
					total_rows -= 1;
					for (int i = 0; i < 6; i++)
					{
						for (int x = 0; x < 7; x++)
							{
							System.out.print("|" + board_setup[i][x]);
							}
						System.out.print("|");
						System.out.println("");
					}
					System.out.println("---------------");
					// This is the horizontal check for red. 
					for (int i = 0; i < 6; i++) 
					{
						for (int x = 0; x < 7 - 4; x++) 
						{
						if (board_setup[i][x] != " ")
							{
								if (board_setup[i][x] == board_setup[i][x+1] && board_setup[i][x+1] == board_setup[i][x+2] && board_setup[i][x+2] == board_setup[i][x+3] && board_setup[i][x+3] == board_setup[i][x+4])
								{
										System.out.println("RED IS VICTORIOUS");
										System.out.print("Would you like to play again? (Y/N) ");
										String reply = scanner_input.next();
										if (reply.equals("Y") || reply.equals("y"))
										{
											return true;
										}
										else if (reply.equals("N") || reply.equals("n"))
										{
											return false;
										}
								}
							}
						}
					}
					// This is the vertical check for Red.
					for (int i = 0; i < 7; i++) 
					{
						for (int x = 0; x < 6-4; x++) 
						{
							if (board_setup[x][i] != " ")
							{
								if (board_setup[x][i] == board_setup[x+1][i] && board_setup[x+1][i] == board_setup[x+2][i] && board_setup[x+2][i] == board_setup[x+3][i] && board_setup[x+3][i]== board_setup[x+4][i])
								{
									System.out.println("RED IS VICTORIOUS");
									System.out.println("Would you like to play again? (Y/N) ");
									String reply = scanner_input.next();
									if (reply.equals("Y") || reply.equals("y"))
									{
										return true;
									}
									else if (reply.equals("N") || reply.equals("n"))
									{
										return false;
									}
								}
							}
						}
					}
					// This is the diagonal check for Red from the right.
					for (int i = 0; i < 6-4; i++) 
					{
						for (int x = 0; x < 7-4; x++) 
						{
							if (board_setup[i][x] != " ")
							{
								if (board_setup[i][x] == board_setup[i+1][x+1] && board_setup[i+1][x+1] == board_setup[i+2][x+2] && board_setup[i+2][x+2] == board_setup[i+3][x+3] && board_setup[i+3][x+3] == board_setup[i+4][x+4])
								{
									System.out.println("RED IS VICTORIOUS");
									System.out.print("Would you like to play again? (Y/N) ");
									String reply = scanner_input.next();
									if (reply.equals("Y") || reply.equals("y")) 
									{
										return true;
									}
									else if (reply.equals("N") || reply.equals("n"))
									{
										return false;
									}
								}
							}
						}
					}
					// This is the diagonal check from the left for red.
					for (int i = 0; i < 6; i++) 
					{
						for (int x = 0; x < 7-4; x++) 
						{
							if (board_setup[i][x] != " ")
							{
								if (board_setup[i][x] == board_setup[i-1][x+1] && board_setup[i-1][x+1] == board_setup[i-2][x+2] && board_setup[i-2][x+2] == board_setup[i-3][x+3] && board_setup[i-3][x+3] == board_setup[i-4][x+4])
								{
									System.out.println("RED IS VICTORIOUS");
									System.out.print("Would you like to play again? (Y/N) ");
									String reply = scanner_input.next();
									if (reply.equals("Y") || reply.equals("y")) 
									{
										return true;
									}
									else if (reply.equals("N") || reply.equals("n"))
									{
										return false;
									}
								}
							}
						}
					}
				}
			
			player_turn = false;
			
			while (player_turn == false)
			{
				System.out.print("Yellow to play. Pick a column (1-7): ");
				yellow_int_turn = scanner_input.nextInt();
				if (total_rows == 0) 
				{
					System.out.println("NO ONE WINS (DRAW)");
					break;
				}
				
				if (yellow_int_turn > 7 || yellow_int_turn == 0 || column_counter[yellow_int_turn] == -1)
				{
					System.out.print("Not a valid move choose another spot, Yellow (1-7): ");
					yellow_int_turn = scanner_input.nextInt();
				}
				
				if (yellow_int_turn <= 7 && yellow_int_turn != 0)
				{
					board_setup[column_counter[yellow_int_turn]][yellow_int_turn - 1] = "Y";
					column_counter[yellow_int_turn] -= 1;
					total_rows -= 1;
							
				for (int i = 0; i < 6; i++)
				{
					for (int x = 0; x < 7; x++)
						{
						System.out.print("|" + board_setup[i][x]);
						}
					System.out.print("|");
					System.out.println("");
				}
					System.out.println("---------------");
					// This is the horizontal check for yellow spots.
					for (int i = 0; i < 6; i++) 
					{
						for (int x = 0; x < 7 - 4; x++) 
						{
						if (board_setup[i][x] != " ")
							{
								if (board_setup[i][x] == board_setup[i][x+1] && board_setup[i][x+1] == board_setup[i][x+2] && board_setup[i][x+2] == board_setup[i][x+3] && board_setup[i][x+3] == board_setup[i][x+4])
								{
									System.out.println("YELLOW IS VICTORIOUS");
									System.out.print("Would you like to play again? (Y/N) ");
									String reply = scanner_input.next();
									if (reply.equals("Y") || reply.equals("y")) 
									{
										return true;
									}
									else if (reply.equals("N") || reply.equals("n"))
									{
										return false;
									}
								}
							}
							
						}
					}
					// This is the vertical check for yellow. 
					for (int i = 0; i < 7; i++) 
					{
						for (int x = 0; x < 6-4; x++) 
						{
							if (board_setup[x][i] != " ")
							{
								if (board_setup[x][i] == board_setup[x+1][i] && board_setup[x+1][i] == board_setup[x+2][i] && board_setup[x+2][i] == board_setup[x+3][i] && board_setup[x+3][i] == board_setup[x+4][i])
								{
									System.out.println("YELLOW IS VICTORIOUS");
									System.out.print("Would you like to play again? (Y/N) ");
									String reply = scanner_input.next();
									if (reply.equals("Y") || reply.equals("y")) 
									{
										return true;
									}
									else if (reply.equals("N") || reply.equals("n"))
									{
										return false;
									}
								}
							}
						}
						
					}
					// This is the diagonal from the right for yellow. 
					for (int i = 0; i < 6-4; i++) 
					{
						for (int x = 0; x < 7-4; x++) 
						{
							if (board_setup[i][x] != " ")
							{
								if (board_setup[i][x] == board_setup[i+1][x+1] && board_setup[i+1][x+1] == board_setup[i+2][x+2] && board_setup[i+2][x+2] == board_setup[i+3][x+3] && board_setup[i+3][x+3] == board_setup[i+4][x+4])
								{
									System.out.println("YELLOW IS VICTORIOUS");
									System.out.print("Would you like to play again? (Y/N) ");
									String reply = scanner_input.next();
									if (reply.equals("Y") || reply.equals("y")) 
									{
										return true;
									}
									else if (reply.equals("N") || reply.equals("n"))
									{
										return false;
									}
								}
							}
						}
					}
					// This is the diagonal check from the left for yellow.
					for (int i = 0; i < 6; i++) 
					{
						for (int x = 0; x < 7-4; x++) 
						{
							if (board_setup[i][x] != " ")
							{
								if (board_setup[i][x] == board_setup[i-1][x+1] && board_setup[i-1][x+1] == board_setup[i-2][x+2] && board_setup[i-2][x+2] == board_setup[i-3][x+3] && board_setup[i-3][x+3] == board_setup[i-4][x+4])
								{
									System.out.println("YELLOW IS VICTORIOUS");
									System.out.print("Would you like to play again? (Y/N) ");
									String reply = scanner_input.next();
									if (reply.equals("Y") || reply.equals("y")) 
									{
										return true;
									}
									else if (reply.equals("N") || reply.equals("n"))
									{
										return false;
									}
								}
							}
						}
					}	
				}
				player_turn = true;
			}
		}
		return true;
	}
	
			
	public String[][] board_setup() 
	/*
	 * This is the method for how the two dimensional board is created. 
	 */
	{
		String[][] connect_board;
		connect_board = new String [6][7];
		for (int i = 0; i < 6 ; i++) 
		{
			for (int x = 0; x <7; x++)
			{
				connect_board[i][x] = " ";
			}
		}
		return connect_board;
	}
	
	public int[] column_counter()
	/*
	 * This is the method for accounting for how much is in a column already.
	 * Allowing for red or yellow to stack on top of each other if the desired
	 * spot is taken. 
	 */
	{
		int[] array_num;
		array_num = new int[8];
		for (int i = 0; i < 8; i++) 
		{	
			array_num[i] = 5;
		}
		return array_num;	
	}
}