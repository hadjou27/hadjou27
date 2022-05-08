package sacados;

import java.util.Scanner;

public class Main {

	private static void sacADosResolution() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		String chemin;
		float poids;
		int methode;

		System.out.println("Probl�me du sac � dos");
		do {
			System.out.println("\n Saisissez le chemin d'acc�s au fichier texte : ");
			chemin = in.nextLine();
		} while (!chemin.contains(".txt"));
		do {
			System.out.println("\n Saisissez le poids maximal du sac : ");
			poids = in.nextFloat();
		} while (poids <= 0);

		SacADos sac = new SacADos(chemin, poids);
		do {
			System.out.println("\n Options :");
			int i = 1;
			for (Methode m : sac.getMethodesDeResolution()) {
				System.out.println("- " + m.toString() + ", saisissez " + (i++));
			}
			
		} while (methode < 1 || methode > sac.getNbMethodes());

		long tempsExecution = sac.resoudre(sac.getMethodesDeResolution().get(methode-1));
		
		System.out.println(sac.toString());
		System.out.println("La r�solution s'est ex�cut�e en " + tempsExecution + "ms");
	}

	public static void main(String[] args) {
		
		sacADosResolution();

	}

}
