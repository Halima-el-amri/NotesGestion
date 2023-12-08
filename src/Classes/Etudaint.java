package Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Etudaint {
    private String ref;
    private String name;
    private HashMap<String, ArrayList<Double>> matiers;

    public Etudaint(String ref, String name, Map<String, ArrayList<Double>> matiers) {
        this.ref = ref;
        this.name = name;

        if (matiers != null) {
            this.matiers = new HashMap<>(matiers);
        } else {
            this.matiers = new HashMap<>();
        }
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, ArrayList<Double>> getMatiers() {
        return matiers;
    }

    public void setMatiers(HashMap<String, ArrayList<Double>> matiers) {
        this.matiers = matiers;
    }


}
