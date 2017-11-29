package gamelogic;

import java.util.ArrayList;

public class SchiffMap {

	// Map ist 8x8 Felder groß
	// int[] num = new int[5];
	private int[][] feld = new int[12][12];
	private int schiffCounter = 0;
	private int versenktCounter = 0;

	public SchiffMap() {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				this.feld[i][j] = 0;
			}
		}
	}

	public boolean setzeSchiffNeu(int x, int y, boolean horizontal, int groesse) {
		// wenn horizontal true, dann ist schiff horzontal

		int rand = 12 - groesse;
		if (horizontal) {
			if (x < (rand) && y < 11) {
				switch (groesse) {
				case 1:
					if (this.pruefeAngrenzende(x, y)) {
						this.feld[x][y] = 1;
						System.out.print("1er ");
					} else {
						System.out.print("Kein ");
					}
					break;
				case 2:
					if (this.pruefeAngrenzende(x, y) && this.pruefeAngrenzende(x + 1, y)) {
						this.feld[x][y] = 1;
						this.feld[x + 1][y] = 1;
						System.out.print("2er ");
					} else {
						System.out.print("Kein ");
					}
					break;
				case 3:
					if (this.pruefeAngrenzende(x, y) && this.pruefeAngrenzende(x + 1, y)
							&& this.pruefeAngrenzende(x + 2, y)) {
						this.feld[x][y] = 1;
						this.feld[x + 1][y] = 1;
						this.feld[x + 2][y] = 1;
						System.out.print("3er ");
					} else {
						System.out.print("Kein ");
					}
					break;
				case 4:
					if (this.pruefeAngrenzende(x, y) && this.pruefeAngrenzende(x + 1, y)
							&& this.pruefeAngrenzende(x + 2, y) && this.pruefeAngrenzende(x + 3, y)) {
						this.feld[x][y] = 1;
						this.feld[x + 1][y] = 1;
						this.feld[x + 2][y] = 1;
						this.feld[x + 3][y] = 1;
						System.out.print("4er ");
					} else {
						System.out.print("Kein ");
					}
					break;
				case 5:
					if (this.pruefeAngrenzende(x, y)) {
						this.feld[x][y] = 5;
						System.out.print("Eisberg ");
					} else {
						System.out.print("Kein ");
					}
					break;
				default:
					break;
				}
				System.out.println("Schiff erstellt");
				this.schiffCounter++;
				return true;
			}
		} else {
			if (y < (rand) && x < 11) {
				switch (groesse) {
				case 1:
					if (this.pruefeAngrenzende(x, y)) {
						this.feld[x][y] = 1;
						System.out.print("1er ");
					} else {
						System.out.print("Kein ");
					}
					break;
				case 2:
					if (this.pruefeAngrenzende(x, y) && this.pruefeAngrenzende(x, y + 1)) {
						this.feld[x][y] = 1;
						this.feld[x][y + 1] = 1;
						System.out.print("2er ");
					} else {
						System.out.print("Kein ");
					}
					break;
				case 3:
					if (this.pruefeAngrenzende(x, y) && this.pruefeAngrenzende(x, y + 1)
							&& this.pruefeAngrenzende(x, y + 2)) {
						this.feld[x][y] = 1;
						this.feld[x][y + 1] = 1;
						this.feld[x][y + 2] = 1;
						System.out.print("3er ");
					} else {
						System.out.print("Kein ");
					}
					break;
				case 4:
					if (this.pruefeAngrenzende(x, y) && this.pruefeAngrenzende(x, y + 1)
							&& this.pruefeAngrenzende(x, y + 2) && this.pruefeAngrenzende(x, y + 3)) {
						this.feld[x][y] = 1;
						this.feld[x][y + 1] = 1;
						this.feld[x][y + 2] = 1;
						this.feld[x][y + 3] = 1;
						System.out.print("4er ");
					} else {
						System.out.print("Kein ");
					}
					break;
				case 5:
					if (this.pruefeAngrenzende(x, y)) {
						this.feld[x][y] = 5;
						System.out.print("Eisberg ");
					} else {
						System.out.print("Kein ");
					}
					break;
				default:
					break;
				}
				System.out.println("Schiff erstellt");
				this.schiffCounter++;
				return true;
			}
		}
		return false;
	}

	public int schuss(int y, int x) {
		// return 0: nichts getroffen, return 1: treffer, return 2: treffer versenkt,
		// return 3: alle versenkt
		if (feld[x][y] == 1) {
			feld[x][y] = -1;

			if (pruefeAngrenzende(x, y)) {
				this.versenktCounter++;
				if (this.versenktCounter == this.schiffCounter) {
					this.showMap();
					System.out.println("Treffer versenkt! Alle Schiffe versenkt!");
					return 3;
				}
				this.showMap();
				System.out.println("Treffer versenkt!");
				return 2;
			}
			this.showMap();
			System.out.println("Treffer! Noch nicht versenkt!");
			return 1;
		}
		this.feld[x][y] = -2;
		this.showMap();
		System.out.println("Kein Treffer!");
		return 0;
	}

	public boolean pruefeAngrenzende(int x, int y) {
		if (this.feld[x - 1][y] < 1 && this.feld[x + 1][y] < 1 && this.feld[x][y - 1] < 1 && this.feld[x][y + 1] < 1
				&& this.feld[x + 1][y + 1] < 1 && this.feld[x + 1][y - 1] < 1 && this.feld[x - 1][y - 1] < 1
				&& this.feld[x - 1][y + 1] < 1) {
			System.out.println("---Kein Schiff grenzt an");
			return true;
		}
		System.out.println("---Schiff grenzt an");
		return false;
	}

	public boolean pruefeVersenkt(int x, int y) {

		int i = 0;
		int newX = 0;
		int newY = 0;
		boolean flagHeilesSchiffGefunden = false;
		if (this.feld[x - 1][y] == -1 || this.feld[x + 1][y] == -1) {
			while (!flagHeilesSchiffGefunden) {
				if (this.feld[x + i][y] == 1) {

				}
			}
		}

		if (this.feld[x][y + 1] == -1 || this.feld[x][y - 1] == -1) {

		}

		// ----
		if (this.feld[x - 1][y] == 1) {
			return true;
		}
		if (this.feld[x + 1][y] == 1) {
			return true;
		}
		if (this.feld[x][y + 1] == 1) {
			return true;
		}
		if (this.feld[x][y - 1] == 1) {
			return true;
		}

		return false;
	}

	public boolean pruefeAngrenzendeAlt(int x, int y) {

		if (x > 0 && y > 0) {
			if (this.feld[x - 1][y] < 1 && this.feld[x + 1][y] < 1 && this.feld[x][y - 1] < 1 && this.feld[x][y + 1] < 1
					&& this.feld[x + 1][y + 1] < 1 && this.feld[x + 1][y - 1] < 1 && this.feld[x - 1][y - 1] < 1
					&& this.feld[x - 1][y + 1] < 1) {
				System.out.println("---Kein Schiff grenzt an");
				return true;
			}
		} else if (x == 0 && y == 0) {
			if (this.feld[x + 1][y] < 1 && this.feld[x + 1][y + 1] < 1 && this.feld[x][y + 1] < 1) {
				System.out.println("---Kein Schiff grenzt an");
				return true;
			}
		} else if (x == 0) {
			if (this.feld[x + 1][y] < 1 && this.feld[x][y - 1] < 1 && this.feld[x][y + 1] < 1
					&& this.feld[x + 1][y + 1] < 1 && this.feld[x + 1][y - 1] < 1) {
				System.out.println("---Kein Schiff grenzt an");
				return true;
			}
		} else if (y == 0) {
			if (this.feld[x + 1][y] < 1 && this.feld[x - 1][y] < 1 && this.feld[x][y + 1] < 1
					&& this.feld[x + 1][y + 1] < 1 && this.feld[x - 1][y + 1] < 1) {
				System.out.println("---Kein Schiff grenzt an");
				return true;
			}
		} else {
			System.out.println("---Schiff grenzt an");
			return false;
		}
		System.out.println("---Schiff grenzt an");
		return false;
	}

	public void showMap() {
		System.out.println("\t1 2 3 4 5 6 7 8 9 10\n");
		for (int i = 1; i < 11; i++) {
			System.out.print(i + "\t");
			for (int j = 1; j < 11; j++) {
				if (this.feld[j][i] == 1) {
					System.out.print("s ");
				} else if (this.feld[j][i] == 0) {
					System.out.print("- ");
				} else if (this.feld[j][i] == -1) {
					System.out.print("x ");
				} else if (this.feld[j][i] == -2) {
					System.out.print("o ");
				}
			}
			System.out.println("");

		}
	}

}

