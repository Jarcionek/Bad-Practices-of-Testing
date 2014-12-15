package presentation._09_fake_not_meeting_the_contract_of_the_interface;

public class RefactoredFakeUuidProvider implements UuidProvider {

    private int counter = 0;

    @Override
    public String nextUuid() {
        return "" + counter++;
    }

}
