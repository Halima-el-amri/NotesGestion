package Services;

import Classes.Etudaint;
import Interfaces.Etudiant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EtudiantManger implements Etudiant {
    private static ArrayList<Etudaint> etudaints = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public ArrayList<Etudaint> Rechercher(String ref) {
        ArrayList<Etudaint> list = new ArrayList<>();
        for (Etudaint etudaint : etudaints) {
            if (etudaint.getRef().equals(ref)) {
                list.add(etudaint);
            }
        }
        return list;
    }



    @Override
public void collectSubjectsAndGrades(Etudaint etudaint) {
        boolean addMoreMatieres = true;

        while (addMoreMatieres) {
            System.out.println("Nom de la matière : ");
            String matiere = scanner.next();

            // Collect grades for the same subject with input validation
            ArrayList<Double> notes = collectGrades();

            // Add subject and grades to the student's matiers map
            etudaint.getMatiers().put(matiere, notes);

            // Ask if the user wants to add more subjects for the same student
            System.out.println("Voulez-vous ajouter une autre matière pour cet étudiant ? (oui/non) : ");
            String response = scanner.next();
            addMoreMatieres = response.equalsIgnoreCase("oui");
        }
    }

    private ArrayList<Double> collectGrades() {
        ArrayList<Double> notes = new ArrayList<>();
        boolean addMoreNotes = true;

        while (addMoreNotes) {
            System.out.println("Note de la matière : ");

            // Validate the input for grades
            while (!scanner.hasNextDouble()) {
                System.out.println("Veuillez entrer une note valide (chiffre) : ");
                scanner.next(); // Consume the invalid input
            }

            Double note = scanner.nextDouble();
            notes.add(note);

            // Ask if the user wants to add more grades
            System.out.println("Voulez-vous ajouter une autre note pour cette matière ? (oui/non) : ");

            String response = scanner.next();
            addMoreNotes = response.equalsIgnoreCase("oui");
        }

        return notes;
    }


    @Override
    public Etudaint UpdateEtudaint(Etudaint etudaint, String ref) {
        for (int i = 0; i < etudaints.size(); i++) {
            if (ref.equals(etudaints.get(i).getRef())) {
                return etudaints.set(i, etudaint);
            }
        }
        return null;
    }

    @Override
    public void delete(String ref) {
        for (Etudaint etudaint : etudaints) {
            if (etudaint.getRef().equals(ref)) {
                etudaints.remove(etudaint);
                System.out.println("Suppression effectuée.");
                return;
            }
        }
        System.out.println("Aucun étudiant avec cette référence.");
    }
    @Override
    public double calculateSubjectAverage(Etudaint etudaint, String matiere) {
        ArrayList<Double> notes = etudaint.getMatiers().get(matiere);

        if (notes != null && !notes.isEmpty()) {
            double sum = 0;
            for (Double note : notes) {
                sum += note;
            }
            return sum / notes.size();
        } else {
            return 0; // Return 0 if there are no grades for the subject
        }
    }
    @Override
    public double calculateOverallAverage(Etudaint etudaint) {
        double sum = 0;
        int count = 0;

        for (Map.Entry<String, ArrayList<Double>> entry : etudaint.getMatiers().entrySet()) {
            double subjectAverage = calculateSubjectAverage(etudaint, entry.getKey());
            sum += subjectAverage;
            count++;
        }

        if (count > 0) {
            return sum / count;
        } else {
            return 0; // Return 0 if there are no subjects with grades
        }
    }




    @Override
    public void createEtudiant(Etudaint etudaint) {
        if (etudaints.contains(etudaint)) {
            System.out.println("L'étudiant est déjà dans votre base de données.");
        } else {
            etudaints.add(etudaint);
            System.out.println("L'étudiant a été ajouté avec succès !");
        }
    }

    @Override
    public void DisplayEtudiant() {
        for (Etudaint etudaint : etudaints) {
            System.out.println("Les infos de l'étudiant " + etudaint.getRef() + "\t" + etudaint.getName());

            for (Map.Entry<String, ArrayList<Double>> entry : etudaint.getMatiers().entrySet()) {
                String matiere = entry.getKey();
                ArrayList<Double> notes = entry.getValue();

                System.out.print(matiere + ": ");

                // Print each grade in the ArrayList
                for (Double note : notes) {
                    System.out.print(note + " ");
                }

                double subjectAverage = calculateSubjectAverage(etudaint, matiere);
                System.out.println("\tMoyenne: " + subjectAverage);
            }

            double overallAverage = calculateOverallAverage(etudaint);
            System.out.println("Moyenne générale: " + overallAverage);

            System.out.println(); // Add a blank line between students
        }
    }


}
