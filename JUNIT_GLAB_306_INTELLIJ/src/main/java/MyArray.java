import java.util.ArrayList;
import java.util.Arrays;

public class MyArray {
    ArrayList<String> names = new ArrayList<String>(Arrays.asList(
            new String[]{"Mike", "Bairon", "Tony"}
    ));

    public void addEntryToArray(String entry){
        names.add(entry);
    }

    public String getNameAtIndex(int index){
        return names.get(index);
    }

    public void addName(String name){
        names.add(name);
    }

    public ArrayList<String> getNames(){
        return names;
    }
}
