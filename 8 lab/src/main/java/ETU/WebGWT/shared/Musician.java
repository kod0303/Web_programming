package ETU.WebGWT.shared;
import java.io.Serializable;

public class Musician implements Serializable{
	 private static final long serialVersionUID = 1L;
	    private String name;
	    private String instrument;
	    private int experience;
	 
	    public Musician(String name, String instrument, int experience) {
	        this.name = name;
	        this.instrument = instrument;
	        this.experience = experience;
	    }

	    public Musician(){}

	    public String getName() {
	        return name;
	    }

	    public String getInstrument() {
	        return instrument;
	    }

	    public int getExp() {
	        return experience;
	    }
}
