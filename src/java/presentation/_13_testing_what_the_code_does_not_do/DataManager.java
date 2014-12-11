package presentation._13_testing_what_the_code_does_not_do;

public class DataManager {

    private final DataSorter dataSorter;

    public DataManager(DataSorter dataSorter) {
        this.dataSorter = dataSorter;
    }

    public void retrieveData() {
        dataSorter.sort(null);
    }

}
