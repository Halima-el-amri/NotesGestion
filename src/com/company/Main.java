package com.company;

import Classes.Etudaint;
import Services.EtudiantManger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;


public class Main {
    private static EtudiantManger etudiantManger = new EtudiantManger();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        manage:
        while (true) {
            System.out.println("#######################");
            System.out.println("Options:");
            System.out.println("1. Add a Student *");
            System.out.println("2. Update a Student *");
            System.out.println("3. Display the Student list *");
            System.out.println("4. Delete a Student *");
            System.out.println("5. Search for a Student *");
            System.out.println("6. Exit #");
            System.out.println("#######################");

            int option = input.nextInt();
            Etudaint etudaint;

            switch (option) {
                case 1:
                    etudaint = collectEtudiantInformation();
                    etudiantManger.createEtudiant(etudaint);
                    break;
                case 2:
                    System.out.println("Type ref");
                    String ref1 = input.next();
                    etudaint = collectEtudiantInformation();
                    Etudaint etudaint1 = etudiantManger.UpdateEtudaint(etudaint, ref1);
                    if (etudaint1 == null) {
                        System.out.println("");
                    }
                    break;
                case 3:
                    etudiantManger.DisplayEtudiant();
                    break;
                case 4:
                    System.out.println("Type ref");
                    String reef = input.next();
                    etudiantManger.delete(reef);
                    break;
                case 5:
                    System.out.println("Type la reference");
                    String ref = input.next();
                    ArrayList<Etudaint> list = etudiantManger.Rechercher(ref);
                    if (list.isEmpty()) {
                        System.out.println("Aucun étudiant trouvé.");
                    } else {
                        list.forEach(c -> System.out.println(c.toString()));
                    }
                    break;
                case 6:
                    break manage;
            }
        }
        input.close(); // Close the Scanner
    }
    public static Etudaint collectEtudiantInformation() {
        System.out.println("Please enter ref: ");
        String ref = input.next();
        System.out.println("Please enter name: ");
        String name = input.next();
        Map<String, ArrayList<Double>> matiers = new HashMap<>();
        boolean addMoreSubjects = true;

        while (addMoreSubjects) {
            System.out.println("Please enter matiere: ");
            String matiere = input.next();
            ArrayList<Double> notes = new ArrayList<>();
            boolean addMoreNotes = true;

            while (addMoreNotes) {
                System.out.println("Please enter note: ");
                Double note = input.nextDouble();
                notes.add(note);

                System.out.println("Do you want to add another note for this subject? (yes/no): ");
                String response = input.next();
                addMoreNotes = response.equalsIgnoreCase("yes");
            }

            matiers.put(matiere, notes);

            // Consume the newline character
            input.nextLine();

            System.out.println("Do you want to add more subjects? (yes/no): ");
            String response = input.nextLine();  // Use nextLine() to consume the newline character
            addMoreSubjects = response.equalsIgnoreCase("yes");
        }

        return new Etudaint(ref, name, matiers);
    }

}
