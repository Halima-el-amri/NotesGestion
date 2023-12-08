package Interfaces;

import Classes.Etudaint;

import java.util.ArrayList;

public interface Etudiant {
    ArrayList<Etudaint> Rechercher(String ref);
    void createEtudiant(Etudaint etudaint);
    void collectSubjectsAndGrades(Etudaint etudaint);
    Etudaint UpdateEtudaint(Etudaint etudaint, String ref);
    void delete(String ref);
    double calculateSubjectAverage(Etudaint etudaint, String matiere);
    double calculateOverallAverage(Etudaint etudaint);
    void DisplayEtudiant();
}
