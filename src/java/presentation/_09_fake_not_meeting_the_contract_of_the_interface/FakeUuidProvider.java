package presentation._09_fake_not_meeting_the_contract_of_the_interface;

public class FakeUuidProvider implements UuidProvider {

    @Override
    public String next() {
        return "12345";
    }

}