/*
 * 
 * 
 * public boolean setzeSchiff(int y, int x, boolean horizontal, boolean groesse)
 * { //wenn horizontal true, dann ist schiff horzontal int rand; if(groesse)
 * {rand = 6;} else {rand = 5;}
 * 
 * if(horizontal) { if(this.pruefeAngrenzende(x,y) &&
 * this.pruefeAngrenzende(x+1,y)) { if( !groesse || (groesse &&
 * this.pruefeAngrenzende(x+2,y)) ) { if(x < (rand) && y < 8 ) { this.feld[x][y]
 * = 1; this.feld[x+1][y] = 1; if(groesse) { this.feld[x+2][y] = 1;
 * System.out.print("Grosses "); } this.schiffCounter++;
 * System.out.println("Schiff erstellt"); return true; } } } }
 * 
 * 
 * 
 * else { if(x < 8 && y < (rand) ) { if(this.pruefeAngrenzende(x,y) &&
 * this.pruefeAngrenzende(x,y+1)) { if( !groesse || (groesse &&
 * this.pruefeAngrenzende(x,y+2)) ) {
 * 
 * this.feld[x][y] = 1; this.feld[x][y+1] = 1; if(groesse) { this.feld[x][y+2] =
 * 1; System.out.print("Grosses "); } this.schiffCounter++;
 * System.out.println("Schiff erstellt"); return true; } } } }
 * System.out.println("Kein Schiff erstellt"); return false; }
 * 
 * 
 * 
 */
